package com.darvdev.ecommerce.domain.usecase

import com.darvdev.ecommerce.domain.model.Product
import com.darvdev.ecommerce.domain.repository.FavoritesRepository
import com.darvdev.ecommerce.domain.repository.ProductRepository
import com.darvdev.ecommerce.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val favoritesRepository: FavoritesRepository
) {

    suspend operator fun invoke(): Resource<List<Product>> {

        return withContext(Dispatchers.IO) {
            val networkCall = productRepository.fetchAllProducts()

            if (networkCall is Resource.Error) {
                Resource.Error(networkCall.error)
            }

            networkCall as Resource.Success
            productRepository.insertAllProductsCache(networkCall.data, favoritesRepository.getFavoriteIds())
            val productList = productRepository.getAllProductsCache()
            Resource.Success(productList)
        }
    }
}