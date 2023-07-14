package com.darvdev.ecommerce.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.darvdev.ecommerce.data.local.entity.ProductEntity
@Dao
interface ProductsDao {

    @Query("SELECT * FROM productentity")
    fun getAllProducts(): List<ProductEntity>

    @Insert(onConflict = REPLACE)
    fun insertAllProducts(list: List<ProductEntity>)

}