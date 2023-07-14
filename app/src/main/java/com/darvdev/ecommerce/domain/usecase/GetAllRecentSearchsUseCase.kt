package com.darvdev.ecommerce.domain.usecase

import com.darvdev.ecommerce.domain.model.RecentSearch
import com.darvdev.ecommerce.domain.repository.RecentSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllRecentSearchsUseCase @Inject constructor(
    private val repository: RecentSearchRepository
) {

    suspend operator fun invoke(): Flow<List<RecentSearch>> {

        return withContext(Dispatchers.IO) {
            repository.getAllRecentSearch()
        }

    }
}