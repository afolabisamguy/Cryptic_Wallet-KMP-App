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
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import components.Topbar
import crypticwallet.composeapp.generated.resources.Res
import crypticwallet.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import theme.PrimaryColor

class EnsureScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var enabler = false
        var walletImage = painterResource(Res.drawable.compose_multiplatform)
        var isChecked by remember { mutableStateOf(false) }
        var isChecked1 by remember { mutableStateOf(false) }
        var isChecked2 by remember { mutableStateOf(false) }
        Column(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background))
        {
            Topbar("Ensure", false)

            Spacer(modifier = Modifier.weight(0.1f))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = walletImage,
                    contentDescription = null,
                    modifier = Modifier.width(200.dp),
                    alignment = Alignment.Center
                )
                Text(
                    modifier = Modifier.padding(top = 22.dp),
                    text = "This secret phrase is the master key to your wallet",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "Tap on all checkboxes to confirm you understand the importance of your secret phrase",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )

            }

            Spacer(modifier = Modifier.height(5.dp))


            Box(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(7.dp)
                .clickable { isChecked = !isChecked }
                .clip(RoundedCornerShape(8.dp))
                .background(PrimaryColor()),
                contentAlignment = Alignment.CenterStart){
                Checkbox(checked = isChecked, onCheckedChange = {isChecked = it} )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Trust Wallet does not keep a copy of your secret phrase.",
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 45.dp))
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp)
                .clickable { isChecked1 = !isChecked1 }
                .clip(RoundedCornerShape(8.dp))
                .background(PrimaryColor()),
                contentAlignment = Alignment.CenterStart){
                Checkbox(checked = isChecked1, onCheckedChange = {isChecked1 = it} )
                Spacer(modifier = Modifier.width(5.dp))
                Column {
                    Text(
                        text = "Saving this digitally in plain text is NOT recommended.",
                        fontSize = 12.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 45.dp)
                    )

                    Text(
                        text = "Examples include screenshots, text files, or emailing yourself",
                        fontSize = 12.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 45.dp)
                    )
                }
            }


            Box(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(7.dp)
                .clickable { isChecked2 = !isChecked2 }
                .clip(RoundedCornerShape(8.dp))
                .background(PrimaryColor()),
                contentAlignment = Alignment.CenterStart){
                Checkbox(checked = isChecked2, onCheckedChange = {isChecked2 = it} )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Write down your secret phrase, and store it in a secure offline location!",
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 45.dp))
            }

            if (isChecked && isChecked1 && isChecked2){
                enabler = true
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = {
                navigator.push(SecretPhraseScreen())
            },
                enabled = enabler,
                colors = buttonColors(
                    backgroundColor = PrimaryColor(),
                    contentColor = Color.Blue
                ),
                modifier = Modifier
                    .padding(bottom = 25.dp, start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .fillMaxWidth()) {
                Text(
                    text = "Continue",
                    modifier = Modifier.padding(7.dp),
                )
            }

        }
    }
}