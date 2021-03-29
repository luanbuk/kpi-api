package br.bkwblz.kpi.arch.filters.authorization

import javax.servlet.http.HttpServletRequest

interface BypassRule {

    fun isAllowed(request: HttpServletRequest): Boolean

}