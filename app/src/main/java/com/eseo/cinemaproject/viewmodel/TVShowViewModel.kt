package com.eseo.cinemaproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eseo.cinemaproject.model.TVShow
import com.eseo.cinemaproject.repository.TVShowsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVShowsViewModel @Inject constructor(
    private val repository: TVShowsRepository
) : ViewModel() {

    // État de l'UI
    private val _uiState = MutableStateFlow(TVShowsUiState())
    val uiState: StateFlow<TVShowsUiState> = _uiState.asStateFlow()

    // Liste des séries TV
    private val _tvShows = MutableStateFlow<List<TVShow>>(emptyList())
    val tvShows: StateFlow<List<TVShow>> = _tvShows.asStateFlow()

    // Page actuelle pour la pagination
    private var currentPage = 1
    private var totalPages = 1

    init {
        loadTVShows()
    }

    /**
     * Charge la première page des séries TV
     */
    fun loadTVShows() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            try {
                val response = repository.getShows(page = 1)

                if (response.isSuccessful) {
                    response.body()?.let { tvShowsResponse ->
                        _tvShows.value = tvShowsResponse.tvShows
                        currentPage = tvShowsResponse.page
                        totalPages = tvShowsResponse.pages

                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = null
                        )
                    }
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "Erreur lors du chargement des séries: ${response.message()}"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Erreur réseau: ${e.message}"
                )
            }
        }
    }

    /**
     * Charge la page suivante des séries TV (pagination)
     */
    fun loadMoreTVShows() {
        if (_uiState.value.isLoadingMore || currentPage >= totalPages) {
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoadingMore = true)

            try {
                val nextPage = currentPage + 1
                val response = repository.getShows(page = nextPage)

                if (response.isSuccessful) {
                    response.body()?.let { tvShowsResponse ->
                        // Ajouter les nouvelles séries à la liste existante
                        val currentShows = _tvShows.value.toMutableList()
                        currentShows.addAll(tvShowsResponse.tvShows)
                        _tvShows.value = currentShows

                        currentPage = tvShowsResponse.page
                        totalPages = tvShowsResponse.pages

                        _uiState.value = _uiState.value.copy(
                            isLoadingMore = false,
                            error = null
                        )
                    }
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoadingMore = false,
                        error = "Erreur lors du chargement: ${response.message()}"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoadingMore = false,
                    error = "Erreur réseau: ${e.message}"
                )
            }
        }
    }

    /**
     * Actualise la liste des séries TV
     */
    fun refreshTVShows() {
        currentPage = 1
        _tvShows.value = emptyList()
        loadTVShows()
    }

    /**
     * Efface les erreurs
     */
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    /**
     * Vérifie si on peut charger plus de séries
     */
    fun canLoadMore(): Boolean {
        return currentPage < totalPages && !_uiState.value.isLoadingMore
    }
}

/**
 * État de l'interface utilisateur
 */
data class TVShowsUiState(
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val error: String? = null
)