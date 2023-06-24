package com.example.noteapplicatoin.screen

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteapplicatoin.components.NoteCard
import com.example.noteapplicatoin.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteHomeScreen(
    notes: List<Note>,
    onRemoveNote: (Note) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                title = {
                    Text(
                        text = "All notes",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 60.sp
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(MaterialTheme.colorScheme.background)
            )

            Text(text = "10 notes")

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(notes) { note ->
                    NoteCard(
                        title = note.title,
                        description = note.description
                    )
                }
            }
        }
    }

    //  Note Add Button
    val contextForToast = LocalContext.current.applicationContext
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            modifier = Modifier
                .size(150.dp)
                .padding(all = 40.dp)
                .align(alignment = Alignment.BottomEnd)
                .clip(RoundedCornerShape(50.dp)),
            onClick = {
                Toast.makeText(contextForToast, "Add Note", Toast.LENGTH_SHORT)
                    .show()
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