package com.example.reciepeapp30

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter

@Composable
fun ReciepeScreen(reciepeState: MainViewModel.ReciepeState) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding( start = 16.dp, end = 16.dp)
            .background(Color.LightGray)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Recipe App" , modifier = Modifier.padding(top = 80.dp , bottom = 40.dp) , color = Color.Black , fontFamily = FontFamily.Cursive , fontSize = 30.sp)
        Box(){
            when{
                reciepeState.load ->{
                    Text(text = "Loading" , color = Color.DarkGray)
                }
                reciepeState.error != "" ->{
                    Text(text = reciepeState.error!!)
                }
                else ->{
                    Reciepe_items(reciepeContent = reciepeState.list[0])
                }
            }
        }
    }

}

@Composable
fun Reciepe_items(reciepeContent : Reciepe) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally ,
    ){
        Text(text = reciepeContent.strMeal , color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Category : " + reciepeContent.strCategory , color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Area : " + reciepeContent.strArea , color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = rememberAsyncImagePainter(model = reciepeContent.strMealThumb),
            contentDescription = "" ,
            modifier = Modifier
                .padding(8.dp)
                .aspectRatio(1F)
                .clip(RoundedCornerShape(10))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Instructions" , color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = reciepeContent.strInstructions , modifier = Modifier.padding(horizontal = 16.dp), color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            buildAnnotatedString {
                append("youtube video : ")
                withLink(
                    LinkAnnotation.Url(
                        reciepeContent.strYoutube,
                        TextLinkStyles(style = SpanStyle(color = Color.Blue))
                    )
                ) {
                    append(reciepeContent.strYoutube)
                }
            },
            modifier = Modifier.padding(horizontal = 16.dp) ,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "-- The End --" , color = Color.Black)
    }
}

