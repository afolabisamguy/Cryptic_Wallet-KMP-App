package data

import Wallet.DriverFactory
import data.local.db.Database
import io.kamel.core.utils.URI
import io.ktor.client.statement.HttpResponse
import repository.downloadImage

class DatabaseHelper(driverFactory: DriverFactory) {
    private val database: Database by lazy{
        val driver = driverFactory.createDriver()
        Database(driver)
    }
    private val tokenImageLogoQueries = database.tokenImageLogoQueries

    suspend fun insertImage(name: String, logoURI: String, logoImage: ByteArray?){
        val existingImage = tokenImageLogoQueries.selectImage(name).executeAsOneOrNull()

        if (existingImage == null) {
            database.transaction {
                tokenImageLogoQueries.insertImage(
                    logoURI = logoURI,
                    name = name,
                    logoImage = logoImage
                )
            }
        } else {
            println("image with name '${name} already exists'")
        }
    }



    // Fetch a single image by name, returning either the image or null if not found
    suspend fun selectImage(name: String): TokenImageLogos? {
        return tokenImageLogoQueries.selectImage(name)
            .executeAsOneOrNull()?.let {
                TokenImageLogos(
                    logoURI = it.logoURI,
                    name = it.name,
                    logoImage = it.logoImage
                )
            }
    }

    suspend fun clearImages(){
        tokenImageLogoQueries.clearImage()
    }
}



