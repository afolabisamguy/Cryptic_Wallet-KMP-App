package presentation.screen.creatingWallet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import components.Topbar

class RestoreScreen(coin: String): Screen {
    val myName = coin
    @Composable
    override fun Content() {
        var text by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Topbar(title = "Restore Wallet", true)

            // Wallet Name Field
            Text(text = "Wallet Name", style = MaterialTheme.typography.body1)
            TextField(
                value = "$myName Wallet",
                onValueChange = { /* Handle wallet name input */ },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Enter your wallet name") }
            )

            // Secret Phrase Field
            Text(text = "Secret Phrase", style = MaterialTheme.typography.body1)
            TextField(
                value = text, // Current text in the field
                onValueChange = {
                    text = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp), // Increased height for larger text field
                placeholder = { Text("Enter your secret phrase") },
                maxLines = 10, // Allows multiline input
                trailingIcon = {
                    Text(
                        text = "Paste",
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .clickable { /* Handle paste action */ }
                            .padding(end = 8.dp)
                    )
                }
            )

            Text(
                text = "Typically 12 (sometimes 18 or 24) words separated by single spaces.",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.caption
            )

            // Restore Wallet Button
            Button(
                onClick = { /* Handle wallet restoration */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(CircleShape)
            ) {
                Text(text = "Restore Wallet", style = MaterialTheme.typography.button)
            }

            // Additional Help
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .clickable { /* Provide more information */ },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "What is a secret phrase?",
                    color = MaterialTheme.colors.onBackground,
                )
            }
        }

    }
}