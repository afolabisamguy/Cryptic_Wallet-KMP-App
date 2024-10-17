package presentation.screen.creatingWallet

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import components.Topbar
import crypticwallet.composeapp.generated.resources.Res
import crypticwallet.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import theme.PrimaryColor
import viewModels.CreateWalletViewModel

class ConfirmPhraseScreen: Screen {
    @Composable
    override fun Content() {
        val viewModel: CreateWalletViewModel = getScreenModel()
        val phrases by viewModel.mnemonic.collectAsState()
        val navigator = LocalNavigator.currentOrThrow


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            Topbar("Confirm Secret Phrase", false)

            Text(
                text = "Please tap on the correct answer of the seed phrases below.",
                fontSize = 15.sp,
                modifier = Modifier.padding(top = 20.dp, start = 10.dp)
            )
            val items = remember {
                phrases.chunked(3).map { it.shuffled() }
            }
            var correctText: MutableList<String> = mutableListOf()
            for (i in phrases.indices step 3) {
                correctText.add(phrases[i])
            }

            var selectedTexts by remember { mutableStateOf(List(items.size) { "" }) }

            var selectedIndices by remember { mutableStateOf(List(items.size) { null as Int? }) }

            val allRowsSelected = selectedIndices.all { it != null }

            Column {
                items.forEachIndexed { index, text ->
                    val value = 1 + 3 * index
                    Text(text = "Word #$value",
                        modifier = Modifier
                            .padding(start = 20.dp, top = 20.dp),
                        fontStyle = FontStyle.Italic
                    )
                    BoxRow(
                        items = text,
                        selected2Index = selectedIndices[index]
                    ) { selectedIndex ->
                        selectedIndices = selectedIndices.toMutableList().apply {
                            this[index] = selectedIndex
                        }
                        selectedTexts = selectedTexts.toMutableList().apply {
                            this[index] = text[selectedIndex]
                        }
                    }
                }
            }

            var showPopup by remember { mutableStateOf(false) }




            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    if (selectedTexts == correctText) {
                        navigator.push(Testing())
                    } else {
                        showPopup = true
                    }
                },
                enabled = allRowsSelected,
                colors = buttonColors(
                    backgroundColor = PrimaryColor(),
                    contentColor = Color.Blue
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = "Confirm",
                    modifier = Modifier.padding(5.dp)
                )
            }

            if (showPopup) {
                Dialog(onDismissRequest = { showPopup = false }) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(40.dp))
                            .background(MaterialTheme.colors.background)
                            .width(280.dp)
                            .height(200.dp)

                    ) {
                        Column(
                            modifier = Modifier
                                .align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val walletImage = painterResource(Res.drawable.compose_multiplatform)
                            Image(
                                painter = walletImage,
                                contentDescription = null,
                                modifier = Modifier.width(70.dp),
                                alignment = Alignment.Center
                            )
                            Text(
                                text = "Incorrect",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(7.dp)
                            )
                            Text(
                                text = "Selections not matched. Please try again.",
                                fontSize = 12.sp,

                                modifier = Modifier
                            )

                            Button(
                                onClick = { showPopup = false },
                                colors = buttonColors(
                                    backgroundColor = PrimaryColor(),
                                    contentColor = Color.Blue
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 30.dp, end = 30.dp, top = 20.dp, bottom = 0.dp)
                            ) {
                                Text(text = "Try Again")
                            }
                        }
                    }
                }
            }
        }
    }


    @Composable
    fun BoxRow(items: List<String>, selected2Index: Int?, onClick: (Int) -> Unit) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, text ->
                Box(
                    modifier = Modifier
                        .height(25.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .width(100.dp)
                        .clickable { onClick(index) }
                        .background(if (selected2Index == index) Color.Blue else PrimaryColor()),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = text,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp,
                        modifier = Modifier
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}