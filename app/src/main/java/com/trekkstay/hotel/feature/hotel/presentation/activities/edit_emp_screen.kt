package com.trekkstay.hotel.feature.hotel.presentation.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
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
import com.trekkstay.hotel.feature.authenticate.presentation.states.EmpAuthState
import com.trekkstay.hotel.feature.authenticate.presentation.states.EmpAuthViewModel
import com.trekkstay.hotel.feature.authenticate.presentation.states.EmpCreateAction
import com.trekkstay.hotel.feature.hotel.presentation.fragments.InfoTextField
import com.trekkstay.hotel.feature.shared.TextDialog
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue

@Composable
fun EditEmpScreen(empAuthViewModel: EmpAuthViewModel, navController: NavHostController) {
    var empFullName by remember { mutableStateOf(TextFieldValue()) }
    var empEmail by remember { mutableStateOf(TextFieldValue()) }
    var empPhone by remember { mutableStateOf(TextFieldValue()) }
    val contractList = arrayOf("Full-time", "Part-time", "Internship")
    var selectedItem by remember { mutableStateOf(contractList.first()) }
    var empBaseSalary by remember { mutableStateOf(TextFieldValue()) }
    fun mapSelectionToApiValue(selectedItem: String): String {
        return when (selectedItem.lowercase()) {
            "full-time" -> "FULL_TIME"
            "part-time" -> "PART_TIME"
            "internship" -> "INTERNSHIP"
            else -> ""
        }
    }

    var loadingCreate by remember { mutableStateOf(false) }
    val authState by empAuthViewModel.authState.observeAsState()
    var showDialog by remember { mutableStateOf(true) }
    if (showDialog) {
        when (authState) {
            is EmpAuthState.SuccessEmpCreate -> {
                loadingCreate = false
                showDialog = true
                TextDialog(
                    title = "Registration Successful",
                    msg = "You have successfully registered.",
                    type = "success",
                    onDismiss = { showDialog = false },
                )
                navController.navigate("hotel_emp_list")
            }

            is EmpAuthState.InvalidEmpCreate -> {
                loadingCreate = false
                showDialog = true
                TextDialog(
                    title = "Employee Creation Failed",
                    msg = "Employee already exists. Please try again with another email or phone.",
                    onDismiss = { showDialog = false },
                )
            }

            is EmpAuthState.EmpCreateCalling -> {
                loadingCreate = true
            }
            else -> {}
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

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = 75.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(25.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navController.navigate("hotel_emp_list")
                    }) {
                        Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
                    }
                    Text(
                        text = "Create employee",
                        fontSize = 20.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(color = Color(0xFFE4E4E4), thickness = 3.dp)
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(horizontal = 25.dp, vertical = 20.dp)
                ) {
                    InfoTextField(
                        label = "Full Name",
                        text = empFullName,
                        onValueChange = { empFullName = it },
                        icon = Icons.Default.AccountBox,
                    )
                    InfoTextField(
                        label = "Email",
                        text = empEmail,
                        onValueChange = { empEmail = it },
                        icon = Icons.Default.Email,
                    )
                    InfoTextField(
                        label = "Phone",
                        text = empPhone,
                        onValueChange = { empPhone = it },
                        icon = Icons.Default.Phone,
                        type = "number"
                    )
                    InfoTextField(
                        label = "Base Salary",
                        text = empBaseSalary,
                        onValueChange = { empBaseSalary = it },
                        icon = ImageVector.vectorResource(R.drawable.money_circle_ico),
                        type = "number"
                    )
                    DropDownMenu(
                        title = "Contract",
                        itemList = contractList,
                        onItemSelected = {
                            selectedItem = it
                        },
                        leadingIcon = ImageVector.vectorResource(R.drawable.contract_ico)
                    )
                }
            }
            Button(
                onClick = {
                    if (empFullName.text.isEmpty() || empEmail.text.isEmpty() || empPhone.text.isEmpty() || empBaseSalary.text.isEmpty() || selectedItem.isEmpty()) {
                        showDialog = true
                        dialogTitle = "Empty Fields"
                        dialogMessage =
                            "Please input all the required information before creating your hotel"
                        showValidateDialog = true
                    } else {
                        loadingCreate = true
                        showDialog = true
                        val action = EmpCreateAction(
                            empFullName.text,
                            empEmail.text,
                            empPhone.text,
                            mapSelectionToApiValue(selectedItem),
                            empBaseSalary.text.toInt(),

                            )
                        empAuthViewModel.processAction(action)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = TrekkStayBlue,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(horizontal = 100.dp, vertical = 15.dp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(5.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Save Employee",
                    fontSize = 18.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DropDownMenu(
    title: String,
    itemList: Array<String>,
    onItemSelected: (String) -> Unit,
    leadingIcon: ImageVector? = null
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(title) }

    ExposedDropdownMenuBox(
        modifier = Modifier
            .fillMaxWidth()
            .size(60.dp),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .size(60.dp)
                .background(Color.Transparent, shape = RoundedCornerShape(12.dp))
                .border(1.dp, TrekkStayBlue, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 15.dp)
                .menuAnchor()
        ) {
            if (leadingIcon != null) {
                Icon(leadingIcon, contentDescription = null, tint = TrekkStayBlue)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                selectedText,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color.Black.copy(0.6f),
                modifier = Modifier.weight(1f)
            )
            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = TrekkStayBlue,
                modifier = Modifier.size(30.dp)
            )
        }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            itemList.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item, fontFamily = PoppinsFontFamily) },
                    onClick = {
                        selectedText = item
                        expanded = false
                        onItemSelected(item)
                    }
                )
            }
        }
    }
}