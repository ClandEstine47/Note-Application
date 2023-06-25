package com.example.noteapplicatoin.screen

enum class NoteScreens {
    NoteHomeScreen,
    NoteAddEditScreen;

    companion object {
        fun fromRoute(route: String?): NoteScreens =
            when (route?.substringBefore("/")) {
                NoteHomeScreen.name -> NoteHomeScreen
                NoteAddEditScreen.name -> NoteAddEditScreen
                null -> NoteHomeScreen
                else -> throw IllegalArgumentException("Route $route is not recoginzed.")
            }
    }

}