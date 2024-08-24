package com.example.buse_ui.PresentationStudent

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.buse_ui.R
import com.example.buse_ui.SharedStateViewModel
import com.example.buse_ui.ui.theme.MyFonts
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild

@Composable
fun StudentApp(navController: NavController) {
    val viewModel: SharedStateViewModel = viewModel()
    val hazeState = remember {
        HazeState()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when (viewModel.selectedItemIndex) {
            0 -> HomeScreen(hazeState = hazeState, navController = navController)
            1 -> UtilitiesScreen(navController = navController, hazeState = hazeState)
            2 -> UpdatesScreen(navController = navController, hazeState = hazeState)
            3 -> AccountScreen()
        }

        BottomMenu(
            items = listOf(
                BottomMenuContent("Home", R.drawable.ic_home),
                BottomMenuContent("Services", R.drawable.ic_bubble),
                BottomMenuContent("Updates", R.drawable.ic_moon),
                BottomMenuContent("Accounts", R.drawable.ic_profile)
            ), modifier = Modifier.align(Alignment.BottomCenter), hazeState = hazeState
        )


    }


}

@Composable
fun HomeScreen(modifier: Modifier = Modifier, hazeState: HazeState, navController: NavController) {
    val boardingPoint = "Demo point"
    val lastBoardingPointCrossed = "Demo point"
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .haze(
                state = hazeState, style = HazeStyle(
                    tint = Color.Gray.copy(alpha = 0.5f), blurRadius = 6.dp, noiseFactor = 0.1f
                )

            )
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.back_map),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
    Box {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
            )
            Row(
                modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .padding(start = 20.dp, top = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "BUS-E", fontFamily = MyFonts.UberBold, fontSize = 33.sp, fontWeight = FontWeight.W900)
            }


            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 9.dp)
                    .verticalScroll(scrollState, enabled = true),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .hazeChild(
                            hazeState, shape = RoundedCornerShape(30.dp)
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth()
                            .height(150.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .height(75.dp)
                                .clip(RoundedCornerShape(200.dp))
                                .weight(0.7f)
                                .background(Color.Yellow),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "571", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.width(50.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1.3f),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .weight(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .border(3.dp, Color.Black)
                                        .fillMaxWidth()
                                        .height(40.dp)
                                        .background(Color.White)
                                        .padding(5.dp),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "IND",
                                        color = Color.Blue,
                                        fontSize = 6.sp,
                                        modifier = Modifier.weight(0.2f)
                                    )
                                    Text(
                                        text = "OD 06 N 2000",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        modifier = Modifier.weight(1.8f),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.filled_circle),
                                    contentDescription = "",
                                    modifier = Modifier.size(15.dp)
                                )
                                Spacer(modifier = Modifier.width(20.dp))
                                Text(text = "ITER")
                            }
                            Image(
                                painter = painterResource(id = R.drawable.filled_circle),
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(start = 3.dp)
                                    .size(10.dp)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.empty_circle),
                                    contentDescription = "",
                                    modifier = Modifier.size(15.dp)
                                )
                                Spacer(modifier = Modifier.width(20.dp))
                                Text(text = lastBoardingPointCrossed)
                            }
                            Image(
                                painter = painterResource(id = R.drawable.filled_circle),
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(start = 3.dp)
                                    .size(10.dp)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.filled_circle),
                                    contentDescription = "",
                                    modifier = Modifier.size(15.dp)
                                )
                                Spacer(modifier = Modifier.width(20.dp))
                                Text(text = boardingPoint)
                            }
                        }
                    }
                }

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = interactionSource, indication = null
                    ) {
                        navController.navigate("mapActivity")
                    }
                    .padding(20.dp)
                    .hazeChild(
                        hazeState, shape = RoundedCornerShape(30.dp)
                    )) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(198.dp)
                    ) {
                        Column(
//                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.live_map),
                                contentDescription = "",
                                Modifier
                                    .clip(RoundedCornerShape(2000.dp))
                                    .fillMaxSize()
                                    .weight(1f)
//                                    .background(Color.Blue)
                                    .padding(
                                        horizontal = 10.dp,
//                                        vertical = 0.dp
                                    ),
//                                contentScale = ContentScale.Crop
                            )
                            Text(
                                text = " view live map",
                                color = Color.Yellow,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .weight(0.2f),
//                                    .background(Color.Red),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Black,
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun UtilitiesScreen(navController: NavController, hazeState: HazeState) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .haze(
                state = hazeState, style = HazeStyle(
                    tint = Color.Gray.copy(alpha = 0.5f), blurRadius = 6.dp, noiseFactor = 0.1f
                )
            )
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.back_map),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
    Box {

        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
            )
            Text(
                text = "Utilities",
                modifier = Modifier.padding(20.dp),
                fontSize = 36.sp,
                fontWeight = FontWeight(1000),
                fontFamily = FontFamily.Cursive
            )


            UtilityMenu(
                listOf(
                    UtilityTileContent(
                        navController = navController,
                        title = "demo",
                        painter = painterResource(id = R.drawable.soa_logo),
                        routeName = "testActivity"
                    ),
                    UtilityTileContent(
                        navController = navController,
                        title = "demo",
                        painter = painterResource(id = R.drawable.soa_logo),
                        routeName = "testActivity"
                    ),
                    UtilityTileContent(
                        navController = navController,
                        title = "demo",
                        painter = painterResource(id = R.drawable.soa_logo),
                        routeName = "testActivity"
                    ),
                    UtilityTileContent(
                        navController = navController,
                        title = "demo",
                        painter = painterResource(id = R.drawable.soa_logo),
                        routeName = "testActivity"
                    ),
                    UtilityTileContent(
                        navController = navController,
                        title = "demo",
                        painter = painterResource(id = R.drawable.soa_logo),
                        routeName = "testActivity"
                    ),
                    UtilityTileContent(
                        navController = navController,
                        title = "demo",
                        painter = painterResource(id = R.drawable.soa_logo),
                        routeName = "testActivity"
                    ),

                    ), hazeState = hazeState
            )
        }
    }
}

@Composable
fun UpdatesScreen(navController: NavController, hazeState: HazeState) {

    val viewModel: SharedStateViewModel = viewModel()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .haze(
                state = hazeState, style = HazeStyle(
                    tint = Color.Gray.copy(alpha = 0.5f), blurRadius = 6.dp, noiseFactor = 0.1f
                )
            )
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.back_map),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
    Box {

        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
            )
            Text(
                text = "Updates",
                modifier = Modifier.padding(20.dp),
                fontSize = 36.sp,
                fontWeight = FontWeight(1000),
                fontFamily = FontFamily.Cursive
            )
            viewModel.addNotification(
                InAppNotifContent(
                    sender = "New Sender",
                    message = "This is a new notification!",
                    painter = R.drawable.soa_logo
                )
            )

            NotificationsList(hazeState = hazeState)
        }
    }
}

@Composable
fun AccountScreen() {

    val viewModel: SharedStateViewModel = viewModel()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(colors = listOf(Color.Blue, Color.Cyan))),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Account Screen")
    }
}


@Preview
@Composable
fun pre(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    UpdatesScreen(navController = navController, hazeState = HazeState())
}





