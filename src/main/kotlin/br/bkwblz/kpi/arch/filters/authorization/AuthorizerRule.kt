package br.bkwblz.kpi.arch.filters.authorization

import javax.servlet.http.HttpServletRequest

interface AuthorizerRule {
    fun isAllowed(request: HttpServletRequest): Boolean
}