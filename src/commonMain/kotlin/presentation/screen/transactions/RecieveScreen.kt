package presentation.screen.transactions

import Wallet.WalletCore
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import components.Searchbar
import components.Topbar
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.brands.Bitcoin
import compose.icons.fontawesomeicons.solid.Copy
import compose.icons.fontawesomeicons.solid.Hashtag
import compose.icons.fontawesomeicons.solid.Share
import theme.PrimaryColor
import viewModels.ReceiveViewModel

class ReceiveScreen: Screen {
    @Composable
    override fun Content() {
        val viewModel: ReceiveViewModel = getScreenModel()
        val address = viewModel.address
        val qrImage = viewModel.qrImage
        val myCoin = viewModel.myCoin
        val mySymbol = viewModel.mySymbol
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(8.dp)
        ) {
            Topbar("Receive", true)

            Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colors.primary)
                    .height(50.dp)

            ) {
                Text(
                    buildAnnotatedString {
                        append("Only send ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(myCoin)
                        }
                        append(" (")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(mySymbol)
                        }
                        append(") assets to this address. Other assets will be lost forever.")
                    }
                )

            }
            Spacer(modifier = Modifier.height(150.dp))
            Box(
                modifier = Modifier.width(400.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row {
                        Icon(
                            imageVector = FontAwesomeIcons.Brands.Bitcoin,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = mySymbol)
                    }
                    Image(
                        bitmap = qrImage,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                    )
                    Text(text = address)

                    Spacer(modifier = Modifier.height(26.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(MaterialTheme.colors.background)
                                .size(70.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.background
                            )
                        ) {
                            Icon(
                                imageVector = FontAwesomeIcons.Solid.Copy,
                                contentDescription = "Settings",
                                modifier = Modifier.size(30.dp)
                            )

                        }

                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(MaterialTheme.colors.background)
                                .size(70.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.background
                            )
                        ) {
                            Icon(
                                imageVector = FontAwesomeIcons.Solid.Hashtag,
                                contentDescription = "Settings",
                                modifier = Modifier.size(30.dp)
                            )

                        }

                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(MaterialTheme.colors.background)
                                .size(70.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.background
                            )
                        ) {
                            Icon(
                                imageVector = FontAwesomeIcons.Solid.Share,
                                contentDescription = "Settings",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}