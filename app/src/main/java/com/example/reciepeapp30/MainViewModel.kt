package com.example.reciepeapp30

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch





class MainViewModel : ViewModel() {
    // status
    private var _catanme : String = ""
    var catname = _catanme
    fun changecatname(name : String){
        _catanme = name
    }



    // categories
    data class CategoriesState(
        var load : Boolean = true ,
        var list : List<Category> ,
        var error : String? ,
    )


    private var _categoryState = mutableStateOf(CategoriesState(true , emptyList() , ""))
    var categoryState : State<CategoriesState> = _categoryState

    fun fetchCategories(){
        viewModelScope.launch {
            try {
                var response = apiScrevice.getCategories()
                _categoryState.value = _categoryState.value.copy(
                    load = false ,
                    list = response.categories
                )

            }catch (e : Exception){
                _categoryState.value = _categoryState.value.copy(
                    load = false ,
                    error = e.message
                )
            }
        }
    }
    fun resetCategoryScreen(){
        viewModelScope.launch {
            _categoryState.value = _categoryState.value.copy(
                load = true ,
                error = "" ,
                list = emptyList()
            )
        }
    }

    // Meals
    data class MealState(
        var category : String ,
        var load: Boolean = true ,
        var list: List<Meal> ,
        var error: String?
    )

    private var _mealState = mutableStateOf(MealState("",true, emptyList(),""))
    var mealState : State<MealState> = _mealState

    fun fetchMeals(cat : String){
        _mealState.value = _mealState.value.copy(
            category = cat
        )
        viewModelScope.launch {
            try {
                var response = apiScrevice.getMeals(cat)
                _mealState.value = _mealState.value.copy(
                    load = false ,
                    list = response.meals
                )
            }catch (e : Exception){
                _mealState.value = _mealState.value.copy(
                    load = false ,
                    error = e.message
                )
            }
        }
    }
    fun resetMealScreen(){
        viewModelScope.launch {
            _mealState.value = _mealState.value.copy(
                load = true ,
                error = "" ,
                list = emptyList() ,
                category = ""
            )
        }
    }

    fun fetchAreaMeals(area : String){
        viewModelScope.launch {
            try {
                var response = apiScrevice.getAreaMeals(area)
                _mealState.value = _mealState.value.copy(
                    load = false ,
                    list = response.meals
                )
            }catch (e : Exception){
                _mealState.value = _mealState.value.copy(
                    load = false ,
                    error = e.message
                )
            }
        }
    }



    // Reciepe
    data class ReciepeState(
        var load: Boolean = true ,
        var list: List<Reciepe> ,
        var error: String?
    )

    private var _reciepeState = mutableStateOf(ReciepeState(true, emptyList(),""))
    var recipeState : State<ReciepeState> = _reciepeState

    fun fetchReciepe(id : String){
        viewModelScope.launch {
            try {
                var response = apiScrevice.getReciepe(id)
                _reciepeState.value = _reciepeState.value.copy(
                    load = false ,
                    list = response.meals
                )
            }catch (e:Exception){
                _reciepeState.value = _reciepeState.value.copy(
                    load = false ,
                    error = e.message
                )
            }
        }
    }
    fun resetReciepeScreen(){
        viewModelScope.launch {
            _reciepeState.value = _reciepeState.value.copy(
                load = true ,
                error = "" ,
                list = emptyList()
            )
        }
    }

    // List Screen

    data class List_State(
        var load: Boolean = true ,
        var list: List<ListClass> ,
        var error: String?
    )

    private var _list_State = mutableStateOf(List_State(true, emptyList(),""))
    var list_State : State<List_State> = _list_State

    fun fetchList(){
        viewModelScope.launch {
            try {
                var response = apiScrevice.getAreas("list")
                _list_State.value = _list_State.value.copy(
                    load = false ,
                    list = response.meals
                )
            }catch (e : Exception){
                _list_State.value = _list_State.value.copy(
                    load = false ,
                    error = e.message
                )
            }
        }
    }
}