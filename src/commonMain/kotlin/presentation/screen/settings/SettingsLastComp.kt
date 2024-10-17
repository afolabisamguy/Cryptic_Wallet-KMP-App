package presentation.screen.settings

import Wallet.UrlOpener
import androidx.compose.runtime.Composable
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.brands.Facebook
import compose.icons.fontawesomeicons.brands.Instagram
import compose.icons.fontawesomeicons.brands.Reddit
import compose.icons.fontawesomeicons.brands.Telegram
import compose.icons.fontawesomeicons.brands.Twitter
import compose.icons.fontawesomeicons.brands.Youtube
import compose.icons.fontawesomeicons.regular.Copyright
import compose.icons.fontawesomeicons.regular.User
import compose.icons.fontawesomeicons.solid.Atlas

val socialslist = listOf("X", "Telegram", "Facebook", "Reddit", "Youtube", "Instagram")
val supportlist = listOf("Help Center", "Support", "About")


val socialsicons = mapOf(
    "X" to FontAwesomeIcons.Brands.Twitter,
    "Telegram" to FontAwesomeIcons.Brands.Telegram,
    "Facebook" to FontAwesomeIcons.Brands.Facebook,
    "Reddit" to FontAwesomeIcons.Brands.Reddit,
    "Youtube" to FontAwesomeIcons.Brands.Youtube,
    "Instagram" to FontAwesomeIcons.Brands.Instagram
)
val socialuri = mapOf(
    "X" to "https://www.x.com",
    "Telegram" to "https://www.Telegram.com",
    "Facebook" to "https://www.fb.com",
    "Reddit" to "https://www.reddit.com",
    "Youtube" to "https://www.youtube.com",
    "Instagram" to "https://www.instagram.com"
)

val supporticons = mapOf(
    "Help Center" to FontAwesomeIcons.Regular.Copyright,
    "Support" to FontAwesomeIcons.Regular.User,
    "About" to FontAwesomeIcons.Solid.Atlas
)

@Composable
fun SettingsFourthComp(){
    for (i in supportlist) {
        supporticons[i]?.let { SettingsComponent(name = i, imageVector = it, onClick = {}) }
    }
}

val sam = UrlOpener()

@Composable
fun SettingsLastComp(){
    for (i in socialslist) {
        socialsicons[i]?.let { SettingsComponent(name = i, imageVector = it, onClick = {
            socialuri[i]?.let { it1 ->
                sam.openUrl(
                    it1
                )
            }
        }) }
    }
}