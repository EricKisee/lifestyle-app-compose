package com.erickisee.lifestyleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erickisee.lifestyleapp.ui.theme.LifestyleAppTheme
import java.security.AllPermission

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LifestyleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        SearchBar()
                        FavouriteCollectionCard(text="What nature does." , drawable = R.drawable.nature,
                            modifier = Modifier.padding( 10.dp))
                    }

//                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LifestyleAppTheme {
        Greeting("Android")
    }
}

@Composable
fun SearchBar(modifier: Modifier= Modifier){
    TextField(
        value = "",
        onValueChange =   {},
        leadingIcon = {
                      Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
                      Text(text = "Search")
        },
        modifier= modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)

    )
}

@Preview
@Composable
fun SearchbarPreview (){
    LifestyleAppTheme {
        SearchBar()
    }
}

@Composable
fun FavouriteCollectionCard(
    @DrawableRes drawable: Int,
    text: String,
    modifier: Modifier=Modifier
){
    Surface (
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant ,
        modifier = modifier
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
                )
        }
    }
}

@Preview
@Composable
fun FavouriteCollectionCardPreview (){
    LifestyleAppTheme {
        FavouriteCollectionCard(
            text = "",
            drawable = R.drawable.nature,
            modifier=Modifier.padding(8.dp)
        )
    }
}