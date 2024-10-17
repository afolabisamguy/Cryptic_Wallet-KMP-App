package components

import androidx.compose.foundation.Image
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
import androidx.compose.material.Surface
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
import crypticwallet.composeapp.generated.resources.Res
import crypticwallet.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.Job
import org.jetbrains.compose.resources.painterResource


@Composable
fun BottomSheetContent(onClick: () -> Unit){
    var walletImage = painterResource(Res.drawable.compose_multiplatform)
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Image(
                    painter = walletImage,
                    contentDescription = null,
                    modifier = Modifier.width(150.dp),
                    alignment = Alignment.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Never Share Your Secret Phrase",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        NumberedTextItem(1, "Your 12-word secret phrase is the master key to your wallet. Anyone that has your secret phrase can access and take your crypto.")
        NumberedTextItem(2, "Trust Wallet does not keep a copy of your secret phrase.")
        NumberedTextItem(3, "Saving this digitally in plain text is NOT recommended. Examples include screenshots, text files, or emailing yourself.")
        NumberedTextItem(4, "Write down your secret phrase, and store it in a secure offline location!")
        Spacer(modifier = Modifier.height(18.dp))

        Button(onClick = onClick,
            modifier = Modifier.fillMaxWidth()
                .padding(5.dp)
                .clip(RoundedCornerShape(100.dp))) {
            Text(text = "I understand")
        }
    }
}


@Composable
fun NumberedTextItem(number: Int, text: String) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Surface(
            shape = CircleShape,
            color = Color.LightGray, // You can customize this color
            modifier = Modifier.size(24.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = number.toString(),
                    fontWeight = FontWeight.Bold,
                    color = Color.Black // You can customize this color
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 12.sp,)
    }
}