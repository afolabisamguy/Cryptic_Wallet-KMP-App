package presentation.screen.creatingWallet

import Wallet.byteArrayToimage
import Wallet.createDataStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import repository.DataStoreRepo
import viewModels.RepoViewModel
import kotlin.random.Random

class Testing4: Screen {
    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        val viewModel : RepoViewModel = getScreenModel()


        val sammy = viewModel.sammy
//        val sammyss = sammys.toSet()
//        val sammy = sammyss.toList()

//        LaunchedEffect(Unit){
//            viewModel.clearImages()
//            val hasduplicate = sammy.size != sammy.toSet().size
//            println("hasduplicate = $hasduplicate")
//        }
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            LazyColumn {
                sammy.forEachIndexed { _, name ->
//                    val names = name.lowercase()
//                    val namess = removeSpaces(names)
//                    val uri =
//                        "https://github.com/trustwallet/assets/blob/master/blockchains/${namess}/info/logo.png?raw=true"
                    item {
                        Text(name)
//                        Text(uri)
                        DisplayImage(name, viewModel)
                    }
                }
            }
        }

    }
}

@Composable
fun DisplayImage(name: String, viewModel: RepoViewModel) {
//    viewModel.clearImages()

    var imageBytes by remember { mutableStateOf<ByteArray?>(null) }

    LaunchedEffect(name) {
        viewModel.fetchImage(name) {
            imageBytes = it
        }
    }

    if (imageBytes != null) {
        val imageBitmap = byteArrayToimage(imageBytes!!)
        Image(
            bitmap = imageBitmap!!,
            contentDescription = null,
            modifier = Modifier.size(35.dp)
        )
    } else {
        val names = name.lowercase()
        val namess = removeSpaces(names)
        val uri =
            "https://github.com/trustwallet/assets/blob/master/blockchains/${namess}/info/logo.png?raw=true"
        KamelImage(
            asyncPainterResource(uri),
            contentDescription = null,
            modifier = Modifier.size(35.dp)
        )
    }
}
//
//    val cachedImages by viewModel.image.collectAsState()
//    viewModel.fetchImage(name)
//
//    val cachedImage = cachedImages[name]
//
//    if (cachedImage != null) {
//        val imageByte = cachedImage.logoImage
//        val imageBitmap = imageByte?.let { byteArrayToimage(it) }
//
//    } else {
//
//
//        viewModel.storeImage(name, url)
//    }
//}

fun removeSpaces(input: String): String{
    return input.replace(" ", "")
}