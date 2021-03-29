package br.bkwblz.kpi.arch.filters.authorization

import br.bkwblz.kpi.arch.extensions.replyAsForbidden
import br.bkwblz.kpi.arch.filters.BaseFilter
import java.util.stream.Collectors.toList
import java.util.stream.StreamSupport
import javax.enterprise.inject.Any
import javax.enterprise.inject.Instance
import javax.inject.Inject
import javax.servlet.FilterChain
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = ["/*"])
class AuthorizationFilter @Inject constructor(
    @Any rules: Instance<AuthorizerRule>
) : BaseFilter() {

    private val rules = rules.spliterator().let { StreamSupport.stream(it, false).collect(toList()) }

    override fun filter(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        if (rules.any { it.isAllowed(request) }) {
            return chain.doFilter(request, response)
        }
        response.replyAsForbidden()
    }

}