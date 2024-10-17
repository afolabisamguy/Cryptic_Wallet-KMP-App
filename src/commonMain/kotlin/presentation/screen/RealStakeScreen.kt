package presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import components.Topbar

class RealStakeScreen(
    val mySymbol: String
): Screen {
    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Topbar("Stake $mySymbol", true)

            Spacer(modifier = Modifier.height(16.dp))
            Text("Amount to stake", modifier = Modifier.padding(start = 10.dp))
            OutlinedTextField(
                value = "",
                onValueChange = { /* Handle value change */ },
                label = { Text("$mySymbol Amount") },
                modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
                trailingIcon = {
                    Text(
                        text = "Max",
                        color = Color.Blue,
                        modifier = Modifier.clickable { /* Handle Max click */ }
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "= 0.00",
                modifier = Modifier.fillMaxWidth().padding(start = 10.dp),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* Handle select validators click */ }
                    .padding(12.dp)
            ) {
                Text(text = "Select validators", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                backgroundColor = Color(0xFFFFF3CD)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "* Funds will be available in 28 days\n" +
                                "* You can unstake or redelegate at any time; normal network fees apply\n" +
                                "* Only a certain number of validators at a time can participate in the consensus and be nominated to rewards",
                        color = Color(0xFF856404)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { /* Handle Continue action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE0E0E0))
            ) {
                Text(text = "Continue", color = Color.Gray)
            }
        }
    }
}