package br.bkwblz.kpi.arch.database.codecRegistry

import br.bkwblz.kpi.arch.database.codecRegistry.ClassnameDiscriminatorConvention
import com.mongodb.MongoClientSettings.getDefaultCodecRegistry
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.jsr310.LocalDateCodec
import org.bson.codecs.jsr310.LocalDateTimeCodec
import org.bson.codecs.pojo.Convention
import org.bson.codecs.pojo.Conventions
import org.bson.codecs.pojo.PojoCodecProvider
import java.util.*
import javax.enterprise.context.Dependent

@Dependent
class CodecRegistryFactoryImpl : CodecRegistryFactory{

    override fun create() = CodecRegistries.fromRegistries(
        CodecRegistries.fromCodecs(LocalDateCodec(), LocalDateTimeCodec()),
        getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(
            PojoCodecProvider.builder().automatic(true)
                .conventions(
                    Arrays.asList<Convention>(
                        ClassnameDiscriminatorConvention(),
                        Conventions.ANNOTATION_CONVENTION,
                        Conventions.CLASS_AND_PROPERTY_CONVENTION,
                        Conventions.SET_PRIVATE_FIELDS_CONVENTION
                    ))
                .build()
        )
    )

}