package com.example.byawatjetpcompest.Componts

import androidx.compose.foundation.Image
import com.example.byawatjetpcompest.R
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.byawatjetpcompest.Pojo.DitealsScreen.HousingUnit

@Composable
fun MarkInfo(houseuit: HousingUnit?,modifier:Modifier=Modifier) {
    if(houseuit!=null)
    {
        Box(modifier.fillMaxWidth().height(200.dp)) {
            Column() {
                Row() {
                    Spacer(modifier = modifier
                        .width(10.dp))

                    Image(         painter = rememberAsyncImagePainter(houseuit?.images?.get(0)?.src),
                        contentDescription = "serch",modifier = modifier
                            .align(CenterVertically)
                            .width(100.dp)
                            .height(100.dp))
                    Spacer(modifier = modifier.width(20.dp))
                    houseInfoi(houseuit)
                }
            }
        }
    }

}

@Composable
fun houseInfoi(houseuit: HousingUnit?,modifier:Modifier=Modifier) {
    Column() {
        Text(text = houseuit!!.title)
        Row(){
            Image(painter = painterResource(id = R.drawable.star), contentDescription ="" )
            Text(text ="4.6")
        }
        Row(){

            Text(text =houseuit.price.value.toString())
            Text(text =houseuit.price.symbol)
        }
        Row(){
            ItemsNumber(houseuit.overview.area,R.drawable.space,modifier)
            ItemsNumber(houseuit.overview.rooms,R.drawable.room,modifier)
            ItemsNumber(houseuit.overview.bedrooms,R.drawable.bed_roms,modifier)
        }
    }
}

@Composable
fun ItemsNumber(ItemNumber:Int,ImageId:Int,modifier: Modifier=Modifier) {
    Row(){

        Text(text =ItemNumber.toString())
        Spacer(modifier= modifier.width(5.dp) )
        Image(painter = painterResource(id = ImageId), contentDescription ="" )
        Spacer(modifier = modifier.width(20.dp))
    }
}