package viewModels

import Wallet.WalletCore
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Bitcoin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import presentation.screen.EarnScreen
import repository.StakingItemData
import kotlin.math.roundToInt
import kotlin.random.Random

class EarnViewModel: ScreenModel {
    private val wally = WalletCore()
    private var _symbol = MutableStateFlow<List<String>>(emptyList())
    val symbol: StateFlow<List<String>> = _symbol

    private var _name = MutableStateFlow<List<String>>(emptyList())
    val name: StateFlow<List<String>> = _name


    private var _apr = MutableStateFlow<List<String>>(emptyList())
    val apr: StateFlow<List<String>> = _apr


    private fun getName(): Flow<List<String>> = flow{
        val myName = wally.getEarnCoins()
        emit(myName)
    }

    private fun getSymbol(): Flow<List<String>> = flow{
        val mySymbol = wally.getEarnSymbol()
        emit(mySymbol)
    }

    init{
        loadData()
    }

    fun loadData() = screenModelScope.launch(Dispatchers.IO) {
        launch {
            getName().collect{
                _name.value = it
            }
        }
        launch {
            getSymbol().collect{
                _symbol.value = it
            }
        }
        launch {
            loadEarnApr().collect{
                _apr.value = it
            }
        }
    }
    private fun roundToTwoDecimalPlaces(value: Double): Double{
        return (value * 100.0).roundToInt() / 100.0
    }

    fun loadEarnApr(): Flow<List<String>> = flow{
        while (true){
            val myApr = List(_name.value.size){ index ->
                val mynewApr = roundToTwoDecimalPlaces(Random.nextDouble(3.0,  25.0))
                mynewApr.toString()
            }
            emit(myApr)
            delay(10000)
        }
    }
}