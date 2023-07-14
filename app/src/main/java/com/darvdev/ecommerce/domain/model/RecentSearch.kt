package com.darvdev.ecommerce.domain.model

import androidx.room.PrimaryKey
import com.darvdev.ecommerce.data.local.entity.RecentSearchEntity

data class RecentSearch(
    val query: String
)

fun RecentSearch.mapToRecentSearchEntity(): RecentSearchEntity {
    return RecentSearchEntity(query = query)
}

fun List<RecentSearch>.mapToRecentSearchListEntity(): List<RecentSearchEntity> {
    return this.map { it.mapToRecentSearchEntity() }
}
