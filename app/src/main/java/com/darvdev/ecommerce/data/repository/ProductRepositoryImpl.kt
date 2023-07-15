package com.darvdev.ecommerce.data.repository

import com.darvdev.ecommerce.data.local.dao.ProductsDao
import com.darvdev.ecommerce.data.local.entity.mapToProductList
import com.darvdev.ecommerce.data.remote.FirestoreConstants.PRODUCTS_COLLECTION
import com.darvdev.ecommerce.domain.model.Product
import com.darvdev.ecommerce.domain.model.mapToProductEntityList
import com.darvdev.ecommerce.domain.repository.ProductRepository
import com.darvdev.ecommerce.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productsDao: ProductsDao,
    private val firestoreInstance: FirebaseFirestore
): ProductRepository {

    override suspend fun fetchAllProducts(): Resource<List<Product>> {
        return try {
            val productList = firestoreInstance.collection(PRODUCTS_COLLECTION)
                .get()
                .await()
                .toObjects(Product::class.java)

            Resource.Success(productList)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun getAllProductsCache(): List<Product> {
        return withContext(Dispatchers.IO) {
            productsDao.getAllProducts().mapToProductList()
        }
    }

    override suspend fun insertAllProductsCache(products: List<Product>, favorites: List<String>) {
        productsDao.insertAllProducts(products.mapToProductEntityList(favorites))
    }

    override suspend fun updateAllProductsCache(products: List<Product>, favorites: List<String>) {
        withContext(Dispatchers.IO) {
            productsDao.updateAllProducts(products.mapToProductEntityList(favorites))
        }
    }

}