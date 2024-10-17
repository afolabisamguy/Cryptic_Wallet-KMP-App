package presentation.screen.creatingWallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import components.Topbar

class CreateScreen: Screen {
    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            Topbar("Create new wallet", false)
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
            ) {
                CreateScreenComp1()

                Spacer(modifier = Modifier.height(16.dp))

                CreateScreenComp2()

                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Icon(
                        imageVector = Icons.Rounded.Info,
                        contentDescription = "Create",
                        modifier = Modifier.size(20.dp)
                    )

                    Text(
                        text = "You can create another wallet anytime under 'Manage Wallet'",
                        fontSize = 10.sp,
                        modifier = Modifier.padding(start = 8.dp, top = 3.dp)
                    )
                }
            }


        }
    }
}