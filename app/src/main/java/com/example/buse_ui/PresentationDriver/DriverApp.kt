package com.example.buse_ui.PresentationDriver

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buse_ui.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.buse_ui.PresentationStudent.BottomMenuContent
import com.example.buse_ui.SharedStateViewModel

@Composable
fun DriverApp(modifier: Modifier = Modifier) {
    val viewModel: SharedStateViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Driver App")

    }

    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        BottomMenu(items = listOf(
            BottomMenuContent("Home", R.drawable.ic_home),
            BottomMenuContent("Services", R.drawable.ic_bubble),
            BottomMenuContent("Updates", R.drawable.ic_moon),
            BottomMenuContent("Accounts", R.drawable.ic_profile)
        ),
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        when(viewModel.selectedItemIndex){
            0 -> HomeScreen()
            1 -> UtilitiesScreen()
            2 -> UpdatesScreen()
            3 -> AccountScreen()
        }
    }

}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Home Screen")

    }
}

@Composable
fun UtilitiesScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Utilities Screen")

    }
}

@Composable
fun UpdatesScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Updates Screen")

    }
}

@Composable
fun AccountScreen(modifier: Modifier = Modifier) {
    Column(
    modifier = Modifier
        .fillMaxSize()
        .background(Color.Blue),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
) {
    Text(text = "Account Screen")

}
}

@Composable
fun  BottomMenu(
    modifier: Modifier = Modifier,
    items : List<BottomMenuContent>,
    activeHighnlight: Color = Color.Cyan,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Color.Blue,
    initialSelectedItemIndex: Int = 0
) {
    val viewmodel: SharedStateViewModel = viewModel()

    viewmodel.selectedItemIndex = initialSelectedItemIndex

    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(Color.Yellow)
            .padding(15.dp)
            .fillMaxWidth()
    ){
        items.forEachIndexed{
                index,
                item2 ->
            BottomMenuItem(
                item = item2,
                isSelected = index == viewmodel.selectedItemIndex,
                activeHighnlight = activeHighnlight,
                inactiveTextColor = inactiveTextColor,
                activeTextColor = activeTextColor
            )
            {
                viewmodel.selectedItemIndex = index
            }
        }
    }

}

@Composable
fun BottomMenuItem(
    item : BottomMenuContent,
    isSelected: Boolean = false,
    activeHighnlight: Color = Color.Cyan,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Color.Blue,
    onItemCLick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemCLick()
        }
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (isSelected) activeHighnlight
                    else Color.Transparent
                )
                .padding(10.dp)
        ){
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription =item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor
        )
    }
}