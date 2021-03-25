package br.bkwblz.kpi.test

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.core.Response

@Path("test")
class TestResource {

    @GET
    fun sayHello(): Response = Response.ok("Hello!").build()

}