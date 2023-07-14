package com.darvdev.ecommerce.di

import com.darvdev.ecommerce.data.repository.ProductRepositoryImpl
import com.darvdev.ecommerce.data.repository.RecentSearchRepositoryImpl
import com.darvdev.ecommerce.domain.repository.ProductRepository
import com.darvdev.ecommerce.domain.repository.RecentSearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindProductsRepository(productsRepository: ProductRepositoryImpl): ProductRepository

    @Binds
    abstract fun bindRecentSearchRepository(recentSearchRepository: RecentSearchRepositoryImpl): RecentSearchRepository
}