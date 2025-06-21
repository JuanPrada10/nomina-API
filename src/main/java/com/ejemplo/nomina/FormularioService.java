package com.ejemplo.nomina;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/formulario")
public class FormularioService {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response recibirFormulario(
            @FormParam("id_empleado") int id,
            @FormParam("nombre_empleado") String nombre,
            @FormParam("salario") double salario,
            @FormParam("dias_trabajados") int dias
    ) {
        Nomina nueva = new Nomina();
        nueva.setId_empleado(id);
        nueva.setNombre_empleado(nombre);
        nueva.setSalario(salario);
        nueva.setDias_trabajados(dias);

        List<Nomina> lista = NominaStorage.load();
        lista.add(nueva);
        NominaStorage.save(lista);

        // Redirige al HTML que muestra los datos
        return Response.status(302).header("Location", "/api/htmlnomina").build();
    }
}
