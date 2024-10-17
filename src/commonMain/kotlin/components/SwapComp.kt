package components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Downloading
import androidx.compose.material.icons.filled.GasMeter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.JoinFull
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material.icons.filled.UnfoldMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SwapScreen() {
    var fromAmount by remember { mutableStateOf("") }
    var toAmount by remember { mutableStateOf("") }


    // Swap Section Box
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {
            // From Swap Section
            SwapTokenBox2(
                tokenSymbol = "UNI",
                tokenIcon = Icons.Filled.Downloading,
                amount = fromAmount,
                onAmountChange = { fromAmount = it },
                blockchain = "Ethereum"
            )

            Spacer(modifier = Modifier.height(12.dp))

            // To Swap Section
            SwapTokenBox2(
                tokenSymbol = "ETH",
                tokenIcon = Icons.Filled.Security,
                amount = toAmount,
                onAmountChange = { toAmount = it },
                blockchain = "Ethereum"
            )
        }

        // Swap Icon in the middle, touching both boxes
        SwapIcon(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(x = (-2).dp, y = (-4).dp) // Adjust this for overlap effect
        )
    }

    // Rate Display and Action Section
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Rate Display Section
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "1 ETH ≈ 372.927564 UNI",
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Filled.Downloading, // Refresh icon placeholder
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Continue Button
        Button(
            onClick = { /* TODO: Handle button click */ },
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .height(48.dp),
            enabled = false // Initially disabled
        ) {
            Text(text = "Continue")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Approve UNI Section
        Text(
            text = "Approve UNI before you can swap it",
            style = MaterialTheme.typography.caption.copy(color = Color.Gray),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Provider and Slippage Tolerance Section
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = "Provider", style = MaterialTheme.typography.body2)
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "1inch Network",
                    style = MaterialTheme.typography.body2.copy(color = Color.Gray)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = "Slippage tolerance", style = MaterialTheme.typography.body2)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "1.0%", style = MaterialTheme.typography.body2.copy(color = Color.Gray))
            }
        }
    }
}

@Composable
fun SwapTokenBox2(
    tokenIcon: ImageVector, // Image resource id
    tokenSymbol: String,
    blockchain: String,
    amount: String,
    onAmountChange: (String) -> Unit
) {
    var hasClicked by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "From", color = Color.Gray, fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Filled.JoinFull, // Use painterResource for vector or drawable resources
                        contentDescription = "$tokenSymbol icon",
                        modifier = Modifier.size(10.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = blockchain, fontWeight = FontWeight.Bold)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Select Token",
                        tint = Color.Gray
                    )

                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Filled.GasMeter,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "0", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = tokenIcon,
                    contentDescription = "$tokenSymbol icon",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = tokenSymbol, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .width(200.dp) // Adjust width based on need
                ) {
                    // Placeholder Text
                    if (!hasClicked && amount.isEmpty()) {
                        Text(
                            text = "0",
                            textAlign = TextAlign.End,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .fillMaxWidth() // Ensure the placeholder spans the width
                                .align(Alignment.CenterEnd) // Align to the end (right)
                        )
                    }

                    // Actual TextField
                    TextField(
                        value = amount,
                        onValueChange = onAmountChange,
                        textStyle = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.End // Right align the input text
                        ),
                        modifier = Modifier
                            .clickable { hasClicked = true }
                            .fillMaxWidth(), // Make the TextField take the full width of the Box
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            cursorColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }

            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))


    }
}





@Composable
fun SwapIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(48.dp)
            .background(Color.White, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.SwapVert, // Swap icon placeholder
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun CustomTabsWithSettingsIcon() {
    val tabs = listOf("Swap", "Hot tokens 🔥")
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start =16.dp, end = 16.dp)
    ) {
        // Row containing tabs and the settings icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.padding(end = 16.dp) // Make space for settings icon
            ) {
                tabs.forEachIndexed { index, title ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clickable { selectedTabIndex = index }
                            .padding(end = 16.dp)
                    ) {
                        // Tab title
                        Text(
                            text = title,
                            color = if (selectedTabIndex == index) Color.Blue else Color.Black,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal
                        )
                        // Blue indicator
                        Spacer(modifier = Modifier.height(4.dp)) // Space between text and indicator
                        Box(
                            modifier = Modifier
                                .height(2.dp)
                                .width(24.dp) // Adjust width to your liking
                                .background(if (selectedTabIndex == index) Color.Blue else Color.Transparent)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            if (selectedTabIndex == 0) {
                // Settings icon at the far right
                IconButton(onClick = { /* Handle settings action */ }) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings Icon",
                        tint = Color.Gray
                    )
                }
            }
        }

        // Placeholder for the content associated with each tab
        Spacer(modifier = Modifier.height(16.dp)) // Adjust spacing between tab and content
        when (selectedTabIndex) {
            0 -> SwapScreen()
            1 -> CryptoListScreen()
        }
    }
}



