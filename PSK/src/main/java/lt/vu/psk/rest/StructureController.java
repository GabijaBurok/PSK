package lt.vu.psk.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.entities.Structure;
import lt.vu.psk.persistance.StructureDAO;
import lt.vu.psk.rest.contracts.StructureDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/structures")
public class StructureController {

    @Inject
    @Setter @Getter
    private StructureDAO structureDAO;

    @Getter @Setter
    private Structure structure;

    // http://localhost:8080/PSK-TP-1-1.0-SNAPSHOT/api/structures/1
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id){
        Structure structure = structureDAO.findOne(id);

        if (structure == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        StructureDto structureDto = new StructureDto();
        structureDto.setStructureName(structure.getStructure_name());
        structureDto.setMemberNumber(structure.getMember_number());

        return Response.ok(structureDto).build();
    }

    // http://localhost:8080/PSK-TP-1-1.0-SNAPSHOT/api/structures/1
    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer structureId, StructureDto structureDto){
        try {
            Structure existingStructure = structureDAO.findOne(structureId);
            if (existingStructure == null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingStructure.setStructure_name(structureDto.getStructureName());
            existingStructure.setMember_number(structureDto.getMemberNumber());
            structureDAO.update(existingStructure);
            return Response.ok().build();
        } catch (OptimisticLockException e){
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

}
