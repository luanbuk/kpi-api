package br.bkwblz.kpi.goals

interface GoalsRepository {
    fun exists(id: String): Boolean

    fun findById(id: String): Goal?

    fun nameExists(goal: Goal): Boolean

    fun insert(goal: Goal)

    fun update(it: Goal): Boolean

    fun deactivate(id: String)

    fun entryExists(id: String, entryId: String): Boolean

    fun deactivateEntry(id: String, entryId: String)
}