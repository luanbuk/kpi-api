package br.bkwblz.kpi.arch.exceptions

import br.bkwblz.kpi.arch.extensions.toResponse
import javax.ws.rs.NotAuthorizedException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
open class NotAuthorizedExceptionMapper : ExceptionMapper<NotAuthorizedException> {
    override fun toResponse(exception: NotAuthorizedException?) = exception.toResponse()
}