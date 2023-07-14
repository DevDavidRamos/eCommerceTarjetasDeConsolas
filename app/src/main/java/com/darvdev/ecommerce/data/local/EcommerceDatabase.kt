package com.darvdev.ecommerce.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.darvdev.ecommerce.data.local.dao.ProductsDao
import com.darvdev.ecommerce.data.local.dao.RecentSearchDao
import com.darvdev.ecommerce.data.local.entity.ProductEntity
import com.darvdev.ecommerce.data.local.entity.RecentSearchEntity


@Database(entities = [ProductEntity::class, RecentSearchEntity::class], version = 1)
abstract class EcommerceDatabase: RoomDatabase() {

    abstract fun productsDao(): ProductsDao
    abstract fun recentSearchDao(): RecentSearchDao
}