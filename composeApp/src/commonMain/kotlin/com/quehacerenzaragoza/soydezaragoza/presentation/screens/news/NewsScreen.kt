package com.quehacerenzaragoza.soydezaragoza.presentation.screens.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import com.quehacerenzaragoza.soydezaragoza.core.BackHandler
import com.quehacerenzaragoza.soydezaragoza.core.viewModel
import com.quehacerenzaragoza.soydezaragoza.presentation.components.AppScaffold
import com.quehacerenzaragoza.soydezaragoza.presentation.components.ObtainDataState
import com.quehacerenzaragoza.soydezaragoza.presentation.components.news.PostCard
import com.quehacerenzaragoza.soydezaragoza.presentation.components.news.ShimmeringPostCardPlaceholder

object NewsScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: NewsViewModel = viewModel()
        MainContent(viewModel)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainContent(viewModel: NewsViewModel) {
        val newsState by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.getPostsByCategories()
        }

        AppScaffold(
            topAppBar = {
                TopAppBar(
                    title = { Text("Noticias") }
                )
            },
            content = { paddingValues ->
                NewsScreenContent(newsState, paddingValues)
            }
        )

        BackHandler(false, onBack = {
        })
    }

    @Composable
    fun NewsScreenContent(newsState: NewsScreenState, paddingValues: PaddingValues){
        Column(modifier = Modifier.padding(paddingValues)) {
            when (val postsState = newsState.postsState) {
                is ObtainDataState.Loading -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(5) {
                            ShimmeringPostCardPlaceholder()
                        }
                    }
                }

                is ObtainDataState.Success -> {
                    val posts = postsState.data
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(posts) { post ->
                            PostCard(post = post)
                        }
                    }
                }

                is ObtainDataState.Error -> {
                    Text("Error: ${postsState.message}", color = Color.Red)
                }

                ObtainDataState.Idle -> {

                }
            }
        }
    }
}