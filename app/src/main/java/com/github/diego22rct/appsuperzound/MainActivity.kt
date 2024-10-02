package com.github.diego22rct.appsuperzound

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.diego22rct.appsuperzound.common.Constants
import com.github.diego22rct.appsuperzound.list_album.presentation.ListAlbumScreen
import com.github.diego22rct.appsuperzound.list_album.presentation.ListFavouriteScreen
import com.github.diego22rct.appsuperzound.common.Screens
import com.github.diego22rct.appsuperzound.home.presentation.HomeScreen
import com.github.diego22rct.appsuperzound.list_album.data.remote.AlbumService
import com.github.diego22rct.appsuperzound.list_album.data.repository.AlbumRepository
import com.github.diego22rct.appsuperzound.list_album.presentation.ListAlbumViewModel
import com.github.diego22rct.appsuperzound.ui.theme.AppSuperZoundTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    private val listAlbumService: AlbumService = Retrofit
    .Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(AlbumService::class.java)
    private val repositoryListAlbum = AlbumRepository(listAlbumService)
    private val listAlbumViewModel = ListAlbumViewModel(repositoryListAlbum)
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
                                    modifier = Modifier.fillMaxHeight().fillMaxWidth().size(12.dp),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Box {
                                        IconButton(onClick = { navController.navigate(Screens.ListAlbumScreen.route) }, modifier = Modifier.padding(24.dp)) {
                                            Icon(
                                                Icons.AutoMirrored.Filled.List,
                                                contentDescription = "List Album"
                                            )
                                        }
                                    }
                                    Box {
                                        IconButton(
                                            onClick = { navController.navigate(Screens.HomeScreen.route) },
                                            modifier = Modifier.padding(24.dp)
                                        ) {
                                            Icon(
                                                Icons.Filled.Home,
                                                contentDescription = "Home"
                                            )
                                        }
                                    }
                                    Box {
                                        IconButton(onClick = { navController.navigate(Screens.ListFavouriteAlbumScreen.route) }, modifier = Modifier.padding(24.dp)) {
                                            Icon(
                                                Icons.Filled.Favorite,
                                                contentDescription = "List Favorite Album"
                                            )
                                        }
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
                            ListAlbumScreen(listAlbumViewModel)
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