package repository

import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Airbnb
import compose.icons.fontawesomeicons.brands.Bitcoin
import compose.icons.fontawesomeicons.brands.Earlybirds
import compose.icons.fontawesomeicons.brands.Ethereum
import compose.icons.fontawesomeicons.brands.Evernote
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import viewModels.ReceiveViewModel
import kotlin.math.roundToInt
import kotlin.random.Random

class TokenFetch(private val viewModel: ReceiveViewModel ) {

    val name = listOf(
        "Bitcoin",
        "Sure Wallet",
        "Polygon",
        "Ethereum",
        "BNB Smart Chain"
    )

    val symbol = listOf(
        "BTC",
        "SWT",
        "MATIC",
        "ETH",
        "BNB"
    )

    val icons = listOf(
        FontAwesomeIcons.Brands.Bitcoin,
        FontAwesomeIcons.Brands.Evernote,
        FontAwesomeIcons.Brands.Airbnb,
        FontAwesomeIcons.Brands.Ethereum,
        FontAwesomeIcons.Brands.Earlybirds
    )


}