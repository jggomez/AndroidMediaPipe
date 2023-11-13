package ai.wordbox.dogsembeddings

import ai.wordbox.dogsembeddings.ui.theme.DogsEmbeddingsTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textEmbeddingsViewModel: TextEmbeddingsViewModel by viewModels()

        setContent {
            DogsEmbeddingsTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current

                    LaunchedEffect(Unit) {
                        textEmbeddingsViewModel.setUpMLModel(context)
                    }

                    Body(
                        sentences = textEmbeddingsViewModel
                            .uiStateTextEmbeddings
                            .sentences,
                        similaritySentences = textEmbeddingsViewModel
                            .uiStateTextEmbeddings
                            .similaritySentences,
                        loading = textEmbeddingsViewModel
                            .uiStateTextEmbeddings
                            .state == State.Loading,
                        error = textEmbeddingsViewModel
                            .uiStateTextEmbeddings
                            .errorMessage
                    ) {
                        textEmbeddingsViewModel.calculateSimilarity(
                            mainSentence = it,
                            sentences = textEmbeddingsViewModel
                                .uiStateTextEmbeddings
                                .sentences
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Body(
    sentences: List<String>,
    similaritySentences: List<SentenceSimilarity>,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    error: String = String(),
    onClickFindSimilarities: (text: String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        SentencesList(sentences = sentences)
        Spacer(modifier = Modifier.height(20.dp))
        InputUserText(text = text) {
            text = it
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { onClickFindSimilarities(text) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            when (loading) {
                false -> Text(text = "Find Similarity Sentences")
                true -> CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        when {
            error.isBlank() -> {
                SentencesList(sentences = similaritySentences.map {
                    "${it.sentence} - ${it.resultSimilarity}"
                })
            }

            else -> Text(text = error, modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun SentencesList(
    sentences: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .verticalScroll(rememberScrollState())
    ) {
        sentences.forEachIndexed { index, sentence ->
            Text(
                text = "${index + 1} -> $sentence",
                modifier = modifier
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputUserText(
    text: String,
    modifier: Modifier = Modifier,
    onValueChanged: (text: String) -> Unit,
) {
    OutlinedTextField(
        value = text,
        modifier = modifier.fillMaxWidth(),
        onValueChange = onValueChanged
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Body(
        sentences = listOf(
            "What is your name?",
            "I'm good",
            "The next weekend will be your birthday"
        ),
        similaritySentences = listOf(
            SentenceSimilarity(
                mainSentence = "hi",
                sentence = "asasasas",
                mainSentenceEmbeddings = "asasasasaasas",
                sentenceEmbeddings = "asasasasaasas",
                resultSimilarity = 40.0
            ),
            SentenceSimilarity(
                mainSentence = "hi",
                sentence = "asasasas",
                mainSentenceEmbeddings = "asasasasaasas",
                sentenceEmbeddings = "asasasasaasas",
                resultSimilarity = 40.0
            ),
        )
    ) {

    }
}