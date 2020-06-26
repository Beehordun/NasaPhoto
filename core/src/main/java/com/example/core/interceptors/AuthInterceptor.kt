package com.example.core.Interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()

        val request: Request = original.newBuilder()
            .header("x-api-key", "3G1dRnqxojY5smDz0xWVmhVasGoUCAK5Hb1ldwng")
            .method(original.method(), original.body())
            .build()

        return chain.proceed(request)
    }
}
