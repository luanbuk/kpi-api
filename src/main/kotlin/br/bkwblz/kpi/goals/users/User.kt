package br.bkwblz.kpi.goals.users

import br.bkwblz.kpi.arch.domain.DomainEntity
import com.mongodb.DBRef
import java.util.*

data class User(
    val name: String
) : DomainEntity {
    override val id = UUID.randomUUID().toString()

    override val active = true

    fun toDbRef() = DBRef(COLLECTION_NAME, this.id)

    companion object {
        val COLLECTION_NAME = "users"
    }
}