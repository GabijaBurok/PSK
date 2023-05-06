package lt.vu.psk.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.entities.Duty;
import lt.vu.psk.persistance.DutyDAO;
import lt.vu.psk.rest.contracts.DutyDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@ApplicationScoped
@Path("/duties")
public class DutyController {

    @Inject
    @Setter @Getter
    private DutyDAO dutyDAO;

    @Inject
    private EntityManager em;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {

        Duty duty = dutyDAO.findOne(id);

        if (duty == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        DutyDto dutyDto = new DutyDto();
        dutyDto.setDutyName(duty.getDuty_name());


        return Response.ok(dutyDto).build();
    }

    // http://localhost:8080/PSK-TP-1-1.0-SNAPSHOT/api/duties
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(DutyDto dutyDto) {

        try {
            if (dutyDto == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            Duty dutyToCreate = new Duty();
            dutyToCreate.setDuty_name(dutyDto.getDutyName());
            dutyDAO.persist(dutyToCreate);

            URI location = UriBuilder.fromResource(DutyController.class)
                    .path("/{id}")
                    .resolveTemplate("id", dutyToCreate.getId())
                    .build();

            return Response.created(location).build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }

    }

}
