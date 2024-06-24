
package com.groupec.cleanarchitecturesampleapp.core.data.di

import com.groupec.cleanarchitecturesampleapp.core.data.repository.ISampleRepository
import com.google.samples.apps.nowinandroid.core.data.repository.SampleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule  {
    @Singleton
    @Binds
    abstract fun bindApplicationsRepository(impl: SampleRepository): ISampleRepository
}