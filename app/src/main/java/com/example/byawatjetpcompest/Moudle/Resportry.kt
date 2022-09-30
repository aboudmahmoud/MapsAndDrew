package com.example.byawatjetpcompest.Moudle

import com.example.byawatjetpcompest.Data.BywatMathod
import com.example.byawatjetpcompest.Pojo.DitealsScreen.DetialsRoot
import com.example.byawatjetpcompest.Pojo.HomePage.HomeRoot
import com.example.byawatjetpcompest.UtilModle.dataStus
import com.plcoding.jetpackcomposepokedex.di.BywatImplemts
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


class Resportry @Inject constructor(   private val provideConnectServies : BywatMathod)  {


    suspend fun getHome():HomeRoot {
        return  provideConnectServies.gethomeInfo()
    }
    suspend fun getdetatile(housing_unit_id:Int): dataStus<DetialsRoot> {
        val response =try {
           provideConnectServies.getdetatile(housing_unit_id)

        }catch (e: Exception) {
            return dataStus.error("Error oucerd ${e.message}")
        }
        if(response.successful)
        {   return dataStus.succses(response)

        }else{
            return dataStus.error("Error oucerd ${response.message}")
        }

        }


    suspend fun getdetatiles(housing_unit_id:Int): DetialsRoot {
        return provideConnectServies.getdetatile(housing_unit_id)
    }
}