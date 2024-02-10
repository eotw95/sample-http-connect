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
        // SampleData
        val sampleData = SampleData(
            1,
            "sample"
        )
        val jsonStr1 = Json.encodeToString(sampleData)
        println("serializeAndDeserialize jsonString=$jsonStr1")
        val deserialized1 = Json.decodeFromString<SampleData>(jsonStr1)
        println("serializeAndDeserialize deserialized=$deserialized1")

        // QiitaResponse
        val qiitaResponse = QiitaResponse(
            "title",
            "https://dummy.com",
            "body",
            QiitaResponse.User(
                "1",
                "https://image.com"
            ),
            "20240210"
        )
        val jsonStr2 = Json.encodeToString(qiitaResponse)
        println("serializeAndDeserialize jsonStr2=$jsonStr2")
        val deserialized2 = Json.decodeFromString<QiitaResponse>(jsonStr2)
        println("serializeAndDeserialize deserialized2=$deserialized2")
    }
}