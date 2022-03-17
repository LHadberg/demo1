package tec.dk.demo1.resources;

import tec.dk.demo1.dataservice.PersonDataService;
import tec.dk.demo1.models.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/person")
public class PersonResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> get() {
        PersonDataService dataService = new PersonDataService();
        return dataService.getAllPersons();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{personId}")
    public Person getWithParam(@PathParam("personId") int personId) {
        PersonDataService dataService = new PersonDataService();
        return dataService.getPerson(personId);
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public int putString(Person person) {
        PersonDataService dataService = new PersonDataService();
        dataService.updatePerson(person);
        return 1;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public int postString(Person person) {
        PersonDataService dataService = new PersonDataService();
        System.out.println("POST Called");
        return dataService.addPerson(person);
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{personId}")
    public int deleteString(@PathParam("personId") int personId) {
        PersonDataService dataService = new PersonDataService();
        return dataService.deletePerson(personId);
    }
}