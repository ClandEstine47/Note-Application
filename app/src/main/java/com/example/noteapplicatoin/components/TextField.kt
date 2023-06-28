package com.example.noteapplicatoin.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteapplicatoin.ui.theme.MontserratFamily

@Composable
fun InputTextField(
    text: String,
    hint: String,
    fontSize: Int,
    fontWeight: FontWeight,
    isHintVisibile: Boolean,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean,
    onValChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                onValChange(it)
            },
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground)
        )
        if (isHintVisibile) {
            Text(
                text = hint,
                style = textStyle,
                color = Color.Gray,
                fontSize = fontSize.sp,
                fontFamily = MontserratFamily,
                fontWeight = fontWeight
            )
        }
    }
}
