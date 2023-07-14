package com.darvdev.ecommerce.domain.ui

import com.darvdev.ecommerce.domain.model.INVALID_PRICE


data class ProductUi(
    val id: String? = null,
    val name: String = "",
    val brand: String = "",
    val price: Int = INVALID_PRICE,
    val image: String = "",
    val isFavorite: Boolean = false
)
