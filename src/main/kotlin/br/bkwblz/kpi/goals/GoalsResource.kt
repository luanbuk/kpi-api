package br.bkwblz.kpi.goals

import br.bkwblz.kpi.goals.GoalsErros.goalsDuplicatedName
import br.bkwblz.kpi.goals.GoalsErros.goalsEntryNotFound
import br.bkwblz.kpi.goals.GoalsErros.goalsNotFound
import br.bkwblz.kpi.goals.users.UsersRepository
import javax.inject.Inject
import javax.ws.rs.*

@Path("goals")
class GoalsResource @Inject constructor(
    private val usersRepository: UsersRepository,
    private val goalsRepository: GoalsRepository
){

    @POST
    fun insert(dto: GoalDTO) = dto.toEntity(usersRepository::findById)
        .throwIfDuplicatedDescription()
        .also { goalsRepository.insert(it) }

    @PUT
    @Path("/{id}")
    fun update(@PathParam("id") id: String, dto: GoalDTO) = dto.update(id.toEntity())
        .throwIfDuplicatedDescription()
        .also { goalsRepository.update(it) }

    @GET
    @Path("/{id}")
    fun getById(@PathParam("id") id: String) = GoalDTO(id.toEntity())

    @DELETE
    @Path("/{id}")
    fun delete(@PathParam("id") id: String) = id.takeIf { goalsRepository.exists(id) }
        ?.also { goalsRepository.deactivate(it) } ?: throw NotFoundException(goalsNotFound)

    @PATCH
    @PathParam("/{id}/entry")
    fun includeEntry(@PathParam("id") id: String, entry: Entry) = id.toEntity()
        .addEntry(entry).also { goalsRepository.update(it) }

    @DELETE
    @Path("/{id}/entry/{entryId}")
    fun delete(@PathParam("id") id: String, @PathParam("entryId") entryId: String) =
        (id to entryId).takeIf { goalsRepository.entryExists(it.first, it.second) }?.also {
            goalsRepository.deactivateEntry(it.first, it.second)
        } ?: throw NotFoundException(goalsEntryNotFound)

    private fun Goal.throwIfDuplicatedDescription() =
        if(goalsRepository.nameExists(this)) this else throw RuntimeException(goalsDuplicatedName)

    private fun String.toEntity() = goalsRepository.findById(this) ?: throw NotFoundException(goalsNotFound)

}