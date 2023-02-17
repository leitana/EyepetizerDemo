package com.example.eye_daily.model

import com.lx.common.model.Issue


data class DailyModel(
    val dialog: Any,
    val issueList: List<Issue>,
    val newestIssueType: String,
    val nextPageUrl: String,
    val nextPublishTime: Long
)

