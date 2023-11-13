package ai.wordbox.dogsembeddings

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class State {
    object Loading : State()
    object Success : State()
    object Error : State()
    object Empty : State()
}

data class TextEmbeddingsUiState(
    val sentences: List<String> = emptyList(),
    val similaritySentences: List<SentenceSimilarity> = emptyList(),
    val state: State = State.Empty,
    val errorMessage: String = String()
)

class TextEmbeddingsViewModel : ViewModel() {

    private lateinit var mediaPipeEmbeddings: MediaPipeEmbeddings

    var uiStateTextEmbeddings by mutableStateOf(TextEmbeddingsUiState(state = State.Empty))
        private set

    fun setUpMLModel(context: Context) {
        mediaPipeEmbeddings = MediaPipeEmbeddings()
        uiStateTextEmbeddings = uiStateTextEmbeddings.copy(
            state = State.Loading,
            similaritySentences = emptyList()
        )
        viewModelScope.launch {
            mediaPipeEmbeddings.setUpMLModel(context)
            uiStateTextEmbeddings = uiStateTextEmbeddings.copy(
                sentences = listOf(
                    "The next weekend will be your birthday",
                    "Birds sing in the morning",
                    "My cat sleeps a lot",
                    "Pizza is my favorite food",
                    "The sun is hot",
                    "I like to play with my dog",
                    "Flowers are colorful",
                    "Books take us on adventures",
                    "The moon comes out at night",
                    "Ice cream melts quickly in the sun",
                    "Soccer is a fun game",
                    "My mom makes yummy cookies",
                    "Cars go fast on the road",
                    "I can count to ten",
                    "Rainbows have many colors",
                    "I love watching cartoons",
                ),
                state = State.Empty
            )
        }

    }

    fun calculateSimilarity(mainSentence: String, sentences: List<String>) {
        uiStateTextEmbeddings = uiStateTextEmbeddings.copy(
            state = State.Loading,
            similaritySentences = emptyList()
        )

        viewModelScope.launch(Dispatchers.IO) {
            uiStateTextEmbeddings = try {
                val similarities = mediaPipeEmbeddings.getSimilarities(mainSentence, sentences)
                uiStateTextEmbeddings.copy(
                    state = State.Success,
                    similaritySentences = similarities
                )
            } catch (e: Exception) {
                uiStateTextEmbeddings.copy(
                    state = State.Error,
                    errorMessage = e.message ?: "Error getting similarities"
                )
            }
        }

    }
}
