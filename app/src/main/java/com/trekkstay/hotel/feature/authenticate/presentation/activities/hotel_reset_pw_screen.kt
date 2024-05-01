package com.trekkstay.hotel.feature.authenticate.presentation.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hotel.R
import com.trekkstay.hotel.feature.authenticate.presentation.fragments.PassField
import com.trekkstay.hotel.feature.shared.TextDialog
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue

@Composable
fun HotelResetPwScreen(navController: NavHostController) {
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
            PassField(
                label = "Old Password",
                value = oldPw,
                onValueChange = { oldPw = it },
                icon = Icons.Default.Lock,
                view = "hotel"
            )
            PassField(
                label = "New Password",
                value = newPw,
                onValueChange = { newPw = it },
                icon = ImageVector.vectorResource(
                    id = R.drawable.pw_ico
                ),
                view = "hotel"
            )
            PassField(
                label = "New Confirm Password",
                value = newRePw,
                onValueChange = { newRePw = it },
                icon = ImageVector.vectorResource(
                    id = R.drawable.pw_ico
                ),
                view = "hotel"
            )
        }
        Button(
            onClick = {
                if (oldPw.text.isEmpty() || newPw.text.isEmpty() || newRePw.text.isEmpty()) {
                    dialogTitle = "Empty Fields"
                    dialogMessage = "Please input all the required information before changing password"
                    showDialog = true
                } else if (newPw.text != newRePw.text) {
                    dialogTitle = "Password Mismatch"
                    dialogMessage = "The new password and confirm password do not match"
                    showDialog = true
                } else {
                    dialogTitle = "Success"
                    dialogMessage = "Password has been changed"
                    showDialog = true
                }

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = TrekkStayBlue,
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
        TextDialog(
            title = dialogTitle,
            msg = dialogMessage,
            onDismiss = { showDialog = false },
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
private fun HotelResetPwPreview() {
    HotelResetPwScreen(rememberNavController())
}