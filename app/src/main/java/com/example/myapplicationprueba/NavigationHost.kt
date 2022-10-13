package com.example.myapplicationprueba

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationHost(){
    val navController = rememberNavController()
    NavHost(navController=navController, startDestination=Destinations.SearchView.route){
        composable(Destinations.SearchView.route){
            SearchView(
                goViewDetails = { textSearch->
                    navController.navigate(Destinations.Pantalla2.createRoute(textSearch))
                }
            )
        }
        composable(Destinations.Pantalla2.route){
            navBackStackEntry ->
            var textSearch=navBackStackEntry.arguments?.getString("textSearch")
            Pantalla2(textSearch!!)
        }
    }
}