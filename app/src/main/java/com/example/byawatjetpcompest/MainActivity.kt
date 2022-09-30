package com.example.byawatjetpcompest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
/*import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.Scaffold*/


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.byawatjetpcompest.Moudle.ByawModleView
import com.example.byawatjetpcompest.Nagavation.Nagvation
import com.example.byawatjetpcompest.ui.theme.NvaBarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    // @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel : ByawModleView by lazy {

            ViewModelProvider(this)[ByawModleView::class.java]
        }
            setContent {

            // A surface container using the 'background' color from the theme
            //GoogleMap()

            val navController = rememberNavController()

            Scaffold(


                content = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()


                    )
                    {
                        Nagvation(
                            navController = navController,
                            modifier = Modifier.padding(bottom = 35.dp),
                                    viewModel=viewModel
                        )
                    }
                },

                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {

                        },
                        backgroundColor = Color.White,
                        shape = RoundedCornerShape(50),


                        ) {
                        Icon(
                            Icons.Filled.Add,
                            tint = Color.White,
                            contentDescription = "Add",
                            modifier = Modifier.background(
                                color = Color.Black,
                                shape = RoundedCornerShape(50)
                            )
                        )
                    }
                },
                isFloatingActionButtonDocked = true,
                floatingActionButtonPosition = FabPosition.Center, //if its not set, it's show default position

                bottomBar = {
                    BottomAppBar(
                        modifier = Modifier.clip(RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)),
                        backgroundColor = NvaBarColor,
                        content = {
                            BottomNavigation(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
                                backgroundColor = NvaBarColor,
                            ) {

                                BottomNavigationItem(
                                    selected = true,
                                    onClick = {

                                    },
                                    icon = {
                                        // Icon(Icons.Filled.Home, contentDescription = "home")
                                    },
                                    label = { Text(text = "Home") },
                                    alwaysShowLabel = false
                                )

                                BottomNavigationItem(
                                    selected = true,
                                    onClick = {

                                    },
                                    icon = {

                                    },
                                    label = { Text(text = "Setting") },
                                    alwaysShowLabel = false
                                )
                            }
                        }
                    )
                }
            )

        }
    }

}





