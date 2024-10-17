package presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import components.Topbar

class StakeScreen(
    val myname: String,
    val mysymbol: String,
    val apr: String
): Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Topbar("$myname($mysymbol)", true)

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = 4.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    StakingInfoRow("Available", "0 $mysymbol")
                    Spacer(modifier = Modifier.padding(8.dp))
                    StakingInfoRow("Staked", "0 $mysymbol")
                    Spacer(modifier = Modifier.padding(8.dp))
                    StakingInfoRow("Minimum Amount", "250 $mysymbol")
                    Spacer(modifier = Modifier.padding(8.dp))
                    StakingInfoRow("APR", "$apr%")
                    Spacer(modifier = Modifier.padding(8.dp))
                    StakingInfoRow("Lock Time", "28 days")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
                    .clickable { navigator.push(RealStakeScreen(mysymbol)) }
            ) {
                Text(
                    text = "Stake",
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Back",
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))
            }
            Divider(thickness = 0.dp, color = MaterialTheme.colors.onBackground)
        }
    }

    @Composable
    fun StakingInfoRow(label: String, value: String) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, color = Color.Gray)
            Text(text = value, fontWeight = FontWeight.Bold)
        }
    }
}