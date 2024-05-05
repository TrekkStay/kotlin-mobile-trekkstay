package com.trekkstay.hotel.feature.hotel.presentation.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hotel.R
import com.trekkstay.hotel.feature.hotel.presentation.fragments.InfoTextField
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelDetailAction
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelState
import com.trekkstay.hotel.feature.hotel.presentation.states.review.ReviewList
import com.trekkstay.hotel.feature.hotel.presentation.states.review.ReviewState
import com.trekkstay.hotel.feature.hotel.presentation.states.review.ReviewViewModel
import com.trekkstay.hotel.feature.shared.Utils.labelizeRating
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@Composable
fun DetailReviewListScreen(navController: NavController, reviewViewModel: ReviewViewModel, id: String) {
    val hotelName = "Estabeez Hotel"
    val reviewNum = 1863
    val ratingPoint = 4.8

    LaunchedEffect(Unit) {
        val action = ReviewList(id)
        reviewViewModel.processAction(action)
    }

    val reviewState by reviewViewModel.state.observeAsState()
    when (reviewState) {
        is ReviewState.SuccessReviewList ->{
            val reviewList = (reviewState as ReviewState.SuccessReviewList).reviewList.reviewList
            println(reviewList)
        }

        is ReviewState.InvalidReviewList -> {

        }

        is ReviewState.ReviewListCalling -> {

        }
         else -> {
             println("calling review list")
         }
    }

    Column(
        modifier = Modifier
            .padding(bottom = 70.dp)
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
                text = "Reviews",
                fontSize = 20.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
            )
        }
        HorizontalDivider(color = Color(0xFFE4E4E4), thickness = 3.dp)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium
                    )
                ) {
                    append("Let's see what other people have to say about ")
                }
                withStyle(
                    style = SpanStyle(
                        color = TrekkStayCyan,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append("$hotelName")
                }
            },
            textAlign = TextAlign.Center,
            fontFamily = PoppinsFontFamily,
            fontSize = 15.sp
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 25.dp)
        ) {
            Box {
                Icon(
                    ImageVector.vectorResource(id = R.drawable.cmt_ico),
                    contentDescription = null,
                    tint = TrekkStayCyan,
                    modifier = Modifier.size(60.dp)
                )
                Text(
                    text = "$ratingPoint",
                    color = Color.White,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = -5.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Excellent",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
                Text(
                    text = "$reviewNum reviews",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray,
                    fontSize = 15.sp
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .background(Color(0xFFE4E4E4))
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            DetailReviewCard()
            DetailReviewCard()
            DetailReviewCard()
            DetailReviewCard()
            DetailReviewCard()
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@Composable
fun DetailReviewCard() {
    val reviewTitle = "Very satisfied with the service experience at the hotel"
    val reviewDate = "20 Feb 2023"
    val ratingPoint = 4
    val reviewContent = "I had a great experience with the staff. The hotel was very clean and comfortable. I would recommend it to others. We rented this hotel for a combined vacation and wedding photo shoot"
    val reviewerName = "Bao Pham"
    val reviewerTravelerType = "Solo Traveler"
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, shape = RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(15.dp)
    ) {
        Text(
            reviewTitle,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = TrekkStayCyan,
            fontSize = 18.sp
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                reviewDate,
                color = Color.Gray,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp
            )
            Text(
                "${labelizeRating(ratingPoint)} $ratingPoint",
                color = TrekkStayCyan,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp
            )
        }
        HorizontalDivider(color = Color(0xFFE4E4E4), thickness = 1.5.dp)
        Text(
            reviewContent,
            textAlign = TextAlign.Justify,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
        )
        HorizontalDivider(color = Color(0xFFE4E4E4), thickness = 1.5.dp)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                reviewerName,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = Color.Gray
            )
            Text(
                reviewerTravelerType,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = Color.Gray
            )
        }
    }
}
