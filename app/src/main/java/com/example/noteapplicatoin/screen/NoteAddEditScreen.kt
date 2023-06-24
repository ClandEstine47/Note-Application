package com.example.noteapplicatoin.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.noteapplicatoin.components.InputTextField
import com.example.noteapplicatoin.model.Note

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteAddEditScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit
) {

    val titleState = remember {
        mutableStateOf("")
    }

    val contentState = remember {
        mutableStateOf("")
    }

    val titleHintState = remember {
        mutableStateOf(true)
    }

    val contentHintState = remember {
        mutableStateOf(true)
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
    val contextForToast = LocalContext.current.applicationContext
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            modifier = Modifier
                .size(150.dp)
                .padding(all = 40.dp)
                .align(alignment = Alignment.BottomEnd)
                .clip(RoundedCornerShape(50.dp)),
            onClick = {
                Toast.makeText(contextForToast, "Save", Toast.LENGTH_SHORT)
                    .show()

                if (titleState.value.isNotEmpty() && contentState.value.isNotEmpty()) {
                    onAddNote(
                        Note(
                            title = titleState.value,
                            description = contentState.value
                        )
                    )
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