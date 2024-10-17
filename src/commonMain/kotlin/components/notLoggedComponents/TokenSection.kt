package components.notLoggedComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import presentation.screen.creatingWallet.DisplayImage
import repository.Token
import viewModels.RepoViewModel


@Composable
fun TokenItems(token: Token, viewModel2: RepoViewModel){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(16.dp)
    )
    {
        Column(modifier = Modifier.fillMaxWidth()) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                DisplayImage(token.name, viewModel2)


                Text(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .weight(1f),
                    text = token.symbol,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colors.onBackground
                )

                Text(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    text = "$ ${token.price}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.End
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {


                Text(
                    modifier = Modifier.padding(start = 26.dp)
                        .weight(1f),
                    text = token.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = MaterialTheme.colors.onBackground
                )

                Text(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    text = " ${token.change}%",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = if (MaterialTheme.colors.background == Color.Black) {
                        Color.Green
                    } else if(token.change < 0){
                        Color.Red
                    } else Color.Blue,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}


