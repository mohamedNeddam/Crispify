package com.example.crispify.ui.screens


import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.crispify.R
import com.example.crispify.Recipe
import com.example.crispify.ui.theme.CrispifyTheme
import com.example.crispify.ui.theme.RecipesState
import com.example.crispify.ui.theme.RecipesViewModel

@Composable
fun HomeScreen(){
    val recipesViewModel: RecipesViewModel = viewModel()

    CrispifyTheme {
        Surface(
            modifier = Modifier
                .safeDrawingPadding()
                .fillMaxSize(),
        ) {
            Column {
                GreetingHerder(modifier = Modifier.padding(top = 16.dp, start = 16.dp))
                SearchBox(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp))

                when(recipesViewModel.recipesList.value){
                    is RecipesState.Error -> Text("error")
                    is RecipesState.Loading -> Text("loading")
                    is RecipesState.Success -> LazyRow(){
                        items(((recipesViewModel.recipesList.value as RecipesState.Success).recipes)){
                                recipe -> RecipeItem(recipe)
                        }
                    }
                }

                BottomExplore()
            }
        }
    }
}


@Composable
fun GreetingHerder(modifier: Modifier){
    Row(modifier = modifier) {
        Column(modifier = Modifier.weight(.7f)) {
            Text(
                "Good morning",
                fontWeight = FontWeight.SemiBold
            )
            Text(
                "What would you like to cook for today ?",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Image(modifier = Modifier
            .weight(.3f)
            .size(60.dp),
            alignment = Alignment.CenterEnd,
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription =""
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox(modifier: Modifier){
    var textstate5 by remember { mutableStateOf("") }

    TextField(
        value = textstate5,
        onValueChange = { textstate5 = it },
        modifier = modifier,
        label = { Text(text = "Search any recipes...", fontWeight = FontWeight.SemiBold) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Email icon",
            )
        },
        shape = RoundedCornerShape(topStart =  10.dp, topEnd = 10.dp)    )
}



@Composable
fun RecipeItem(recipe: Recipe){

    Box(modifier = Modifier
        .padding(start = 16.dp, top = 16.dp)
        .width(200.dp)
        .height(300.dp)
        .clip(RoundedCornerShape(25.dp))
    )
    {
        Card(modifier = Modifier.fillMaxSize()){
            AsyncImage(model = recipe.imageUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .drawWithCache {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color(red = 43, green = 45, blue = 65)
                            ),
                            startY = size.height / 3,
                            endY = size.height
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient, blendMode = BlendMode.Multiply)
                        }
                    }
            )
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(20.dp), verticalArrangement = Arrangement.Bottom) {

                Box(modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
//                    .background(Color.Gray)
                    .padding(vertical = 5.dp, horizontal = 10.dp)) {
                    Text("Breakfast", fontWeight = FontWeight.Medium, color = Color.White)
                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(recipe.title, fontWeight = FontWeight.Bold, color = Color.White)
                Spacer(modifier = Modifier.height(5.dp))
                Text("${recipe.ingredients.size} ingredients | ${recipe.cookTime} Min",  fontWeight = FontWeight.Normal, fontSize = 10.sp, color = Color.White)

            }}
    }
}

@Composable
fun BottomExplore(){
    Card(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .height(130.dp)
        .clip(RoundedCornerShape(25.dp))
//        .background(Color.White)
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            ,verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Column(modifier = Modifier.weight(.5f)) {
                Box(modifier = Modifier) {
                    Text("Breakfast", fontWeight = FontWeight.Medium)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text("We have 15 Recipes recommendation", fontWeight = FontWeight.Normal)
            }
            Button(onClick = { /*TODO*/ })
            {
                Text("Explore")
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)
@Composable
fun GreetingPreview() {
    CrispifyTheme {
        Surface(
            modifier = Modifier
                .safeDrawingPadding()
                .fillMaxSize(),
        ) {
            Column {
                GreetingHerder(modifier = Modifier.padding(16.dp))
                SearchBox(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp))
                BottomExplore()
            }
        }
    }
}

