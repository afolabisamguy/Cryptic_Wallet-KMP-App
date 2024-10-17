package com.alphatech.crypto1

import App
import Wallet.initializeSetttingsManager
import Wallet.setSystemBar
import android.content.Context
import com.russhwolf.settings.SharedPreferencesSettings
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.softartdev.theme.pref.ThemeEnum
import com.softartdev.theme.pref.ThemePrefs
import viewModels.CryptoSwitchStateManager
import java.lang.ref.WeakReference

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var settingsManager: CryptoSwitchStateManager

        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("crypto_prefs", Context.MODE_PRIVATE)
        val settings = SharedPreferencesSettings(sharedPreferences)
        initializeSetttingsManager(settings)
        installSplashScreen()
        setContent {

            GlobalVariables.globalVariable = LocalContext.current.applicationContext
            GlobalVariables.secondGlobal = LocalContext.current
            GlobalVariables.thirdGlobal = this
            App()
            SetBarColor(colorState = GlobalVariables.barColorState)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}


@Composable
fun SetBarColor(colorState: State<Color>){
    val systemUiController = rememberSystemUiController()
    val color by colorState
    LaunchedEffect(color) {
        systemUiController.setSystemBarsColor(
            color = color,
        )
    }
}


object GlobalVariables {
    private var _globalVariable: WeakReference<Context>? = null

    private var _secondGlobal: WeakReference<Context>? = null
    private var _thirdGlobal: WeakReference<Context>? = null

    var secondGlobal: Context?
        get() = _secondGlobal?.get()
        set(secondValue){
            _secondGlobal = secondValue?.let { WeakReference(it) }
        }

    var thirdGlobal: Context?
            get() = _thirdGlobal?.get()
            set(thirdValue){
                _thirdGlobal = thirdValue?.let { WeakReference(it) }
            }

    var globalVariable: Context?
        get() = _globalVariable?.get()
        set(value){
            _globalVariable = value?.let { WeakReference(it) }
        }
    var barColorState: MutableState<Color> = mutableStateOf(Color.White)

}
