package com.darvdev.ecommerce.domain.usecase

import com.darvdev.ecommerce.domain.model.Product
import com.darvdev.ecommerce.domain.repository.FavoritesRepository
import com.darvdev.ecommerce.domain.repository.ProductRepository
import com.darvdev.ecommerce.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetProductsByNameQueryUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val favoritesRepository: FavoritesRepository
) {

    suspend operator fun invoke(query: String): Flow<Resource<List<Product>>> = channelFlow {

        val networkResult = productRepository.fetchAllProducts()

        if (networkResult is Resource.Error) {
            send(Resource.Error(networkResult.error))
        }

        networkResult as Resource.Success
        favoritesRepository.getFavoriteIds()
            .flowOn(Dispatchers.IO)
            .collect() { favorites ->
                productRepository.updateAllProductsCache(networkResult.data, favorites)
                val productList = productRepository.getAllProductsCache()
                send(Resource.Success(productList.filter { it.name.contains(query) }))
            }
    }
}