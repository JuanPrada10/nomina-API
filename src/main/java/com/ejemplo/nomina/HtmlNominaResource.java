package com.ejemplo.nomina;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/htmlnomina")
public class HtmlNominaResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getHtmlNomina() {
        List<Nomina> nominas = NominaStorage.load();

        StringBuilder html = new StringBuilder();
        html.append("""
            <html>
            <head>
              <meta charset="UTF-8">
              <title>Nómina Detallada</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
            </head>
            <body class='bg-dark'>
              <nav class="navbar text-bg-primary bg-gradient">
                <div class="container-fluid">
                  <span class="navbar-brand text-light mb-0 h1">Nomina Detallada</span>
                </div>
              </nav>
                    <div class="container-xl mt-5 ">
                    <table class=" table table-dark table-striped table-hover table-bordered">
                                    <tr><th >ID</th><th>Nombre</th><th>Salario</th><th>Días Trabajados</th><th>Total a Pagar</th></tr>
                    """);
                            for (Nomina n : nominas) {
                                double totalPagar = (n.getSalario() / 30) * n.getDias_trabajados();
                                html.append("<tr class='table-dark'>")
                                        .append("<td class='table-dark'>").append(n.getId_empleado()).append("</td>")
                                        .append("<td class='table-dark'>").append(n.getNombre_empleado()).append("</td>")
                                        .append("<td class='table-dark'>$").append(n.getSalario()).append("</td>")
                                        .append("<td class='table-dark'>").append(n.getDias_trabajados()).append("</td>")
                                        .append("<td class='table-dark'>$").append(String.format("%.2f", totalPagar)).append("</td>")
                                        .append("</tr>");
                            }
                    
                            html.append("""
                      </table>
                                        <a href="formulario">
                                             <button class="btn btn-success bg-gradient"> Añadir empleado</button>
                                           </a>
                    </div>
              
   
</body>
</html>
""");

        return Response.ok(html.toString()).build();
    }
}
