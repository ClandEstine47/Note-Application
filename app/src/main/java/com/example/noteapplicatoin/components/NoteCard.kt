package com.example.noteapplicatoin.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoteCard(
    title: String = "",
    description: String = "",
    onDeleteClick: (Boolean) -> Unit,
    onCardClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onCardClick()
            }
            .size(150.dp)
            .clip(RoundedCornerShape(10.dp))
            .padding(5.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        border = BorderStroke(width = 1.dp, Color.Gray),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            ) {
                Text(
                    text = "$title",
                    fontSize = 30.sp
                )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )
                Text(text = "$description")
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
//                val contextForToast = LocalContext.current.applicationContext
                Icon(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(10.dp)
                        .clickable {
//                            Toast
//                                .makeText(contextForToast, "Delete Note", Toast.LENGTH_SHORT)
//                                .show()
                                   onDeleteClick(true)
                        },
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete Note",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}