package com.example.myapplicationprueba.model

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("site_id") var site_id: String?,
    @SerializedName("query") var id: String?,
    @SerializedName("paging") var paging: Paging?,
    @SerializedName("results") var products: List<Product>?,
    @SerializedName("results") var products: List<Product>?,

    )

data class Paging(
    var total: Int? = null,
    var offset: Int? = null,
    var limit: Int? = null,
    var primary_results: Int? = null,
)
