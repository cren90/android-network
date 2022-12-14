/**
 * Created by Chris Renfrow on 2019-11-21.
 */

package com.cren90.android.network.inject.modules

import com.cren90.android.logging.Logger
import com.cren90.android.network.AUTHORIZATION_HEADER_KEY
import com.cren90.android.network.logging.LogLevelProvider
import com.tinder.scarlet.lifecycle.android.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InterceptorModule {

    @Provides
    @Singleton
    fun provideLogInterceptor(logger: Logger, logLevelProvider: LogLevelProvider): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message ->
            logger.debug(message, "android-network")
        }.apply {
            level = logLevelProvider.networkLogLevel
            logLevelProvider.headersToRedact.forEach {
                redactHeader(it)
            }
        }
}