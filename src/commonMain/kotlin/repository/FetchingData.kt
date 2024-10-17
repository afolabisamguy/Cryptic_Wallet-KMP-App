package repository

import data.TokenImageLogos
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readBytes
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val httpClient = HttpClient(CIO){
    install(ContentNegotiation){
        json(Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
        })
    }
}

private val myjson = Json{
    ignoreUnknownKeys = true
    prettyPrint = true
    isLenient = true
}

suspend fun getTokens(name: String): TokenImageLogos{
    val url = "https://github.com/trustwallet/assets/blob/master/blockchains/$name/info/logo.png?raw=true"
    val response : String = httpClient.get(url){
        accept(ContentType.Application.Json)
    }.body()

    val token: TokenImageLogos = myjson.decodeFromString(response)

    return token
}


suspend fun downloadImage(url: String): ByteArray? {
    return try{
        val response: HttpResponse = httpClient.get(url)
        if (response.status == HttpStatusCode.OK){
            response.readBytes()
        } else {
            null
        }
    } catch (e: Exception){
        null
    }
}