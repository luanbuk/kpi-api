package br.bkwblz.kpi.goals

import br.bkwblz.kpi.arch.extensions.BsonExtensions.DOCUMENT_ID
import br.bkwblz.kpi.goals.Goal.Companion.COLLECTION_NAME
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters.*
import com.mongodb.client.model.UpdateOptions
import com.mongodb.client.model.Updates.combine
import com.mongodb.client.model.Updates.set
import org.litote.kmongo.findOne
import javax.enterprise.context.Dependent
import javax.inject.Inject

@Dependent
class GoalsRepositoryMongoImpl @Inject constructor(
    private val database: MongoDatabase
) : GoalsRepository {

    private val collection = database.getCollection(COLLECTION_NAME, Goal::class.java)

    override fun exists(id: String) = collection.countDocuments(eq(id)) > 0

    override fun findById(id: String) = collection.findOne(eq(id))

    override fun nameExists(goal: Goal) = collection.countDocuments(
        and(ne(DOCUMENT_ID, goal.id), eq(Goal::name.name, goal.name))
    ) > 0

    override fun insert(goal: Goal) = collection.insertOne(goal)

    override fun update(goal: Goal) = collection.replaceOne(eq(goal.id), goal).modifiedCount > 0

    override fun deactivate(id: String) {
        this.collection.updateOne(eq(id), combine(set("active", false)), UpdateOptions().upsert(false))
    }

    override fun entryExists(id: String, entryId: String) = collection.countDocuments(
        and(eq(id), eq("entries.$DOCUMENT_ID", entryId))
    ) > 0

    override fun deactivateEntry(id: String, entryId: String) {
        this.collection.updateOne(
            and(eq(id), eq("entries.$DOCUMENT_ID", entryId)),
            set("entries.$.active", false)
        )
    }

}