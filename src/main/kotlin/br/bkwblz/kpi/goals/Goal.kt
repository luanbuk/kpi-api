package br.bkwblz.kpi.goals

import br.bkwblz.kpi.arch.domain.DomainEntity
import br.bkwblz.kpi.arch.dates.DateInterval
import br.bkwblz.kpi.goals.GoalsErros.goalsEntryDateNotWithinInterval
import br.bkwblz.kpi.goals.GoalsErros.goalsEntryWithDuplicatedDate
import br.bkwblz.kpi.goals.users.User
import org.bson.codecs.pojo.annotations.BsonId
import java.lang.RuntimeException
import java.time.LocalDate
import java.util.*

data class Goal(
    var name: String,
    var begin: LocalDate,
    var end: LocalDate,
    val user: User
): DomainEntity {

    @BsonId
    override val id =  UUID.randomUUID().toString()

    override val active = true

    private val executionInterval: DateInterval get() = DateInterval(begin = begin, end = end)

    private var entries = mutableSetOf<Entry>()

    fun addEntry(entry: Entry): Goal {
        if(entry.isWithinInterval(executionInterval)){
            if(entry.isDayDuplicated(this::isDayPresentInEntries)){
                throw RuntimeException(goalsEntryWithDuplicatedDate)
            }
            this.entries.add(entry)
        }
        throw RuntimeException(goalsEntryDateNotWithinInterval)
    }

    private fun isDayPresentInEntries(day: LocalDate) = this@Goal.entries.any { e -> e.active && e.day == day }

    companion object{
        val COLLECTION_NAME = "goals"
    }
}

data class Entry(
    val day: LocalDate,
    val observation: String
){
    @BsonId
    val id =  UUID.randomUUID().toString()

    val active = true

    fun isWithinInterval(interval: DateInterval) = interval.contains(day)

    fun isDayDuplicated(isPresent: (LocalDate) -> Boolean) = isPresent(day)
}