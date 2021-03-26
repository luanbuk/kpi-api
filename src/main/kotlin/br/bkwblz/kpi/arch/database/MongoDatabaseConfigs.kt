package br.bkwblz.kpi.arch.database

data class MongoDatabaseConfigs constructor(
    val uri: String,
    val username: String?,
    val databaseName: String,
    val password: String?
)