package br.bkwblz.kpi.arch.filters.authorization

import br.bkwblz.kpi.goals.users.User
import br.bkwblz.kpi.goals.users.current.Current
import br.bkwblz.kpi.goals.users.current.CurrentUserCache
import javax.enterprise.context.Dependent
import javax.inject.Inject
import javax.servlet.http.HttpServletRequest

@Dependent
class DefaultAuthorizerRule @Inject constructor(
    @Current private val user: User?
): AuthorizerRule {
    override fun isAllowed(request: HttpServletRequest) = user != null
}