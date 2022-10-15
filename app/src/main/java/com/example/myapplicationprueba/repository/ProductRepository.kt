package com.example.myapplicationprueba.repository

import com.example.myapplicationprueba.model.ProductDetails
import com.example.myapplicationprueba.model.Response
import com.example.myapplicationprueba.network.MeliAPI
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val meliAPI: MeliAPI
) {
    suspend fun getResponse(siteId: String, searchText: String, pageId:Int): Response {
        return meliAPI.getResponse(siteId, searchText,pageId)
    }

    suspend fun getProduct(id:String,):ProductDetails{
        return meliAPI.getProduct(id)
    }
}