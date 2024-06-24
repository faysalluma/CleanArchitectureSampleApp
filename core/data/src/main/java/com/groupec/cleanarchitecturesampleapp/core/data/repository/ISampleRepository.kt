package com.groupec.cleanarchitecturesampleapp.core.data.repository

import com.google.samples.apps.nowinandroid.core.data.model.Repository
import kotlinx.coroutines.flow.Flow

interface ISampleRepository {
    fun getAllRepositories(): Flow<List<Repository>>
    suspend fun getRepositoryBranches()
    suspend fun getRepositoryContributors()
    suspend fun saveFullName(reponame: String, username: String)
}