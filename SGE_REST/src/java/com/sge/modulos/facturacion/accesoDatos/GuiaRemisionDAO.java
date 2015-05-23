package com.sge.modulos.facturacion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.facturacion.entidades.GuiaRemision;
import java.util.List;

/**
 *
 * @author elderson
 */
public class GuiaRemisionDAO extends BaseDAO {
    
    public List<GuiaRemision> ObtenerGuiasRemision(String filtro) {
        String sql = "SELECT \n"
                + "GuiaRemision.* \n"
                + "FROM \n"
                + "Facturacion.GuiaRemision AS GuiaRemision " + filtro;
        return super.ObtenerLista(sql, GuiaRemision.class);
    }

    public int ActualizarGuiaRemision(GuiaRemision guiaRemision) {
        String sql = String.format("UPDATE Facturacion.GuiaRemision "
                + "SET idCliente = %d, razonSocialCliente = '%s', idResponsable = %d, nombreResponsable = '%s', "
                + "idChofer = %d, nombreChofer = '%s', licenciaConducir = '%s', numeroPlaca = '%s', idContactoCliente = %d, "
                + "nombreContactoCliente = '%s', idDepartamento = %d, nombreDepartamento = '%s', idProvincia = %d, "
                + "nombreProvincia = '%s', idDistrito = %d, nombreDistrito = '%s', direccion = '%s', observacion = '%s' "
                + "WHERE idGuiaRemision = %d", guiaRemision.getIdCliente(), guiaRemision.getRazonSocialCliente(), 
                guiaRemision.getIdResponsable(), guiaRemision.getNombreResponsable(), guiaRemision.getIdChofer(),
                guiaRemision.getNombreChofer(), guiaRemision.getLicenciaConducir(), guiaRemision.getNumeroPlaca(),
                guiaRemision.getIdContactoCliente(), guiaRemision.getNombreContactoCliente(), guiaRemision.getIdDepartamento(),
                guiaRemision.getNombreDepartamento(), guiaRemision.getIdProvincia(), guiaRemision.getNombreProvincia(),
                guiaRemision.getIdDistrito(), guiaRemision.getNombreDistrito(), guiaRemision.getDireccion(), guiaRemision.getObservacion(),
                guiaRemision.getIdGuiaRemision());
        return super.Ejecutar(sql);
    }
    
    public int EliminarGuiaRemision(int idGuiaRemision) {
        String sql = "DELETE FROM Facturacion.GuiaRemision WHERE idGuiaRemision = " + idGuiaRemision;
        return super.Ejecutar(sql);
    }
}
