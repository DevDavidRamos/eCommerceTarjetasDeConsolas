package com.darvdev.ecommerce.data.repository

import com.darvdev.ecommerce.data.local.dao.RecentSearchDao
import com.darvdev.ecommerce.data.local.entity.mapToRecentSearchList
import com.darvdev.ecommerce.domain.model.RecentSearch
import com.darvdev.ecommerce.domain.model.mapToRecentSearchEntity
import com.darvdev.ecommerce.domain.repository.RecentSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecentSearchRepositoryImpl @Inject constructor(
    private val recentSearchDao: RecentSearchDao
): RecentSearchRepository {

    override suspend fun getAllRecentSearch(): Flow<List<RecentSearch>> {
        return recentSearchDao.getAllRecentSearch().map { it.mapToRecentSearchList() }
    }

    override suspend fun insertRecentSearch(recentSearch: RecentSearch) {
        recentSearchDao.insertRecentSearch(recentSearch.mapToRecentSearchEntity())
    }

}