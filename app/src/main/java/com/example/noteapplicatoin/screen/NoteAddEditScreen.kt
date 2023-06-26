package com.example.noteapplicatoin.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteapplicatoin.components.InputTextField
import com.example.noteapplicatoin.model.Note

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteAddEditScreen(
    navController: NavController,
    noteId: String? = null,
    noteViewModel: NoteHomeScreenViewModel = hiltViewModel()
) {

    val notesList = noteViewModel.noteList.collectAsState().value

    val titleState = remember {
        if (noteId == null) {
            mutableStateOf("")
        } else {
            mutableStateOf(notesList.filter {
                it.id == noteId
            }[0].title)
        }
    }

    val contentState = remember {
        if (noteId == null) {
            mutableStateOf("")
        } else {
            mutableStateOf(notesList.filter {
                it.id == noteId
            }[0].description)
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            //  Heading field
            InputTextField(
                text = titleState.value,
                hint = "Heading",
                fontSize = 30,
                isHintVisibile = !titleState.value.isNotEmpty(),
                singleLine = true
            ) {
                titleState.value = it
            }

            //  Description field
            InputTextField(
                text = contentState.value,
                hint = "Type here...",
                fontSize = 16,
                isHintVisibile = !contentState.value.isNotEmpty(),
                singleLine = false
            ) {
                contentState.value = it
            }
        }
    }
    //  Note Save Button
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            modifier = Modifier
                .size(150.dp)
                .padding(all = 40.dp)
                .align(alignment = Alignment.BottomEnd)
                .clip(RoundedCornerShape(50.dp)),
            onClick = {
//                Toast.makeText(contextForToast, "Save", Toast.LENGTH_SHORT)
//                    .show()
                if (titleState.value.isNotEmpty() && contentState.value.isNotEmpty()) {
                    noteViewModel.addNote(
                        Note(
                            title = titleState.value,
                            description = contentState.value
                        )
                    )
                    if (noteId != null) {
                        noteViewModel.removeNote(
                            notesList.filter {
                                it.id == noteId
                            }[0]
                        )
                    }
                    navController.popBackStack()
                }
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Save Note",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}