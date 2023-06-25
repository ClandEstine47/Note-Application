package com.example.noteapplicatoin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteapplicatoin.screen.NoteAddEditScreen
import com.example.noteapplicatoin.screen.NoteHomeScreen
import com.example.noteapplicatoin.screen.NoteHomeScreenViewModel
import com.example.noteapplicatoin.screen.NoteScreens
import com.example.noteapplicatoin.ui.theme.NoteApplicatoinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteApplicatoinTheme {
                val noteViewModel: NoteHomeScreenViewModel = viewModel()
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = NoteScreens.NoteHomeScreen.name
                ) {
                    composable(NoteScreens.NoteHomeScreen.name) {
                        NoteHomeScreen(
                            navController,
                            noteViewModel
                        )
                    }

                    composable(NoteScreens.NoteAddEditScreen.name) {
                        NoteAddEditScreen(
                            navController,
                            noteViewModel,
                            null
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
                            noteViewModel,
                            backStackEntry.arguments?.getString("id")
                        )
                    }
                }
            }
        }
    }
}