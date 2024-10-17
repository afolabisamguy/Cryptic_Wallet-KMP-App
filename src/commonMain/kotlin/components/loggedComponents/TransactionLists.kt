package components.loggedComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowCircleDown
import compose.icons.fontawesomeicons.solid.ArrowUp
import compose.icons.fontawesomeicons.solid.CreditCard
import compose.icons.fontawesomeicons.solid.History
import compose.icons.fontawesomeicons.solid.Home
import presentation.screen.CoinScreen
import presentation.screen.ReceiveButton
import theme.PrimaryColor

@Composable
fun ActionButton(icon: ImageVector, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(50.dp))
            .clickable(onClick = onClick)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(50.dp)
                .background(PrimaryColor(), shape = CircleShape)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colors.onBackground
            )
        }
        Text(
            text = label,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun ActionButtonsRow() {
    val navigator = LocalNavigator.currentOrThrow
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        ActionButton(icon = FontAwesomeIcons.Solid.ArrowUp, label = "Send") {
            // Handle click
        }
        ActionButton(icon = FontAwesomeIcons.Solid.ArrowCircleDown, label = "Receive", onClick = {navigator.push(ReceiveButton())})
        ActionButton(icon = FontAwesomeIcons.Solid.CreditCard, label = "Buy") {
            navigator.push(ReceiveButton())
        }
        ActionButton(icon = FontAwesomeIcons.Solid.Home, label = "Sell", onClick = { })
        ActionButton(icon = FontAwesomeIcons.Solid.History, label = "History") {
            // Handle click
        }
    }
}