@Composable
fun CryptoListScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header with filters
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            FilterChip(text = "Hot")
            FilterChip(text = "24H")
            FilterChip(text = "All Networks")
        }

        // Table headers
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HeaderText("Token/24H Volume", Modifier.weight(2f))
            Row(
                modifier = Modifier.weight(1.5f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HeaderText("Last price", Modifier.weight(0.2f), textAlign = TextAlign.End)
                HeaderText("24H %", Modifier.weight(0.1f), textAlign = TextAlign.End)
            }
        }

        // LazyColumn for scrolling items
        LazyColumn(
            contentPadding = PaddingValues(vertical = 6.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(getCryptoList()) { crypto ->
                CryptoRow(crypto = crypto)
            }
        }
    }
}

@Composable
fun FilterChip(text: String) {
    Row(
        modifier = Modifier
            .background(Color(0xFFE0E0E0), RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically // Ensures text and icon are vertically aligned
    ) {
        Text(
            text = text, // This makes the text take up available space
        )
        Icon(
            imageVector = Icons.Filled.ArrowDropDown,
            contentDescription = null
        )
    }
}

@Composable
fun HeaderText(text: String, modifier: Modifier = Modifier, textAlign: TextAlign = TextAlign.Start) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            textAlign = textAlign,
            fontSize = 12.sp,
        )
        Icon(
            imageVector = Icons.Filled.UnfoldMore,
            contentDescription = null,
            modifier = Modifier.size(10.dp)
        )
    }
}

@Composable
fun CryptoRow(crypto: Crypto) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Token column
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1.8f)
        ) {
            Image(
                imageVector = Icons.Filled.Home,
                contentDescription = crypto.name,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = crypto.name, fontWeight = FontWeight.Bold)
                Text(text = crypto.volume, style = MaterialTheme.typography.body2)
            }
        }

        // Last Price column
        Text(
            text = crypto.lastPrice,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )

        // 24H % change column
        Text(
            text = crypto.change,
            color = if (crypto.isPositive) Color.Green else Color.Red,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )
    }
}

// Sample data class to represent each cryptocurrency
data class Crypto(
    val iconUrl: String,
    val name: String,
    val volume: String,
    val lastPrice: String,
    val change: String,
    val isPositive: Boolean
)

// Sample function to generate a list of cryptos
fun getCryptoList(): List<Crypto> {
    return listOf(
        Crypto("https://crypto-icons.com/btc.png", "BTC", "$32.31B", "$56,503.14", "2.36%", true),
        Crypto("https://crypto-icons.com/lina.png", "LINA", "$16B", "$2,405.2", "-2.11%", false),
        Crypto("https://crypto-icons.com/eth.png", "ETH", "$16B", "$2,405.2", "-2.11%", false),
        Crypto("https://crypto-icons.com/matic.png", "MATIC", "$16B", "$2,405.2", "-2.11%", false),
        Crypto("https://crypto-icons.com/sol.png", "SOL", "$16B", "$2,405.2", "-2.11%", false),
        Crypto("https://crypto-icons.com/luna.png", "LUNA", "$16B", "$2,405.2", "-2.11%", false),
        Crypto("https://crypto-icons.com/avax.png", "AVAX", "$16B", "$2,405.2", "-2.11%", false),
        Crypto("https://crypto-icons.com/bnb.png", "BNB", "$16B", "$2,405.2", "-2.11%", false),
        Crypto("https://crypto-icons.com/dot.png", "DOT", "$16B", "$2,405.2", "-2.11%", false),
        Crypto("https://crypto-icons.com/doge.png", "DOGE", "$16B", "$2,405.2", "-2.11%", false),
        Crypto("https://crypto-icons.com/shib.png", "SHIB", "$16B", "$2,405.2", "-2.11%", false),
        Crypto("https://crypto-icons.com/usdc.png", "USDC", "$16B", "$2,405.2", "-2.11%", false),
        Crypto("https://crypto-icons.com/usdt.png", "USDT", "$16B", "$2,405.2", "-2.11%", false),
        Crypto("https://crypto-icons.com/xrp.png", "XRP", "$16B", "$2,405.2", "-2.11%", false),
        Crypto("https://crypto-icons.com/ada.png", "ADA", "$16B", "$2,405.2", "-2.11%", true),
        // Add more cryptos...
    )
}
