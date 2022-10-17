package com.example.myapplicationprueba

import com.example.myapplicationprueba.model.Product
import com.google.gson.JsonObject

sealed class Destinations (val route:String){

    object SearchView: Destinations("searchView")
    object ResultsView: Destinations("resultsView/{searchText}"){
        fun createRoute(searchText:String)="resultsView/$searchText"
    }
    object DetailsView: Destinations("detailsView/{id}"){
        fun createRoute(id:String)="detailsView/$id"
    }

}