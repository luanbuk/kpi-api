package br.bkwblz.kpi.arch.database.codecRegistry

import org.bson.codecs.pojo.ClassModelBuilder
import org.bson.codecs.pojo.Convention

class ClassnameDiscriminatorConvention : Convention {
    override fun apply(classModelBuilder: ClassModelBuilder<*>) {
        classModelBuilder.enableDiscriminator(true).discriminatorKey(CLASS_DISCRIMINATOR_FIELD)
            .discriminator(classModelBuilder.type.simpleName)
    }

    companion object {
        private const val CLASS_DISCRIMINATOR_FIELD = "className"
    }
}