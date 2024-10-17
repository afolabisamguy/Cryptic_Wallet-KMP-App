package presentation.screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import components.CoinScreen12
import viewModels.RepoViewModel

class CoinScreen(coinName: String, showGas: Boolean, showAct: Int, coinSymbol: String, coinPrice: Double, priceChange: Double): Screen {
    private val coinName = coinName
    private val showGas = showGas
    private val showAct = showAct
    private val coinSymbol = coinSymbol
    private val coinPrice = coinPrice
    private val priceChange = priceChange

    @Composable
    override fun Content() {
        val viewModel2: RepoViewModel = getScreenModel()
        CoinScreen12(coinName, showGas, showAct, coinSymbol, coinPrice, priceChange, viewModel2)
    }
}