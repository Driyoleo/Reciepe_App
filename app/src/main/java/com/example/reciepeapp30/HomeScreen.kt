package com.example.reciepeapp30

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import okhttp3.internal.wait

@Composable
fun HomeScreen(
    navtoCategoryScreen: ()-> Unit ,
    navtoAreaScreen: () -> Unit ,
    navtoIngredientsScreen: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
            .background(color = Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Recipe App" , modifier = Modifier.padding(top = 80.dp , bottom = 40.dp) , color = Color.Black , fontFamily = FontFamily.Cursive , fontSize = 30.sp)
        Text(text = "Search by.." , color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navtoCategoryScreen() } ,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp) ,
            colors = ButtonColors(Color.DarkGray,Color.White,Color.Gray,Color.White)
        ) {
            Text(text = "Category")
        }
        Button(
            onClick = { navtoAreaScreen() } ,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp) ,
            colors = ButtonColors(Color.DarkGray,Color.White,Color.Gray,Color.White)
        ) {
            Text(text = "Area")
        }
        Button(
//            onClick = { navtoIngredientsScreen() } ,
            onClick = {},
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp) ,
            colors = ButtonColors(Color.DarkGray,Color.White,Color.Gray,Color.White)
        ) {
            Text(text = "Ingredients")
        }
    }



}


