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
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.hotel.R
import com.trekkstay.hotel.feature.authenticate.presentation.fragments.PassField
import com.trekkstay.hotel.feature.authenticate.presentation.states.AuthState
import com.trekkstay.hotel.feature.authenticate.presentation.states.AuthViewModel
import com.trekkstay.hotel.feature.authenticate.presentation.states.ChangePasswordAction
import com.trekkstay.hotel.feature.shared.TextDialog
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@Composable
fun CustomerResetPwScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    var oldPw by remember { mutableStateOf(TextFieldValue()) }
    var newPw by remember { mutableStateOf(TextFieldValue()) }
    var newRePw by remember { mutableStateOf(TextFieldValue()) }
    var showDialog by remember { mutableStateOf(false) }
    var showValidateDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }

    val authState by authViewModel.authState.observeAsState()
    if (showDialog) {
        when (authState) {
            is AuthState.SuccessChangePassword -> {
                TextDialog(
                    title = "Password Changed",
                    msg = "You have successfully changed your password",
                    type = "success",
                    onDismiss = {
                        showDialog = false
                        navController.navigate("customer_profile") {
                            launchSingleTop = true
                        }
                    }
                )

            }
            is AuthState.InvalidChangePassword -> {
                TextDialog(
                    title = "Change Password Failed!",
                    msg = (authState as AuthState.InvalidChangePassword).message,
                    onDismiss = { showDialog = false }
                )
            }
            is AuthState.ChangePasswordCalling -> { }
            else -> {}
        }
    }

    if (showValidateDialog) {
        TextDialog(
            title = dialogTitle,
            msg = dialogMessage,
            onDismiss = { showValidateDialog = false },
        )
    }

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
                view = "customer"
            )
            PassField(
                label = "New Password",
                value = newPw,
                onValueChange = { newPw = it },
                icon = ImageVector.vectorResource(
                    id = R.drawable.pw_ico
                ),
                view = "customer"
            )
            PassField(
                label = "New Confirm Password",
                value = newRePw,
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
                    dialogTitle = "Empty Fields"
                    dialogMessage =
                        "Please input all the required information before changing password"
                    showValidateDialog = true
                } else if (newPw.text != newRePw.text) {
                    dialogTitle = "Password Mismatch"
                    dialogMessage = "The new password and confirm password do not match"
                    showValidateDialog = true
                } else {
                    showDialog = true
                    val action = ChangePasswordAction(oldPw.text, newPw.text, newRePw.text)
                    authViewModel.processAction(action)
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
}