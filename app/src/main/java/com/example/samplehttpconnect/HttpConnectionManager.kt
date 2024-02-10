package com.example.samplehttpconnect

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class HttpConnectionManager {
    fun fetchHttpData(): String {
        // http接続
        val uri = URL("https://qiita.com/api/v2/items?page=1&per_page=15")
        val conn = uri.openConnection() as HttpURLConnection
        conn.connect()

        // GETリクエスト送信し、返されたレスポンスからデータを読み込んで返す
        conn.requestMethod = "GET"
        if (conn.responseCode == HttpURLConnection.HTTP_OK) {
            val inputStream = conn.inputStream // バイトストリーム(バイナリデータのストリーム)がレスポンスとして返される)
            val reader = BufferedReader(InputStreamReader(inputStream)) // InputStreamReaderでバイトストリームを文字入力ストリームに変換、BufferedReaderで文字列入力ストリームからテキストを読み込む。
            val response = StringBuilder()
            var line: String? = null
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }
            println("fetchHttpData response=$response")
            return response.toString()
        } else {
            throw IllegalStateException("Invalid connection")
        }
    }
}