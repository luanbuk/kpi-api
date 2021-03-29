package br.bkwblz.kpi.arch.google

import org.eclipse.microprofile.config.inject.ConfigProperty
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.Produces

@ApplicationScoped
class OAuthCredentialsFactoryImpl @Inject constructor(
    @ConfigProperty(name = "GOOGLE_OAUTH_CLIENT_ID", defaultValue = " ") val id: String,
    @ConfigProperty(name = "GOOGLE_OAUTH_CLIENT_KEY", defaultValue = " ") val key: String
): OAuthCredentialsFactory {

    @Produces
    override fun create() = OAuthCredentials(
        id = id,
        key = key
    )
}