package br.bkwblz.kpi.arch.exceptions

import br.bkwblz.kpi.arch.extensions.toResponse
import javax.ws.rs.BadRequestException
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
open class BadRequestExceptionMapper : ExceptionMapper<BadRequestException> {
    override fun toResponse(exception: BadRequestException?) = exception.toResponse()
}