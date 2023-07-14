package com.darvdev.ecommerce.domain.repository

import com.darvdev.ecommerce.domain.model.Product
import com.darvdev.ecommerce.utils.Resource


interface ProductRepository {

    suspend fun fetchAllProducts(): Resource<List<Product>>

    suspend fun getAllProductsCache(): List<Product>

    suspend fun insertAllProductsCache(products: List<Product>)
}