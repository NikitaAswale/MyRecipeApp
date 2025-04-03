package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController){
    val recipeViewModel : MainViewModel = viewModel()
    val viewState by recipeViewModel.CategoriesState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route){
        composable(route = Screen.RecipeScreen.route){
            RecipeScreen(viewState = viewState, navigateToDetail = {
                //This part is responsible for passing data from the current screen to the detail screen.
                //It utilizes the sasvedStateHandle, which is a component of the compose navigation framework.
                navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }

        composable(route = Screen.DetailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.
            get<Category>("cat")?:Category("","","","")
            CategoryDetailScreen(category = category)
        }

    }
}