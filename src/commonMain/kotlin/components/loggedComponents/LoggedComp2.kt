package components.loggedComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.BellSlash
import compose.icons.fontawesomeicons.regular.Clock
import presentation.screen.CoinScreen
import presentation.screen.creatingWallet.DisplayImage
import presentation.screen.settings.SettingsScreen
import repository.Token
import viewModels.RepoViewModel

@Composable
fun HeadingItem(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h3,
            color = if (isSelected) MaterialTheme.colors.primary else Color.Unspecified
        )
        if (isSelected) {
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .height(3.dp)
                    .width(90.dp)
            )
        }
    }
}

enum class Heading {
    HEADING1,
    HEADING2
}



@Composable
fun CryptoList(token: Token, viewModel2: RepoViewModel) {
    val navigator = LocalNavigator.currentOrThrow
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(16.dp)
            .clickable { navigator.push(CoinScreen(
                coinName = token.name,
                showGas = if (token.symbol == "ETH" || token.symbol == "BTC") true else false,
                coinSymbol = token.symbol,
                showAct = 5,
                coinPrice = token.price,
                priceChange = token.change
            )) }
    )
    {
        Column(modifier = Modifier.fillMaxWidth()) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                DisplayImage(token.name, viewModel2)


                Text(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .weight(1f),
                    text = token.symbol,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colors.onBackground
                )

                Text(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    text = "${0}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.End
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    text = "$ ${token.price}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.onBackground,

                    )

                Text(
                    modifier = Modifier
                        .padding(start = 5.dp),
                    text = " ${token.change}%",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = if (MaterialTheme.colors.background == Color.Black) {
                        Color.Green
                    } else if(token.change < 0){
                        Color.Red
                    } else Color.Blue,
                    )
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    modifier = Modifier,
                    text = "$ ${0.000}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}


@Composable
fun TopRow2(modifier: Modifier = Modifier) {
    val navigator = LocalNavigator.currentOrThrow
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navigator.push(SettingsScreen())     })
        {
            Icon(
                imageVector = Icons.Rounded.Settings,
                contentDescription = "Settings",
                tint = Color.Gray
            )

        }

        Text(
            text = "Home",
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = FontAwesomeIcons.Regular.Clock,
                contentDescription = "Manage Crypto",
                tint = Color.Gray,
                modifier = Modifier.size(30.dp)
            )
        }
    }

}


@Composable
fun TopLoggedBar(text: String){
    val navigator = LocalNavigator.currentOrThrow
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {   navigator.push(SettingsScreen())  })
        {
            Icon(
                imageVector = Icons.Rounded.Settings,
                contentDescription = "Settings",
                tint = Color.Gray
            )

        }

        Text(
            text = text,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = FontAwesomeIcons.Regular.Clock,
                contentDescription = "Manage Crypto",
                tint = Color.Gray,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}