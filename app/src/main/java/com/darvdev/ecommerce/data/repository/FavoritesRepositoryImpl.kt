package com.darvdev.ecommerce.data.repository

import com.darvdev.ecommerce.data.local.dao.FavoritesDao
import com.darvdev.ecommerce.data.local.entity.FavoriteEntity
import com.darvdev.ecommerce.domain.repository.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesDao: FavoritesDao
):FavoritesRepository{

    override suspend fun saveFavorite(id: String) {
      withContext(Dispatchers.IO){
          favoritesDao.insertFavorite(favoriteEntity = FavoriteEntity(id))
      }
    }

    override fun getFavoriteIds(): Flow<List<String>> {
      return favoritesDao.getFavorites().map { favoriteEntities ->
          favoriteEntities.map { it.id }
      }

    }

    override suspend fun deleteFavorite(id: String) {
       favoritesDao.deleteFavorite(id)


    }
}