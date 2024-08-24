package com.example.buse_ui.PresentationStudent

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.buse_ui.SharedStateViewModel
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild

@Composable
fun BottomMenu(
    modifier: Modifier = Modifier,
    items: List<BottomMenuContent>,
    activeTextColor: Color = Color.Black,
    inactiveTextColor: Color = Color.Blue,
    hazeState: HazeState
) {

    val viewmodel: SharedStateViewModel = viewModel()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp)
            .haze(
                state = hazeState,
            )
    )

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
//            .padding(25.dp)
            .fillMaxWidth()
            .hazeChild(
                state = hazeState,
            )


    ) {
        items.forEachIndexed { index, item2 ->
            BottomMenuItem(
                item = item2,
                isSelected = index == viewmodel.selectedItemIndex,
                inactiveTextColor = inactiveTextColor,
                activeTextColor = activeTextColor
            ) {
                viewmodel.selectedItemIndex = index
            }
        }
    }


}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeTextColor: Color = Color.Black,
    inactiveTextColor: Color = Color.Blue,
    onItemCLick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable(
            interactionSource = interactionSource, indication = null
        ) {
            onItemCLick()
        }) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .padding(15.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(25.dp)
            )
        }
    }
}

data class BottomMenuContent(
    val title: String,
    @DrawableRes val iconId: Int
)