package br.bkwblz.kpi.arch.exceptions

import br.bkwblz.kpi.arch.extensions.toResponse
import javax.ws.rs.ForbiddenException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
open class ForbiddenExceptionMapper : ExceptionMapper<ForbiddenException> {
    override fun toResponse(exception: ForbiddenException?) = exception.toResponse()
}