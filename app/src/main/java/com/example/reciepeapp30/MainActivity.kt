package com.example.reciepeapp30

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reciepeapp30.ui.theme.ReciepeApp30Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var navController = rememberNavController()
            ReciepeApp30Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainApp(navHostController = navController)
                }
            }
        }
    }
}

@Composable
fun MainApp(navHostController: NavHostController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        var viewmodel : MainViewModel = viewModel()
        NavHost(navController = navHostController, startDestination = Screen.HomeScreen.route) {
            composable(Screen.HomeScreen.route){
                HomeScreen(
                    navtoCategoryScreen = {
                        viewmodel.resetCategoryScreen()
                        navHostController.navigate(Screen.CategoryScreen.route)
                        viewmodel.fetchCategories()
                    } ,
                    navtoAreaScreen = {
                        navHostController.navigate(Screen.ListScreen.route)
                        viewmodel.fetchList()
                    }
                ) {
                    navHostController.navigate(Screen.ReciepeScreen.route)
                }
            }
            composable(Screen.CategoryScreen.route){
                CategoryScreen (viewmodel.categoryState.value){
                    viewmodel.resetMealScreen()
                    navHostController.navigate(Screen.MealScreen.route)
                    viewmodel.fetchMeals(it)
                }
            }
            composable(Screen.MealScreen.route){
                MealScreen(viewmodel.mealState.value){
                    viewmodel.resetReciepeScreen()
                    navHostController.navigate(Screen.ReciepeScreen.route)
                    viewmodel.fetchReciepe(it)
                }
            }
            composable(Screen.ReciepeScreen.route){
                ReciepeScreen(viewmodel.recipeState.value)
            }
            composable(Screen.ListScreen.route){
                ListScreen(ListData = viewmodel.list_State.value) {
                    viewmodel.resetMealScreen()
                    navHostController.navigate(Screen.MealScreen.route)
                    viewmodel.fetchAreaMeals(it)
                }
            }
        }
    }


}