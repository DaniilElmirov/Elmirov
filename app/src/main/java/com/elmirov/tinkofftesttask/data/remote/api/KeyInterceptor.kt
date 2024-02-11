package com.elmirov.tinkofftesttask.data.remote.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class KeyInterceptor @Inject constructor() : Interceptor {
    private companion object {
        private const val API_KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("X-API-KEY", API_KEY)

        return chain.proceed(requestBuilder.build())
    }
}