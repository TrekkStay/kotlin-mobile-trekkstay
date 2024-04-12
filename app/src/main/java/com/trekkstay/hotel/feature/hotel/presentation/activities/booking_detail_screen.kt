package com.trekkstay.hotel.feature.hotel.presentation.activities


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.hotel.R
import com.trekkstay.hotel.feature.hotel.presentation.fragments.InfoTextField
import com.trekkstay.hotel.feature.hotel.presentation.fragments.RoomDetailCard
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily
import com.trekkstay.hotel.ui.theme.TrekkStayBlue

@Composable
fun BookingDetailScreen(navController: NavController) {

    var name by remember { mutableStateOf(TextFieldValue()) }
    var email by remember { mutableStateOf(TextFieldValue()) }
    var phone by remember { mutableStateOf(TextFieldValue()) }

    Box(
        modifier = Modifier.padding(bottom = 70.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 80.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 20.dp)
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null,
                    Modifier.clickable{
                        navController.popBackStack()
                    })
                Text(
                    text = "Booking Detail",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally  ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 5.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 20.dp)
                ) {
                    Text(
                        text = "Estabeez Hotel",
                        fontFamily = PoppinsFontFamily,
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )

                }
                Image(
                    painter = painterResource(id = R.drawable.qr_scanner),
                    modifier = Modifier
                        .size(200.dp),
                    contentDescription = "QR"
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(200.dp)
                ) {
                    AsyncImage(
                        model = "https://example.com/image.jpg",
                        contentDescription = "Translated description of what the image contains"
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .border(1.dp, TrekkStayBlue,  shape = RoundedCornerShape(30.dp) )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = "Name",
                        fontFamily = PoppinsFontFamily,
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                            .padding(start = 20.dp),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Bao Pham",
                        fontFamily = PoppinsFontFamily,
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                            .padding(start = 20.dp),
                        textAlign = TextAlign.Start
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = "Email",
                        fontFamily = PoppinsFontFamily,
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                            .padding(start = 20.dp),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "baopham@gmail.com",
                        fontFamily = PoppinsFontFamily,
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                            .padding(start = 20.dp),
                        textAlign = TextAlign.Start
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = "Phone",
                        fontFamily = PoppinsFontFamily,
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                            .padding(start = 20.dp),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "0978564322",
                        fontFamily = PoppinsFontFamily,
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                            .padding(start = 20.dp),
                        textAlign = TextAlign.Start
                    )
                }
            }


        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                /*Button(
                    onClick = {
                        // Handle button click
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF238C98)),
                    modifier = Modifier
                        .size(200.dp, 40.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Download Booking",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }*/
            }
        }

    }


}
