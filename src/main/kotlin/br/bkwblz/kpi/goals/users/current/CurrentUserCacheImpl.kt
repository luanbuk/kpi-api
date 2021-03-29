package br.bkwblz.kpi.goals.users.current

import br.bkwblz.kpi.goals.users.User
import javax.enterprise.context.RequestScoped

@RequestScoped
class CurrentUserCacheImpl : CurrentUserCache{

    var user: User? = null

    override fun update(principal: User) {
        this.user = principal
    }
}