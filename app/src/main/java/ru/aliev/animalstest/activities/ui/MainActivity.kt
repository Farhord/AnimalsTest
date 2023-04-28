package ru.aliev.animalstest.activities.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.aliev.animalstest.R
import ru.aliev.animalstest.activities.navigation.Navigation
import ru.aliev.animalstest.activities.navigation.Screen

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimalsTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val currentItem = navController.currentBackStackEntryAsState()
                    
                    Scaffold(
                        bottomBar = {
                            BottomNavigation {
                                listOf(
                                    BottomNavigationItem(
                                        selected = currentItem.value?.destination?.route == Screen.MAIN.route,
                                        selectedContentColor = Teal200,
                                        unselectedContentColor = Black,
                                        alwaysShowLabel = true,
                                        onClick = { navController.navigate(Screen.MAIN.route) },
                                        icon = {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic18_map_home),
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .size(24.dp),
                                            )

                                        },
                                        label = {
                                            Text(text = Screen.MAIN.screenName)
                                        }
                                    ),
                                    BottomNavigationItem(
                                        selected = currentItem.value?.destination?.route == Screen.FAVORITE.route,
                                        selectedContentColor = Teal200,
                                        unselectedContentColor = Black,
                                        alwaysShowLabel = true,
                                        onClick = { navController.navigate(Screen.FAVORITE.route) },
                                        icon = {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic18_rate_fav_fill),
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .size(24.dp),
                                            )

                                        },
                                        label = {
                                            Text(text = Screen.FAVORITE.screenName)
                                        }
                                    )
                                )
                            }
                        }
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier
                                .padding(innerPadding)
                        ) {
                            Navigation(navController)
                        }
                    }
                }
            }
        }
    }
}