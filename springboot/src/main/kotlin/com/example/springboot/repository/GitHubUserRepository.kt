package com.example.springboot.repository

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "github")
class GitHubRepository {
    private val authUrl = "https://github.com/login/oauth/access_token"
    lateinit var clientId: String
    lateinit var clientSecret: String
    // OkHttpClientを作成
    private val client = OkHttpClient.Builder().build()

    fun getAccessToken(code: String): ApiAccessTokenResponse {
        System.out.println(clientId)
        System.out.println(clientSecret)
        val currencies = mutableMapOf(
            Pair("code", code),
            Pair("client_id", clientId),
            Pair("client_secret", clientSecret)
        )
        System.out.println(currencies)

        val gson = Gson()
        val jsonStr = gson.toJson(currencies)
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = jsonStr.toRequestBody(mediaType)
        val request = Request.Builder()
            .url(authUrl)
            .post(requestBody)
            .addHeader("Accept", "application/json")
            .build()
        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            throw Exception("api error")
        }
        val responseBody = response.body?.string().orEmpty()
        val mapper = jacksonObjectMapper()
        val responseMap = mapper.readValue<ApiAccessTokenResponse>(responseBody)
        return responseMap
    }
}

data class ApiAccessTokenResponse (
    val access_token: String,
    val scope: String,
    val token_type: String,
)