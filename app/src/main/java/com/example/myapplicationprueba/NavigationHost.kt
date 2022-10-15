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
                goToDetailsView = { searchText->
                    navController.navigate(Destinations.ResultsView.createRoute(searchText))
                }
            )
        }
        composable(Destinations.ResultsView.route){
            navBackStackEntry ->
            var searchText=navBackStackEntry.arguments?.getString("searchText")
            ResultsView(searchText!!)


        }
    }
}