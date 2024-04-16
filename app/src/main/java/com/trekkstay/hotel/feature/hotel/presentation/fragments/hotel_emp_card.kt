package com.trekkstay.hotel.feature.hotel.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotel.R
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue

@Composable
fun HotelEmpCard() {
    var empId = "#21127711"
    var empFullName = "Pham Hong Gia Bao"
    var empEmail = "tt26419@gmail"
    var empPhone = "+84 123 456 789"
    var empContract = "Full-time"
    var empBaseSalary = "$10,000"
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .border(2.dp, TrekkStayBlue, shape = RoundedCornerShape(10.dp))
            .padding(15.dp)
            .clickable {
                //Navigate to edit emp
            }
    ) {
        Text(
            empId,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
        EmpInfoRow(
            icon = Icons.Default.AccountBox,
            label = "Full Name",
            value = empFullName
        )
        EmpInfoRow(
            icon = Icons.Default.Email,
            label = "Email",
            value = empEmail
        )
        EmpInfoRow(
            icon = ImageVector.vectorResource(R.drawable.discount_ico),
            label = "Phone",
            value = empPhone
        )
        EmpInfoRow(
            icon = ImageVector.vectorResource(R.drawable.contract_ico),
            label = "Contract",
            value = empContract
        )
        EmpInfoRow(
            icon = ImageVector.vectorResource(R.drawable.money_circle_ico),
            label = "Base Salary",
            value = empBaseSalary
        )
    }
}

@Composable
private fun EmpInfoRow(
    icon: ImageVector,
    label: String,
    value: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TrekkStayBlue
        )
        Text(
            buildAnnotatedString {
                append("$label: ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(value.toString())
                }
            },
            fontFamily = PoppinsFontFamily,
            fontSize = 16.sp,
        )
    }
}