package com.trekkstay.hotel.feature.hotel.presentation.activities

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trekkstay.hotel.feature.hotel.presentation.fragments.DateRangeSelector
import com.trekkstay.hotel.feature.hotel.presentation.fragments.DestinationSearchBar
import com.trekkstay.hotel.feature.hotel.presentation.fragments.CustomerRoomOptSelector
import com.trekkstay.hotel.feature.hotel.presentation.states.search.SearchViewModel
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchEngineScreen(searchViewModel: SearchViewModel,navController: NavHostController) {
    Scaffold(
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "backFromSearch")
                }
                Text(text = "Search Hotel",fontFamily = PoppinsFontFamily, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .padding(20.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFE4E4E4).copy(0.5f))
                    .padding(20.dp)
            ) {
                DestinationSearchBar()
                DateRangeSelector(type = "search")
                CustomerRoomOptSelector()
                Row {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFF238C98).copy(0.24f))
                            .size(240.dp, 30.dp)
                    ) {
                        Text(
                            text = "Search",
                            fontWeight = FontWeight.Bold,
                            fontFamily = PoppinsFontFamily,
                            color = Color(0xFF238C98).copy(0.9f),
                            fontSize = 16.sp,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}