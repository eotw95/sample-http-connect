package com.example.samplehttpconnect

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.samplehttpconnect.ui.theme.SampleHttpConnectTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    private val httpConnectionManager = HttpConnectionManager()

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // serialize and deserialize sample data
        serializeAndDeserialize()

        setContent {
            SampleHttpConnectTheme {
                var response by remember { mutableStateOf("") }
                GlobalScope.launch {
                    response = httpConnectionManager.fetchHttpData()
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Text(text = response)
                }
            }
        }
    }

    private fun serializeAndDeserialize() {
        val sampleData = SampleData(
            1,
            "sample"
        )
        val jsonString = Json.encodeToString(sampleData)
        println("serializeAndDeserialize jsonString=$jsonString")
        val deserialized = Json.decodeFromString<SampleData>(jsonString)
        println("serializeAndDeserialize deserialized=$deserialized")
    }
}