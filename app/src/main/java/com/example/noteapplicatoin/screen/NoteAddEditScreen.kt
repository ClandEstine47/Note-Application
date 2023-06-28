package com.example.noteapplicatoin.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteapplicatoin.components.InputTextField
import com.example.noteapplicatoin.model.Note
import com.example.noteapplicatoin.ui.theme.MontserratFamily

@OptIn(ExperimentalMaterial3Api::class)
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
        val contextForToast = LocalContext.current.applicationContext
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {

            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(top = 20.dp, end = 10.dp),
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(3.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    navController.popBackStack()
                                },
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Go Back"
                        )
                        Icon(
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
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
                                        Toast.makeText(contextForToast, "Note Saved", Toast.LENGTH_SHORT).show()
                                    } else {
                                        Toast.makeText(contextForToast, "Enter valid Input", Toast.LENGTH_SHORT).show()
                                    }
                                },
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Save"
                        )
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(MaterialTheme.colorScheme.background)
            )

            //  Heading field
            InputTextField(
                text = titleState.value,
                hint = "Heading",
                fontSize = 30,
                fontWeight = FontWeight.SemiBold,
                isHintVisibile = titleState.value.isEmpty(),
                singleLine = true,
                textStyle = TextStyle(
                    fontFamily = MontserratFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            ) {
                titleState.value = it
            }

            //  Description field
            InputTextField(
                text = contentState.value,
                hint = "Type here...",
                fontSize = 16,
                fontWeight = FontWeight.Normal,
                isHintVisibile = contentState.value.isEmpty(),
                textStyle = TextStyle(
                    fontFamily = MontserratFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 1.5.em,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                singleLine = false,

                ) {
                contentState.value = it
            }
        }
    }
}