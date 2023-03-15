package com.example.eye_player.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.transition.Transition
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import cn.jzvd.Jzvd
import cn.jzvd.JzvdStd
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.BarUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.eye_player.adapter.TransitionListenerAdapter
import com.example.eye_player.adapter.VideoRelateAdapter
import com.example.eye_player.jvzd.JZVDObserver
import com.example.eye_player.jvzd.JzvdStdRv
import com.example.eye_player.jvzd.ViewAttr
import com.example.eye_player.jvzd.ViewMoveHelper
import com.example.eye_player.viewmodel.VideoPlayerViewModel
import com.lx.common.Constants
import com.lx.common.ext.fromJson
import com.lx.common.ext.toJson
import com.lx.common.model.Data
import com.lx.common.model.Item
import com.lx.common.mvvm.activity.BaseBindVMActivity
import com.lx.common.router.RouterPath
import com.lx.eye_player.R
import com.lx.eye_player.databinding.PlayerActivityVideoBinding
import com.lx.lib_base.ext.immersionStatusBar
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint


/**
 * @title：VideoPlayerActivity
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/02/20
 */
@AndroidEntryPoint
@Route(path = RouterPath.Video.PATH_PLAYER_Activity)
class VideoPlayerActivity: BaseBindVMActivity<VideoPlayerViewModel, PlayerActivityVideoBinding>() {

    private val DURATION: Long = 250

    private var mTransition: Transition? = null

    private lateinit var videoModel: Data

    @JvmField
    @Autowired(name = Constants.VIDEO_MODE_KEY)
    var videoModelJSON: String = ""

    @JvmField
    @Autowired(name = Constants.VIDEO_IS_FROM_RELATE_KEY)
    var fromRelate: Boolean = false

    private lateinit var mCurrentAttr: ViewAttr

    @Autowired(name = Constants.VIDEO_IS_FROM_PLAYLIST_KEY)
    lateinit var viewAttr: ViewAttr

    private fun isInitViewAttr() = ::viewAttr.isInitialized

    private val mAdapter: VideoRelateAdapter by lazy { VideoRelateAdapter() }

    override val getLayoutRes: Int
        get() = R.layout.player_activity_video

