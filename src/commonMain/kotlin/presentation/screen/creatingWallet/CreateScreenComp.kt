package presentation.screen.creatingWallet

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.brands.Bitbucket
import compose.icons.fontawesomeicons.brands.Bitcoin
import compose.icons.fontawesomeicons.brands.Ethereum
import compose.icons.fontawesomeicons.brands.Fedora
import compose.icons.fontawesomeicons.brands.Swift
import compose.icons.fontawesomeicons.regular.Star
import theme.PrimaryColor

@Composable
fun CreateScreenComp1() {
    val expanded = remember { mutableStateOf(false) }
    val navigator = LocalNavigator.currentOrThrow

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(PrimaryColor())
            .clickable { expanded.value = !expanded.value }
            .animateContentSize()
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Icon(
            imageVector = Icons.Rounded.Create,
            contentDescription = "Create",
            tint = Color.Blue
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            Text(
                text = "Secret phrase",
                modifier = Modifier
                    .padding(start = 26.dp)
            )
            Row(
                modifier = Modifier
                    .padding(start = 26.dp, top = 4.dp)
            ) {
                Text(
                    text = "Show details"
                )

                Icon(
                    imageVector = if (expanded.value) {
                        Icons.Rounded.KeyboardArrowUp
                    } else Icons.Rounded.KeyboardArrowDown,
                    contentDescription = "Create",
                )
            }
        }

        Box(modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .height(35.dp)
            .width(80.dp)
            .background(Color.Blue)
            .clickable {
                navigator.push(BackupScreen())
            }
            .padding(8.dp)
            .align(Alignment.TopEnd)) {
            Text(
                text = "Create",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )

        }

        if (expanded.value) {

            Column(
                modifier = Modifier
                    .padding(top = 50.dp)
            ) {
                Text(
                    text = "Security", fontSize = 11.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = "Create and recover wallet with a 12, 18 or 24-word secret phrase. You must manually store this, or back up with Google Drive storage.",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )
                Divider(
                    color = MaterialTheme.colors.onBackground, thickness = 0.dp
                )

                Text(
                    text = "Transaction",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    "Create and recover wallet with a 12, 18 or 24-word secrete phrase. You must manually store this, or back up with Google Drive",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Row {
                    Icon(
                        imageVector = FontAwesomeIcons.Brands.Bitcoin,
                        contentDescription = "Create",
                        modifier = Modifier.size(15.dp)
                    )
                    Icon(
                        imageVector = FontAwesomeIcons.Brands.Ethereum,
                        contentDescription = "Create",
                        modifier = Modifier.size(15.dp)
                    )
                    Icon(
                        imageVector = FontAwesomeIcons.Brands.Swift,
                        contentDescription = "Create",
                        modifier = Modifier.size(15.dp)
                    )
                    Icon(
                        imageVector = FontAwesomeIcons.Brands.Bitbucket,
                        contentDescription = "Create",
                        modifier = Modifier.size(15.dp)
                    )
                    Icon(
                        imageVector = FontAwesomeIcons.Brands.Fedora,
                        contentDescription = "Create",
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = " + 88 more",
                        fontSize = 10.sp
                    )
                }

                Divider(
                    color = MaterialTheme.colors.onBackground, thickness = 0.dp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Fees", fontSize = 11.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = "Pay network fee(gas) with native tokens only. For example, if your transaction is on the Ethereum network, you can only pay for this fee with Eth.",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )

            }
        }
    }


}

@Composable
fun CreateScreenComp2() {
    val expanded2 = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(PrimaryColor())
            .clickable { expanded2.value = !expanded2.value }
            .animateContentSize()
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Icon(
            imageVector = FontAwesomeIcons.Regular.Star,
            contentDescription = "Create",
            tint = Color.Blue,
            modifier = Modifier.size(30.dp)
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            Row {
                Text(
                    text = "Swift",
                    modifier = Modifier
                        .padding(start = 26.dp)
                )
                Box(
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .background(Color.Black)
                ) {
                    Text(
                        text = "Beta",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(start = 26.dp, top = 4.dp)
            ) {
                Text(
                    text = "Show details"
                )

                Icon(
                    imageVector = if (expanded2.value) {
                        Icons.Rounded.KeyboardArrowUp
                    } else Icons.Rounded.KeyboardArrowDown,
                    contentDescription = "Create",
                )
            }
        }
        Box(modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .width(80.dp)
            .height(35.dp)
            .background(Color.Blue)
            .clickable { }
            .padding(8.dp)
            .align(Alignment.TopEnd)
        ) {
            Text(
                text = "Create",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if (expanded2.value) {
            Column(
                modifier = Modifier
                    .padding(top = 50.dp)
            ) {
                Text(
                    text = "Security", fontSize = 11.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = "Create and recover wallet with Face ID or fingerprint. This is done automatically when you create a new wallet. with your device's passkey.",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )
                Divider(
                    color = MaterialTheme.colors.onBackground, thickness = 0.dp
                )

                Text(
                    text = "Transaction",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    "Transactions are available on 7 EVM networks (chains) currently, but complete in fewer, simpler steps.",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )
                Row {
                    Icon(
                        imageVector = FontAwesomeIcons.Brands.Bitcoin,
                        contentDescription = "Create",
                        modifier = Modifier.size(15.dp)
                    )
                    Icon(
                        imageVector = FontAwesomeIcons.Brands.Ethereum,
                        contentDescription = "Create",
                        modifier = Modifier.size(15.dp)
                    )
                    Icon(
                        imageVector = FontAwesomeIcons.Brands.Swift,
                        contentDescription = "Create",
                        modifier = Modifier.size(15.dp)
                    )
                    Icon(
                        imageVector = FontAwesomeIcons.Brands.Bitbucket,
                        contentDescription = "Create",
                        modifier = Modifier.size(15.dp)
                    )
                    Icon(
                        imageVector = FontAwesomeIcons.Brands.Fedora,
                        contentDescription = "Create",
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = " + 88 more",
                        fontSize = 10.sp
                    )
                }

                Divider(
                    color = MaterialTheme.colors.onBackground, thickness = 0.dp
                )
                Text(
                    text = "Fees", fontSize = 11.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = "Pay network fee(gas) with any of our 200+ tokens. Regardless of the transaction network, you can pay this fee with any token that has enough balance.",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )

            }
        }

    }
}
