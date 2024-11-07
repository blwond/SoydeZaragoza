package com.quehacerenzaragoza.soydezaragoza.presentation.screens.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import coil3.compose.AsyncImage
import com.quehacerenzaragoza.soydezaragoza.presentation.components.ProgressIndicator
import com.quehacerenzaragoza.soydezaragoza.core.BackHandler
import com.quehacerenzaragoza.soydezaragoza.core.viewModel
import com.quehacerenzaragoza.soydezaragoza.data.model.post.Post
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
                    println(postsState.message)
                }

                ObtainDataState.Idle -> {
                }
            }
        }

        BackHandler(false, onBack = {
        })
    }

    @Composable
    fun PostCard(post: Post) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(200.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                post._embedded.wp_FeaturedMedia?.firstOrNull()?.let { featuredMedia ->
                    AsyncImage(
                        modifier = Modifier.fillMaxWidth().height(120.dp),
                        model = featuredMedia.source_url,
                        contentDescription = "user image",
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = post.title.rendered,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Fecha: ${post.date}",
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = post.content.rendered.take(100) + "...",
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}