package com.quehacerenzaragoza.soydezaragoza.presentation.screens.news

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import com.quehacerenzaragoza.soydezaragoza.presentation.components.ProgressIndicator
import com.quehacerenzaragoza.soydezaragoza.core.BackHandler
import com.quehacerenzaragoza.soydezaragoza.core.viewModel
import com.quehacerenzaragoza.soydezaragoza.presentation.components.ObtainDataState

object NewsScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: NewsViewModel = viewModel()
        MainContent(viewModel)
    }

    @Composable
    fun MainContent(viewModel: NewsViewModel) {
        val newsState by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.getPostsByCategories()
        }

        Column {
            when (val postsState = newsState.postsState) {
                is ObtainDataState.Loading -> {
                    ProgressIndicator()
                }

                is ObtainDataState.Success -> {
                    println(postsState.data[0].title.rendered)
                }

                is ObtainDataState.Error -> {
                    println(postsState.message)
                }

                ObtainDataState.Idle -> {
                }
            }
        }

        BackHandler(false, onBack = {
        })
    }
}