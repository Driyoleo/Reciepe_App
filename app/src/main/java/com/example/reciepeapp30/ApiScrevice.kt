package com.example.reciepeapp30


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

val retrofit = Retrofit
    .Builder()
    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiScrevice = retrofit.create(ApiScrevice::class.java)

interface ApiScrevice{
    @GET("categories.php")
    suspend fun getCategories() : CategoryRoot

    @GET("filter.php")
    suspend fun getMeals(@Query("c") c : String) : MealRoot

    @GET("lookup.php")
    suspend fun getReciepe(@Query("i") i : String) : ReciepeRoot

    @GET("list.php")
    suspend fun getAreas(@Query("a") a : String) : ListRoot

    @GET("list.php")
    suspend fun getIngredients(@Query("i") i : String) : ListRoot

    @GET("filter.php")
    suspend fun getAreaMeals(@Query("a") a : String) : MealRoot


}
