package com.trekkstay.hotel.feature.hotel.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.hotel.R
import com.trekkstay.hotel.feature.hotel.domain.entities.Room
import com.trekkstay.hotel.feature.shared.Utils.formatPrice
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayCyan

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RoomDetailCard(
    room: Room
) {
    val roomImgList = if(room.image.media.isEmpty()) {
        arrayOf(
            "https://www.usatoday.com/gcdn/-mm-/05b227ad5b8ad4e9dcb53af4f31d7fbdb7fa901b/c=0-64-2119-1259/local/-/media/USATODAY/USATODAY/2014/08/13/1407953244000-177513283.jpg?width=1320&height=746&fit=crop&format=pjpg&auto=webp",
        )
    }
    else{
        room.image.media.toTypedArray()
    }
    var expandedDesc by remember { mutableStateOf(false) }
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
        ) {
            AsyncImage(
                model = roomImgList.first(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(135.dp)
            )
        }
        Text(
            text = room.type,
            fontSize = 15.sp,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Medium,
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
                value = "${room.facilities.sleepRoom.adults}"
            )
            RoomInfoRow(
                icon = ImageVector.vectorResource(R.drawable.child_ico),
                label = "Children",
                value = "${room.facilities.sleepRoom.children}"
            )
            RoomInfoRow(
                icon = ImageVector.vectorResource(R.drawable.bed_ico),
                label = "Beds",
                value = "${room.facilities.numBed}"
            )
            RoomInfoRow(
                icon = ImageVector.vectorResource(R.drawable.size_ico),
                label = "m2",
                value = "${room.facilities.roomSize}"
            )
            RoomInfoRow(
                icon = ImageVector.vectorResource(R.drawable.eye_ico),
                label = "",
                value = room.facilities.view
            )
        }
        HorizontalDivider(color = Color(0xFFB8B8B9), thickness = 2.dp)
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    "Description",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                )
                ClickableText(
                    text = AnnotatedString("Show more"),
                    style = TextStyle(
                        color = TrekkStayCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = PoppinsFontFamily
                    ),
                    onClick = {
                        expandedDesc = !expandedDesc
                    }
                )
            }
            Text(
                room.description,
                textAlign = TextAlign.Justify,
                fontFamily = PoppinsFontFamily,
                fontSize = 12.sp,
                maxLines = if (expandedDesc) Int.MAX_VALUE else 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        HorizontalDivider(color = Color(0xFFB8B8B9), thickness = 2.dp)
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            Text(
                "Facilities",
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp
            )
            FlowRow(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalArrangement = Arrangement.Absolute.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                if(room.facilities.airConditioner){FacilityBullet(label = "Air Condition")}
                if(room.facilities.kitchen){FacilityBullet(label = "Kitchen")}
                if(room.facilities.television){FacilityBullet(label = "Television")}
                if(room.facilities.balcony){FacilityBullet(label = "Balcony")}
                if(room.facilities.bathTub){FacilityBullet(label = "Bathtub")}
                if(room.facilities.hairDryer){FacilityBullet(label = "Hairdryer")}
                if(room.facilities.shower){FacilityBullet(label = "Shower")}
                if(room.facilities.nonSmoking) {FacilityBullet(label = "Non Smoking")}
                if(room.facilities.slippers) {FacilityBullet(label = "Slippers")}
            }
        }
        HorizontalDivider(color = TrekkStayCyan, thickness = 2.dp)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    "$ ${formatPrice(room.originalPrice - (room.originalPrice * (room.discountRate / 100.0)))}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    fontFamily = PoppinsFontFamily,
                    color = TrekkStayCyan
                )
                Text(
                    "$ ${formatPrice(room.originalPrice.toDouble())}",
                    fontFamily = PoppinsFontFamily,
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.LineThrough
                )
            }
            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF238C98)),
                modifier = Modifier
                    .size(180.dp, 40.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(
                    ImageVector.vectorResource(R.drawable.bed_ico),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "BOOK", color = Color.White, fontWeight = FontWeight.Bold)
            }
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