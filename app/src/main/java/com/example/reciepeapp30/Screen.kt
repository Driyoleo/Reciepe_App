package com.example.reciepeapp30

sealed class Screen(var route : String){
    object HomeScreen : Screen("HomeScreen")
    object CategoryScreen : Screen("CategoryScreen")
    object MealScreen : Screen("MealScreen")
    object ReciepeScreen : Screen("ReciepeScreen")
    object ListScreen : Screen("ListScreen")
}