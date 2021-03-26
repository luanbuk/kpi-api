package br.bkwblz.kpi.arch.database

interface MongoDatabaseConfigsFactory {
    fun create(): MongoDatabaseConfigs
}