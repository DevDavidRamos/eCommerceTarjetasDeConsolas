package com.darvdev.ecommerce.ui.result

import androidx.lifecycle.*
import com.darvdev.ecommerce.domain.model.Product
import com.darvdev.ecommerce.domain.repository.FavoritesRepository
import com.darvdev.ecommerce.domain.usecase.GetProductsByNameQueryUseCase
import com.darvdev.ecommerce.utils.UiState
import com.darvdev.ecommerce.utils.mapToUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val getProductsByNameQueryUseCase: GetProductsByNameQueryUseCase,
    private val favoritesRepository: FavoritesRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _products: MutableLiveData<UiState<List<Product>>> = MutableLiveData()
    val products: LiveData<UiState<List<Product>>>
        get() = _products

    private val query: String

    init {
        query = savedStateHandle.get<String>("nameQuery").orEmpty()
        getProductByNameQuery(query)
    }

    private fun getProductByNameQuery(queryName: String) {
        viewModelScope.launch {
            _products.postValue(UiState.Loading)

            val productsList = getProductsByNameQueryUseCase(query = queryName)
            _products.postValue(productsList.mapToUiState())
        }
    }

    fun saveFavorite(id: String) {
        viewModelScope.launch {
            favoritesRepository.saveFavorite(id)

            val productsList = getProductsByNameQueryUseCase(query = query)
            _products.postValue(productsList.mapToUiState())
        }
    }

    fun deleteFavorite(id: String) {
        viewModelScope.launch {
            favoritesRepository.deleteFavorite(id)

            val productsList = getProductsByNameQueryUseCase(query = query)
            _products.postValue(productsList.mapToUiState())
        }
    }

}