package com.example.byawatjetpcompest.Moudle


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.byawatjetpcompest.Pojo.DitealsScreen.DetialsRoot
import com.example.byawatjetpcompest.Pojo.HomePage.HomeRoot

import com.example.byawatjetpcompest.UtilModle.IntentState
import com.example.byawatjetpcompest.UtilModle.dataStus
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class ByawModleView @Inject constructor(private val repostory: Resportry) : ViewModel() {
    private val _viewState = MutableStateFlow<IntentState>(IntentState.Idle)
    val state2: StateFlow<IntentState> get() = _viewState
     var DataDetialsRootStatus:Int =3
    var ErrorMessag:String=""
    private val _state = MutableStateFlow<DetialsRoot?>(null)
    val state: StateFlow<DetialsRoot?> get() = _state
    private val _state3 = MutableStateFlow<dataStus<DetialsRoot>>(dataStus.Loading())
    val state3: StateFlow<dataStus<DetialsRoot>> get() = _state3



    fun getHouuseIDInfodoneStasus(id: Int )
    {
        viewModelScope.launch {
            _state3.value=try{
                val result =repostory.getdetatiles(id)
                if(result.successful)
                {

                    dataStus.succses(result)

                }
                else{
                    dataStus.error(result.message,null)
                }
            }catch (e: Exception){
                dataStus.error(e.message,null)
            }
        }
    }
    fun getHouuseIDInfo(id: Int) {
        viewModelScope.launch {
            val result = repostory.getdetatile(id)
            when (result) {
                is dataStus.succses ->{
                    _state.value=result.data
                    Log.d("Aboud", "getHouuseIDInfo :succses ${result.data!!.message} ")
                    DataDetialsRootStatus=1
                }
                is dataStus.error->{
                    Log.d("Aboud", "getHouuseIDInfoerror: ${result.Messeg}")
                    DataDetialsRootStatus=2
                    ErrorMessag= result.Messeg!!
                }
                is dataStus.Loading->{
                    Log.d("Aboud", "getHouuseIDInfoerror:Loading")
                    DataDetialsRootStatus=3
                }
            }

        }

    }

    fun refehdata() {
        viewModelScope.launch {
            _viewState.value = try {

                IntentState.HomdData(repostory.getHome())
            } catch (e: Exception) {
                IntentState.Error("Error is ${e.message} ")
            }
        }
    }


}