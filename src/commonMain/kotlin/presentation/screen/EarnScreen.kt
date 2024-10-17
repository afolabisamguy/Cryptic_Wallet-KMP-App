package presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Bitcoin
import crypticwallet.composeapp.generated.resources.Res
import crypticwallet.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import presentation.screen.creatingWallet.DisplayImage
import repository.StakingItemData
import viewModels.EarnViewModel
import viewModels.RepoViewModel


class EarnScreen: Screen {
    @Composable
    override fun Content() {
        var walletImage = painterResource(Res.drawable.compose_multiplatform)
        val viewModel: EarnViewModel = getScreenModel()
        val viewModel2: RepoViewModel = getScreenModel()
//        LaunchedEffect(Unit) {
//            viewModel.loadData()
//        }
        val coinName by viewModel.name.collectAsState()
        val coinSymbol by viewModel.symbol.collectAsState()
        val coinApr by viewModel.apr.collectAsState()
        Column(modifier = Modifier.fillMaxSize()) {
            // Header
            Text(
                text = "Earn",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // Native Staking Section
            Text(
                text = "Native Staking",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp)
            )

            // Call to Action (Stake ETH section)
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .clickable { /* Handle click event */ },
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),  // Internal padding applied to content inside the card
                    verticalAlignment = Alignment.CenterVertically // Ensures content is centered vertically
                ) {
                    Image(
                        painter = walletImage,
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))  // Space between the image and the column
                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Stake Your ETH with Trust", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically // Align text and icon vertically
                        ) {
                            Text(text = "Stake now ")
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowRight,
                                contentDescription = "Stake"
                            )
                        }
                    }
                }
            }

            // Staking List
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp, bottom = 46.dp)
            ) {
                if (coinName.size > 1) {
                    item {
                        coinName.forEachIndexed { index, s ->
                            StakingItem(s, coinSymbol[index], coinApr[index], viewModel2)
                        }
                    }
                } else {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }


    @Composable
    fun StakingItem(name: String, symbol: String, apr: String, viewModel2: RepoViewModel) {
        val navigator = LocalNavigator.currentOrThrow
        var walletImage = painterResource(Res.drawable.compose_multiplatform)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navigator.push(StakeScreen(name, symbol, apr)) }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            DisplayImage(name, viewModel2)

            Spacer(modifier = Modifier.width(16.dp))

            // Name and Abbreviation
            Column(modifier = Modifier.weight(1f)) {
                Text(text = symbol)
                Text(text = name, color = Color.Gray)
            }

            // APR Value
            Row {
                Text(text = "APR", color = Color.Blue)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = ".", style = TextStyle(
                        baselineShift = BaselineShift(0.3f)
                    ),
                    color = Color.Blue
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "$apr%",
                    color = Color.Blue
                )
            }
        }
    }

// Sample data class for staking item

}
