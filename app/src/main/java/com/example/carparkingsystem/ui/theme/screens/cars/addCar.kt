package com.example.carparkingsystem.ui.theme.screens.cars


import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.ElectricCar
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.carparkingsystem.data.CarViewModel
import com.example.carparkingsystem.navigation.ROUTE_DASHBOARD
import com.example.carparkingsystem.navigation.ROUTE_LOGIN

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCarScreen( navController: NavController){

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri : Uri? -> imageUri = uri }
    var plate_number by remember { mutableStateOf("") }
    var vehicle_type by remember { mutableStateOf("") }
    var driver_name by remember { mutableStateOf("") }
    var phone_number by remember { mutableStateOf("") }
    val carViewModel : CarViewModel = viewModel()
    val context = LocalContext.current
    var isError by remember { mutableStateOf(false) }

    Scaffold(

        topBar = {
            TopAppBar(
                title =  { Text("Add Car Details")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Gray,
                    titleContentColor = Color.Cyan
                )
            )
        }

    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.Center
        )

        {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ){

                if (imageUri != null){
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        Icons.Default.Person,
                        contentDescription =  null,
                        modifier = Modifier.size(80.dp)
                    )
                }

            }

            Button(
                onClick = { launcher.launch("image/*") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Select Image")
            }

            OutlinedTextField(
                value = plate_number,
                label = { Text(text = "Number Plate") },
                onValueChange = { plate_number = it
                    isError = false},
                placeholder = { Text(text = "Please enter your number plate") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.DirectionsCar, contentDescription = null) }
            )
            OutlinedTextField(
                value = vehicle_type,
                onValueChange = { vehicle_type = it
                    isError = false},
                label = { Text(text = "Vehicle Type") },
                placeholder = { Text(text = "Please input your vehicle type") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.ElectricCar, contentDescription = null) },
            )
            OutlinedTextField(
                value = driver_name,
                onValueChange = { driver_name = it
                    isError = false},
                label = { Text(text = "Driver's Name") },
                placeholder = { Text(text = "Please input your name") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.People, contentDescription = null) },
            )
            OutlinedTextField(
                value = phone_number,
                label = { Text(text = "Phone Number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                onValueChange = { phone_number = it
                    isError = false},
                placeholder = { Text(text = "Please enter your phone number") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) }
            )
            Button(
                onClick = {
                    carViewModel.uploadCar(
                        imageUri,
                        plate_number,
                        vehicle_type,
                        driver_name,
                        phone_number,
                        context,
                        navController
                    )
                    if (plate_number.isBlank() &&
                        vehicle_type.isBlank() &&
                        driver_name.isBlank() &&
                        phone_number.isBlank()) {
                        isError = true
                        Toast.makeText(context, "Please fill in all the fields required!!", Toast.LENGTH_SHORT)
                            .show()
                    } else{
                        navController.navigate(ROUTE_DASHBOARD)

                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF87CEFA))

            ) {
                Text(text = "Save Car", color = Color.Black)
            }



        }
    }
}

@Preview(showBackground = true, showSystemUi = true )
@Composable
fun AddCarScreenPreview(){
    AddCarScreen(rememberNavController())
}