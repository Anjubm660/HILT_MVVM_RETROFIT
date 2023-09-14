package com.anju.hilt_mvvm_retro

import com.anju.hilt_mvvm_retro.api.UsersAPI
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APITest {
    lateinit var api: UsersAPI
    lateinit var mockWebServer: MockWebServer


    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS) // Adjust the timeout as needed
                    .readTimeout(60, TimeUnit.SECONDS)    // Adjust the timeout as needed
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsersAPI::class.java)
    }



    @Test
    fun testGetUsers() = runBlocking {
       val mockResponse = MockResponse()
        mockResponse.setBody("[]")
       mockWebServer.enqueue(mockResponse)
        val response = api.getStaff()
        mockWebServer.takeRequest()
        Assert.assertEquals(true,response.body()!!.isEmpty())
    }


    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }


}