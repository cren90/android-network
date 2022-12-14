/**
 * Created by Chris Renfrow on 2019-12-09.
 */

package com.cren90.android.network.auth

import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class OAuthRefreshDelegate @Inject constructor() {

    @Inject
    lateinit var authTokenRepo: AuthTokenRepo

    @Inject
    lateinit var tokenValidator: TokenValidator

    val oauthBearerToken: String?
        get() = authTokenRepo.getAccessToken()

    val oauthRefreshToken: String?
        get() = authTokenRepo.getRefreshToken()

    val isOAuthSessionRecoverable: Boolean
        get() = tokenValidator.isTokenValid(oauthRefreshToken)

    fun refreshBearerToken(): String? = runBlocking {
        authTokenRepo.refreshAccessToken()
    }
}