package com.example.noteapplicatoin.components

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteapplicatoin.ui.theme.MontserratFamily
import com.example.noteapplicatoin.util.formatDate

@Composable
fun NoteCard(
    title: String = "",
    description: String = "",
    date: Long,
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
            .padding(bottom = 10.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        border = BorderStroke(width = 0.dp, MaterialTheme.colorScheme.background),
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
                    .padding(10.dp)
            ) {
                Text(
                    text = formatDate(date),
                    fontFamily = MontserratFamily,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic
                    )
                Spacer(
                    modifier = Modifier
                        .height(5.dp)
                )
                Text(
                    text = "$title",
                    fontSize = 30.sp,
                    fontFamily = MontserratFamily,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )
                Text(
                    text = "$description",
                    fontFamily = MontserratFamily,
                    fontWeight = FontWeight.Normal
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val contextForToast = LocalContext.current.applicationContext
                Icon(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(10.dp)
                        .clickable {
                            Toast
                                .makeText(contextForToast, "Note Deleted", Toast.LENGTH_SHORT)
                                .show()
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