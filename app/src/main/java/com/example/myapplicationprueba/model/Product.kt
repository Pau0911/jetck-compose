package com.example.myapplicationprueba.model

data class Response(
    val accepts_mercadopago: Boolean,
    val address: Address,
    val attributes: List<Attribute>,
    val available_quantity: Int,
    val buying_mode: String,
    val catalog_listing: Boolean,
    val catalog_product_id: String,
    val category_id: String,
    val condition: String,
    val currency_id: String,
    val id: String,
    val installments: Installments,
    val listing_type_id: String,
    val official_store_id: Int,
    val original_price: Any,
    val permalink: String,
    val price: Int,
    val seller: Seller,
    val seller_address: SellerAddress,
    val shipping: Shipping,
    val site_id: String,
    val sold_quantity: Int,
    val stop_time: String,
    val tags: List<String>,
    val thumbnail: String,
    val title: String
) {
    data class Address(
        val city_id: String,
        val city_name: String,
        val state_id: String,
        val state_name: String
    )

    data class Attribute(
        val attribute_group_id: String,
        val attribute_group_name: String,
        val id: String,
        val name: String,
        val source: Int,
        val value_id: String,
        val value_name: String,
        val value_struct: Any
    )

    data class Installments(
        val amount: Double,
        val currency_id: String,
        val quantity: Int,
        val rate: Double
    )

    data class Seller(
        val car_dealer: Boolean,
        val id: Int,
        val power_seller_status: String,
        val real_estate_agency: Boolean,
        val tags: List<Any>
    )

    data class SellerAddress(
        val city: City,
        val country: Country,
        val latitude: String,
        val longitude: String,
        val state: State
    ) {
        data class City(
            val id: String,
            val name: String
        )

        data class Country(
            val id: String,
            val name: String
        )

        data class State(
            val id: String,
            val name: String
        )
    }

    data class Shipping(
        val free_shipping: Boolean,
        val logistic_type: String,
        val mode: String,
        val store_pick_up: Boolean,
        val tags: List<Any>
    )
}