    override fun initView() {
        immersionStatusBar(true, android.R.color.white, true, 0.5f)
        mBinding.run {
            mRecyclerView.layoutManager = LinearLayoutManager(this@VideoPlayerActivity)
            mRecyclerView.isNestedScrollingEnabled = false
            mRecyclerView.adapter = mAdapter
            smartRefresh.setEnableLoadMore(false)
            smartRefresh.setOnRefreshListener {
                getRelateVideoList()
            }
        }
        mAdapter.setOnItemClickListener(object : BaseQuickAdapter.OnItemClickListener<Item> {
            override fun onClick(adapter: BaseQuickAdapter<Item, *>, view: View, position: Int) {
                if (adapter.getItem(position)?.itemType == VideoRelateAdapter.TYPE_VIDEO) {
                    ARouter.getInstance().build(RouterPath.Video.PATH_PLAYER_Activity)
                        .also { postcard ->
                            view?.let { shareView ->
                                postcard.withOptionsCompat(
                                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                                        this@VideoPlayerActivity,
                                        shareView,
                                        Constants.SHARED_ELEMENT_NAME
                                    )
                                )
                            }
                        }
                        .withString(Constants.VIDEO_MODE_KEY, toJson(adapter.getItem(position)!!.data))
                        .withBoolean(Constants.VIDEO_IS_FROM_RELATE_KEY, false)
                        .navigation(this@VideoPlayerActivity)
                }
            }
        })
    }

    override fun initData() {
        ARouter.getInstance().inject(this)
        videoModel = fromJson(videoModelJSON)
        mBinding.videoModel = videoModel
        mViewModel.videoModelList.add(videoModel)

        if (isInitViewAttr()){ //从播放列表进入
            addVideoViewFromList()
        } else {
            addNormalVideoView()
            lifecycle.addObserver(JZVDObserver())
            if (fromRelate) {//从相关视频Item项点击进入
                mBinding.smartRefresh.autoRefresh()
//                initTransition()
            } else {
                initTransition()
            }
        }
    }

    override fun startObserve() {
    }

    override fun showLoading() {
//        mBinding.smartRefresh.autoRefresh()
    }

    override fun hideLoading() {
        mBinding.smartRefresh.finishRefresh()
    }

    override fun handlerError() {
        mBinding.smartRefresh.finishRefresh()
    }

    private fun addNormalVideoView(){
        mBinding.mSurfaceContainer.viewTreeObserver
            .addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener{
                override fun onPreDraw(): Boolean {
                    mBinding.mSurfaceContainer.viewTreeObserver.removeOnPreDrawListener(this)
                    val jzvdStd = JzvdStd(this@VideoPlayerActivity).apply {
                        setUp(videoModel.playUrl, videoModel.title)
                        startVideo()
                    }
                    mBinding.mSurfaceContainer.addView(
                        jzvdStd, FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    )
                    return true
                }
            })
    }

    private fun addVideoViewFromList(){
        mBinding.mSurfaceContainer.viewTreeObserver
            .addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener{
                override fun onPreDraw(): Boolean {
                    mBinding.mSurfaceContainer.viewTreeObserver.removeOnPreDrawListener(this)
                    //将Jzvd从列表中移除再添加到播放详情渲染控件中，实现无缝续播功能
                    val parent: ViewParent = JzvdStdRv.CURRENT_JZVD.parent
                    (parent as ViewGroup).removeView(JzvdStdRv.CURRENT_JZVD)
                    mBinding.mSurfaceContainer.addView(
                        JzvdStdRv.CURRENT_JZVD, FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    )
                    //获取视频详情页面视频渲染控件的坐标以及宽高
                    mCurrentAttr = ViewAttr()
                    val location = IntArray(2)
                    mBinding.mSurfaceContainer.getLocationInWindow(location)
                    mCurrentAttr.x = location[0]
                    mCurrentAttr.y = location[1] - BarUtils.getStatusBarHeight()
                    mCurrentAttr.width = mBinding.mSurfaceContainer.measuredWidth
                    mCurrentAttr.height = mBinding.mSurfaceContainer.measuredHeight
                    //开启平移动画实现将列表播放控件平移到详情渲染控件的过渡效果
                    ViewMoveHelper(mBinding.mSurfaceContainer, viewAttr, mCurrentAttr, DURATION).startAnim()
                        .addListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                super.onAnimationEnd(animation)
                                mBinding.smartRefresh.autoRefresh()
                            }
                        })

                    return true
                }

            })
    }

    private fun getRelateVideoList(){
//        toastInfo(mViewModel.test().toString())
        mViewModel.getRelateVideoList(videoModel.id).observe(this) {
            mAdapter.submitList(it)
        }

//        lifecycleScope.launch {
//            mViewModel.test(videoModel.id)
//        }
    }

    private fun initTransition() {
        //因为进入视频详情页面后还需请求数据，所以在过渡动画完成后在请求数据
        //延迟动画执行
        postponeEnterTransition()
        //设置共用元素对应的View
        ViewCompat.setTransitionName(mBinding.mSurfaceContainer, Constants.SHARED_ELEMENT_NAME)
        //获取共享元素进入转场对象
        mTransition = window.sharedElementEnterTransition
        //设置共享元素动画执行完成的回调事件
        mTransition?.addListener(object : TransitionListenerAdapter() {
            override fun onTransitionEnd(transition: Transition?) {
//                getRelateVideoList()
                mBinding.smartRefresh.autoRefresh()
                //移除共享元素动画监听事件
                mTransition?.removeListener(this)
            }
        })
        //开始动画执行
        startPostponedEnterTransition()
    }

    private fun backAnimation() {
        ViewMoveHelper(mBinding.mSurfaceContainer, mCurrentAttr, viewAttr, DURATION).startAnim()
        mBinding.mVideoBackground.isVisible = false
        mBinding.smartRefresh.finishRefresh()
        mBinding.mSurfaceContainer.postDelayed({
//            LiveDataBus.with<VideoAutoPlayEvent>(BaseConstant.VIDEO_AUTO_PLAY_EVENT)
//                .setData(VideoAutoPlayEvent())
            finish()
            overridePendingTransition(0, 0)
        }, DURATION)
    }

    override fun onBackPressed() {
        if (Jzvd.backPress()) {
            return
        }
        if (isInitViewAttr()) {
            backAnimation()
        } else {
            finishAfterTransition()
        }
    }
}