package presentation.screen.creatingWallet

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import components.Searchbar
import components.Topbar
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Bitcoin
import presentation.screen.CoinRow
import viewModels.ReceiveViewModel

class SelectingNetwork: Screen {
    @Composable
    override fun Content() {
        val viewModel: ReceiveViewModel = getScreenModel()
        val networkName by viewModel.network.collectAsState()
        LaunchedEffect(Unit){
            viewModel.loadNetworkName()
        }
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                Topbar("Select Network", true)

                Searchbar()

                LazyColumn {
                    item {
                        CoinRow123("Multi Coin Wallet")
                    }
                    item {
                        networkName.forEachIndexed { _, s ->
                            CoinRow123(s)
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun CoinRow123(name: String){
    val navigator = LocalNavigator.currentOrThrow
    Box(
        modifier = Modifier
            .clickable {
                navigator.push(RestoreScreen(name))
            }
            .height(60.dp)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.padding(10.dp, top = 15.dp)
                .background(MaterialTheme.colors.background),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Icon(
                imageVector = FontAwesomeIcons.Brands.Bitcoin,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = name
                )
        }
    }
}