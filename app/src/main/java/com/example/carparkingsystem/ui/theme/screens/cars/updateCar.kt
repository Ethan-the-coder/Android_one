package com.example.carparkingsystem.ui.theme.screens.cars

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.carparkingsystem.data.CarViewModel
import com.example.carparkingsystem.models.CarModel
import com.example.carparkingsystem.navigation.ROUTE_DASHBOARD
import com.example.carparkingsystem.navigation.ROUTE_UPDATE_CAR
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
import kotlinx.coroutines.tasks.await



@Composable
fun UpdateCarScreen(navController: NavController,carId:String) {
    val carViewModel: CarViewModel = viewModel()
    var car by remember { mutableStateOf<CarModel?>(null) }
    LaunchedEffect (carId){
        val ref = FirebaseDatabase.getInstance()
            .getReference("Cars").child(carId)
        val snapshot = ref.get().await()
        car = snapshot.getValue(CarModel::class.java)?.apply {
            id = carId
        }
    }
    if (car==null){
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
        return
    }

    var plate_number by remember { mutableStateOf(car!!.plate_number ?: "") }
    var vehicle_type by remember { mutableStateOf(car!!.vehicle_type ?: "") }
    var car_color by remember { mutableStateOf(car!!.car_color ?: "") }
    var entryDateTime by remember { mutableStateOf(car!!.entryDateTime ?: "") }
    var driver_name by remember { mutableStateOf(car!!.driver_name ?: "") }
    var phone_number by remember { mutableStateOf(car!!.phone_number ?: "") }
    var isError by remember { mutableStateOf(false) }


    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        it?.let { uri -> imageUri.value = uri }
    }

    val context = LocalContext.current


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFCE4EC), Color(0xFFF8BBD0))
                )
            )
            .padding(16.dp)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .align(Alignment.Center),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Update Car Details",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF880E4F)
                )

                Spacer(modifier = Modifier.height(16.dp))


                Card(
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(6.dp),
                    modifier = Modifier
                        .size(140.dp)
                        .clickable { launcher.launch("image/*") }
                        .shadow(8.dp, CircleShape)
                ) {
                    AnimatedContent(
                        targetState = imageUri.value,
                        label = "Image Picker Animation"
                    ) { targetUri ->
                        AsyncImage(
                            model = imageUri.value ?: car!!.imageUrl,
                            contentDescription = "Car Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                Text(
                    text = "Tap to change picture",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Divider(
                    modifier = Modifier.padding(vertical = 20.dp),
                    color = Color.LightGray,
                    thickness = 1.dp
                )

                val fieldModifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)

                val fieldShape = RoundedCornerShape(14.dp)

                OutlinedTextField(
                    value = plate_number,
                    onValueChange = { plate_number = it
                        isError = false },
                    label = { Text("Plate Number") },
                    placeholder = { Text("e.g., KDA 234Z") },
                    modifier = fieldModifier,
                    shape = fieldShape
                )

                OutlinedTextField(
                    value = vehicle_type,
                    onValueChange = { vehicle_type = it
                        isError = false},
                    label = { Text("Vehicle Type") },
                    placeholder = { Text("e.g., Nissan") },
                    modifier = fieldModifier,
                    shape = fieldShape
                )

                OutlinedTextField(
                    value = car_color,
                    onValueChange = { car_color = it
                        isError = false},
                    label = { Text("Color") },
                    placeholder = { Text("e.g., Blue") },
                    modifier = fieldModifier,
                    shape = fieldShape
                )

                OutlinedTextField(
                    value = phone_number,
                    onValueChange = { phone_number = it
                        isError = false },
                    label = { Text("Phone Number") },
                    placeholder = { Text("e.g., 07XX XXX XXX") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = fieldModifier,
                    shape = fieldShape
                )
                OutlinedTextField(
                    value = entryDateTime,
                    onValueChange = { entryDateTime = it },
                    label = { Text("Entry Date & Time") },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth()
                        .clickable { showDateTimePicker(context) { it -> entryDateTime = it } }
                        .padding(bottom = 16.dp),
                    leadingIcon = { Icon(Icons.Default.Event, contentDescription = null) },
                    trailingIcon = {
                        IconButton(onClick = {
                            showDateTimePicker(context) { it -> entryDateTime = it }
                        }
                        ) {
                            Icon(Icons.Default.CalendarToday, contentDescription = "Select Date")
                        }
                    }
                )
                OutlinedTextField(
                    value = driver_name,
                    onValueChange = { driver_name = it
                        isError = false},
                    label = { Text(text = "Driver's Name") },
                    placeholder = { Text("eg; Elvis") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(Icons.Default.People, contentDescription = null) },
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { navController.popBackStack() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.width(140.dp)
                    ) {
                        Text("Go Back", color = Color.DarkGray)
                    }

                    Button(
                        onClick = {
                            carViewModel.updateCar(
                                carId,
                                plate_number,
                                vehicle_type,driver_name,
                                phone_number,
                                car_color,
                                entryDateTime,
                                imageUri.value,
                                context,
                                navController
                            )
                            if (plate_number.isBlank() &&
                                vehicle_type.isBlank() &&
                                car_color.isBlank() &&
                                entryDateTime.isBlank() &&
                                driver_name.isBlank() &&
                                phone_number.isBlank()) {
                                isError = true
                                Toast.makeText(context, "Please fill in all the fields required!!", Toast.LENGTH_SHORT)
                                    .show()
                            } else{
                                navController.navigate(ROUTE_UPDATE_CAR)
                                Toast.makeText(context, "Please update your car details!!", Toast.LENGTH_SHORT)
                                    .show()

                            }


                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD81B60)
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.width(140.dp)
                    ) {
                        Text("Update", color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}



