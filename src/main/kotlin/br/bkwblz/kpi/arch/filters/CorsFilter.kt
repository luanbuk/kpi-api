package br.bkwblz.kpi.arch.filters

import javax.servlet.*
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter(filterName = "CorsFilter", urlPatterns = ["/*"])
class CorsFilter : BaseFilter() {

    override fun filter(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        response.setHeader("Access-Control-Allow-Origin", "*")
        response.setHeader(
            "Access-Control-Allow-Headers",
            "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With, Origin, Accept, Tenant"
        )
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS, HEAD")
        response.setHeader("Access-Control-Allow-Credentials", "true")
        response.setHeader("Access-Control-Max-Age", "1209600")
        chain.doFilter(request, response)
    }

    override fun destroy() {}
}