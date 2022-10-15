package com.example.myapplicationprueba

sealed class Destinations (val route:String){

    object SearchView: Destinations("searchView")
    object ResultsView: Destinations("resultsView/{searchText}"){
        fun createRoute(searchText:String)="resultsView/$searchText"
    }

}