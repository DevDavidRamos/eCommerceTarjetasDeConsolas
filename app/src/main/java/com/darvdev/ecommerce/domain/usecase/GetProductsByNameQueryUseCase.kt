package com.darvdev.ecommerce.domain.usecase

import com.darvdev.ecommerce.domain.model.Product
import com.darvdev.ecommerce.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetProductsByNameQueryUseCase @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
) {

    suspend operator fun invoke(query: String): Resource<List<Product>> {

        return withContext(Dispatchers.IO) {
            val productResult = getAllProductsUseCase()

            if (productResult is Resource.Error) {
                Resource.Error(productResult.error)
            }

            productResult as Resource.Success
            Resource.Success(productResult.data.filter { it.name.contains(query) })
        }
    }
}