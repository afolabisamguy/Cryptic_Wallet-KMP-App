package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Ethereum
import kotlinx.coroutines.flow.map
import presentation.screen.creatingWallet.DisplayImage
import viewModels.ReceiveViewModel
import viewModels.RepoViewModel

@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf("") }

    Card(
        shape = RoundedCornerShape(50.dp),
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(40.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color(0xFFF6F6F6))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))
            BasicTextField(
                value = searchText,
                onValueChange = { searchText = it },
                decorationBox = { innerTextField ->
                    if (searchText.isEmpty()) {
                        Text("Search", color = Color.Gray)
                    }
                    innerTextField() // Place the actual text here
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
fun NetworkFilterDropdown() {
    // A button that mimics a dropdown for "All Networks"
    Button(
        onClick = {},
        modifier = Modifier.clip(CircleShape).wrapContentSize()
            .width(150.dp)
            .height(40.dp)
    ) {
        Text(
            text = "All Networks",
            style = MaterialTheme.typography.body1
        )
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Dropdown",
        )
    }
}

@Composable
fun CryptoList(viewModel: ReceiveViewModel, viewModel2: RepoViewModel) {

    LaunchedEffect(Unit){
        viewModel.loadData()
    }

    val name by viewModel.name.collectAsState()
    val symbol by viewModel.symbol.collectAsState()

    val cryptoList = if (symbol.size == name.size) {
        println("cryptoList: Works")
        symbol.zip(name) { coinSymbol, coinName ->
            Crypto12345(coinSymbol, coinName, false)
        }
    } else {
        println("CyrpoList: Doesnt work")
        emptyList()
    }


    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(cryptoList) { crypto ->
            CryptoItem(crypto, viewModel, viewModel2)
        }
    }
}

@Composable
fun CryptoItem(crypto: Crypto12345, viewModel: ReceiveViewModel, viewModel2: RepoViewModel) {
    val isChecked by viewModel.cryptoSwitchStates
        .map { it[crypto.name] ?: crypto.isActive}
        .collectAsState(initial = crypto.isActive)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Cryptocurrency Icon
            DisplayImage(crypto.name, viewModel2)
            Spacer(modifier = Modifier.width(16.dp))

            // Cryptocurrency Name
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = crypto.symbol, style = MaterialTheme.typography.body1)
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .background(
                                Color.LightGray,
                                shape = CircleShape
                            )

                    ) {
                        Text(
                            text = crypto.name,
                            style = MaterialTheme.typography.body2,
                            color = Color.Gray
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = crypto.name,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
        Switch(
            checked = isChecked,
            onCheckedChange = { checked ->
                println("The name is ${crypto.name}")
                viewModel.updateSwitchState(crypto.name, checked)
            }
        )
    }
}

// Data class for each cryptocurrency
data class Crypto12345(
    val symbol: String,
    val name: String,
    val isActive: Boolean
)

