package com.google.samples.apps.nowinandroid.core.network.model

data class NetworkRepository(val id: Long, val full_name: String, val description: String?, val languages_url: String,
                      val stargazers_url: String, val branches_url : String, val contributors_url: String)
