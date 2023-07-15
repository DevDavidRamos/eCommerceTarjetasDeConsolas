package com.darvdev.ecommerce.domain.repository

interface FavoritesRepository {

        suspend fun  saveFavorite(id:String)


        suspend fun getFavoriteIds(): List<String>

        suspend fun deleteFavorite(id:String)
}