package br.bkwblz.kpi.arch.filters

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

abstract class BaseFilter : Filter {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) =
        this.filter(request as HttpServletRequest, response as HttpServletResponse, chain)

    abstract fun filter(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain)

}