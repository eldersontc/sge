package com.sge.modulos.administracion.servicios;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author elderson
 */
@Path("generic")
public class UsuarioSRV {
    
    
    @Context
    private UriInfo context;

    public UsuarioSRV() {
    }

    @GET
    @Produces("application/xml")
    public String getXml() {
        return "XML";
    }
}
