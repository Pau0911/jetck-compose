package com.example.myapplicationprueba

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationprueba.screen.DetailsView

@Composable
fun NavigationHost(){
    val navController = rememberNavController()
    NavHost(navController=navController, startDestination=Destinations.SearchView.route){
        composable(Destinations.SearchView.route){
            SearchView(
                goToResultsView = { searchText->
                    navController.navigate(Destinations.ResultsView.createRoute(searchText))
                }
            )
        }
        composable(Destinations.ResultsView.route){
            navBackStackEntry ->
            val searchText=navBackStackEntry.arguments?.getString("searchText")
            ResultsView(searchText!!, goToDetailsView = { id ->
                navController.navigate(Destinations.DetailsView.createRoute(id))
            })
        }
        composable(Destinations.DetailsView.route){
                navBackStackEntry ->
            val id=navBackStackEntry.arguments?.getString("id")
            DetailsView(id!!)
        }
    }
}