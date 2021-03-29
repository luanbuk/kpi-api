package br.bkwblz.kpi.goals.users.current

import br.bkwblz.kpi.goals.users.User
import javax.enterprise.context.RequestScoped
import javax.ws.rs.Produces

@RequestScoped
class CurrentUserProducer : CurrentUserCache{

    @get:Produces
    @field:Current
    var user: User? = null

    override fun update(principal: User) {
        this.user = principal
    }
}