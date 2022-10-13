package com.example.myapplicationprueba

sealed class Destinations (val route:String){

    object SearchView: Destinations("searchView")
    object Pantalla2: Destinations("pant2/{textSearch}"){
        fun createRoute(textSearch:String)="pant2/$textSearch"
    }
}