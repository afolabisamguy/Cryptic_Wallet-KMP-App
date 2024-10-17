package presentation.screen.creatingWallet

import Wallet.createDataStore
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import repository.DataStoreRepo
import utilities.RequestState
import viewModels.CreateWalletViewModel


class Testing2: Screen{
    @Composable
    override fun Content() {
        val viewModel: CreateWalletViewModel = getScreenModel()
        val state = viewModel.state
        val navigator = LocalNavigator.currentOrThrow
        val dataStoreRepository = remember {
            DataStoreRepo(dataStore = createDataStore())
        }
        LaunchedEffect(Unit){
            viewModel.deleteWallet()
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            when(state){
                is RequestState.Error -> {
                    Text(text = state.message)
                }
                RequestState.Idle -> {
                    Text(text = "Idle")
                }
                RequestState.Loading -> {
                    CircularProgressIndicator()
                }
                is RequestState.Success -> {
                    LaunchedEffect(state){
                        dataStoreRepository.updateLoginState(false)
                        navigator.popUntilRoot()
                    }
                }
            }
        }
    }
}