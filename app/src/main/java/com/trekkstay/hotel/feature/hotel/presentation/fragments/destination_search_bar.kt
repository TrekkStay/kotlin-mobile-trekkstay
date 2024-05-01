package com.trekkstay.hotel.feature.hotel.presentation.fragments

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trekkstay.hotel.feature.hotel.domain.entities.Destination
import com.trekkstay.hotel.feature.hotel.presentation.states.search.SearchState
import com.trekkstay.hotel.feature.hotel.presentation.states.search.SearchViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.search.ViewDestinationAction
import com.trekkstay.hotel.ui.theme.PoppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationSearchBar(
    searchViewModel: SearchViewModel,
    onDestinationSelected: (Destination) -> Unit
) {

    var destinationArr by remember {
        mutableStateOf(listOf<Destination>())
    }
    var selectedDestination by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val keyboard = LocalSoftwareKeyboardController.current
    val searchState by searchViewModel.state.observeAsState()
    when (searchState) {
        is SearchState.SuccessViewDestination -> {
            destinationArr =
                (searchState as SearchState.SuccessViewDestination).destinations.destinationList
        }

        is SearchState.InvalidViewDestination -> {
        }

        is SearchState.ViewDestinationCalling -> {
        }

        else -> {}
    }

    LaunchedEffect(Unit) {
        val action = ViewDestinationAction
        searchViewModel.processAction(action)
    }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
        TextField(
            readOnly = true,
            value = selectedDestination, onValueChange = {
                selectedDestination = it
            },
            trailingIcon = {
                if (selectedDestination != "") {
                    IconButton(
                        onClick = { selectedDestination = "" }) {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = null,
                        )
                    }
                } else {
                    Icon(Icons.Default.Search, contentDescription = null)
                }
            },
            shape = RoundedCornerShape(15.dp),
            placeholder = {
                Text(
                    text = "Search for destination",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedTrailingIconColor = Color.Black,
                focusedTrailingIconColor = Color.Black,
                unfocusedPlaceholderColor = Color(0xFF303030).copy(alpha = 0.24f),
                focusedPlaceholderColor = Color(0xFF303030).copy(alpha = 0.24f),
                containerColor = Color(0xFFC4C4C4).copy(alpha = 0.65f)
            ),
            modifier = Modifier
                .size(315.dp, 55.dp)
                .menuAnchor()
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            destinationArr.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = it.name,
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    onClick = {
                        selectedDestination = it.name
                        onDestinationSelected(it)
                        keyboard?.hide()
                        expanded = false
                    }
                )
            }
        }
    }
}