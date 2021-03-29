package br.bkwblz.kpi.arch.extensions

import javax.servlet.http.HttpServletResponse
import javax.ws.rs.core.MediaType

fun HttpServletResponse.replyAsForbidden() =
    this.apply {
        status = HttpServletResponse.SC_FORBIDDEN
        contentType = MediaType.APPLICATION_JSON
    }