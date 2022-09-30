package com.example.byawatjetpcompest.Pojo.global

data class MainCatagory(
    val id: Int,
    val image_url: String,
    val name: String,
    val sub_categories: List<SubCategory>
)