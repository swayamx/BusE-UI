package com.example.buse_ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.buse_ui.PresentationDriver.DriverApp
import com.example.buse_ui.PresentationStudent.MapActivity
import com.example.buse_ui.PresentationStudent.StudentApp
import com.example.buse_ui.PresentationStudent.UtilityScreens.TestPage
import com.example.buse_ui.data.MapsViewModel
import com.example.buse_ui.data.Retrofitnstance
import com.example.buse_ui.notif.notifViewModel
import com.example.buse_ui.ui.theme.BusEUITheme

class MainActivity : ComponentActivity() {

    private val mapsViewModel by viewModels<MapsViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return com.example.buse_ui.data.MapsViewModel(
                    com.example.buse_ui.data.MapRepoImpl(
                        Retrofitnstance.api
                    )
                ) as T
            }
        }
    })

    private val notifViewModel: notifViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestNotificationPermission()
        setContent {
            BusEUITheme {
                MapActivity(mapsViewModel = mapsViewModel, notifViewModel)
//                HomeScreen(mapsViewModel)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestNotificationPermission() {
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            val hasPermission = ContextCompat.checkSelfPermission(
//                this,
//                Manifest.permission
//            ) == PackageManager.PERMISSION_GRANTED
//
//            if(!hasPermission) {
//                ActivityCompat.requestPermissions(
//                    this,
//                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
//                    0
//                )
//            }
//        }
        var allPermissionGranted by mutableStateOf(false)
        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                allPermissionGranted = permissions.values.all { it }
            }

        requestPermissionLauncher.launch(
            arrayOf(
                android.Manifest.permission.POST_NOTIFICATIONS
            )
        )
    }
}


@Composable
fun HomeScreen(mapsViewModel: MapsViewModel, notifViewModel: notifViewModel) {
    val navController = rememberNavController()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { navController.navigate("studentAppScreen") }) {
            Text(text = "Student App")
        }
        Button(onClick = { navController.navigate("driverAppScreen") }) {
            Text(text = "Driver App")
        }
    }

    NavHost(navController = navController, startDestination = "homeScreen") {
        composable("homeScreen") {

        }
        composable("studentAppScreen") {
            StudentApp(navController = navController)
        }
        composable("DriverAppScreen") {
            DriverApp()
        }
        composable("mapActivity") {
            MapActivity(mapsViewModel, notifViewModel)
        }
        composable("testActivity") {
            TestPage()
        }
    }



}