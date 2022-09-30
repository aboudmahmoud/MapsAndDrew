package com.example.byawatjetpcompest.UtilModle

import com.example.byawatjetpcompest.Pojo.HomePage.HomeRoot

sealed class IntentState {
    //idle
    object Idle:IntentState()
    //number
    data class HomdData(val data:HomeRoot):IntentState()
    //error
    data class Error(val error:String):IntentState()

}