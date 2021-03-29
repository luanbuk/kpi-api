package br.bkwblz.kpi.arch.filters.authorization

import javax.enterprise.context.Dependent
import javax.servlet.http.HttpServletRequest
import javax.ws.rs.HttpMethod

@Dependent
class DefaultBypassRule : BypassRule {
    override fun isAllowed(request: HttpServletRequest) =
        request.isPathAllowed() || request.isMethodAllowed()

    private fun HttpServletRequest.isPathAllowed() = this.requestURI.startsWith("/public")

    private fun HttpServletRequest.isMethodAllowed() =
        HttpMethod.OPTIONS == this.method || HttpMethod.HEAD == this.method
}