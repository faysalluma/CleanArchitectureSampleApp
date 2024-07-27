
package com.groupec.cleanarchitecturesampleapp.core.data.di

import com.groupec.cleanarchitecturesampleapp.core.data.repository.OrderRepository
import com.groupec.cleanarchitecturesampleapp.core.data.repository.OrderRepositoryImpl
import com.groupec.cleanarchitecturesampleapp.core.data.repository.ProductRepository
import com.groupec.cleanarchitecturesampleapp.core.data.repository.ProductRepositoryImpl
import com.groupec.cleanarchitecturesampleapp.core.network.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule  {
    @Provides
    @Singleton
    fun providerRepository(
        apiService: ApiService
    ) : OrderRepository {
        return OrderRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun providerProduct(
        apiService: ApiService
    ) : ProductRepository {
        return ProductRepositoryImpl(apiService)
    }
}