package br.bkwblz.kpi.goals.users

import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters.eq
import org.litote.kmongo.findOne
import javax.enterprise.context.Dependent
import javax.inject.Inject

@Dependent
class UsersRepositoryMongoImpl  @Inject constructor(
    private val database: MongoDatabase
): UsersRepository {

    private val collection by lazy { database.getCollection(User.COLLECTION_NAME, User::class.java) }

    override fun findById(id: String) = collection.findOne(eq(id))
}