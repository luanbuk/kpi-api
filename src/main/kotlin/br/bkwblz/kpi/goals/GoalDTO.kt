package br.bkwblz.kpi.goals

import br.bkwblz.kpi.goals.users.User
import java.time.LocalDate
import javax.validation.constraints.Size
import javax.ws.rs.NotFoundException

data class GoalDTO(
    var id: String,
    var description: String,
    var begin: LocalDate,
    var end: LocalDate,
    val userId: String
) {
    constructor(goal: Goal): this(
        id = goal.id,
        description = goal.name,
        begin = goal.begin,
        end = goal.end,
        userId = goal.user.id
    )

    fun toEntity(userFinder: (String) -> User?) = Goal(
        name = description,
        begin = begin,
        end = end,
        user = userFinder(this.userId) ?: throw NotFoundException("goals.user.notFound")
    )

    fun update(goal: Goal) = goal.apply {
        name = this@GoalDTO.description
        begin = this@GoalDTO.begin
        end = this@GoalDTO.end
    }
}