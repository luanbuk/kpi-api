package br.bkwblz.kpi.goals.users.current

import br.bkwblz.kpi.goals.users.User

interface CurrentUserCache {
    fun update(principal: User)
}