package com.example.noteapplicatoin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.noteapplicatoin.data.NotesDataSource
import com.example.noteapplicatoin.navigation.NoteScreenNavigation
import com.example.noteapplicatoin.screen.NoteAddEditScreen
import com.example.noteapplicatoin.screen.NoteHomeScreen
import com.example.noteapplicatoin.ui.theme.NoteApplicatoinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteApplicatoinTheme {
//                NoteHomeScreen(
//                    notes = NotesDataSource().loadNotes(),
//                    onRemoveNote = {}
//                )
//                NoteAddEditScreen(
//                    notes = NotesDataSource().loadNotes(),
//                    onAddNote = {}
//                )
                NoteScreenNavigation()
            }
        }
    }
}
