package com.trekkstay.hotel.feature.hotel.presentation.activities

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hotel.R
import com.trekkstay.hotel.feature.hotel.domain.entities.Review
import com.trekkstay.hotel.feature.hotel.presentation.states.review.ReviewList
import com.trekkstay.hotel.feature.hotel.presentation.states.review.ReviewState
import com.trekkstay.hotel.feature.hotel.presentation.states.review.ReviewViewModel
import com.trekkstay.hotel.feature.shared.Utils.labelizeRating
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailReviewListScreen(
    navController: NavController,
    reviewViewModel: ReviewViewModel,
    id: String,
    hotelName: String,
    reviewNum: Int,
    ratingPoint: Double
) {
    var reviewList by remember { mutableStateOf(listOf<Review>()) }

    LaunchedEffect(Unit) {
        val action = ReviewList(id)
        reviewViewModel.processAction(action)
    }

    val reviewState by reviewViewModel.state.observeAsState()
    when (reviewState) {
        is ReviewState.SuccessReviewList -> {
            reviewList = (reviewState as ReviewState.SuccessReviewList).reviewList.reviewList
        }

        is ReviewState.InvalidReviewList -> {}

        is ReviewState.ReviewListCalling -> {}

        else -> {}
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
                navController.popBackStack()
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Let's see what other people have to say about",
                textAlign = TextAlign.Center,
                fontFamily = PoppinsFontFamily,
                fontSize = 15.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
            Text(
                hotelName,
                textAlign = TextAlign.Center,
                fontFamily = PoppinsFontFamily,
                fontSize = 18.sp,
                color = TrekkStayCyan,
                fontWeight = FontWeight.SemiBold
            )
        }
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
                    "$ratingPoint",
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
                    text = "${labelizeRating(ratingPoint)}",
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
        LazyColumn(
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .background(Color(0xFFE4E4E4))
                .fillMaxSize()
        ) {
            items(reviewList) { review ->
                DetailReviewCard(review = review)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailReviewCard(review: Review) {
    val originalDateTime =
        LocalDateTime.parse(review.createdAt, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    val formattedDate = originalDateTime.format(DateTimeFormatter.ofPattern("dd, MMM, yyyy"))
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, shape = RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(15.dp)
    ) {
        Text(
            review.title,
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
                formattedDate,
                color = Color.Gray,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp
            )
            Text(
                "${labelizeRating(review.point)} ${review.point}",
                color = TrekkStayCyan,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp
            )
        }
        HorizontalDivider(color = Color(0xFFE4E4E4), thickness = 1.5.dp)
        Text(
            review.summary,
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
                review.user.fullName,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = Color.Gray
            )
            Text(
                review.typeOfTraveler,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = Color.Gray
            )
        }
    }
}
