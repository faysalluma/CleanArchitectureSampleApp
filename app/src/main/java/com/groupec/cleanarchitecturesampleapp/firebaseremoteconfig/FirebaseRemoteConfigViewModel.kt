package com.groupec.cleanarchitecturesampleapp.firebaseremoteconfig

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupec.core.firebaseremoteconfig.FirebaseRemoteConfigProvider
import com.groupec.core.firebaseremoteconfig.HomeScreenFeatureFlag
import com.groupec.core.firebaseremoteconfig.defaultValueMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirebaseRemoteConfigViewModel @Inject constructor(
    private val firebaseRemoteConfigProvider: FirebaseRemoteConfigProvider,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        viewModelScope.launch {
            fetchRemoteConfig()
        }
    }

    private suspend fun fetchRemoteConfig() {
        firebaseRemoteConfigProvider
            .configKeys(
                keyList =
                    listOf(
                        HomeScreenFeatureFlag.BREAKING_NEWS_MESSAGE.keyName,
                        HomeScreenFeatureFlag.IS_VISIBLE_BREAKING_NEWS_MESSAGE.keyName,
                        HomeScreenFeatureFlag.BREAKING_NEWS_COUNTS.keyName,
                    ),
            )
            .collectLatest { configMap ->
                _uiState.update { currentState ->
                    currentState.copy(
                        breakingNewsMessage =
                            configMap[HomeScreenFeatureFlag.BREAKING_NEWS_MESSAGE.keyName]?.let {
                                firebaseRemoteConfigProvider.getStringFlagValue(configMap, HomeScreenFeatureFlag.BREAKING_NEWS_MESSAGE.keyName)
                            } ?: currentState.breakingNewsMessage,
                        isVisibleBreakingNewsMessage =
                            configMap[HomeScreenFeatureFlag.IS_VISIBLE_BREAKING_NEWS_MESSAGE.keyName]?.let {
                                firebaseRemoteConfigProvider.getBooleanFlagValue(configMap, HomeScreenFeatureFlag.IS_VISIBLE_BREAKING_NEWS_MESSAGE.keyName)
                            } ?: currentState.isVisibleBreakingNewsMessage,
                        breakingNewsCount =
                            configMap[HomeScreenFeatureFlag.BREAKING_NEWS_COUNTS.keyName]?.let {
                                firebaseRemoteConfigProvider.getLongFlagValue(configMap, HomeScreenFeatureFlag.BREAKING_NEWS_COUNTS.keyName)
                            } ?: currentState.breakingNewsCount,
                    )
                }
            }
    }
}

data class HomeUiState(
    val breakingNewsCount: Long =
        defaultValueMap[HomeScreenFeatureFlag.BREAKING_NEWS_COUNTS.keyName] as Long,
    val isVisibleBreakingNewsMessage: Boolean =
        defaultValueMap[HomeScreenFeatureFlag.IS_VISIBLE_BREAKING_NEWS_MESSAGE.keyName] as Boolean,
    val breakingNewsMessage: String =
        defaultValueMap[HomeScreenFeatureFlag.BREAKING_NEWS_MESSAGE.keyName] as String,
)