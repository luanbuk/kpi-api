package br.bkwblz.kpi.goals

import br.bkwblz.kpi.arch.domain.DomainEntity
import br.bkwblz.kpi.goals.users.User
import java.time.LocalDate
import java.util.*

data class Goal(
    var description: String,
    var begin: LocalDate,
    var end: LocalDate,
    val entries: Set<Entry>,
    val user: User
): DomainEntity {
    override val id =  UUID.randomUUID().toString()
}

data class Entry(
    val day: LocalDate,
    val observation: String
)