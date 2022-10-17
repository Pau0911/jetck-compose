package com.example.myapplicationprueba.repository

import com.example.myapplicationprueba.model.ProductDetails
import com.example.myapplicationprueba.model.Response
import com.example.myapplicationprueba.network.MeliAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val meliAPI: MeliAPI
) {
    suspend fun getResponse(siteId: String, searchText: String, pageId:Int): Response {
        return meliAPI.getResponse(siteId, searchText,pageId)
    }


    fun getDetailProduct(id: String): Flow<com.example.myapplicationprueba.Response<ProductDetails>> = flow{
        try {
            emit(com.example.myapplicationprueba.Response.Loading)
            val responseApi = meliAPI.getProduct(id)
            emit(com.example.myapplicationprueba.Response.Success(responseApi))
        } catch (e: Exception) {
            emit(com.example.myapplicationprueba.Response.Failure(e))
        }
    }.flowOn(Dispatchers.IO)
}