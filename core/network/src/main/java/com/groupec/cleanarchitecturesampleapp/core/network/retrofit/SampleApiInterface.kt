package com.google.samples.apps.nowinandroid.core.network.retrofit


import com.google.samples.apps.nowinandroid.core.network.model.NetworkBranch
import com.google.samples.apps.nowinandroid.core.network.model.NetworkContributor
import com.google.samples.apps.nowinandroid.core.network.model.NetworkRepository
import retrofit2.Call
import retrofit2.http.*

interface SampleApiInterface {
    @GET("/repositories")
    fun getAllRepositories(): Call<List<NetworkRepository>>

    @GET("/repos/{reponame}/{username}/branches")
    fun getRepositoryBranches(@Path("reponame") reponame: String, @Path("username") username: String): Call<List<NetworkBranch>>

    @GET("/repos/{reponame}/{username}/contributors")
    fun getRepositoryContributors(@Path("reponame") reponame: String, @Path("username") username: String): Call<List<NetworkContributor>>
}