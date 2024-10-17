package viewModels

import Wallet.WalletCore
import Wallet.byteArrayToimage
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.DatabaseHelper
import data.TokenImageLogos
import io.kamel.core.utils.URI
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import presentation.screen.creatingWallet.removeSpaces
import repository.downloadImage
import repository.getTokens

class RepoViewModel(
    private val databaseHelper: DatabaseHelper
): ScreenModel {

    val wally = WalletCore()
    val sammy = wally.getNetworkName()

    private var _image = MutableStateFlow<Map<String, TokenImageLogos>>(emptyMap())
    val image = _image.asStateFlow()

    private var _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

//
//    fun fetchAndCacheImages(name: String, logoURI: String) = screenModelScope.launch(Dispatchers.IO) {
//        if (_isLoading.value) return@launch
//
//        _isLoading.value = true
//
//        try {
//            val cachedImage = databaseHelper.selectImage(name)
//            if (cachedImage != null) {
//                _image.value = cachedImage
//            } else {
//                val downloadedImage = downloadImage(logoURI)
//                if (downloadedImage != null) {
//                    val downloadImaging = TokenImageLogos(name, logoURI, downloadedImage)
//                    databaseHelper.insertImage(name, logoURI, downloadedImage)
//                    _image.value = downloadImaging
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        } finally {
//            _isLoading.value = false
//        }
//    }

    fun fetchImage(name: String, onImageFetched: (ByteArray?) -> Unit) = screenModelScope.launch(Dispatchers.IO) {
        val cachedImage = databaseHelper.selectImage(name)
        if (cachedImage != null){
            onImageFetched(cachedImage.logoImage)
        } else {
            val uri = generateImageUrl(name)
            val downloadImage = downloadImage(uri)
            if (downloadImage != null){
                databaseHelper.insertImage(name, uri, downloadImage)
            }
            onImageFetched(downloadImage)
        }
    }


    private fun generateImageUrl(name: String): String{
        val lowerCaseName = name.lowercase()
        val sanitizedName = removeSpaces(lowerCaseName)
        return "https://github.com/trustwallet/assets/blob/master/blockchains/${sanitizedName}/info/logo.png?raw=true"
    }

    fun storeImage(name: String, uri: String) = screenModelScope.launch(Dispatchers.IO) {
        val downloadedImage = downloadImage(uri)
        if (downloadedImage != null) {
            val downloadImaging = TokenImageLogos(name, uri, downloadedImage)
            databaseHelper.insertImage(name, uri, downloadedImage)
        }
    }

    fun clearImages() = screenModelScope.launch(Dispatchers.IO) {
        databaseHelper.clearImages()
    }
}