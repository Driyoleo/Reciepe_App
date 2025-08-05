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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun CategoryScreen(catState : MainViewModel.CategoriesState ,navtoMealScreen : (String)->Unit) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
//        var catViewModel : CatVM = viewModel()

        Text(text = "Recipe App" , modifier = Modifier.padding(top = 80.dp , bottom = 40.dp) , color = Color.Black , fontFamily = FontFamily.Cursive , fontSize = 30.sp)
        Text(text = "Categories" , color = Color.Black , modifier = Modifier.padding(bottom = 16.dp))
        Box {
            when{
                catState.load ->{
                    Text(text = "Loading" , color = Color.DarkGray)
                }
                catState.error != "" ->{
                    Text(text = "error : ${catState.error}")
                }
                catState.list == emptyList<Category>() ->{
                    Text(text = "net slow")
                }
                else ->{
                    CatList(catState.list , navtoMealScreen)
                }
            }
        }
    }
}

@Composable
fun CatList(itemlist : List<Category> , navtoMealScreen: (String) -> Unit) {
    LazyVerticalGrid(GridCells.Fixed(2)) {
        items(itemlist, itemContent = { CatItem(item = it , navtoMealScreen)})
    }
}

@Composable
fun CatItem(item: Category , navtoMealScreen: (String) -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(20))
            .clickable { navtoMealScreen(item.strCategory) },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = rememberAsyncImagePainter(model = item.strCategoryThumb),
            contentDescription = "" ,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .aspectRatio(1F)
        )
        Text(text = item.strCategory , modifier = Modifier.padding(start = 12.dp , end = 12.dp , bottom = 8.dp) , color = Color.Black)
    }
}



