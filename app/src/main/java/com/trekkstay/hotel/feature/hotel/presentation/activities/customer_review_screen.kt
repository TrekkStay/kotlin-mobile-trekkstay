package com.trekkstay.hotel.feature.hotel.presentation.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hotel.R
import com.trekkstay.hotel.feature.hotel.presentation.fragments.InfoTextField
import com.trekkstay.hotel.feature.shared.Utils.labelizeRating
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@Composable
fun CustomerReviewScreen(navController: NavController) {
    var ratingPoint by remember { mutableIntStateOf(0) }
    val hotelName = "Estabeez Hotel"
    var travellerType by remember { mutableStateOf("") }
    var reviewTitle by remember { mutableStateOf(TextFieldValue("")) }
    var reviewContent by remember { mutableStateOf(TextFieldValue("")) }
    Column(
        modifier = Modifier
            .padding(bottom = 80.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            IconButton(onClick = {
                navController.navigate("hotel_profile")
            }) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
            }
            Text(
                text = "Rate and review",
                fontSize = 20.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
            )
        }
        HorizontalDivider(color = Color(0xFFE4E4E4), thickness = 3.dp)
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .weight(1f)
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(2.dp, shape = RoundedCornerShape(10.dp))
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                    .padding(15.dp)
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Gray,
                                fontWeight = FontWeight.Medium
                            )
                        ) {
                            append("How was your experience with ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = TrekkStayCyan,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append("$hotelName")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.Gray,
                                fontWeight = FontWeight.Medium
                            )
                        ) {
                            append("?")
                        }
                    },
                    textAlign = TextAlign.Center,
                    fontFamily = PoppinsFontFamily,
                    fontSize = 20.sp
                )
                RatingRow(ratingPoint) {
                    ratingPoint = it
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(2.dp, shape = RoundedCornerShape(10.dp))
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                    .padding(15.dp)
            ) {
                Text(
                    "Review",
                    textAlign = TextAlign.Center,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
                TravellerTypeBox(
                    travellerType
                ) {
                    travellerType = it
                }
                InfoTextField(
                    label = "Title",
                    text = reviewTitle,
                    onValueChange = { reviewTitle = it },
                    icon = Icons.Default.Create,
                    view = "customer",
                )
                InfoTextField(
                    label = "Review Summary",
                    text = reviewContent,
                    onValueChange = { reviewContent = it },
                    icon = ImageVector.vectorResource(id = R.drawable.note_ico),
                    view = "customer",
                    maxLine = 4
                )
            }
        }
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = TrekkStayCyan,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(horizontal = 100.dp, vertical = 15.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(5.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Submit Review",
                fontSize = 18.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun TravellerTypeBox(
    travellerType: String,
    typeChange: (String) -> Unit
) {
    var showTypeSelector by remember { mutableStateOf(false) }
    val types = listOf(
        "Solo",
        "Couple",
        "Business",
        "Family with young children",
        "Family with older children",
        "Group"
    )
    OutlinedTextField(
        value = travellerType,
        readOnly = true,
        enabled = false,
        onValueChange = {
        },
        label = {
            Text(
                "Type of traveller",
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Medium,
                color = Color.Black.copy(0.6f)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = TrekkStayCyan
            )
        },
        trailingIcon = {
            if (travellerType.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    tint = TrekkStayCyan,
                    modifier = Modifier.clickable {
                        typeChange("")
                    }
                )
            }
        },
        textStyle = TextStyle(
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Color.Black
        ),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            disabledBorderColor = TrekkStayCyan,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                showTypeSelector = true
            }
    )
    if (showTypeSelector) {
        AlertDialog(
            title = {
                Text(
                    text = "Choose type of traveller",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            },
            text = {
                Column {
                    types.forEach { type ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    if (travellerType == type) {
                                        typeChange("")
                                    } else {
                                        typeChange(type)
                                        showTypeSelector = false
                                    }
                                }
                        ) {
                            RadioButton(
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = TrekkStayCyan,
                                    unselectedColor = TrekkStayCyan
                                ),
                                selected = travellerType == type,
                                onClick = {
                                    if (travellerType == type) {
                                        typeChange("")
                                    } else {
                                        typeChange(type)
                                        showTypeSelector = false
                                    }
                                }
                            )
                            Text(
                                text = type,
                                fontFamily = PoppinsFontFamily,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }, onDismissRequest = { showTypeSelector = false }, confirmButton = { })
    }
}

@Composable
private fun RatingRow(
    selectedStars: Int,
    onStarSelected: (Int) -> Unit
) {
    var ratingText by remember { mutableStateOf("") }
    Column (
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 35.dp)
        ) {
            repeat(5) {
                val isStarSelected = it < selectedStars
                Icon(
                    Icons.Filled.Star,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clickable {
                            onStarSelected(it + 1)
                            ratingText = labelizeRating(it + 1)
                        },
                    tint = if (isStarSelected) Color(0xFFFFC107) else Color.Gray
                )
            }
        }
        if (ratingText.isNotEmpty()) {
            Text(
                text = ratingText,
                textAlign = TextAlign.Center,
                fontFamily = PoppinsFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = TrekkStayCyan,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp)
            ) {
                Text(
                    "Terrible",
                    fontFamily = PoppinsFontFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
                Text(
                    "Excellent",
                    fontFamily = PoppinsFontFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun ReviewRatingPreview() {
    CustomerReviewScreen(rememberNavController())
}