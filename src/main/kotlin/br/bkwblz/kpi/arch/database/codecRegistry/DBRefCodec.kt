package br.bkwblz.kpi.arch.database.codecRegistry

import com.mongodb.DBRef
import org.bson.BsonReader
import org.bson.BsonType
import org.bson.BsonType.END_OF_DOCUMENT
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext
import org.bson.codecs.configuration.CodecRegistry

class DBRefCodec(private val registry: () -> CodecRegistry) : Codec<DBRef> {
    override fun encode(writer: BsonWriter, value: DBRef?, encoderContext: EncoderContext) {
        if (value != null) {
            writer.writeStartDocument()
            writer.writeString(REF_FIELD, value.collectionName)
            writer.writeName(ID_FIELD)
            val codec = registry().get(String::class.java)
            codec.encode(writer, value.id as String, encoderContext)
            if (value.databaseName != null) {
                writer.writeString(DB_FIELD, value.databaseName)
            }
            writer.writeEndDocument()
        }
    }

    override fun getEncoderClass(): Class<DBRef> = DBRef::class.java

    override fun decode(reader: BsonReader, decoderContext: DecoderContext): DBRef {
        reader.readStartDocument()
        val ref = reader.readString(REF_FIELD)
        val id = reader.readString(ID_FIELD)
        val db = if (reader.readBsonType() != END_OF_DOCUMENT) reader.readString(DB_FIELD) else null
        reader.readEndDocument()
        return if (db == null) DBRef(ref, id) else DBRef(ref, id, db)
    }

    companion object {
        const val REF_FIELD = "\$ref"
        const val ID_FIELD = "\$id"
        private const val DB_FIELD = "\$db"
    }
}
