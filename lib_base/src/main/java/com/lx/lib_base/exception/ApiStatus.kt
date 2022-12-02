package com.lx.lib_base.exception

/**
 * @title：ApiStatus
 * @projectName Szt
 * @description: <Description>
 * @author linxiao
 * @data Created in 2021/05/06
 */
object ApiStatus {
    /**
     * 响应成功
     */
    const val SUCCESS = 0

    /**
     * 失败
     */
    const val ERROR = 10001

    /**
     * 权限不足
     */
    const val ACCESS_ERROR = 10002

    /**
     * 权限不足
     */
    const val USER_NO_LOGIN = 10003

    /**
     * 服务器内部错误
     */
    const val SERVER_ERROR = 99999

    /**
     * 用户名或密码错误
     */
    const val USER_FAIL = 20001

    /**
     * Token 不合法
     */
    const val TOKEN_GENERATOR_ERROR = 401

    /**
     * sql非法
     */
    const val SQL_ILLEGAL = 20009

    /**
     * 无数据返回
     */
    const val NO_RECORDS = 30001

    /**
     * 不能为空
     */
    const val NULL_ERR = 30002

    /**
     * 数据已经存在
     */
    const val DUPLICATE_ERR = 30003

    /**
     * 添加失败
     */
    const val ADD_FAIL = 30004

    /**
     * 修改失败
     */
    const val MODIFY_FAIL = 30005

    /**
     * 修改失败
     */
    const val REMOVE_FAIL = 30006

    /**
     * 修改失败
     */
    const val FUNCTION_ERR = 30006

    /**
     * 未知错误
     */
    const val UNKNOWN_ERROR = 1002

    /**
     * 网络连接超时
     */
    const val NETWORK_ERROR = 1004
//
//    /**
//     * API解析异常（或者第三方数据结构更改）等其他异常
//     */
//    const val API_ERROR = 1005
}