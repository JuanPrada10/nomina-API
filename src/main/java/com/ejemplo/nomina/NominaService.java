package com.ejemplo.nomina;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("nomina")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NominaService {

    @GET
    public Response getAll() {
    	System.out.println("Ingresa a Get ");
        return Response.ok(NominaStorage.load()).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") int id) {
        List<Nomina> lista = NominaStorage.load();
        for (Nomina n : lista) {
            if (n.getId_empleado() == id) {
                return Response.ok(n).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response create(Nomina nomina) {
        List<Nomina> lista = NominaStorage.load();
        lista.add(nomina);
        NominaStorage.save(lista);
        return Response.status(Response.Status.CREATED).entity(nomina).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") int id, Nomina updated) {
        List<Nomina> lista = NominaStorage.load();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId_empleado() == id) {
                lista.set(i, updated);
                NominaStorage.save(lista);
                return Response.ok(updated).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        List<Nomina> lista = NominaStorage.load();
        boolean removed = lista.removeIf(n -> n.getId_empleado() == id);
        if (removed) {
            NominaStorage.save(lista);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}