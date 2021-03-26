package br.bkwblz.kpi.arch.exceptions

import br.bkwblz.kpi.arch.extensions.toResponse
import javax.ws.rs.NotFoundException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
open class NotFoundExceptionMapper : ExceptionMapper<NotFoundException> {
    override fun toResponse(exception: NotFoundException?) = exception.toResponse()
}