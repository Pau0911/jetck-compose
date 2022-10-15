package com.example.myapplicationprueba.network

import com.example.myapplicationprueba.model.ProductDetails
import com.example.myapplicationprueba.model.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MeliAPI {

    @GET("/sites/{site_id}/search")
    suspend fun getResponse(@Path("site_id") site_id: String, @Query("q") q: String,@Query("offset") offset:Int): Response
//
//    @GET("/sites")
//    suspend fun getCountries(): List<Country>

    @GET("/items/{id}")
    suspend fun getProduct(@Path("id") id: String):ProductDetails


}