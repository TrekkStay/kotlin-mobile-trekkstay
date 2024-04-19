package com.trekkstay.hotel.feature.hotel.presentation.activities

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotel.R
import com.trekkstay.hotel.feature.hotel.presentation.fragments.CustomerFilterHotel
import com.trekkstay.hotel.feature.hotel.presentation.fragments.CustomerSortHotel
import com.trekkstay.hotel.feature.hotel.presentation.fragments.HotelSearchResultCard
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@Composable
fun SearchResultScreen() {
    Column(
        modifier = Modifier
            .padding(top = 15.dp, bottom = 70.dp)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .background(
                    color = Color(0xFFC4C4C4).copy(alpha = 0.65f),
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                    tint = TrekkStayCyan
                )
            }
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
                )
                Text(
                    text = "Feb 2 - Feb 9 * 2 Guests",
                    fontSize = 16.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = TrekkStayCyan,
                )
            }
        }
        Spacer(modifier = Modifier.height(0.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            CustomerSortHotel()
            CustomerFilterHotel()
            OutlinedButton(
                contentPadding = PaddingValues(10.dp),
                border = BorderStroke(2.dp, TrekkStayCyan),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = TrekkStayCyan
                ),
                modifier = Modifier.width(90.dp),
                onClick = { /*TODO*/ }
            )
            {
                Icon(
                    ImageVector.vectorResource(R.drawable.map_ico),
                    contentDescription = null,
                    tint = TrekkStayCyan
                )
            }
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(15.dp),
        ) {
            items(5) {
                HotelSearchResultCard()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyListHotelPreview() {
    SearchResultScreen()
}