package com.example.myapplicationprueba.model

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("site_id") var site_id: String?,
    @SerializedName("query") var id: String?,
    @SerializedName("paging") var paging: Paging,
    @SerializedName("results") var products: List<Product>,
    @SerializedName("catalog_listing") var catalog_listing: Boolean?
)

data class Paging(
    var total: Int?,
    var offset: Int?,
    var limit: Int?,
    var primary_results: Int?
)
