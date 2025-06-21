package com.ejemplo.nomina;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/formulario")
public class FormNominaResource {

    @GET
    @Produces(MediaType.TEXT_HTML + ";charset=UTF-8")
    public String mostrarFormulario() {
        return """
        <!DOCTYPE html>
        <html lang="es">
        <head>
          <meta charset="UTF-8">
          <title>Nuevo Empleado</title>
          <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        </head>
        <body class='text-bg-dark'>
               <nav class="navbar text-bg-primary bg-gradient">
                               <div class="container-fluid">
                                 <span class="navbar-brand text-light mb-0 h1">Formulario</span>
                               </div>
                             </nav>
          <div class="container container-fluid w-25 mt-5">
            
            <form method="post" action="/api/nomina/formulario">
              <label class="form-label" for="nombre_empleado">Nombre completo:</label>
              <input class="form-control" type="text" id="nombre_empleado" name="nombre_empleado" required>
        
              <label class="form-label" for="salario">Salario:</label>
              <input class="form-control" type="number" step="1" id="salario" name="salario" required>
        
              <label class="form-label" for="dias_trabajados">Días trabajados:</label>
              <input class="form-control" type="number" id="dias_trabajados" name="dias_trabajados" required>
        
              <input type="submit" class="btn btn-primary mt-3" value="Guardar">
            </form>
          </div>
        </body>
        </html>
        """;
    }
}
