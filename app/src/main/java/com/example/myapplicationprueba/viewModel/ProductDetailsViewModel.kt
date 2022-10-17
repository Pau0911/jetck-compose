package com.example.myapplicationprueba.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationprueba.Response
import com.example.myapplicationprueba.model.ProductDetails
import com.example.myapplicationprueba.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) :
    ViewModel() {

    private val _productState = mutableStateOf<Response<ProductDetails>>(Response.Success(null))
    val productState: State<Response<ProductDetails>> = _productState

//    private val _isLoading: MutableLiveData<Boolean> by lazy {
//        MutableLiveData<Boolean>(false)
//    }

    fun getDetailProduct(id: String) {
        viewModelScope.launch {
            productRepository.getDetailProduct(id).collect { response ->
                _productState.value = response
            }
        }
    }

//    val isLoading: LiveData<Boolean> get() = _isLoading

//    fun getProduct(id: String): ProductDetails? {
//        var product: ProductDetails?=null
//        if (_isLoading.value == false)
//            viewModelScope.launch(Dispatchers.IO) {
//                _isLoading.postValue(true)
//                product = productRepository.getProduct(id)
//                _isLoading.postValue(false)
//
//            }
//        return product
//    }
}