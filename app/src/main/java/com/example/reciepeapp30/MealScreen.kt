package com.example.reciepeapp30

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter


@Composable
fun MealScreen(mealState: MainViewModel.MealState ,navtoReciepeScreen : (String)->Unit) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding( start = 16.dp, end = 16.dp)
                .background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
//            var mealViewModel : MealVM = viewModel()

            Text(text = "Recipe App" , modifier = Modifier.padding(top = 80.dp , bottom = 40.dp) , color = Color.Black , fontFamily = FontFamily.Cursive , fontSize = 30.sp)
            Text(text = "Category : " + mealState.category , color = Color.Black , modifier = Modifier.padding(bottom = 16.dp))
            Box {
                when{
                    mealState.load ->{
                        Text(text = "Loading" , color = Color.DarkGray)
                    }
                    mealState.error != "" ->{
                        Text(text = "error : ${mealState.error}")
                    }
                    mealState.list == emptyList<Category>() ->{
                        Text(text = "net slow")
                    }
                    else ->{
                        MealList(mealState.list , navtoReciepeScreen)
                    }
                }
            }
        }

}

@Composable
fun MealList(itemlist : List<Meal> , navtoReciepeScreen: (String) -> Unit) {
    LazyVerticalGrid(GridCells.Fixed(2)) {
        items(itemlist, itemContent = { MealItem(item = it , navtoReciepeScreen)})
    }
}

@Composable
fun MealItem(item : Meal , navtoReciepeScreen: (String) -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(20))
            .clickable { navtoReciepeScreen(item.idMeal) },
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Image(
            painter = rememberAsyncImagePainter(model = item.strMealThumb),
            contentDescription = "" ,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .aspectRatio(1F)
                .clip(RoundedCornerShape(20)),
        )
        Text(text = item.strMeal ,
            modifier = Modifier.padding(start = 12.dp , end = 12.dp , bottom = 8.dp).fillMaxWidth() ,
            textAlign = TextAlign.Center ,
            color = Color.Black
        )
    }

}