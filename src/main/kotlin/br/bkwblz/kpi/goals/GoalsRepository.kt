package br.bkwblz.kpi.goals

interface GoalsRepository {
    fun findById(id: String): Goal?

    fun nameExists(goal: Goal): Boolean

    fun insert(goal: Goal)

    fun update(it: Goal)
}