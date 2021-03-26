package br.bkwblz.kpi.arch.database

import br.bkwblz.kpi.arch.database.codecRegistry.CodecRegistryFactory
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoClients.create
import com.mongodb.client.MongoDatabase
import mu.KLogging
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.context.Dependent
import javax.enterprise.inject.Produces
import javax.inject.Inject

@Dependent
class MongoDatabaseProducer @Inject constructor(
    configsFactory: MongoDatabaseConfigsFactory,
    private val codecRegistryFactory: CodecRegistryFactory
) {

    private val configs = configsFactory.create()

    private val mongoUri = String.format(configs.uri, configs.username, configs.password)

    @get:ApplicationScoped
    @get:Produces
    val db: MongoDatabase by lazy {
        this.createNewClient().also {
            logger.info { "Abrindo nova conexão com MongoDB - ${configs.databaseName}" }
        }.getDatabase(configs.databaseName)
    }

    private fun createNewClient() = create(
        MongoClientSettings.builder().applyConnectionString(ConnectionString(this.mongoUri))
            .codecRegistry(codecRegistryFactory.create()).build()
    )

    companion object : KLogging()

}