package com.erickisee.lifestyleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erickisee.lifestyleapp.ui.theme.LifestyleAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(activity = this)
            MyApp(windowSize = windowSizeClass)
        }
    }
}

@Composable
fun MyApp(windowSize: WindowSizeClass){
    when (windowSize.widthSizeClass){
        WindowWidthSizeClass.Compact->{
            MyAppPotrait()
        }
        WindowWidthSizeClass.Medium->{
            MyAppLandscape()
        }
        WindowWidthSizeClass.Expanded->{
            MyAppLandscape()
        }
    }
}

@Composable
fun MyAppPotrait () {
    LifestyleAppTheme {
        Scaffold (
            bottomBar = { BottomNavigation()}
        ) {
            padding-> HomeScreen(Modifier.padding(padding))
        }
    }
}

@Composable
fun MyAppLandscape() {
    LifestyleAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Row {
                AppNavigationRail()
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier=Modifier){
    Column (modifier.verticalScroll(rememberScrollState())) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favorite_collections) {
            FavouriteCollectionGrid()
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun BottomNavigation(modifier: Modifier=Modifier){
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceVariant
        ) {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = {
                Icon(imageVector = Icons.Default.Spa,
                    contentDescription = null)
            },
            label = {
                Text(text = stringResource(id = R.string.bottom_navigation_home))
            })
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(id = R.string.bottom_navigation_profile))
            }
            )
    }
}

@Composable
fun AppNavigationRail(modifier: Modifier = Modifier){
    NavigationRail (
        modifier = modifier.padding(start=8.dp,end=8.dp),
        containerColor = MaterialTheme.colorScheme.background) {
            Column (
                modifier = modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NavigationRailItem(
                    selected = true,
                    onClick = {},
                    icon = {
                        Icon(imageVector = Icons.Default.Spa,
                        contentDescription = null)
                    },
                    label = {Text(stringResource(id = R.string.bottom_navigation_home))}
                )
                Spacer(modifier = Modifier.height(8.dp))
                NavigationRailItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null
                        )
                    }
                )

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
    @StringRes text: Int,
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
                text = stringResource(id = text),
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
            text = R.string.align_your_body,
            drawable = R.drawable.nature,
            modifier=Modifier.padding(8.dp)
        )
    }
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
){
    Column (modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(id = text),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
fun AlignYourBodyElementPreview (){
    LifestyleAppTheme {
        AlignYourBodyElement(drawable = R.drawable.yoga, text = R.string.align_your_body, modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ){
        items(alignYourBodyData) { item-> AlignYourBodyElement(drawable = item.drawable, text = item.text) }
    }
}

@Composable
fun FavouriteCollectionGrid(
    modifier: Modifier = Modifier
){
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier=modifier.height(168.dp) ){
        items(favoriteCollectionsData){
            item -> FavouriteCollectionCard(drawable = item.drawable, text = item.text, Modifier.height(80.dp))
        }
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Column (modifier){
        Text(
            stringResource(id = title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)