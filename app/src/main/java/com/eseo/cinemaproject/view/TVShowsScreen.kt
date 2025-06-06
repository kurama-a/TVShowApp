package com.eseo.cinemaproject.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.eseo.cinemaproject.model.TVShow
import com.eseo.cinemaproject.viewmodel.TVShowsViewModel

@Composable
fun TVShowsScreen(viewModel: TVShowsViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val tvShows by viewModel.tvShows.collectAsState()

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        if (uiState.isLoading) {
            // Affiche un spinner de chargement
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (uiState.error != null) {
            // Affiche un message d'erreur
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text(text = "Erreur: ${uiState.error}")
            }
        } else {
            // Affiche la grille des séries
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(8.dp),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(tvShows) { show ->
                    TVShowItem(show = show)
                }

                // Chargement infini : déclencher chargement de la page suivante quand on arrive en bas
                item {
                    if (viewModel.canLoadMore() && !uiState.isLoadingMore) {
                        viewModel.loadMoreTVShows()
                    }
                    if (uiState.isLoadingMore) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = androidx.compose.ui.Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TVShowItem(show: TVShow) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberAsyncImagePainter(show.imageThumbnailPath),
            contentDescription = show.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = show.name,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}