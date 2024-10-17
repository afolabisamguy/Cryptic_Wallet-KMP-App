package presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import components.Topbar
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Coins
import crypticwallet.composeapp.generated.resources.Res
import crypticwallet.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import theme.PrimaryColor

class WalletListScreen: Screen {
    @Composable
    override fun Content() {
        val walletImage = painterResource(Res.drawable.compose_multiplatform)
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Topbar("Wallet", true)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Multi-coin wallets")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PrimaryColor())
                    .clickable { }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
//                        .padding(16.dp), // Optional: Add padding if needed
                    verticalAlignment = Alignment.CenterVertically, // Align items vertically
                    horizontalArrangement = Arrangement.SpaceBetween // Distribute space between items
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically // Ensure vertical alignment of items inside this row
                    ) {
                        Image(
                            painter = walletImage,
                            contentDescription = null,
                            modifier = Modifier.width(60.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Main Wallet 1")
                    }
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.Coins,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                navigator.push(WalletActionScreen())
                            }
                    )
                }
            }
        }
    }
}