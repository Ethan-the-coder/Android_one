package com.example.carparkingsystem.ui.theme.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.carparkingsystem.R
import com.example.carparkingsystem.navigation.ROUTE_ADD_CAR
import com.example.carparkingsystem.navigation.ROUTE_VIEW_CAR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(navController: NavHostController, onLogoutClick:() -> Unit){
    val selectedItem = remember { mutableStateOf(0) }
    val context = LocalContext.current



    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = "Parking Dashboard",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold)
                        },

                actions = { IconButton(onClick = onLogoutClick){
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Logout",
                        tint = MaterialTheme.colorScheme.error
                    )
                }

                          },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF7FB),
                    titleContentColor = Color.Black,

                )

            )
        },


        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFFF7FB)
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
        Column(modifier = Modifier
            .padding(padding)
        )
        {
            Text(
                text = "Smart Parking System",
                color = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 20.dp),

            )
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors( containerColor = Color(0xFFE8F5E9))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Column {
                        Text(text = "Available", color = Color(0xFFE8F5E9),
                            style = MaterialTheme.typography.labelLarge)
                        Text(text = "18 Slots", color = Color(0xFF2E7D32), fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )


                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                ActionCard(
                    title = "Add Car",
                    icon = Icons.Default.AddCircle,
                    backgroundColor = Color(0xFF6CABDD),
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate(ROUTE_ADD_CAR) }
                )
                ActionCard(
                    title = "View Cars",
                    icon = Icons.Default.DirectionsCar,
                    backgroundColor = Color(0xFF5F6368),
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate(ROUTE_VIEW_CAR)/* navigate to list */ }
                )

//                Card(
//                    modifier = Modifier.size(90.dp),
//                    shape = RoundedCornerShape(20.dp),
//                    colors = CardDefaults.cardColors( containerColor = Color.Gray)
//
//                ) {
//                    Box(
//                        modifier = Modifier.fillMaxSize(),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Text(text = "Add Car", color = Color.White, fontSize = 20.sp)
//                    }
//
//                }
//                Card(
//                    modifier = Modifier.size(90.dp),
//                    shape = RoundedCornerShape(20.dp),
//                    colors = CardDefaults.cardColors( containerColor = Color.Gray)
//
//                ) {
//                    Box(
//                        modifier = Modifier.fillMaxSize(),
//                        contentAlignment = Alignment.Center,
//
//                    ) {
//                        Text(text = "View Car", color = Color.White, fontSize = 20.sp)
//
//                    }
//                }


            }





        }
    }
}



@Preview( showBackground = true, showSystemUi = true)
@Composable



fun DashboardScreenPreview(){
    Dashboard(rememberNavController(),onLogoutClick = {})
}



@Composable
fun ActionCard(
    title: String,
    icon: ImageVector,
    backgroundColor: Color,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.height(120.dp).clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = null, tint = Color.White)
            Text(text = title, color = Color.White)
        }
    }
}