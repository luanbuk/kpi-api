package br.bkwblz.kpi.goals.users

interface UsersRepository {
    fun findById(id: String): User?
}