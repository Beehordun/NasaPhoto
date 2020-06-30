package com.example.core.interceptors

import com.example.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()

        val request: Request = original.newBuilder()
            .header("x-api-key", BuildConfig.NASA_API_KEY)
            .method(original.method(), original.body())
            .build()

        return chain.proceed(request)
    }
}
