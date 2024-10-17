package presentation.screen.creatingWallet

import Wallet.MyClipboardManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import components.BottomSheetContent
import components.Topbar
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.CaretSquareRight
import compose.icons.fontawesomeicons.regular.Copy
import kotlinx.coroutines.launch
import theme.PrimaryColor
import viewModels.CreateWalletViewModel

class SecretPhraseScreen: Screen {
    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun Content() {
        val viewModel: CreateWalletViewModel = getScreenModel()
        val mnemonic by viewModel.mnemonic.collectAsState()
        val mnemonic2 = viewModel.myMnemonic
        val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        val coroutineScope = rememberCoroutineScope()

        val sam = MyClipboardManager()

        val navigator = LocalNavigator.currentOrThrow

        println(mnemonic)
//        Log.d("CreateWalletViewModel", mnemonic)


        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetContent = {
                BottomSheetContent { coroutineScope.launch { sheetState.hide() } }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
            ) {
                Topbar("Secret phrase", true)
                for (i in 0 until 6) {
                    Row(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp, top = 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .height(27.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .width(180.dp)
                                .background(PrimaryColor()),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${i + 1}. ${mnemonic[i]}",
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                modifier = Modifier
                            )
                        }

                        Box(
                            modifier = Modifier
                                .height(30.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .width(180.dp)
                                .background(PrimaryColor()),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${i + 7}. ${mnemonic[i + 6]}",
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                modifier = Modifier
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .clickable {
                            sam.copyToClipboard(mnemonic2)
                            sam.showToast("Copied to Clipboard")
                        }
                        .align(Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {

                    Icon(
                        imageVector = FontAwesomeIcons.Regular.Copy,
                        contentDescription = "Copy",
                        modifier = Modifier.padding(5.dp).size(25.dp)
                    )

                    Text(
                        text = "Copy to clipboard",
                        modifier = Modifier.padding(5.dp)
                    )

                }

                Spacer(modifier = Modifier.weight(1f))


                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .clickable {
                            coroutineScope.launch {
                                sheetState.show()
                            }
                        }
                        .padding(start = 10.dp, end = 10.dp)
                        .background(PrimaryColor()),
                ) {
                    Row(
                        modifier = Modifier.padding(5.dp),

                        ) {
                        Icon(
                            imageVector = Icons.Rounded.Info,
                            contentDescription = "Info",
                            modifier = Modifier.padding(5.dp)
                        )
                        Text(
                            text = "Never share your secret phrase with anyone, and store it securely!",
                            fontSize = 10.sp,
                            modifier = Modifier.padding(top = 9.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = FontAwesomeIcons.Regular.CaretSquareRight,
                            contentDescription = "Info",
                            modifier = Modifier.padding(top = 3.dp).size(25.dp)
                        )
                    }
                }


                Button(
                    onClick = {
                        navigator.push(ConfirmPhraseScreen())
                    },
                    colors = buttonColors(
                        backgroundColor = PrimaryColor(),
                        contentColor = Color.Blue
                    ),
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Continue",
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }
    }
}