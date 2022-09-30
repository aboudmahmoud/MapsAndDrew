package com.example.byawatjetpcompest.Screens

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.byawatjetpcompest.Componts.MarkInfo
import com.example.byawatjetpcompest.Moudle.ByawModleView
import com.example.byawatjetpcompest.Pojo.HomePage.Unite
import com.example.byawatjetpcompest.R
import com.example.byawatjetpcompest.Moudle.Resposnes
import com.example.byawatjetpcompest.Pojo.DitealsScreen.DetialsRoot
import com.example.byawatjetpcompest.Pojo.DitealsScreen.HousingUnit
import com.example.byawatjetpcompest.UtilModle.IntentState
import com.example.byawatjetpcompest.UtilModle.dataStus
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

import com.google.maps.android.compose.*
import kotlinx.coroutines.*


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenMapa(modifier: Modifier = Modifier,viewModel : ByawModleView ) {
    Log.d("Aboud", "Wtf Why0: ")
    LaunchedEffect(key1 = true){
        viewModel.refehdata()

    }


var housint: HousingUnit? by remember {
    mutableStateOf(null)
}
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState =
    BottomSheetState(BottomSheetValue.Collapsed))

    // Declaing Coroutine scope
    val coroutineScope = rememberCoroutineScope()


    BottomSheetScaffold(

        scaffoldState = bottomSheetScaffoldState,
        sheetContent =  {
         MarkInfo(housint)
        },
        sheetPeekHeight = 0.dp
    ){
        var langLocationMark = LatLng(29.9787258, 31.2780905)
        var cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(langLocationMark, 10f)
        }

        Log.d("Aboud", "Wtf WhyBottomSheetScaffold0: ")

        GoogleMap(
            modifier = modifier,
            contentPadding= PaddingValues(10.dp),
            cameraPositionState = cameraPositionState
        ) {
            Log.d("Aboud", "Wtf Why: ")

            //this to get intnetstate form api and

            //this to store data we get in bludling its caled unite
            val intent_state_data=viewModel.state2.collectAsState().value
            var uniteArrayList: ArrayList<Unite>? by remember {
                mutableStateOf(null)
            }
            LaunchedEffect(intent_state_data){
                when (intent_state_data) {
                    is IntentState.Idle -> Log.d("Aboud", "HomeScreenMapa:Idels ")
                    is IntentState.HomdData -> {
                        uniteArrayList = intent_state_data.data.units

                        Log.d("Aboud", "HomeScreenMapa:Succes ")
                    }
                    is IntentState.Error -> Log.d(
                        "Aboud",
                        "HomeScreenMapa:Errors ${intent_state_data.error}"
                    )


                }
            }


            if(uniteArrayList!=null)
            {
                LaunchedEffect(key1 =  true) {
                    langLocationMark = LatLng(uniteArrayList!!.get(1).center.get(0), uniteArrayList!!.get(1).center.get(1))
                    cameraPositionState.position=CameraPosition.fromLatLngZoom(langLocationMark, 10f)
                }


                var done_state_data = viewModel.state3.collectAsState().value


                for (i in uniteArrayList!!) {

                    Marker( state = MarkerState(position = LatLng(i.center.get(0), i.center.get(1))),
                        icon =iconset(i.type,i.have_VR), onClick = {


                            viewModel.getHouuseIDInfodoneStasus(i.id)
                                when(done_state_data)
                                {
                                    is dataStus.Loading->{
                                        Log.d("Aboud", "uniteArrayList: 2")
                                    }
                                    is dataStus.succses-> {
                                        housint=done_state_data.data?.housing_unit
                                        coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
                                        Log.d("Aboud", "uniteArrayList: 1 ${done_state_data.data?.housing_unit?.addresses}")

                                    }

                                    is dataStus.error->{
                                        Log.d("Aboud", "uniteArrayList: 3  ${done_state_data.Messeg}")
                                    }

                                }



                            //
                            true
                        })
                }
            }

        }
    }




}



//this for Icon mapGooglee
fun bitmapDescriptor(
    context: Context,
    vectorResId: Int
): BitmapDescriptor? {

    // retrieve the actual drawable
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    // draw it onto the bitmap
    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}

//this method to make to check type of bulding and have vr or not
@Composable
fun iconset( type:Int , Havevr:Int ):BitmapDescriptor?{

    if (type==1) {
        if(Havevr==0)
        {
            return bitmapDescriptor(
                LocalContext.current, R.drawable.el
            )
        }else if(Havevr==1){
            return bitmapDescriptor(
                LocalContext.current, R.drawable.elver
            )
        }


    } else if(type==2){
        if(Havevr==0)
        {
            return bitmapDescriptor(
                LocalContext.current, R.drawable.rent
            )
        }else if(Havevr==1){
            return  bitmapDescriptor(
                LocalContext.current, R.drawable.rentvr
            )
        }
    }
  return null
}







