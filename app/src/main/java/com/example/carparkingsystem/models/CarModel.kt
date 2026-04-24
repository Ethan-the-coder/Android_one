package com.example.carparkingsystem.models

data class CarModel(
    var id: String? = null,
    var plate_number: String? = null,
    var vehicle_type: String? = null,
    var car_color: String? = null,
    var entryDateTime: String? = null,
    var driver_name: String? = null,
    var phone_number: String? = null,
    var imageUrl: String? = null
){
    constructor() : this(null,null,null,null,null,null,null,null)
}
