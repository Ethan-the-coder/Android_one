package com.example.carparkingsystem.ui.theme.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.carparkingsystem.R
import com.example.carparkingsystem.navigation.ROUTE_DASHBOARD
import com.example.carparkingsystem.navigation.ROUTE_LOGIN
import com.example.carparkingsystem.navigation.ROUTE_REGISTER

@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    var isError by remember { mutableStateOf(false) }



    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.aud),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray.copy(alpha = 0.7f)))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            . verticalScroll(rememberScrollState())
            .background(
            Brush.radialGradient(colors = listOf(Color.Transparent,
                Color.Gray
            ),
                center = Offset(x = 500f, y = 1150f),
                radius = 900f
            )
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.audi),
            contentDescription = "audi",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
                .border(2.dp,Color.Black)
                .shadow(4.dp,CircleShape)
        )
        Text(text ="Login Here!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        OutlinedTextField(
            value = email,
            label = {Text(text = "Email")},
            onValueChange = {email=it
                isError = false},
            placeholder = {Text(text = "Please enter your email")},
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null)},
        )

        OutlinedTextField(
            value = password,
            onValueChange = {password=it
                isError = false},
            label = {Text(text = "Password")},
            placeholder = {Text(text = "Enter your password")},
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null)},
        )

        Button(
            onClick = {
                if (email.isBlank() && password.isBlank()) {
                    isError = true
                    Toast.makeText(context, "Please fill in all the fields!!", Toast.LENGTH_SHORT)
                        .show()
                } else{
                    navController.navigate(ROUTE_DASHBOARD){
                        popUpTo(ROUTE_LOGIN) {inclusive = true }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)) {
            Text(text = "Login", color = Color.Black)
        }
        Row (){
            Text(text = "Don't have an account ?", color = Color.Yellow)

                Text(
                    text = "Register here", color = Color.Blue,
                    modifier = Modifier.clickable {
                        navController.navigate(
                            ROUTE_REGISTER
                        )
                    }

                )
            }
        }


    }



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview(){
    LoginScreen(rememberNavController())
}