package com.github.diego22rct.appsuperzound.common

object Screens {
    val HomeScreen = Screen("home_screen")
    val ListAlbumScreen = Screen("list_album_screen")
    val ListFavouriteAlbumScreen = Screen("list_favourite_album_screen")

    data class Screen(val route: String)
}