package presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.WindowClose
import presentation.screen.creatingWallet.Testing2

class WalletActionScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable {  //navController.navigate("home")
                        }
                )

                Text(
                    text = "wallet",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )

                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "Back",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable {
                            navigator.push(Testing2())
                        }
                )
            }

            Text(text = "Name", modifier = Modifier.padding(start = 10.dp))
            TextField(
                value = "",
                onValueChange = {},
                trailingIcon = { Icon(imageVector = FontAwesomeIcons.Regular.WindowClose, contentDescription = "Add") },
                placeholder = { Text(text = "SAM Wallet") },
                modifier = Modifier.fillMaxWidth().padding(10.dp).size(30.dp)
            )

            Text(text = "Secret phrase backups", modifier = Modifier.padding(10.dp))

            Box{
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Add",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                    Text(text = "Google Drive", modifier = Modifier.padding(10.dp))
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "Back up now", modifier = Modifier.padding(10.dp))
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Box {
                Row {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Add",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                    Text(text = "Manual", modifier = Modifier.padding(10.dp))
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "Back up now", modifier = Modifier.padding(10.dp))
                }
            }
        }
    }
}