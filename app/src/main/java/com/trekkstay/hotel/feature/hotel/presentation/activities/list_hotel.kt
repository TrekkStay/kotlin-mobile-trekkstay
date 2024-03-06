package com.trekkstay.hotel.feature.hotel.presentation.activities


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trekkstay.hotel.feature.main.presentation.fragments.BotNavBar
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListOfHotel() {
    Scaffold(
    ) { _ ->
        Column(
            modifier = Modifier
                .padding(top = 15.dp, bottom = 70.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .padding(15.dp)
                    .height(60.dp),

                ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        modifier = Modifier.border(2.dp, Color(0xFFB8B8B9).copy(alpha = 0.42f)),
                        onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null,
                            tint = TrekkStayCyan
                        )
                    };
                    Box(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .height(200.dp)
                            .background(
                                color = Color(0xFFC4C4C4).copy(alpha = 0.65f),
                                shape = RoundedCornerShape(15.dp)
                            )
                    ) {
                        Column(
                            verticalArrangement = Arrangement.SpaceAround,

                            ) {
                            Text(
                                "Ho Chi Minh city",
                                textAlign = TextAlign.Left,
                                fontSize = 16.sp,
                                fontFamily = PoppinsFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = TrekkStayCyan,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, top = 5.dp)
                            )
//
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.padding(start = 20.dp)
                            ) {
                                Text(
                                    text = "Feb 2 - Feb 9",
                                    fontSize = 16.sp,
                                    fontFamily = PoppinsFontFamily,
                                    fontWeight = FontWeight.Medium,
                                    color = TrekkStayCyan,
                                )
                                Text(
                                    text = "*",
                                    fontSize = 16.sp,
                                    fontFamily = PoppinsFontFamily,
                                    fontWeight = FontWeight.Medium,
                                    color = TrekkStayCyan,
                                    modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                                )
                                Text(
                                    text = "2 Guests",
                                    fontSize = 14.sp,
                                    fontFamily = PoppinsFontFamily,
                                    fontWeight = FontWeight.Medium,
                                    color = TrekkStayCyan,
                                )
                            }
                        }
                    }


                }
            }

            Spacer(modifier = Modifier.height(0.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .height(30.dp)
                        .width(100.dp)
                        .background(
                            color = TrekkStayCyan,
                            shape = RoundedCornerShape(15.dp)
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Filter",
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            modifier = Modifier.padding(end = 5.dp)
                        )
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(Color.White, shape = CircleShape)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(90.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "1",
                                    textAlign = TextAlign.Center,
                                    fontSize = 12.sp,
                                    fontFamily = PoppinsFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black,
                                )
                            }
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .height(30.dp)
                        .width(100.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(15.dp)

                        )
                        .border(
                            width = 2.dp,
                            color = TrekkStayCyan,
                            shape = RoundedCornerShape(15.dp)
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Sort",
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = TrekkStayCyan,
                            modifier = Modifier.padding(end = 5.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            tint = TrekkStayCyan
                        )
                    }
                }
            }
//
//            LazyColumn {
//                // Add a single item
//                item {
//                    Text(text = "First item")
//                }
//
//                // Add 5 items
//                items(5) { index ->
//                    Text(text = "Item: $index")
//                }
//
//                // Add another single item
//                item {
//                    Text(text = "Last item")
//                }
//            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyAppPreview() {
    ListOfHotel()
}