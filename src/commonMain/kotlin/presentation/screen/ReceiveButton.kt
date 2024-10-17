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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import presentation.screen.creatingWallet.DisplayImage
import presentation.screen.creatingWallet.Testing3
import viewModels.ReceiveViewModel
import viewModels.RepoViewModel

class ReceiveButton: Screen {
    @Composable
    override fun Content() {

        val viewModel: ReceiveViewModel = getScreenModel()
        LaunchedEffect(Unit){
            viewModel.loadData()
        }
        val symbol by viewModel.symbol.collectAsState()
        val name by viewModel.name.collectAsState()

        val viewModel2 : RepoViewModel = getScreenModel()

        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                Topbar("Receive", true)
                Searchbar()
                Button(onClick = {}) {
                    Text(text = "All Networks")
                }
                LazyColumn{
                    item {
                        symbol.forEachIndexed { index, s ->
                            CoinRow(symbol = s, name = name[index], viewModel2)
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun CoinRow(symbol: String, name: String, viewModel2: RepoViewModel){
    val navigator = LocalNavigator.currentOrThrow
    Box(
        modifier = Modifier
            .clickable {
                navigator.push(Testing3(name, symbol))
            }
            .height(60.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                .background(MaterialTheme.colors.background)
        ){
            DisplayImage(name, viewModel2)
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(
                    text = symbol,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                )
                Text(text = name)
            }
        }
    }
}