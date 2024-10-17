package presentation.screen.creatingWallet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import presentation.screen.transactions.ReceiveScreen
import utilities.RequestState
import viewModels.ReceiveViewModel

class Testing3(name: String, symbol: String): Screen {
    private val myName = name
    private val mySymbol = symbol


    @Composable
    override fun Content() {
        val viewModel: ReceiveViewModel = getScreenModel()
        val state = viewModel.state
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            viewModel.getCoinAddress(myName, mySymbol)
        }

        LaunchedEffect(state) {
            if (state is RequestState.Success) {
                navigator.replace(ReceiveScreen())
            }
        }

        DisposableEffect(Unit){
            onDispose {
                viewModel.resetState()
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (state) {
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
                    Text(text = "Success")
                }
            }
        }
    }
}