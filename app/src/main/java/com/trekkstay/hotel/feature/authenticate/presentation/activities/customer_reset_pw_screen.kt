package com.trekkstay.hotel.feature.authenticate.presentation.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hotel.R
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@Composable
fun CustomerResetPwScreen(navController: NavHostController) {
    var oldPw by remember { mutableStateOf(TextFieldValue()) }
    var newPw by remember { mutableStateOf(TextFieldValue()) }
    var newRePw by remember { mutableStateOf(TextFieldValue()) }
    var showDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.padding(top = 25.dp, bottom = 80.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
            }
            Text(
                text = "Reset your password",
                fontSize = 20.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalDivider(color = Color(0xFFE4E4E4), thickness = 2.dp)
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 25.dp, vertical = 20.dp)
        ) {
            PasswordField(
                label = "Old Password",
                text = oldPw,
                onValueChange = { oldPw = it },
                icon = Icons.Default.Lock,
                view = "customer"
            )
            PasswordField(
                label = "New Password",
                text = newPw,
                onValueChange = { newPw = it },
                icon = ImageVector.vectorResource(
                    id = R.drawable.pw_ico
                ),
                view = "customer"
            )
            PasswordField(
                label = "New Confirm Password",
                text = newRePw,
                onValueChange = { newRePw = it },
                icon = ImageVector.vectorResource(
                    id = R.drawable.pw_ico
                ),
                view = "customer"
            )
        }
        Button(
            onClick = {
                if (oldPw.text.isEmpty() || newPw.text.isEmpty() || newRePw.text.isEmpty()) {
                    dialogTitle = "Information Missing"
                    dialogMessage = "Please input all the required information before changing password"
                    showDialog = true
                } else if (newPw.text != newRePw.text) {
                    dialogTitle = "Wrong Confirmation"
                    dialogMessage = "The new password and confirm password do not match"
                    showDialog = true
                } else {
                    dialogTitle = "Success"
                    dialogMessage = "Password has been changed"
                    showDialog = true
                }

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = TrekkStayCyan,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(horizontal = 80.dp, vertical = 15.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(5.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Change Password",
                fontSize = 18.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
    if (showDialog) {
        Dialog(
            title = dialogTitle,
            msg = dialogMessage,
            onDismiss = { showDialog = false },
        )
    }
}

@Composable
private fun PasswordField(
    label: String,
    text: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    icon: ImageVector,
    view: String = "hotel",
) {
    var textFieldValue by remember { mutableStateOf(text) }
    var boxColor = (if (view == "hotel") TrekkStayBlue else if (view == "customer") TrekkStayCyan else Color.White)
    var passwordVisible by remember { mutableStateOf(false) }
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
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                val passVisibleIco =
                    (if (passwordVisible) R.drawable.eye_off_ico else R.drawable.eye_ico)
                Icon(ImageVector.vectorResource(passVisibleIco), contentDescription = null)
            }
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
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    )
}

@Composable
fun Dialog(
    title: String = "Dialog Title",
    msg: String = "Dialog content goes here",
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                Icons.Filled.Warning,
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
        },
        title = {
            Text(
                text = title,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = msg,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        },
        iconContentColor = Color(0xFFC82222).copy(0.6f),
        containerColor = Color.White,
        confirmButton = {},
    )
}

@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
private fun CustomerResetPwPreview() {
    CustomerResetPwScreen(rememberNavController())
}