package br.bkwblz.kpi.arch.extensions

import javax.json.Json.createObjectBuilder
import javax.json.JsonObjectBuilder
import javax.ws.rs.BadRequestException
import javax.ws.rs.ForbiddenException
import javax.ws.rs.NotAuthorizedException
import javax.ws.rs.NotFoundException
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status.*
import javax.ws.rs.core.Response.status

fun BadRequestException?.toResponse() = this.toResponse(BAD_REQUEST)

fun ForbiddenException?.toResponse() = this.toResponse(FORBIDDEN)

fun NotAuthorizedException?.toResponse() = this.toResponse(UNAUTHORIZED)

fun NotFoundException?.toResponse() = this.toResponse(NOT_FOUND)

private fun Exception?.toResponse(status: Response.Status) = status(status)
    .addBodyIfPresent(this?.message).type(APPLICATION_JSON)
    .build()

private fun Response.ResponseBuilder.addBodyIfPresent(message: String?) =
    message?.let { m -> this.entity(createObjectBuilder().add("failure", message)) } ?: this