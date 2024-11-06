package com.quehacerenzaragoza.soydezaragoza.di

import com.quehacerenzaragoza.soydezaragoza.data.remote.news.NewsRemoteDataSource
import com.quehacerenzaragoza.soydezaragoza.data.remote.news.NewsRemoteDataSourceImplementation
import com.quehacerenzaragoza.soydezaragoza.data.repository.NewsRepositoryImpl
import com.quehacerenzaragoza.soydezaragoza.domain.repository.NewsRepository
import com.quehacerenzaragoza.soydezaragoza.domain.usecase.NewsUseCase
import com.quehacerenzaragoza.soydezaragoza.presentation.screens.news.NewsViewModel
import com.quehacerenzaragoza.soydezaragoza.util.extensions.BASE_URL_API
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
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
