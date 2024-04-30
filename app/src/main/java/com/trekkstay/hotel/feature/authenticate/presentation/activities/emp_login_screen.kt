package com.trekkstay.hotel.feature.authenticate.presentation.activities

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.hotel.R
import com.trekkstay.hotel.core.storage.LocalStore
import com.trekkstay.hotel.feature.authenticate.presentation.states.*
import com.trekkstay.hotel.feature.shared.TextDialog
import com.trekkstay.hotel.ui.theme.TrekkStayBlue
import com.trekkstay.hotel.ui.theme.black

@Composable
fun EmpLoginScreen(viewModel: EmpAuthViewModel, navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState by viewModel.authState.observeAsState()
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(true) }
    var passwordVisible by remember { mutableStateOf(false) }

    if (showDialog) {
        when (authState) {
            is EmpAuthState.SuccessEmpLogin -> {
                showDialog = true
                LocalStore.saveKey(
                    context, "name",
                    (authState as EmpAuthState.SuccessEmpLogin).res.name
                )
                LocalStore.saveKey(
                    context, "email",
                    (authState as EmpAuthState.SuccessEmpLogin).res.email
                )
                LocalStore.saveKey(
                    context, "jwtKey",
                    (authState as EmpAuthState.SuccessEmpLogin).res.jwtToken
                )
                LocalStore.saveKey(
                    context, "hotelId",
                    (authState as EmpAuthState.SuccessEmpLogin).res.hotelId
                )
                navController.navigate("hotel_main") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }

            is EmpAuthState.InvalidEmpLogin -> {
                showDialog = true
                TextDialog(
                    title = "Login Failed",
                    msg = "Wrong email or password. Please try again!!!",
                    onDismiss = { showDialog = false }
                )
            }

            is EmpAuthState.EmpLoginCalling -> {
                // You can show a progress dialog or a loading indicator here
            }

            else -> {
                // Handle other states
            }
        }
    }

    var showValidateDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }

    if (showValidateDialog) {
        TextDialog(
            title = dialogTitle,
            msg = dialogMessage,
            onDismiss = { showValidateDialog = false },
        )
    }

    Surface(color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            IconButton(
                onClick = { navController.navigate("start-up") },
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Welcome ",
                    color = black,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Back",
                    color = Color(0xFF007EF2),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "We missed you! Login to continue your journey with us.",
                color = black,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(vertical = 8.dp),
            )
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email Address") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = null
                    )
                },
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = black,
                    unfocusedBorderColor = black,
                    cursorColor = black,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        val passVisibleIco =
                            (if (passwordVisible) R.drawable.eye_off_ico else R.drawable.eye_ico)
                        Icon(ImageVector.vectorResource(passVisibleIco), contentDescription = null)
                    }
                },
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = black,
                    unfocusedBorderColor = black,
                    cursorColor = black,
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Forgot password?",
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Medium,
                color = TrekkStayBlue,
                fontSize = 15.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.fillMaxWidth().clickable { /*TODO*/ }
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    if (email.isEmpty() || password.isEmpty()) {
                        dialogTitle = "Empty Fields"
                        dialogMessage = "Please fill all fields before logging in"
                        showValidateDialog = true
                    } else {
                        showDialog = true
                        val action = EmpLoginAction(email, password)
                        viewModel.processAction(action)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007EF2)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Login", color = Color.White, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(0.25f, fill = false),
                    thickness = 1.dp,
                    color = black,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Or continue with",
                    color = black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.width(8.dp))
                HorizontalDivider(
                    modifier = Modifier.weight(0.25f, fill = false),
                    thickness = 1.dp,
                    color = Color(0xFF333333)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

            }
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Doesn't have an account? ",
                    color = black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
                ClickableText(
                    text = AnnotatedString("Register"),
                    style = TextStyle(
                        color = TrekkStayBlue,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        textDecoration = TextDecoration.Underline
                    ),
                    onClick = {
                        navController.navigate("emp_register")
                    }
                )
            }


        }
    }
}
