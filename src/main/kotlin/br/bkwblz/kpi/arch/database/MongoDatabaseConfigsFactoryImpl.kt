package br.bkwblz.kpi.arch.database

import org.eclipse.microprofile.config.inject.ConfigProperty
import javax.enterprise.context.Dependent
import javax.inject.Inject

@Dependent
class MongoDatabaseConfigsFactoryImpl @Inject constructor(
    @ConfigProperty(name = "MONGODB_URI", defaultValue = "mongodb://localhost") val mongoUri: String,
    @ConfigProperty(name = "MONGODB_USERNAME", defaultValue = "") val username: String?,
    @ConfigProperty(name = "MONGODB_NAME", defaultValue = "kpi") val databaseName: String,
    @ConfigProperty(name = "MONGODB_PASSWORD", defaultValue = "") val password: String?
) : MongoDatabaseConfigsFactory {

    override fun create() = MongoDatabaseConfigs(
        uri = mongoUri,
        username = username,
        databaseName = databaseName,
        password = password
    )
}