package components.loggedComponents

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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.Bell
import compose.icons.fontawesomeicons.regular.BellSlash
import compose.icons.fontawesomeicons.regular.Eye
import compose.icons.fontawesomeicons.regular.EyeSlash
import compose.icons.fontawesomeicons.solid.ArrowCircleDown
import compose.icons.fontawesomeicons.solid.ArrowUp
import compose.icons.fontawesomeicons.solid.Copy
import compose.icons.fontawesomeicons.solid.CreditCard
import compose.icons.fontawesomeicons.solid.History
import compose.icons.fontawesomeicons.solid.Home
import compose.icons.fontawesomeicons.solid.Qrcode
import crypticwallet.composeapp.generated.resources.Res
import crypticwallet.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import presentation.screen.ScanQrScreen
import presentation.screen.WalletActionScreen
import presentation.screen.WalletListScreen
import presentation.screen.settings.SettingsScreen
import theme.PrimaryColor
import viewModels.LoggedInViewModel

//
//val Transaction = mapOf(
//    "Send" to FontAwesomeIcons.Solid.ArrowUp,
//    "Receive" to FontAwesomeIcons.Solid.ArrowCircleDown,
//    "Buy" to FontAwesomeIcons.Solid.CreditCard,
//    "Sell" to FontAwesomeIcons.Solid.Home,
//    "History" to FontAwesomeIcons.Solid.History
//)
//
//val TransactionList = listOf(
//    "Send",
//    "Receive",
//    "Buy",
//    "Sell",
//    "History"
//)



@Composable
fun LoggedComp1(){
    val navigator = LocalNavigator.currentOrThrow
    var walletImage = painterResource(Res.drawable.compose_multiplatform)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {


        Box(modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .background(PrimaryColor())
            .height(40.dp),
            contentAlignment = Alignment.CenterStart){
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Search",
                modifier = Modifier.padding(start = 15.dp),
            )

            Text(
                text = "Search",
                modifier = Modifier.padding(start = 50.dp)
            )
        }

        Row(
            modifier = Modifier
                .padding(5.dp)
                .clickable {
                    navigator.push(WalletListScreen())
                }
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Main Wallet 1",
                modifier = Modifier.padding(start = 10.dp)
            )
            Icon(
                Icons.Rounded.ArrowDropDown,
                contentDescription = "Change Wallet",
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(40.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.background
                )) {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.Copy,
                    contentDescription = "Settings",
                    modifier = Modifier.size(30.dp)
                )

            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = { navigator.push(ScanQrScreen()) },
                modifier = Modifier
                    .background(PrimaryColor())
                    .height(30.dp)
                    .width(30.dp)
            ) {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.Qrcode,
                    contentDescription = "Settings",
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = { /*TODO*/ },
                modifier = Modifier
                    .background(PrimaryColor())
                    .height(30.dp)
                    .width(30.dp)) {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Settings",
                )

            }
        }
        var isBalanceHidden by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .padding(10.dp)
                .height(40.dp)
                .widthIn(min=80.dp, max=100.dp)
                .clickable {
                    isBalanceHidden = !isBalanceHidden
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = if (isBalanceHidden) "****" else "$0.00",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f) // This ensures the Text takes up all available space
                )
                Icon(
                    imageVector = if (isBalanceHidden) FontAwesomeIcons.Regular.EyeSlash else FontAwesomeIcons.Regular.Eye,
                    contentDescription = "hide balance",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(20.dp)
                )
            }
        }

        ActionButtonsRow()

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)){
            Image(
                painter = walletImage,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                alignment = Alignment.CenterStart
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 100.dp),
            ){
                Text(
                    text = "Back up to secure your assets"
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row {

                    Text(
                        text = "Back up wallet"
                    )
                    Icon(
                        imageVector = Icons.Rounded.ArrowForward,
                        contentDescription = null
                    )
                }
            }

        }


    }
}