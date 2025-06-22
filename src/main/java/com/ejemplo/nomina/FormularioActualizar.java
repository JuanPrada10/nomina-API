package com.ejemplo.nomina;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/formulario-actualizar")
public class FormularioActualizar {

    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_HTML + ";charset=UTF-8")
    public String mostrarFormulario(@PathParam("id") int id) {
        // Buscar empleado por ID
        Nomina empleado = null;
        List<Nomina> nominas = NominaStorage.load();
        for (Nomina n : nominas) {
            if (n.getId_empleado() == id) {
                empleado = n;
                break;
            }
        }

        if (empleado == null) {
            return "<h1>Empleado no encontrado</h1>";
        }

        // Construir formulario con valores precargados
        return """
        <!DOCTYPE html>
        <html lang="es">
        <head>
          <meta charset="UTF-8">
          <title>Actualizar Empleado</title>
          <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        </head>
        <body class='text-bg-dark'>
          <nav class="navbar text-bg-primary bg-gradient">
            <div class="container-fluid">
              <span class="navbar-brand text-light mb-0 h1">Actualizar Empleado</span>
            </div>
          </nav>
          <div class="container container-fluid w-25 mt-5">
            <form method="post" action="/api/nomina/formulario-actualizar">
              <input type="hidden" name="id_empleado" value="%d">
              <label class="form-label" for="nombre_empleado">Nombre completo:</label>
              <input class="form-control" type="text" id="nombre_empleado" name="nombre_empleado" value="%s" required>

              <label class="form-label" for="salario">Salario:</label>
              <input class="form-control" type="number" step="1" id="salario" name="salario" value="%s" required min="500000" max="10000000">

              <label class="form-label" for="dias_trabajados">Días trabajados:</label>
              <input class="form-control" type="number" id="dias_trabajados" name="dias_trabajados" value="%d" required min="1" max="31">

              <input type="submit" class="btn btn-primary mt-3" value="Actualizar">
            </form>
          </div>
        </body>
        </html>
        """.formatted(
            empleado.getId_empleado(),
            empleado.getNombre_empleado(),
            empleado.getSalario(),
            empleado.getDias_trabajados()
        );
    }
}
