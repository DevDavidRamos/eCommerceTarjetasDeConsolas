package com.darvdev.ecommerce.domain.usecase

import com.darvdev.ecommerce.domain.model.Product
import com.darvdev.ecommerce.domain.repository.ProductRepository
import com.darvdev.ecommerce.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(): Resource<List<Product>> {

        return withContext(Dispatchers.IO) {
            val networkCall = repository.fetchAllProducts()

            if (networkCall is Resource.Error) {
                Resource.Error(networkCall.error)
            }

            networkCall as Resource.Success
            repository.insertAllProductsCache(networkCall.data)
            val productList = repository.getAllProductsCache()
            Resource.Success(productList)
        }
    }
}