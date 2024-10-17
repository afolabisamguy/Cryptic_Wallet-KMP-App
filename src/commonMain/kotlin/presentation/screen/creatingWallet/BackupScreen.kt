package presentation.screen.creatingWallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

class BackupScreen: Screen {
    @Composable
    override fun Content() {
        var walletImage = painterResource(Res.drawable.compose_multiplatform)
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            Topbar("Backup", true)
            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
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
                    text = "Back up secret phrase",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "Protect your assets by backing up your seed phrase now",
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center
                )

            }

            Spacer(modifier = Modifier.weight(1f))


            Column(
                modifier = Modifier
                    .padding(bottom = 20.dp, start = 5.dp, end = 5.dp)
            ) {
                Button(
                    onClick = {
                        navigator.push(EnsureScreen())
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = PrimaryColor(),
                        contentColor = Color.Blue
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(30.dp))
                ) {
                    Text(
                        text = "Back up manually",
                        color = Color.Blue,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(5.dp)
                    )
                }
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = PrimaryColor(),
                        contentColor = Color.Blue
                    ),
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(30.dp))
                ) {
                    Text(
                        text = "Back up to Google Drive",
                        color = Color.Blue,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }

        }
    }
}