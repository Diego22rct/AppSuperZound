package com.github.diego22rct.appsuperzound

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.diego22rct.appsuperzound.ListAlbum.presentation.ListAlbumScreen
import com.github.diego22rct.appsuperzound.ListAlbum.presentation.ListFavouriteScreen
import com.github.diego22rct.appsuperzound.common.Screens
import com.github.diego22rct.appsuperzound.home.presentation.HomeScreen
import com.github.diego22rct.appsuperzound.ui.theme.AppSuperZoundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppSuperZoundTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomAppBar(
                            actions = {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    IconButton(onClick = { navController.navigate(Screens.HomeScreen.route) }) {
                                        Icon(
                                            Icons.Filled.Home,
                                            contentDescription = "Home"
                                        )
                                    }
                                    IconButton(onClick = { navController.navigate(Screens.ListAlbumScreen.route) }) {
                                        Icon(
                                            Icons.Filled.List,
                                            contentDescription = "List Album"
                                        )
                                    }
                                    IconButton(onClick = { navController.navigate(Screens.ListFavouriteAlbumScreen.route) }) {
                                        Icon(
                                            Icons.Filled.Favorite,
                                            contentDescription = "List Favorite Album"
                                        )
                                    }
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screens.HomeScreen.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = Screens.HomeScreen.route) {
                            HomeScreen()
                        }
                        composable(route = Screens.ListAlbumScreen.route) {
                            ListAlbumScreen()
                        }
                        composable(route = Screens.ListFavouriteAlbumScreen.route) {
                            ListFavouriteScreen()
                        }
                    }
                }
            }
        }
    }
}