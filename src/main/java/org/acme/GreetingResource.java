package org.acme;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.acme.entities.JsonHello;

@Path("/demo")
public class GreetingResource {

    /**
     * Uses GET with no arguments, returns String
     * WIll be used for default curl http://localhost:8080/demo/hello
     * @return 
     */
    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloText() {
        return "Hello from RESTEasy Reactive";
    }
    
    /**
     * Uses GET with no arguments, returns String
     * call using curl -H "Accept: application/json" http//localhost:8080/demo/hello
     * @return 
     */
    @Path("/hello")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonHello helloJson() {
        JsonHello greeting = new JsonHello("JSON hello from RESTEasy Reactive");
        return greeting;
    }
    
    /**
     * This GET uses path to provide arguments to web service. 
     * Notice that ALL parameters must be present!
     * See test case for usage
     * @param user
     * @param greeting
     * @return 
     */
    @Path("/userhello/{user}/{greeting}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloPathGet(@PathParam("user") String user, @PathParam("greeting") String greeting) {
        return "Hello " + user + ": " + greeting;
    }
    
    /**
     * This GET uses query parameters to provide args. 
     * Queryparams may be left out and you can provide default values to handle that
     * Test case shows usage
     * @param user
     * @param greeting
     * @return 
     */
    @Path("/queryhello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloQueryGet(@QueryParam("user") @DefaultValue("Random") String user, @QueryParam("greeting") @DefaultValue("Nice to see you!") String greeting) {
        return "Hello " + user + ": " + greeting;
    }
    
    /**
     * This GET uses query parameters to provide args. 
     * Queryparams may be left out and you can provide default values to handle that
     * Test case shows usage
     * @param user
     * @param greeting
     * @return 
     */
    @Path("/querymixed/{user}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloMixedGet(@PathParam("user") String user, @QueryParam("greeting") @DefaultValue("Nice to see you!") String greeting) {
        return "Hello " + user + ": " + greeting;
    }
    
    
    /**
     * I en POST så blir argumentet i funktionen datasatt från det som kommer som body-data i POST. Man skickar alltså 
     * data efter att URL'en kopplats upp, och kan skicka stora mängder om det behövs. (En URL får inte vara hur lång som helst, så GET funkar inte då)
     * Dessutom så krypteras datat om vi kör HTTPS, vilket är bra.
     * Kan anropas med
     * curl -H "Content-Type: application/json" -H "Accept: text/plain" -X POST -d '{"greeting":"Hi there!"}' "http://localhost:8080/demo/hello/kalle"
     * Notera att Quarkus sköter hanteringen av JSON på egen hand...
     * @param user
     * @param greeting
     * @return 
     */
    @Path("/hello/{user}")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String helloPost(@PathParam("user") String user, JsonHello greeting) {
        return "Hello " + user + ": " + greeting.getMyGreeting();
    }
    
    
}