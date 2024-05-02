package com.trekkstay.hotel.feature.hotel.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.hotel.R
import com.trekkstay.hotel.feature.reservation.domain.entities.Reservation
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RoomReservationCard(
    reservation: Reservation,
) {
    val roomImgList = if (reservation.room.images.media.isEmpty()) {
        arrayOf(
            "https://www.usatoday.com/gcdn/-mm-/05b227ad5b8ad4e9dcb53af4f31d7fbdb7fa901b/c=0-64-2119-1259/local/-/media/USATODAY/USATODAY/2014/08/13/1407953244000-177513283.jpg?width=1320&height=746&fit=crop&format=pjpg&auto=webp",
        )
    } else {
        reservation.room.images.media.toTypedArray()
    }
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .border(2.dp, TrekkStayCyan, shape = RoundedCornerShape(10.dp))
            .padding(15.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(135.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(10.dp))
        ) {
            HorizontalPager(state = rememberPagerState(pageCount = { roomImgList.size })) {
                Box() {
                    AsyncImage(
                        model = roomImgList[it],
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(135.dp)
                    )
                    Text(
                        "${it + 1}/${roomImgList.size}",
                        fontSize = 14.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        modifier = Modifier
                            .offset(x = -5.dp, y = -5.dp)
                            .background(TrekkStayCyan, RoundedCornerShape(15.dp))
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                            .align(Alignment.BottomEnd)
                    )
                }
            }
        }
        Text(
            text = reservation.room.type,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )
        HorizontalDivider(color = Color(0xFFB8B8B9), thickness = 2.dp)
        FlowRow(
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            RoomInfoRow(
                icon = ImageVector.vectorResource(R.drawable.people_ico),
                label = "Adults",
                value = "${reservation.guestInfo.adult}"
            )
            RoomInfoRow(
                icon = ImageVector.vectorResource(R.drawable.child_ico),
                label = "Children",
                value = "${reservation.guestInfo.children}"
            )
        }
    }
}

@Composable
private fun RoomInfoRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TrekkStayCyan
        )
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(value)
                }
                append(" $label")
            },
            fontFamily = PoppinsFontFamily,
            fontSize = 13.sp,
        )
    }
}