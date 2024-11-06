package com.quehacerenzaragoza.soydezaragoza.di

import com.quehacerenzaragoza.soydezaragoza.data.remote.news.NewsRemoteDataSource
import com.quehacerenzaragoza.soydezaragoza.data.remote.news.NewsRemoteDataSourceImplementation
import com.quehacerenzaragoza.soydezaragoza.data.repository.NewsRepositoryImpl
import com.quehacerenzaragoza.soydezaragoza.domain.repository.NewsRepository
import com.quehacerenzaragoza.soydezaragoza.domain.usecase.NewsUseCase
import com.quehacerenzaragoza.soydezaragoza.presentation.screens.news.NewsViewModel
import com.quehacerenzaragoza.soydezaragoza.util.extensions.AUTH_KEY_SOYDEZARAGOZA
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import com.quehacerenzaragoza.soydezaragoza.util.extensions.BASE_URL_API
import com.quehacerenzaragoza.soydezaragoza.util.extensions.PASSWORD_DEFAULT
import com.quehacerenzaragoza.soydezaragoza.util.extensions.USERNAME_DEFAULT
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.koin.dsl.module

fun KoinApplication.init(){
    modules(
        provideHttpClientModule,
        viewModelsModule,
        repositoriesModule,
        useCasesModule,
        dataSourceModule
    )
}

val useCasesModule = module {
    factory { NewsUseCase(get()) }
}
val repositoriesModule = module {
    single<NewsRepository> { NewsRepositoryImpl(get()) }
}
val viewModelsModule = module {
    factory { NewsViewModel(get()) }
}
val dataSourceModule = module {
    single<NewsRemoteDataSource> { NewsRemoteDataSourceImplementation(get()) }
}
val provideHttpClientModule = module {
    single {
        HttpClient {
            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.SIMPLE
            }
            install(Auth) {
                basic {
                    credentials {
                        BasicAuthCredentials(username = USERNAME_DEFAULT, password = PASSWORD_DEFAULT)
                    }
                    sendWithoutRequest { true }
                }
            }
            install(DefaultRequest) {
                url(BASE_URL_API)
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
        }
    }
}
