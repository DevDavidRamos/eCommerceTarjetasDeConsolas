package com.darvdev.ecommerce.ui.result

import androidx.lifecycle.*
import com.darvdev.ecommerce.domain.model.Product
import com.darvdev.ecommerce.domain.usecase.GetProductsByNameQueryUseCase
import com.darvdev.ecommerce.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val getProductsByNameQueryUseCase: GetProductsByNameQueryUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _products: MutableLiveData<Resource<List<Product>>> = MutableLiveData()
    val products: LiveData<Resource<List<Product>>>
        get() = _products

    init {
        val nameQuery = savedStateHandle.get<String>("nameQuery").orEmpty()
        getProductByNameQuery(nameQuery)
    }

    private fun getProductByNameQuery(queryName: String) {
        viewModelScope.launch {
            val productsList = getProductsByNameQueryUseCase(query = queryName)
            _products.postValue(productsList)
        }
    }

}