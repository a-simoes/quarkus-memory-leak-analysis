package org.acme.getting.started;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

@Path("/hello")
public class GreetingResource {

    @Inject
    Logger log;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        log.info("hello from org.jboss.logging.Logger");
        return "hello";
    }
}