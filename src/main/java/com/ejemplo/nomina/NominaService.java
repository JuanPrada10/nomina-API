package com.ejemplo.nomina;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
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
@POST
@Path("/formulario")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_HTML)
public Response recibirFormulario(
        @FormParam("nombre_empleado") String nombre,
        @FormParam("salario") double salario,
        @FormParam("dias_trabajados") int dias
) {
    List<Nomina> lista = NominaStorage.load();

    int nuevoId = lista.stream()
            .mapToInt(Nomina::getId_empleado)
            .max()
            .orElse(0) + 1;

    Nomina nueva = new Nomina();
    nueva.setId_empleado(nuevoId);
    nueva.setNombre_empleado(nombre);
    nueva.setSalario(salario);
    nueva.setDias_trabajados(dias);

    create(nueva);

    return Response.status(302).header("Location", "/api/htmlnomina").build();
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
    
    
@POST
@Path("/formulario-actualizar")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public Response procesarFormularioActualizar(
        @FormParam("id_empleado") int id,
        @FormParam("nombre_empleado") String nombre,
        @FormParam("salario") double salario,
        @FormParam("dias_trabajados") int dias
) {
    Nomina actualizado = new Nomina();
    actualizado.setId_empleado(id);
    actualizado.setNombre_empleado(nombre);
    actualizado.setSalario(salario);
    actualizado.setDias_trabajados(dias);
    
    update(actualizado.getId_empleado(),actualizado);
    

    return Response.status(302).header("Location", "/api/htmlnomina").build();
}

    @GET
    @Path("/eliminar/{id}")
    public Response delete(@PathParam("id") int id) {
        List<Nomina> lista = NominaStorage.load();
        boolean removed = lista.removeIf(n -> n.getId_empleado() == id);
        if (removed) {
            NominaStorage.save(lista);
            return Response.status(302).header("Location", "/api/htmlnomina").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

