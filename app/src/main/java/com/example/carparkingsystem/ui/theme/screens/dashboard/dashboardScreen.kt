package com.example.carparkingsystem.ui.theme.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(){
    val selectedItem = remember { mutableStateOf(0) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = "Parking Dashboard")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Gray,
                    titleContentColor = Color.Black,

                )
            )
        },


        bottomBar = {
            NavigationBar(
                containerColor = Color.Gray
            )  {
                NavigationBarItem(
                    selected = selectedItem.value == 0,
                    onClick = { selectedItem.value = 0 },
                    icon = {Icon(Icons.Default.Home,
                        contentDescription = null)},
                    label = {Text("Home")}
                )
                NavigationBarItem(
                    selected = selectedItem.value == 1,
                    onClick = { selectedItem.value = 1 },
                    icon = {Icon(Icons.Default.AccountCircle,
                        contentDescription = null)},
                    label = {Text("Account")}
                )
                NavigationBarItem(
                    selected = selectedItem.value == 2,
                    onClick = { selectedItem.value = 2 },
                    icon = {Icon(Icons.Default.Settings,
                        contentDescription = null)},
                    label = {Text("Settings")}
                )
            }
        },

    )

    { padding ->
        Column(modifier = Modifier.padding(padding))
        {
            Text(
                text = "Smart Parking System",
                color = Color.Green,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 20.dp),

            )
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors( containerColor = Color.Green)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "Available", color = Color.Black)
                        Text(text = "18 Slots", color = Color.Black, fontSize = 20.sp)
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Card(
                    modifier = Modifier.size(100.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors( containerColor = Color.DarkGray)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Add Car", color = Color.White, fontSize = 20.sp)
                    }
                    
                }
                Card(
                    modifier = Modifier.size(100.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors( containerColor = Color.DarkGray)

                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,

                    ) {
                        Text(text = "View Car", color = Color.White, fontSize = 20.sp)

                    }
                }


            }





        }
    }
}


@Preview( showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview(){
    Dashboard()
}