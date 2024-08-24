package com.example.buse_ui.PresentationStudent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.buse_ui.R
import com.example.buse_ui.SharedStateViewModel
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild

@Composable
fun NotificationsList(viewModel: SharedStateViewModel = viewModel(), hazeState: HazeState) {
    val notifications by viewModel.notifications.observeAsState(initial = emptyList())

    LazyColumn {
        itemsIndexed(notifications) { index, notification ->
            InAppNotifItem(notification, index = index, hazeState = hazeState)
        }
    }
}


@Composable
fun InAppNotifItem(item: InAppNotifContent, index: Int, hazeState: HazeState) {

    val viewModel: SharedStateViewModel = viewModel()

    Row(
        modifier = Modifier
            .padding(9.dp)
            .hazeChild(
                hazeState,
                shape = RoundedCornerShape(20.dp)
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Spacer(modifier = Modifier.width(10.dp))
        Image(
            painter = painterResource(id = item.painter),
            contentDescription = "sender",
            modifier = Modifier
                .padding(10.dp)
                .size(30.dp)
        )
        Spacer(modifier = Modifier.width(9.dp))
        Column {
            Spacer(modifier = Modifier.height(9.dp))
            Text(
                text = item.sender,
                fontSize = 20.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = item.message,
                fontSize = 15.sp,
                color = Color.Gray,
                modifier = Modifier.padding(end = 9.dp)
            )
            Spacer(modifier = Modifier.height(9.dp))
        }

        Button(onClick = {
//            viewModel.removeNotification(notification = item)
            viewModel.removeNotification(index)
        }) {
        }
    }
}

@Entity(tableName = "inAppNotif")
data class InAppNotifContent(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,


    val sender: String,
    val message: String,
    val painter: Int
)

@Preview
@Composable
fun pre() {

}