package com.trekkstay.hotel.feature.hotel.presentation.fragments

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@Composable
fun InfoTextField(
    label: String,
    text: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    icon: ImageVector,
    type: String = "text",
    view: String = "hotel",
    minLine: Int = 1,
    maxLine: Int = 1,
) {
    var textFieldValue by remember { mutableStateOf(text) }
    var boxColor = (if (view == "hotel") TrekkStayBlue else if (view == "customer") TrekkStayCyan else Color.White)
    OutlinedTextField(
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onValueChange(it)
        },
        label = {
            Text(
                label,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Medium,
                color = Color.Black.copy(0.6f)
            )
        },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = boxColor
            )
        },
        textStyle = TextStyle(
            fontFamily = PoppinsFontFamily,
            fontSize = 14.sp
        ),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = boxColor,
            unfocusedBorderColor = boxColor,
            cursorColor = boxColor,
        ),
        minLines = minLine,
        maxLines = maxLine,
        keyboardOptions = if (type == "number") KeyboardOptions.Default.copy(keyboardType=KeyboardType.Number) else KeyboardOptions.Default
    )
}