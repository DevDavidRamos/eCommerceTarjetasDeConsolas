package com.darvdev.ecommerce.domain.repository

import com.darvdev.ecommerce.domain.model.RecentSearch
import kotlinx.coroutines.flow.Flow



interface RecentSearchRepository {

    suspend fun getAllRecentSearch(): Flow<List<RecentSearch>>

    suspend fun insertRecentSearch(recentSearch: RecentSearch)
}