package br.bkwblz.kpi.arch.google

import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import javax.inject.Inject

@QuarkusTest
class GoogleAuthIT {

    @Inject
    lateinit var credentialsFactory: OAuthCredentialsFactory

    @Test
    fun testCredentials(){
        val credentials = credentialsFactory.create()

        assertTrue(credentials.id.isNotBlank())
        assertTrue(credentials.key.isNotBlank())
    }

}