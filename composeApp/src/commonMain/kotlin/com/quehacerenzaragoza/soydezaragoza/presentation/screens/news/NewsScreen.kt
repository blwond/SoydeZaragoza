package com.quehacerenzaragoza.soydezaragoza.presentation.screens.news

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import coil3.compose.AsyncImage
import com.quehacerenzaragoza.soydezaragoza.core.BackHandler
import com.quehacerenzaragoza.soydezaragoza.core.viewModel
import com.quehacerenzaragoza.soydezaragoza.data.model.category.Category
import com.quehacerenzaragoza.soydezaragoza.data.model.post.Post
import com.quehacerenzaragoza.soydezaragoza.presentation.components.AppScaffold
import com.quehacerenzaragoza.soydezaragoza.presentation.components.ObtainDataState
import com.quehacerenzaragoza.soydezaragoza.presentation.components.news.NewsTopAppBar
import com.quehacerenzaragoza.soydezaragoza.presentation.components.news.PostCard
import com.quehacerenzaragoza.soydezaragoza.presentation.components.news.ShimmeringCategoryViewPlaceholder
import com.quehacerenzaragoza.soydezaragoza.presentation.components.news.ShimmeringPostCardPlaceholder
import com.quehacerenzaragoza.soydezaragoza.presentation.components.news.ShimmeringTrendingNewsPlaceholder
import com.quehacerenzaragoza.soydezaragoza.util.extensions.getRelativeTimeFromNow
import compose.icons.FeatherIcons
import compose.icons.feathericons.Clock
import compose.icons.feathericons.Heart

object NewsScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: NewsViewModel = viewModel()
        MainContent(viewModel)
    }

    @Composable
    fun MainContent(viewModel: NewsViewModel) {
        val newsState by viewModel.state.collectAsState()

        AppScaffold(
            topAppBar = {
                NewsTopAppBar()
            },
            content = { paddingValues ->
                NewsScreenContent(newsState, paddingValues)
            }
        )

        BackHandler(false, onBack = {
        })
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun NewsScreenContent(newsState: NewsScreenState, paddingValues: PaddingValues){
        LazyColumn (
            modifier = Modifier
                .padding(paddingValues)
        ) {

            item {
                Box(modifier = Modifier.padding(horizontal = 20.dp)){
                    PostsStatefulList(
                        postsState = newsState.trendingPostsState,
                        content = { post -> TrendingNew(post = post) },
                        placeholder = { ShimmeringTrendingNewsPlaceholder() },
                        placeholderItems = 1
                    )
                }
            }

            stickyHeader {
                Spacer(modifier = Modifier.height(24.dp).fillMaxWidth().background(MaterialTheme.colorScheme.background))

                Column (Modifier.background(MaterialTheme.colorScheme.background)) {
                    Row (Modifier.padding(horizontal = 20.dp)) {
                        Text(
                            text = "Por categorías",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = "Ver más",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    CategoriesStatefulList(
                        categoriesState = newsState.categoriesState,
                        selectedCategory = newsState.selectedCategory,
                        onCategorySelected = { category -> newsState.onCategorySelected(category) },
                        placeholder = { ShimmeringCategoryViewPlaceholder() },
                        placeholderItems = 10
                    )

                    Spacer(Modifier.height(24.dp))
                }
            }

            item {
                Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                    PostsStatefulList(
                        postsState = newsState.postsState,
                        content = { post -> PostCard(post = post, newsState) },
                        placeholder = { ShimmeringPostCardPlaceholder() },
                        placeholderItems = 5
                    )
                }
            }

        }
    }

    @Composable
    fun TrendingNew(post: Post){
        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    post._embedded.wp_FeaturedMedia?.firstOrNull()?.let { featuredMedia ->
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(20.dp)),
                            model = featuredMedia.source_url,
                            contentDescription = "trending image",
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text = "Destacado",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .padding(20.dp)
                            .align(Alignment.TopStart)
                            .background((MaterialTheme.colorScheme.primary), shape = RoundedCornerShape(20.dp))
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                }
            }

            Text(
                text = post.title.rendered,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp, bottom = 10.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = post.primary_category,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.weight(1f)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = FeatherIcons.Clock,
                        contentDescription = "Clock",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.height(14.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = getRelativeTimeFromNow(post.date),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        imageVector = FeatherIcons.Heart,
                        contentDescription = "Heart",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.height(14.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "23",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }

        }
    }

    @Composable
    fun CategoryView(
        category: Category,
        isSelected: Boolean,
        onCategoryClick: (Category) -> Unit
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .padding(end = 15.dp)
                .clickable (
                    interactionSource = interactionSource,
                    indication = null
                ){ onCategoryClick(category) }
                .background(
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = category.name,
                style = MaterialTheme.typography.labelLarge,
                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary
            )
        }
    }

    @Composable
    fun PostsStatefulList(
        postsState: ObtainDataState<List<Post>>,
        content: @Composable (post: Post) -> Unit,
        placeholder: @Composable () -> Unit,
        placeholderItems: Int
    ) {
        when (postsState) {
            is ObtainDataState.Loading -> {
                Column {
                    repeat(placeholderItems) {
                        placeholder()
                    }
                }
            }
            is ObtainDataState.Success -> {
                val posts = postsState.data
                Column {
                    posts.forEach { post ->
                        content(post)
                    }
                }
            }
            is ObtainDataState.Error -> {
                println("Error: ${postsState.message}")
            }
            ObtainDataState.Idle -> {}
        }
    }

    @Composable
    fun CategoriesStatefulList(
        categoriesState: ObtainDataState<List<Category>>,
        selectedCategory: Category?,
        onCategorySelected: (Category) -> Unit,
        placeholder: @Composable () -> Unit,
        placeholderItems: Int
    ) {
        when (categoriesState) {
            is ObtainDataState.Loading -> {
                LazyRow {
                    item {
                        Spacer(Modifier.width(20.dp))
                    }
                    items(placeholderItems) {
                        placeholder()
                    }
                }
            }
            is ObtainDataState.Success -> {
                val allCategory = Category(
                    count = 0,
                    id = -1,
                    name = "Todas",
                    slug = "all"
                )

                val allCategories = listOf(allCategory) + categoriesState.data

                LazyRow {
                    item {
                        Spacer(Modifier.width(20.dp))
                    }
                    items(allCategories) { category ->
                        CategoryView(
                            category = category,
                            isSelected = category == (selectedCategory ?: allCategory),
                            onCategoryClick = onCategorySelected
                        )
                    }
                }
            }
            is ObtainDataState.Error -> {
                println("Error: ${categoriesState.message}")
            }
            ObtainDataState.Idle -> {}
        }
    }

}