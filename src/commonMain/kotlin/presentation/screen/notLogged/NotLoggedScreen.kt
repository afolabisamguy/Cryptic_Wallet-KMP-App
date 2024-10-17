package presentation.screen.notLogged

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import components.notLoggedComponents.SettingsComp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowCircleDown
import crypticwallet.composeapp.generated.resources.Res
import crypticwallet.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import presentation.screen.creatingWallet.CreateScreen
import components.notLoggedComponents.TokenItems
import presentation.screen.creatingWallet.ExistingWallet
import presentation.screen.creatingWallet.Testing4
import repository.Token
import theme.PrimaryColor
import viewModels.RepoViewModel


@Composable
fun NotLoggedScreen(token: List<Token>, viewmodel2: RepoViewModel) {
    val navigator = LocalNavigator.currentOrThrow
    var walletImage = painterResource(Res.drawable.compose_multiplatform)
    //Settings Component
    SettingsComp()
    // Welcome Component
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp),
        contentAlignment = Alignment.Center

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = walletImage,
                contentDescription = null,
                modifier = Modifier.width(200.dp),
                alignment = Alignment.Center
            )
            Text(
                modifier = Modifier.padding(top = 22.dp),
                text = "Join 70M+ People shaping the future of the\ninternet with us",
                color = MaterialTheme.colors.onBackground,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }

    // Create Wallet Component
    Column(
        modifier = Modifier
            .padding(top = 15.dp, start = 12.dp, end = 12.dp)
            .fillMaxWidth()
    ) {

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(PrimaryColor())
                .clickable {
                    navigator.push(CreateScreen())
                }
                .fillMaxWidth()
                .height(85.dp)
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .width(350.dp)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Settings",
                    tint = Color.Green
                )
                Column {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Create new Wallet",
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Secret phrase or Swift wallet",
                        color = MaterialTheme.colors.onBackground
                    )
                }

            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(
                    PrimaryColor())
                .clickable {
                    navigator.push(ExistingWallet())
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
                    imageVector = FontAwesomeIcons.Solid.ArrowCircleDown,
                    contentDescription = "Settings",
                    tint = Color.Green,
                    modifier = Modifier.size(30.dp)
                )
                Column {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Add existing Wallet",
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Import, restore or view-only",
                        color = MaterialTheme.colors.onBackground
                    )
                }

            }
        }
    }

    // Popular Tokens Component
    Column(modifier = Modifier.padding(start = 16.dp, top = 16.dp)) {
        Text(
            text = "Popular tokens",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.onBackground
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        token.forEachIndexed{ index, tokene ->
            TokenItems(tokene, viewmodel2)
        }
    }
}


