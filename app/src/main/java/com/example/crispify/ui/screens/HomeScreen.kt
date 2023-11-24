package com.example.crispify.ui.screens


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
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
import com.example.crispify.ui.RecipesState
import com.example.crispify.ui.RecipesViewModel

@Composable
fun HomeScreen(){
    val recipesViewModel: RecipesViewModel = viewModel(factory = RecipesViewModel.Factory)

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

                when(recipesViewModel.recipesList){
                    is RecipesState.Error -> Text("error")
                    is RecipesState.Loading -> {
                        Row{
                            ComponentRectangle(
                                Modifier
                                    .padding(start = 16.dp, top = 16.dp)
                                    .width(200.dp)
                                    .height(300.dp)
                            )
                            ComponentRectangle(
                                Modifier
                                    .padding(start = 16.dp, top = 16.dp)
                                    .width(200.dp)
                                    .height(300.dp)
                            )
                        }
                    }
                    is RecipesState.Success -> LazyRow(){
                        items((recipesViewModel.recipesList as RecipesState.Success).recipes){
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
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Column( modifier = Modifier.width(250.dp)) {
            Text(
                "Good morning",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            Text(
                "What would you like to cook for today ?",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(modifier = Modifier
            .width(150.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.ic_launcher_foreground),
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
        .clip(RoundedCornerShape(bottomEnd = 25.dp))
    )
    {
        Card(modifier = Modifier.fillMaxSize()){
            Box {
                AsyncImage(model = recipe.imageUrl,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                        .clip(RoundedCornerShape(bottomEnd = 25.dp))
                )
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp), verticalArrangement = Arrangement.Bottom) {

                    Box(modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
//                    .background(Color.Gray)
                        .padding(top = 5.dp)) {
                        Text("Breakfast", fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(recipe.title, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(5.dp))
                    Text("${recipe.ingredients.size} ingredients | ${recipe.cookTime} Min",  fontWeight = FontWeight.Normal, fontSize = 10.sp)

                }
            }

        }
    }
}

@Composable
fun BottomExplore(){
    Card(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .height(130.dp)
        .clip(RoundedCornerShape(bottomEnd = 25.dp))
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


fun Modifier.shimmerLoadingAnimation(
    widthOfShadowBrush: Int = 500,
    angleOfAxisY: Float = 270f,
    durationMillis: Int = 1000,
): Modifier {
    return composed {

        val shimmerColors = listOf(
            Color.White.copy(alpha = 0.3f),
            Color.White.copy(alpha = 0.5f),
            Color.White.copy(alpha = 1.0f),
            Color.White.copy(alpha = 0.5f),
            Color.White.copy(alpha = 0.3f),
        )

        val transition = rememberInfiniteTransition(label = "")

        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = durationMillis,
                    easing = LinearEasing,
                ),
                repeatMode = RepeatMode.Restart,
            ),
            label = "Shimmer loading animation",
        )

        this.background(
            brush = Brush.linearGradient(
                colors = shimmerColors,
                start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0.0f),
                end = Offset(x = translateAnimation.value, y = angleOfAxisY),
            ),
        )
    }
}

@Composable
fun ComponentRectangle(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(24.dp))
            .background(color = Color.Gray)
            .shimmerLoadingAnimation() // <--- Here.
    )
}