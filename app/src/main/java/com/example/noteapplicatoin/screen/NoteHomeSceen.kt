package com.example.noteapplicatoin.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteapplicatoin.components.NoteCard
import com.example.noteapplicatoin.ui.theme.MontserratFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteHomeScreen(
    navController: NavController,
    noteViewModel: NoteHomeScreenViewModel = hiltViewModel()
) {

    val notesList = noteViewModel.noteList.collectAsState().value

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                title = {
                    Text(
                        modifier = Modifier,
                        text = "All notes",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 50.sp,
                        fontFamily = MontserratFamily,
                        fontWeight = FontWeight.Normal
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(MaterialTheme.colorScheme.background)
            )

            Text(
                modifier = Modifier,
                text = if (notesList.size > 1) "${notesList.size} notes" else "${notesList.size} note",
//                fontSize = 60.sp,
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(notesList) { note ->
                    NoteCard(
                        title = note.title,
                        description = note.description,
                        date = note.entryDate.time,
                        onDeleteClick = {
                            if (it) {
                                noteViewModel.removeNote(note)
                            }
                        }
                    ) {
                        navController.navigate(
                            route = NoteScreens.NoteAddEditScreen.name + "/${note.id}"
                        )
                    }
                }
            }
        }
    }

    //  Note Add Button
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            modifier = Modifier
                .size(150.dp)
                .padding(all = 40.dp)
                .align(alignment = Alignment.BottomEnd)
                .clip(RoundedCornerShape(50.dp)),
            onClick = {
                navController.navigate(
                    route = NoteScreens.NoteAddEditScreen.name
                )
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Note",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}