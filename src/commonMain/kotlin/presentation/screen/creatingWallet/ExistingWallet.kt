package presentation.screen.creatingWallet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import components.Topbar
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowCircleDown
import compose.icons.fontawesomeicons.solid.Bullseye
import compose.icons.fontawesomeicons.solid.Pen
import compose.icons.fontawesomeicons.solid.SimCard
import compose.icons.fontawesomeicons.solid.Star
import theme.PrimaryColor

val myText = listOf(
    "Secret phrase",
    "Swift",
    "Google Drive Backup",
    "View-only Wallet"
)

val texts = mapOf(
    "Secret phrase" to "Use a 12, 18 or 24-word seed phrase",
    "Swift" to "Use Face ID or fingerprint",
    "Google Drive Backup" to "Restore from Google Drive backup",
    "View-only Wallet" to "Observe or track assets of other wallets"
)

val images = mapOf(
    "Secret phrase" to FontAwesomeIcons.Solid.Pen,
    "Swift" to FontAwesomeIcons.Solid.Star,
    "Google Drive Backup" to FontAwesomeIcons.Solid.SimCard,
    "View-only Wallet" to FontAwesomeIcons.Solid.Bullseye
)

class ExistingWallet: Screen {
    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Topbar("Add Existing Wallet", false)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                myText.forEachIndexed { index, s ->
                    myBox(s, texts[s]!!, images[s]!!)
                }
            }

        }
    }
}



@Composable
fun myBox(upText: String, downText: String, icon: ImageVector,) {
    val navigator = LocalNavigator.currentOrThrow
    Column(
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(
                    PrimaryColor()
                )
                .clickable {
                    if (upText == "Secret phrase") {
                        navigator.push(SelectingNetwork())
                    }
                }
                .fillMaxWidth()
                .height(85.dp),

            ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .width(350.dp)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Settings",
                    tint = Color.Green,
                    modifier = Modifier.size(25.dp)
                )
                Column {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = upText,
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = downText,
                        color = MaterialTheme.colors.onBackground
                    )
                }

            }
        }
    }
}