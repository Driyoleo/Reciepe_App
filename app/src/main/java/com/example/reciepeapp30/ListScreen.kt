package com.example.reciepeapp30

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListScreen(ListData : MainViewModel.List_State , navtoMealScreen: (String)-> Unit) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding( start = 16.dp, end = 16.dp)
            .background(color = Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Recipe App" , modifier = Modifier.padding(top = 80.dp , bottom = 40.dp) , color = Color.Black , fontFamily = FontFamily.Cursive , fontSize = 30.sp)
        Text(text = "Areas" , color = Color.Black , modifier = Modifier.padding(bottom = 16.dp))
        Box {
            when{
                ListData.load ->{
                    Text(text = "Loading" , color = Color.DarkGray)
                }
                ListData.error != "" ->{
                    Text(text = "error : " + ListData.error)
                }
                else ->{
                    ListDetails(TypList = ListData.list , navtoMealScreen)
                }
            }
        }
    }
}

@Composable
fun ListDetails(TypList : List<ListClass> , navtoMealScreen: (String) -> Unit) {
    LazyColumn {
        items(TypList, itemContent = {
            Button(
                onClick = { navtoMealScreen(it.strArea) } ,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp) ,
                colors = ButtonColors(Color.DarkGray,Color.White,Color.Gray,Color.White)
            ) {
                Text(text = it.strArea)
            }
        })
    }
}