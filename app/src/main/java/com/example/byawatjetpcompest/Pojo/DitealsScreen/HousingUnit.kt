package com.example.byawatjetpcompest.Pojo.DitealsScreen

data class HousingUnit(
    val VR: String,
    val addresses: Addresses,
    val amenities: List<Amenity>,
    val brocker: Brocker,
    val description: String,
    val discount: Int,
    val floor_plan: Any,
    val have_VR: Int,
    val have_documented: Int,
    val id: Int,
    val images: List<Image>,
    val location: Location,
    val nearby: Nearby,
    val overview: Overview,
    val price: Price,
    val title: String
)