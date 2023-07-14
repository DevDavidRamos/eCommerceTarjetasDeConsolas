package com.darvdev.ecommerce.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.darvdev.ecommerce.data.local.entity.RecentSearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentSearchDao {

    @Query("SELECT * FROM recentsearchentity")
    fun getAllRecentSearch(): Flow<List<RecentSearchEntity>>

    @Insert(onConflict = REPLACE)
    fun insertRecentSearch(recentSearchEntity: RecentSearchEntity)
}