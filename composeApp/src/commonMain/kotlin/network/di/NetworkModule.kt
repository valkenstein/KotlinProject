import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRedirect
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

const val BASE_URL = "https://api.redharemarket.ru/"
private const val TIME_OUT = 30000L
private const val X_USER_PLATFORM_HEADER = "X-User-Platform"
private const val X_USER_PLATFORM_VERSION = "X-User-Version"
private const val USER_ID = "UserId"
private const val USER_ID_TEMP = "UserIdTemp"
const val SECRET_KEY = "wYDkhhejSaZjP5IUATkphP846cJZ6D5x"
const val DEV_MODE = "devMode"

internal val networkModule = module {
    factoryOf(::provideJson)
    factoryOf(::provideHttpClient)
    single { provideKtorHttpClient(get(), BASE_URL) }
}

private fun provideJson(): Json {
    return Json {
        isLenient = true
        ignoreUnknownKeys = true
        explicitNulls = false
        encodeDefaults = true
        prettyPrint = true
    }
}

private fun provideHttpClient(json: Json) = HttpClient {
    install(HttpRedirect) {
        checkHttpMethod = false
        allowHttpsDowngrade = false
    }
    defaultRequest {
        header("Content-Type", "application/json")
        header(DEV_MODE, "Y")
        header(X_USER_PLATFORM_HEADER, "android")
        header(X_USER_PLATFORM_VERSION, "version")
        header("Authorization", "Bearer $SECRET_KEY")
        header(USER_ID, "8811")
        header(USER_ID_TEMP, "76121a6f-4019-4905-a9ef-303a7d23b158" + "_A")
    }
    install(ContentNegotiation) {
        json(json)
    }
    HttpResponseValidator {
        handleResponseExceptionWithRequest { cause, request ->
            print(cause)
            print(request)
        }
    }
    install(HttpTimeout) {
        connectTimeoutMillis = TIME_OUT
        requestTimeoutMillis = TIME_OUT
    }
    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                println("json: \n $message")
            }
        }
        level = LogLevel.ALL
    }
}

private fun provideKtorHttpClient(httpClient: HttpClient, baseUrl: String): Ktorfit {
    return Ktorfit.Builder()
        .httpClient(httpClient)
        .baseUrl(baseUrl)
       // .addCallAdapterFactory(ResultCallAdapterFactory())
        .build()
}
