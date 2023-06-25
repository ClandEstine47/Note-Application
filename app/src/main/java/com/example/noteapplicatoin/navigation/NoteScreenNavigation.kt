package com.example.noteapplicatoin.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteapplicatoin.data.NotesDataSource
import com.example.noteapplicatoin.model.Note
import com.example.noteapplicatoin.screen.NoteAddEditScreen
import com.example.noteapplicatoin.screen.NoteHomeScreen
import com.example.noteapplicatoin.screen.NoteScreens

@Composable
fun NoteScreenNavigation() {
    val navController = rememberNavController()
    val notesList = remember {
        mutableStateListOf<Note>()
    }
    NavHost(
        navController = navController,
        startDestination = NoteScreens.NoteHomeScreen.name
    ) {
        composable(NoteScreens.NoteHomeScreen.name) {
            NoteHomeScreen(
                navController,
//                notes = NotesDataSource().loadNotes(),
                notes = notesList,
                onRemoveNote = {
                    notesList.remove(it)
                }
            )
        }

        composable(NoteScreens.NoteAddEditScreen.name) {
            NoteAddEditScreen(
                navController,
                notes = notesList,
                null,
                onRemoveNote = {
                    notesList.remove(it)
                },
                onAddNote = {
                    notesList.add(it)
                }
            )
        }

        composable(
            NoteScreens.NoteAddEditScreen.name + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            NoteAddEditScreen(
                navController = navController,
                notes = notesList,
                backStackEntry.arguments?.getString("id"),
                onRemoveNote = {
                    notesList.remove(it)
                },
                onAddNote = {
                    notesList.add(it)
                }
            )
        }
    }
}