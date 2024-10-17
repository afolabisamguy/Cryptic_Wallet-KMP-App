package Wallet

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.alphatech.crypto1.GlobalVariables
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.zxing.BarcodeFormat
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.journeyapps.barcodescanner.CaptureActivity
import data.local.db.Database
import wallet.core.jni.BitcoinAddress
import wallet.core.jni.CoinType
import wallet.core.jni.CoinTypeConfiguration
import wallet.core.jni.HDWallet
import wallet.core.jni.StoredKey
import wallet.core.jni.StoredKeyEncryption
import wallet.core.jni.proto.LiquidStaking.Coin
import java.io.File

actual class WalletCore {
    init {
        System.loadLibrary("TrustWalletCore")
    }


    private val fullNetwork = listOf(
        CoinType.AETERNITY,
        CoinType.AION,
        CoinType.BINANCE,
        CoinType.BITCOIN,
        CoinType.BITCOINCASH,
        CoinType.BITCOINGOLD,
        CoinType.CALLISTO,
        CoinType.CARDANO,
        CoinType.COSMOS,
        CoinType.PIVX,
        CoinType.DASH,
        CoinType.DECRED,
        CoinType.DIGIBYTE,
        CoinType.DOGECOIN,
        CoinType.EOS,
        CoinType.WAX,
        CoinType.ETHEREUM,
        CoinType.ETHEREUMCLASSIC,
        CoinType.FIO,
        CoinType.GOCHAIN,
        CoinType.GROESTLCOIN,
        CoinType.ICON,
        CoinType.IOTEX,
        CoinType.KAVA,
        CoinType.KIN,
        CoinType.LITECOIN,
        CoinType.MONACOIN,
        CoinType.NEBULAS,
        CoinType.NULS,
        CoinType.NANO,
        CoinType.NEAR,
        CoinType.NIMIQ,
        CoinType.ONTOLOGY,
        CoinType.POANETWORK,
        CoinType.QTUM,
        CoinType.XRP,
        CoinType.SOLANA,
        CoinType.STELLAR,
        CoinType.TEZOS,
        CoinType.THETA,
        CoinType.THUNDERCORE,
        CoinType.NEO,
        CoinType.VICTION,
        CoinType.TRON,
        CoinType.VECHAIN,
        CoinType.VIACOIN,
        CoinType.WANCHAIN,
        CoinType.ZCASH,
        CoinType.FIRO,
        CoinType.ZILLIQA,
        CoinType.ZELCASH,
        CoinType.RAVENCOIN,
        CoinType.WAVES,
        CoinType.TERRA,
        CoinType.TERRAV2,
        CoinType.HARMONY,
        CoinType.ALGORAND,
        CoinType.KUSAMA,
        CoinType.POLKADOT,
        CoinType.FILECOIN,
        CoinType.MULTIVERSX,
        CoinType.BANDCHAIN,
        CoinType.SMARTCHAINLEGACY,
        CoinType.SMARTCHAIN,
        CoinType.TBINANCE,
        CoinType.OASIS,
        CoinType.POLYGON,
        CoinType.THORCHAIN,
        CoinType.BLUZELLE,
        CoinType.OPTIMISM,
        CoinType.ZKSYNC,
        CoinType.ARBITRUM,
        CoinType.ECOCHAIN,
        CoinType.AVALANCHECCHAIN,
        CoinType.XDAI,
        CoinType.FANTOM,
        CoinType.CRYPTOORG,
        CoinType.CELO,
        CoinType.RONIN,
        CoinType.OSMOSIS,
        CoinType.ECASH,
        CoinType.IOST,
        CoinType.CRONOSCHAIN,
        CoinType.SMARTBITCOINCASH,
        CoinType.KUCOINCOMMUNITYCHAIN,
        CoinType.BITCOINDIAMOND,
        CoinType.BOBA,
        CoinType.SYSCOIN,
        CoinType.VERGE,
        CoinType.ZEN,
        CoinType.METIS,
        CoinType.AURORA,
        CoinType.EVMOS,
        CoinType.NATIVEEVMOS,
        CoinType.MOONRIVER,
        CoinType.MOONBEAM,
        CoinType.KAVAEVM,
        CoinType.KLAYTN,
        CoinType.METER,
        CoinType.OKXCHAIN,
        CoinType.STRATIS,
        CoinType.KOMODO,
        CoinType.NERVOS,
        CoinType.EVERSCALE,
        CoinType.APTOS,
        CoinType.NEBL,
        CoinType.HEDERA,
        CoinType.SECRET,
        CoinType.NATIVEINJECTIVE,
        CoinType.AGORIC,
        CoinType.TON,
        CoinType.SUI,
        CoinType.STARGAZE,
        CoinType.POLYGONZKEVM,
        CoinType.JUNO,
        CoinType.STRIDE,
        CoinType.AXELAR,
        CoinType.CRESCENT,
        CoinType.KUJIRA,
        CoinType.IOTEXEVM,
        CoinType.NATIVECANTO,
        CoinType.COMDEX,
        CoinType.NEUTRON,
        CoinType.SOMMELIER,
        CoinType.FETCHAI,
        CoinType.MARS,
        CoinType.UMEE,
        CoinType.COREUM,
        CoinType.QUASAR,
        CoinType.PERSISTENCE,
        CoinType.AKASH,
        CoinType.NOBLE,
        CoinType.SCROLL,
        CoinType.ROOTSTOCK,
        CoinType.THETAFUEL,
        CoinType.CONFLUXESPACE,
        CoinType.ACALA,
        CoinType.ACALAEVM,
        CoinType.OPBNB,
        CoinType.NEON,
        CoinType.BASE,
        CoinType.SEI,
        CoinType.ARBITRUMNOVA,
        CoinType.LINEA,
        CoinType.GREENFIELD,
        CoinType.MANTLE,
        CoinType.ZENEON,
        CoinType.INTERNETCOMPUTER,
        CoinType.TIA,
        CoinType.MANTAPACIFIC,
        CoinType.NATIVEZETACHAIN,
        CoinType.ZETAEVM,
        CoinType.DYDX,
        CoinType.MERLIN,
        CoinType.LIGHTLINK,
        CoinType.BLAST,
        CoinType.BOUNCEBIT,
        CoinType.ZKLINKNOVA,
    )

    private val coins = listOf(
        CoinType.ETHEREUM,
        CoinType.BITCOIN,
        CoinType.DOGECOIN,
        CoinType.EOS,
        CoinType.FIO,
        CoinType.TRON,
        CoinType.ACALA,
        CoinType.ACALAEVM,
        CoinType.POLKADOT,
        CoinType.POLYGON,
        CoinType.SOLANA,
        CoinType.AVALANCHECCHAIN,
        CoinType.BITCOINCASH,
        CoinType.BITCOINGOLD,
        CoinType.BITCOINDIAMOND,
    )


    private val earnCoins = listOf(
        CoinType.ETHEREUM,
        CoinType.SOLANA,
        CoinType.TRON,
        CoinType.POLKADOT,
        CoinType.COSMOS,
        CoinType.NATIVEINJECTIVE,
        CoinType.NEAR,
        CoinType.SUI,
        CoinType.OSMOSIS,
        CoinType.TERRA,
        CoinType.NATIVEZETACHAIN,
        CoinType.CARDANO,
        CoinType.NATIVEEVMOS,
        CoinType.STARGAZE,
        CoinType.CRONOSCHAIN,
        CoinType.KAVA,
        CoinType.KUSAMA,
        CoinType.STRIDE,
        CoinType.TEZOS,
        CoinType.JUNO,
        CoinType.AKASH,
        CoinType.AGORIC,
        CoinType.AXELAR,
        CoinType.SEI
    )


    private val network = listOf(
        CoinType.BITCOIN,
        CoinType.ETHEREUM,
        CoinType.CARDANO,
        CoinType.XRP,
        CoinType.SOLANA,
        CoinType.DOGECOIN,
        CoinType.SMARTCHAIN,
        CoinType.TRON,
        CoinType.POLKADOT,
        CoinType.AVALANCHECCHAIN,
        CoinType.POLYGON,
        CoinType.LITECOIN,
        CoinType.CRONOSCHAIN,
        CoinType.CRYPTOORG,
        CoinType.INTERNETCOMPUTER,
        CoinType.NEAR,
        CoinType.TON,
        CoinType.BITCOINCASH,
        CoinType.STELLAR,
        CoinType.COSMOS,
        CoinType.ALGORAND,
        CoinType.ETHEREUMCLASSIC,
        CoinType.APTOS,
        CoinType.MULTIVERSX,
        CoinType.VECHAIN,
        CoinType.FILECOIN,
        CoinType.TEZOS,
        CoinType.KUCOINCOMMUNITYCHAIN,
        CoinType.ZCASH,
        CoinType.MANTLE,
        CoinType.THETA,
        CoinType.ECOCHAIN,
        CoinType.FANTOM,
        CoinType.ARBITRUM,
        CoinType.SUI,
        CoinType.NATIVEINJECTIVE,
        CoinType.THORCHAIN,
        CoinType.ZILLIQA,
        CoinType.CONFLUXESPACE,
        CoinType.WAVES,
        CoinType.KLAYTN,
        CoinType.DASH,
        CoinType.CELO,
        CoinType.HARMONY,
        CoinType.DECRED,
        CoinType.KAVA,
        CoinType.KAVAEVM,
        CoinType.ICON,
        CoinType.QTUM,
        CoinType.OSMOSIS,
        CoinType.RAVENCOIN,
        CoinType.ONTOLOGY,
        CoinType.KUSAMA,
        CoinType.IOTEX,
        CoinType.IOTEXEVM,
        CoinType.DIGIBYTE,
        CoinType.MOONBEAM,
        CoinType.NANO,
        CoinType.SEI,
        CoinType.AXELAR,
        CoinType.TERRA,
        CoinType.AURORA,
        CoinType.BOUNCEBIT,
        CoinType.AKASH,
        CoinType.METIS,
        CoinType.ACALA,
        CoinType.ACALAEVM,
        CoinType.THUNDERCORE,
        CoinType.BASE,
        CoinType.LINEA,
        CoinType.VICTION,
        CoinType.MOONRIVER,
        CoinType.WANCHAIN,
        CoinType.GROESTLCOIN,
        CoinType.MOONBEAM,
        CoinType.OPBNB,
        CoinType.BOBA,
        CoinType.AETERNITY,
        CoinType.FIO,
        CoinType.FIRO,
        CoinType.AION,
        CoinType.NIMIQ,
        CoinType.GOCHAIN,
        CoinType.CALLISTO,
        CoinType.NEBULAS,
        CoinType.VIACOIN,
        CoinType.JUNO,
        CoinType.NEON,
        CoinType.AGORIC,
        CoinType.RONIN,
        CoinType.EVMOS,
        CoinType.NATIVEEVMOS,
        CoinType.STRIDE,
        CoinType.STARGAZE,
        CoinType.MANTAPACIFIC,
        CoinType.BLAST,
        CoinType.GREENFIELD,
        CoinType.MERLIN,
        CoinType.NATIVEZETACHAIN,
        CoinType.NEUTRON,
        CoinType.SCROLL,
        CoinType.ZETAEVM,
        CoinType.POLYGONZKEVM,
        CoinType.ZKLINKNOVA,
        CoinType.ZKSYNC,
    )

    val coinTypes = mapOf(
        "Ethereum" to CoinType.ETHEREUM,
        "Bitcoin" to CoinType.BITCOIN,
        "Dogecoin" to CoinType.DOGECOIN,
        "EOS" to CoinType.EOS,
        "FIO" to CoinType.FIO,
        "Tron" to CoinType.TRON,
        "Acala" to CoinType.ACALA,
        "Acala EVM" to CoinType.ACALAEVM,
        "Polkadot" to CoinType.POLKADOT,
        "Polygon" to CoinType.POLYGON,
        "Solana" to CoinType.SOLANA,
        "Avalanche" to CoinType.AVALANCHECCHAIN,
        "Bitcoin Cash" to CoinType.BITCOINCASH,
        "Bitcoin Gold" to CoinType.BITCOINGOLD,
        "Bitcoin Diamond" to CoinType.BITCOINDIAMOND,
        "Ethereum Classic" to CoinType.ETHEREUMCLASSIC,
        "AION" to CoinType.AION,
    )

    private val password = "samguy100"
    private val name = "My Wallet"
    private val bPassword = password.toByteArray()


    actual fun createWallet(): String {
        val wallet = HDWallet(128, "")
        val mnemonic = wallet.mnemonic()
        val value = GlobalVariables.globalVariable

        val privateKey = wallet.getKeyForCoin(CoinType.BITCOIN)
        val publicKey = privateKey.getPublicKeySecp256k1(true)



        return mnemonic

    }

    actual suspend fun storeWallet(walletName: String, mnemonic: String) {
        // I'm Going to be hardcoding the context for now,
        // if it works please and please find a better solution
        // Finally found a better solution :)
        val contexts = GlobalVariables.globalVariable
        val context = contexts?.filesDir?.path
        val walletFilepath = "${context}/${walletName}"
        val storedKey = StoredKey.importHDWalletWithEncryption(
            mnemonic,
            name,
            bPassword,
            CoinType.ETHEREUM,
            StoredKeyEncryption.AES128CTR
        )
        val sam = storedKey.store(walletFilepath)
        println("Wallet stored successfully")
        println(walletFilepath)
        println(sam)

    }

    actual suspend fun retrieveWallet(walletName: String): Boolean {
        try {
            val contexts = GlobalVariables.globalVariable
            val context = contexts?.filesDir?.path
            val walletFilepath = "${context}/${walletName}"
            val storedKey = StoredKey.load(walletFilepath)
            val dmnemonic = storedKey.decryptMnemonic(bPassword)
            val wallet = HDWallet(dmnemonic, "")
            val bitcoin = wallet.getAddressForCoin(CoinType.BITCOIN)
            val symbol = CoinTypeConfiguration.getSymbol(CoinType.BITCOIN)
            println(dmnemonic)
            println(wallet)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    actual suspend fun deleteWallet(walletName: String) {
        val contexts = GlobalVariables.globalVariable
        val context = contexts?.filesDir?.path
        val walletFilepath = "${context}/${walletName}"
        val walletFile = File(walletFilepath)
        if (walletFile.exists()) {
            walletFile.delete()
        }
    }

    actual fun getEarnCoins(): List<String>{
        val coinName = mutableListOf<String>()
        earnCoins.forEachIndexed { _, coin ->
            val samuel = CoinTypeConfiguration.getName(coin)
            coinName.add(samuel)
        }
        return coinName
    }

    actual fun getEarnSymbol(): List<String>{
        val symbol = mutableListOf<String>()
        earnCoins.forEachIndexed { _, coin ->
            val samuel = CoinTypeConfiguration.getSymbol(coin)
            symbol.add(samuel)
        }
        return symbol
    }

    actual fun getSymbol(): List<String> {

        val symbol = mutableListOf<String>()
        coins.forEachIndexed { _, coin ->
            val samuel = CoinTypeConfiguration.getSymbol(coin)
            symbol.add(samuel)
        }

        val coinId = mutableListOf<String>()
        coins.forEachIndexed { _, coin ->
            val samuel = CoinTypeConfiguration.getID(coin)
            coinId.add(samuel)
        }

        return symbol
    }

    actual fun getName(): List<String> {
        val coinName = mutableListOf<String>()
        coins.forEachIndexed { _, coin ->
            val samuel = CoinTypeConfiguration.getName(coin)
            coinName.add(samuel)
        }
        return coinName
    }

    actual fun getNetworkName(): List<String> {
        val networkName = mutableListOf<String>()
        network.forEachIndexed { _, coin ->
            val samuel = CoinTypeConfiguration.getName(coin)
            networkName.add(samuel)
        }
        return networkName
    }

    actual fun getFullNetworkName(): List<String> {
        val myNetworkName = mutableListOf<String>()
        fullNetwork.forEach { coin ->
            val myCoin = CoinTypeConfiguration.getName(coin)
            myNetworkName.add(myCoin)
        }
        return myNetworkName
    }

    fun getCoinTypeFromName(name: String): CoinType? {
        return CoinType.values().find { coin ->
            CoinTypeConfiguration.getName(coin) == name
        }
    }



    actual fun getFullNetworkSymbol(): List<String> {
        val myNetworkSymbol = mutableListOf<String>()
        fullNetwork.forEach { coin ->
            val mySymbol = CoinTypeConfiguration.getSymbol(coin)
            myNetworkSymbol.add(mySymbol)
        }
        return myNetworkSymbol
    }

    actual suspend fun getAddress(coin: String): String {
        val coinType = getCoinTypeFromName(coin)
        val wallet = retrieveWalletInternally()
        val address = wallet.getAddressForCoin(coinType)
        return address
    }
}

actual class UrlOpener actual constructor() {
    actual fun openUrl(url: String) {
        val context = GlobalVariables.secondGlobal
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context?.startActivity(intent)
    }

}

actual class MyClipboardManager {
    private val context = GlobalVariables.globalVariable
    actual fun copyToClipboard(text: String) {
        val clipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", text)
        clipboard.setPrimaryClip(clip)
    }

    actual fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}








fun retrieveWalletInternally(): HDWallet{
    val password = "samguy100"
    val walletName = "Wallet1"
    val bPassword = password.toByteArray()
    val contexts = GlobalVariables.globalVariable
    val context = contexts?.filesDir?.path
    val walletFilepath = "${context}/${walletName}"
    val storedKey = StoredKey.load(walletFilepath)
    val dmnemonic = storedKey.decryptMnemonic(bPassword)
    val wallet = HDWallet(dmnemonic, "")
    return wallet
}

actual class QRCodeGenerator {

    private val scanner: BarcodeScanner = BarcodeScanning.getClient()

    actual fun generateQRCode(text: String): ByteArray {
        val writer = QRCodeWriter()
        val bitMatrix: BitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, 512, 512)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

        for (x in 0 until width) {
            for (y in 0 until height) {
                bmp.setPixel(x, y, if (bitMatrix[x, y]) 0x000000 else 0xFFFFFF)
            }
        }

        val stream = java.io.ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    actual fun qrImage(text: String): ImageBitmap {
        val qrCodeByteArray = generateQRCode(text)
        val bitmap = BitmapFactory.decodeByteArray(qrCodeByteArray, 0, qrCodeByteArray.size)
        return bitmap.asImageBitmap()
    }

//    actual fun scanQRCode(context: Context, onResult: (String?) -> Unit) {
//        val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> =
//            ProcessCameraProvider.getInstance(context)
//        cameraProviderFuture.addListener(
//            {
//                val cameraProvider = cameraProviderFuture.get()
//                bindCameraUseCases(context, cameraProvider)
//            },
//            ContextCompat.getMainExecutor(context)
//        )
//    }
//
//    private fun bindCameraUseCases(context: Context, cameraProvider: ProcessCameraProvider) {
//        val preview = Preview.Builder().build().also {
//            it.setSurfaceProvider(previewView.surfaceProvider)
//        }
//        val imageAnalysis = ImageAnalysis.Builder()
//            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
//            .build()
//
//        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(context), { imageProxy ->
//            processImageProxy(imageProxy)
//        })
//
//        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
//
//        try {
//            cameraProvider.unbindAll()
//            cameraProvider.bindToLifecycle(
//                context as LifecycleOwner,
//                cameraSelector,
//                preview,
//                imageAnalysis
//            )
//        } catch (exc: Exception) {
//            Log.e("QRCodeScan", "Use case binding failed: ${exc.message}")
//        }
//    }
//
//    private fun processImageProxy(imageProxy: ImageProxy) {
//        val image = imageProxy.image ?: return
//        val buffer = image.planes[0].buffer
//        val bytes = ByteArray(buffer.remaining())
//        buffer.get(bytes)
//
//        val source = PlanarYUVLuminanceSource(
//            bytes, imageProxy.width, imageProxy.height, 0, 0,
//            imageProxy.width, imageProxy.height, false
//        )
//        val bitmap = BinaryBitmap(HybridBinarizer(source))
//        try {
//            val result = MultiFormatReader().decode(bitmap)
//            Log.d("QrCodeScan", "Qr Code detected: ${result.text}")
//        } catch (e: NotFoundException) {
//            e.printStackTrace()
//        } finally {
//            imageProxy.close()
//        }
//    }
    actual fun scanQRCode(onResult: (String) -> Unit) {
    }
}

class QrCodeScanActivity: CaptureActivity()


@Composable
actual fun setSystemBar(newTheme: Boolean) {

    if (newTheme) {
        GlobalVariables.barColorState.value = Color.Black
    } else
        GlobalVariables.barColorState.value = Color.White

}


actual fun createDataStore(): DataStore<Preferences> {
    val context = GlobalVariables.thirdGlobal
    requireNotNull(context){"Context cannot be null"}
    return AppSettings.getDataStore(
        producePath = {
            context.filesDir
                .resolve(dataStoreFileName)
                .absolutePath
        }
    )
}

actual class DriverFactory actual constructor() {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, GlobalVariables.thirdGlobal!!, "database.db")
    }
}


actual fun byteArrayToimage(byteArray: ByteArray): ImageBitmap? {
    val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    return bitmap?.asImageBitmap()
}

