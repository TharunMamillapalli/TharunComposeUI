package com.example.uipractice.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uipractice.R
import com.example.uipractice.ui.components.BottomNavigationBar
import com.example.uipractice.ui.screens.HomeScreen
import com.example.uipractice.ui.screens.IntroScreen
import com.example.uipractice.ui.screens.PlaceHolderScreen
import com.example.uipractice.ui.screens.ProfileScreen
import com.example.uipractice.ui.theme.DarkBlue


@Composable
fun AppNavigation(){
    val navController= rememberNavController()

    NavHost(navController = navController, startDestination = "intro"){
        composable("intro"){
            IntroScreen(
                onGetStatedClick = {
                    navController.navigate("main_app"){
                        popUpTo("intro"){
                            inclusive=true
                        }
                    }
                }
            )
        }
        composable ("main_app"){
            MainAppContainer()
        }
    }
}
@Composable

fun MainAppContainer(){
    var selectedTab by remember { mutableStateOf("Home") }
    Scaffold(
        contentWindowInsets = WindowInsets.navigationBars,
        bottomBar = {BottomNavigationBar(
            selectedTab=selectedTab,
            onTabSelected = {newTab-> selectedTab=newTab })},
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = DarkBlue,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier
                    .offset(y = 50.dp)
                    .size(60.dp)

            ) {
                Icon(
                    painter = painterResource(R.drawable.float_icon),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)

                    )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        containerColor = Color.White
    ) {paddingValues ->
        Box(modifier=Modifier.padding(paddingValues)){
            when(selectedTab){
                "Home"->{
                    HomeScreen()
                }
                "Wallet"->{
                    PlaceHolderScreen("Wallet Screen")
                }
                "Profile"->{
                    ProfileScreen(onBackClick = {selectedTab="Home"})
                }
                "Settings"->{
                    PlaceHolderScreen("Settings Screen")
                }
                else -> HomeScreen()
            }
        }

    }

}