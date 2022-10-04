package com.cren90.android.network.logging

import okhttp3.logging.HttpLoggingInterceptor

interface LogLevelProvider {
    val networkLogLevel: HttpLoggingInterceptor.Level
    val headersToRedact: List<String>
}