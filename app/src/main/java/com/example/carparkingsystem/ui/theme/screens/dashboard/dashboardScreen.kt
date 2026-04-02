package com.example.carparkingsystem.ui.theme.screens.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(){
    val selectedItem = remember { mutableStateOf(0) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = "Parking Dashboard")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.Gray,

                )
            )
        },


        bottomBar = {
            NavigationBar(
                containerColor = Color.DarkGray
            )  {
                NavigationBarItem(
                    selected = selectedItem.value == 0,
                    onClick = { selectedItem.value = 0 },
                    icon = {Icon(Icons.Default.Home, contentDescription = null)},
                    label = {Text("Home")}
                )
            }
        },

    )

    { padding ->
        Column(modifier = Modifier.padding(padding) )
        {

        }
    }
}

@Preview( showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview(){
    Dashboard()
}