package viewModels

import Wallet.QRCodeGenerator
import Wallet.WalletCore
import Wallet.settingsManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.russhwolf.settings.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import repository.Token
import utilities.RequestState
import kotlin.math.roundToInt
import kotlin.random.Random

class ReceiveViewModel: ScreenModel {
    private val wally = WalletCore()
    private val qally = QRCodeGenerator()
    var _symbol = MutableStateFlow<List<String>>(emptyList())
    val symbol: StateFlow<List<String>> = _symbol
    var _name = MutableStateFlow<List<String>>(emptyList())
    val name: StateFlow<List<String>> = _name
    lateinit var address: String
    lateinit var qrImage: ImageBitmap
    lateinit var myCoin: String
    lateinit var mySymbol: String
    private var _network = MutableStateFlow<List<String>>(emptyList())
    val network: StateFlow<List<String>> = _network

    private val _cryptoSwitchStates = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val cryptoSwitchStates: StateFlow<Map<String, Boolean>> = _cryptoSwitchStates


    private var requestState by mutableStateOf<RequestState<Unit>>(RequestState.Idle)
    val state: RequestState<Unit> get() = requestState

    private var _tokensList = MutableStateFlow<List<Token>>(emptyList())
    var tokensList: StateFlow<List<Token>> = _tokensList


//
//    fun scanQrCode(): String{
//        var scannedText = ""
//        screenModelScope.launch(Dispatchers.Main) {
//            scannedText = qally.scanQRCode().toString()
//        }
//        return scannedText
//    }

    init {
        loadData()
        loadSwitchStates()
        updateToken()
    }

    fun loadSwitchStates(){
        val savedStates = mutableMapOf<String, Boolean>()
        val cryptoId = listOf("btc", "eth", "ltc")
        cryptoId.forEach { cryptoI ->
            val isActive = settingsManager.getSwitchState(cryptoI)
            savedStates[cryptoI] = isActive
        }
        _cryptoSwitchStates.value = savedStates
    }

    fun updateToken() = screenModelScope.launch(Dispatchers.IO) {
        tokenSomething().collect {
            _tokensList.value = it
        }
    }


    fun updateSwitchState(cryptoId: String, isActive: Boolean){
        _cryptoSwitchStates.value = _cryptoSwitchStates.value.toMutableMap().apply {
            put(cryptoId, isActive)
        }
        settingsManager.saveSwitchState(cryptoId, isActive)
    }

    fun getSwitchState(cryptoId: String): Boolean {
        return _cryptoSwitchStates.value[cryptoId] ?: false
    }

    private fun getfSymbol(): Flow<List<String>> = flow{
        val mySymbol = wally.getFullNetworkSymbol()
        emit(mySymbol)
    }

    private fun getfNetwork(): Flow<List<String>> = flow{
        val myNetwork = wally.getNetworkName()
        emit(myNetwork)
    }

    private fun getfName(): Flow<List<String>> = flow{
        val myName = wally.getFullNetworkName()
        emit(myName)
    }

    private fun getQr(text: String){
        qrImage = qally.qrImage(text)
    }

    fun loadData() = screenModelScope.launch(Dispatchers.IO) {
        launch {
            getfSymbol().collect {
                _symbol.value = it
            }
        }
        launch {
            getfName().collect {
                _name.value = it
            }
        }
    }

    fun loadNetworkName() = screenModelScope.launch(Dispatchers.IO) {
        launch {
            getfNetwork().collect {
                _network.value = it
            }
        }
    }


    fun resetState(){
        requestState = RequestState.Idle
    }

    fun getCoinAddress(coin: String, symbol: String){
        requestState = RequestState.Loading
        screenModelScope.launch(Dispatchers.IO) {
            myCoin = coin
            mySymbol = symbol
            address = wally.getAddress(coin)
            getQr(address)
            withContext(Dispatchers.Main){
                requestState = RequestState.Success(Unit)
            }
        }
    }

    private fun roundToTwoDecimalPlaces(value: Double): Double{
        return (value * 100.0).roundToInt() / 100.0
    }

    fun tokenSomething(): Flow<List<Token>> = flow {
        while (true) {
            val activeTokens = symbol.value.zip(name.value) { symbol, name ->
                if (cryptoSwitchStates.value[name] == true){
                    val price = roundToTwoDecimalPlaces(Random.nextDouble(1000.0, 10000.0))
                    val change = roundToTwoDecimalPlaces(Random.nextDouble(-10.0, 10.0))
                    Token(name, symbol, price, change)
                } else {
                    null
                }
            }.filterNotNull()
            emit(activeTokens)
            delay(5000)
        }
    }

}

class CryptoSwitchStateManager(private val settings: Settings){
    fun saveSwitchState(cryptoId: String, isActive: Boolean){
        settings.putBoolean(cryptoId, isActive)
    }

    fun getSwitchState(cryptoId: String): Boolean{
        return settings.getBoolean(cryptoId, defaultValue = true)
    }
}