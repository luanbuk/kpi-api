package br.bkwblz.kpi.goals.users

import br.bkwblz.kpi.arch.domain.DomainEntity
import java.util.*

data class User (
    val name: String
): DomainEntity {
    override val id =  UUID.randomUUID().toString()
}