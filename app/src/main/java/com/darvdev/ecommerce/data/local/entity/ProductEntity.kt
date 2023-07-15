package com.darvdev.ecommerce.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.darvdev.ecommerce.domain.model.INVALID_PRICE
import com.darvdev.ecommerce.domain.model.Product


@Entity
data class ProductEntity(
    @PrimaryKey
    val id: String,
    val name: String = "",
    val brand: String = "",
    val price: Int = INVALID_PRICE,
    val image: String = "",
    val isFavorite: Boolean = false
)

fun ProductEntity.mapToProduct(): Product {
    return Product(
        id = id,
        name = name,
        brand = brand,
        price = price,
        image = image,
        isFavorite = isFavorite
    )
}

fun List<ProductEntity>.mapToProductList(): List<Product> {
    return this.map { it.mapToProduct() }
}