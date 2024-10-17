package components

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.HomeMax
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.NotificationsOff
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.rounded.TransferWithinAStation
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Ethereum
import kotlinx.atomicfu.TraceBase.None.append
import presentation.screen.creatingWallet.DisplayImage
import viewModels.RepoViewModel


@Composable
fun CoinScreen12(coinName: String, showGas: Boolean, showAct: Int, coinSymbol: String,coinPrice: Double, priceChange: Double, viewModel2: RepoViewModel) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background))
    {
        EthereumTopAppBar(coinName, coinSymbol)

        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top AppBar (Back Icon, Title, Icons)

            Spacer(modifier = Modifier.height(8.dp))

            // ETH Price
            if (showGas) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.LocalGasStation,
                        contentDescription = "Gas Price",
                        tint = Color.Blue
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("$0.16", color = Color.Gray, style = MaterialTheme.typography.body2)
                }
            }


            // ETH Logo and Main Value
            DisplayImage(coinName, viewModel2)
            Spacer(modifier = Modifier.height(4.dp))
            Text("0 $coinSymbol", style = MaterialTheme.typography.h4, fontWeight = FontWeight.Bold)
            Text("$0.00", color = Color.Gray, style = MaterialTheme.typography.body2)

            Spacer(modifier = Modifier.height(16.dp))

            // Action Buttons (Send, Receive, Swap, Buy, Sell)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                when (showAct) {
                    2 -> {
                        ActionButton(icon = Icons.Default.ArrowUpward, text = "Send")
                        ActionButton(icon = Icons.Default.ArrowDownward, text = "Receive")
                    }

                    3 -> {
                        ActionButton(icon = Icons.Default.ArrowUpward, text = "Send")
                        ActionButton(icon = Icons.Default.ArrowDownward, text = "Receive")
                        ActionButton(icon = Icons.Default.SwapHoriz, text = "Swap")
                    }

                    4 -> {
                        ActionButton(icon = Icons.Default.ArrowUpward, text = "Send")
                        ActionButton(icon = Icons.Default.ArrowDownward, text = "Receive")
                        ActionButton(icon = Icons.Default.SwapHoriz, text = "Swap")
                        ActionButton(icon = Icons.Default.AccountBalance, text = "Sell")
                    }

                    5 -> {
                        ActionButton(icon = Icons.Default.ArrowUpward, text = "Send")
                        ActionButton(icon = Icons.Default.ArrowDownward, text = "Receive")
                        ActionButton(icon = Icons.Default.SwapHoriz, text = "Swap")
                        ActionButton(icon = Icons.Default.AccountBalance, text = "Sell")
                        ActionButton(icon = Icons.Default.ShoppingCart, text = "Buy")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Promotional Banner (Start earning)
            Card(
                backgroundColor = Color(0xFFF1F1F1),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.HomeMax,
                        contentDescription = "Earn Icon",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Start earning", style = MaterialTheme.typography.h6)
                        Text("Start earning on your $coinSymbol", color = Color.Gray)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = "Arrow",
                        tint = Color.Gray
                    )
                }
            }
            NoTransactionsScreen(coinSymbol, coinName, coinPrice, priceChange)
        }
    }
}

@Composable
fun ActionButton(icon: ImageVector, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(48.dp)
                .background(Color(0xFFF1F1F1), shape = CircleShape)
        ) {
            Icon(icon, contentDescription = text)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text, style = MaterialTheme.typography.body2)
    }
}



@Composable
fun EthereumTopAppBar(coinSymbol: String, coinName: String) {
    TopAppBar(
        backgroundColor = Color.White,
        elevation = 0.dp
    ) {
        // Back Arrow Icon
        IconButton(onClick = { /* TODO: Handle back action */ }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
        }

        // Title and Subtitle in the center
        Column(
            modifier = Modifier.weight(1.5f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = coinSymbol,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "COIN | $coinName",
                style = MaterialTheme.typography.caption,
                color = Color.Gray
            )
        }

        // Icons on the right side
        Row {
            IconButton(onClick = { /* TODO: Handle mute action */ }) {
                Icon(
                    imageVector = Icons.Filled.NotificationsOff,
                    contentDescription = "Mute",
                    tint = Color.Gray
                )
            }
            IconButton(onClick = { /* TODO: Handle info action */ }) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "Info",
                    tint = Color.Gray
                )
            }
        }
    }
}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoTransactionsScreen(coinSymbol: String, coinName: String, coinPrice: Double, priceChange: Double) {
    // Scaffold with a Bottom Sheet
    val bottomSheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        scaffoldState = bottomSheetState,
        sheetContent = {
            BottomSheetContent(coinSymbol, coinName, coinPrice, priceChange)
        },
        sheetPeekHeight = 60.dp, // The height when the sheet is collapsed
        sheetBackgroundColor = Color.White,
    ) {
        // Main content of the screen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Icon or image placeholder
            Icon(
                imageVector = Icons.Rounded.TransferWithinAStation,
                contentDescription = "No Transactions",
                modifier = Modifier.size(80.dp),
                tint = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Text: "Transactions will appear here."
            Text(
                text = "Transactions will appear here.",
                style = MaterialTheme.typography.body1,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Text: "Cannot find your transaction? Check explorer"
            Text(
                buildAnnotatedString {
                    append("Cannot find your transaction? ")
                    withStyle(style = SpanStyle(color = Color.Blue)) {
                        append("Check explorer")
                    }
                },
                style = MaterialTheme.typography.body2,
                modifier = Modifier.clickable {
                    // Handle explorer click
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Button: "Buy ETH"
            Button(
                onClick = { /* Handle Buy ETH */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                shape = RoundedCornerShape(50)
            ) {
                Text(text = "Buy $coinSymbol", color = Color.White)
            }
        }
    }
}

@Composable
fun BottomSheetContent(coinSymbol: String, coinName: String, coinPrice: Double, priceChange: Double) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Current ETH price and the percentage change
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Current $coinSymbol price",
                    style = MaterialTheme.typography.caption,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$ $coinPrice",
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "$priceChange",
                        style = MaterialTheme.typography.body1,
                        color = Color.Red
                    )
                }
            }

            // Graph and arrow (icon placeholders)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.GraphicEq,
                    contentDescription = "Graph",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.ArrowDropUp, // Arrow icon
                    contentDescription = "Expand",
                    tint = Color.Gray,
                    modifier = Modifier.clickable {
                        // Handle expand/collapse action
                    }
                )
            }
        }
    }
}


