package com.example.myapplicationprueba.model

data class ProductDetails(
    val accepts_mercadopago: Boolean,
    val attributes: List<Attribute>,
    val automatic_relist: Boolean,
    val available_quantity: Int,
    val base_price: Int,
    val buying_mode: String,
    val catalog_listing: Boolean,
    val catalog_product_id: String,
    val category_id: String,
    val channels: List<String>,
    val condition: String,
    val coverage_areas: List<Any>,
    val currency_id: String,
    val date_created: String,
    val deal_ids: List<Any>,
    val descriptions: List<Any>,
    val differential_pricing: Any,
    val domain_id: String,
    val health: Double,
    val id: String,
    val initial_quantity: Int,
    val international_delivery_mode: String,
    val last_updated: String,
    val listing_source: String,
    val listing_type_id: String,
    val location: Location,
    val non_mercado_pago_payment_methods: List<Any>,
    val official_store_id: Any,
    val original_price: Any,
    val parent_item_id: Any,
    val permalink: String,
    val pictures: List<Picture>,
    val price: Int,
    val sale_terms: List<SaleTerm>,
    val secure_thumbnail: String,
    val seller_address: SellerAddress,
    val seller_contact: Any,
    val seller_id: Int,
    val shipping: Shipping,
    val site_id: String,
    val sold_quantity: Int,
    val start_time: String,
    val status: String,
    val stop_time: String,
    val sub_status: List<String>,
    val subtitle: Any,
    val tags: List<String>,
    val thumbnail: String,
    val thumbnail_id: String,
    val title: String,
    val variations: List<Variation>,
    val video_id: Any,
    val warnings: List<Any>,
    val warranty: String
) {
    data class Attribute(
        val attribute_group_id: String,
        val attribute_group_name: String,
        val id: String,
        val name: String,
        val value_id: String,
        val value_name: String,
        val value_struct: ValueStruct,
        val values: List<Value>
    ) {
        data class ValueStruct(
            val number: Double,
            val unit: String
        )

        data class Value(
            val id: String,
            val name: String,
            val struct: Struct
        ) {
            data class Struct(
                val number: Double,
                val unit: String
            )
        }
    }

    class Location

    data class Picture(
        val id: String,
        val max_size: String,
        val quality: String,
        val secure_url: String,
        val size: String,
        val url: String
    )

    data class SaleTerm(
        val id: String,
        val name: String,
        val value_id: String,
        val value_name: String,
        val value_struct: Any,
        val values: List<Value>
    ) {
        data class Value(
            val id: String,
            val name: String,
            val struct: Any
        )
    }

    data class SellerAddress(
        val city: City,
        val country: Country,
        val id: Int,
        val search_location: SearchLocation,
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

        data class SearchLocation(
            val city: City,
            val neighborhood: Neighborhood,
            val state: State
        ) {
            data class City(
                val id: String,
                val name: String
            )

            data class Neighborhood(
                val id: String,
                val name: String
            )

            data class State(
                val id: String,
                val name: String
            )
        }

        data class State(
            val id: String,
            val name: String
        )
    }

    data class Shipping(
        val dimensions: Any,
        val free_shipping: Boolean,
        val local_pick_up: Boolean,
        val logistic_type: String,
        val methods: List<Any>,
        val mode: String,
        val store_pick_up: Boolean,
        val tags: List<String>
    )

    data class Variation(
        val attribute_combinations: List<AttributeCombination>,
        val available_quantity: Int,
        val catalog_product_id: String,
        val id: Long,
        val picture_ids: List<String>,
        val price: Double,
        val sale_terms: List<Any>,
        val sold_quantity: Int
    ) {
        data class AttributeCombination(
            val id: String,
            val name: String,
            val value_id: String,
            val value_name: String,
            val value_struct: Any,
            val values: List<Value>
        ) {
            data class Value(
                val id: String,
                val name: String,
                val struct: Any
            )
        }
    }
}