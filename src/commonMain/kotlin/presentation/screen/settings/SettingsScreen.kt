package presentation.screen.settings

import Wallet.createDataStore
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import components.Topbar
import repository.DataStoreRepo
import viewModels.CreateWalletViewModel


class SettingsScreen: Screen {

    @Composable
    override fun Content() {
        val viewModel2: CreateWalletViewModel = getScreenModel()
        val dataStoreRepository = remember {
            DataStoreRepo(dataStore = createDataStore())
        }
//        val isLogged by AppSession.isLoggedIn.collectAsState()
        val isLogged by dataStoreRepository.isLoggedIn.collectAsState()

        Column {
            Topbar("Settings", false)
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(top = 65.dp, start = 5.dp, end = 5.dp),
        ) {
            // Dark Mode
            item {
                if (isLogged == true) {
                    SettingsFirstComp(show = true)
                } else
                    SettingsFirstComp(show = false)
                Divider(
                    color = Color.DarkGray,
                    thickness = 0.dp
                )
                if (isLogged == true) {
                    SettingsSecondComp()
                    Divider(
                        color = Color.DarkGray,
                        thickness = 0.dp
                    )
                }


                if (isLogged == true) { SettingsThirdComp(show = true) }

                Divider(
                    color = Color.DarkGray,
                    thickness = 0.dp
                )
                SettingsFourthComp()
                Divider(
                    color = Color.DarkGray,
                    thickness = 0.dp
                )
                SettingsLastComp()
            }
        }
    }
}