package viewModels

import Wallet.WalletCore
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import utilities.RequestState

class CreateWalletViewModel: ScreenModel {

    private val wally = WalletCore()
    val myMnemonic: String = WalletCore().createWallet()

    private val _mnemonic = MutableStateFlow(
        myMnemonic
            .split(" ").map { it.trim() })
    val mnemonic: StateFlow<List<String>> = _mnemonic


    private var requestState by mutableStateOf<RequestState<Unit>>(RequestState.Idle)
    val state: RequestState<Unit> get() = requestState

    fun storeWallet() {
        requestState = RequestState.Loading
        screenModelScope.launch(Dispatchers.IO){
            wally.storeWallet("Wallet1", myMnemonic)
            withContext(Dispatchers.Main){
                requestState = RequestState.Success(Unit)
            }
        }
    }

    fun retrieveWallet(): Boolean {
        var logged = false
        screenModelScope.launch {
            logged = wally.retrieveWallet("Wallet1")
        }
        return logged
    }

    fun deleteWallet(){
        requestState = RequestState.Loading
        screenModelScope.launch(Dispatchers.IO) {
            wally.deleteWallet("Wallet1")
            withContext(Dispatchers.Main) {
                requestState = RequestState.Success(Unit)
            }
        }
    }
    
}
