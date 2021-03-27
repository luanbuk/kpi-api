package br.bkwblz.kpi.arch.database.codecRegistry

import org.bson.codecs.configuration.CodecRegistry

interface CodecRegistryFactory {
    fun create(codecRegistrySupplier: () -> CodecRegistry): CodecRegistry
}