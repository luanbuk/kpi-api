package br.bkwblz.kpi.arch.filters.authentication

import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import com.google.api.client.auth.oauth2.BrowserClientRequestUrl
import javax.servlet.annotation.WebServlet


@WebServlet("/login")
class AuthenticationFilter : HttpServlet(){

    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        val url = BrowserClientRequestUrl(
            "https://server.example.com/authorize", "s6BhdRkqt3"
        ).setState("xyz")
            .setRedirectUri("https://client.example.com/cb").build()
        response.sendRedirect(url)
    }
}