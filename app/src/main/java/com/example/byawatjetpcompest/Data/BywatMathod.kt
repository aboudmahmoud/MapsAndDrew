package com.example.byawatjetpcompest.Data

import com.example.byawatjetpcompest.Pojo.DitealsScreen.DetialsRoot
import com.example.byawatjetpcompest.Pojo.HomePage.HomeRoot
import com.example.byawatjetpcompest.Pojo.global.globalsRoot
import com.example.byawatjetpcompest.UtilModle.dataStus

import retrofit2.http.GET
import retrofit2.http.Query

interface BywatMathod {
    @GET("guest/home/map")
    suspend fun gethomeInfo(): HomeRoot
    @GET("guest/guest/global")
    suspend fun getBulicInfo(): globalsRoot
    @GET("guest/housing_units/details")
    suspend fun getdetatile(@Query("housing_unit_id")housing_unit_id:Int): DetialsRoot

}