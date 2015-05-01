package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.ventas.entidades.Maquina;
import java.util.List;

/**
 *
 * @author elderson
 */
public class MaquinaDAO extends BaseDAO {

    public List<Object[]> ObtenerMaquinas(String filtro) {
        String sql = "SELECT \n"
                + "Maquina.idMaquina, Maquina.descripcion, Maquina.tipoMaquina, Maquina.activo \n"
                + "FROM \n"
                + "Ventas.Maquina " + filtro;
        return super.ObtenerLista(sql);
    }

    public int ActualizarMaquina(Maquina maquina) {
        String sql = String.format("UPDATE Ventas.Maquina SET descripcion = '%s', tipoMaquina = '%s', "
                + "cantidadCuerpos = %d, minimoGramaje = %d, maximoGramaje = %d, largoMinimoPliego = %d, "
                + "largoMaximoPliego = %d, altoMinimoPliego = %d, altoMaximoPliego = %d, margenPinza = %d, "
                + "margenSalida = %d, margenEscuadra = %d, margenContraEscuadra = %d, margenCalle = %d, "
                + "minimaResolucion = %d, maximaResolucion = %d, activo = %b WHERE idMaquina = %d",
                maquina.getDescripcion(), maquina.getTipoMaquina(), maquina.getCantidadCuerpos(),
                maquina.getMinimoGramaje(), maquina.getMaximoGramaje(), maquina.getLargoMinimoPliego(),
                maquina.getLargoMaximoPliego(), maquina.getAltoMinimoPliego(), maquina.getAltoMaximoPliego(),
                maquina.getMargenPinza(), maquina.getMargenSalida(), maquina.getMargenEscuadra(),
                maquina.getMargenContraEscuadra(), maquina.getMargenCalle(), maquina.getMinimaResolucion(),
                maquina.getMaximaResolucion(), maquina.isActivo(), maquina.getIdMaquina());
        return super.Ejecutar(sql);
    }

    public int EliminarMaquina(int idMaquina) {
        String sql = "DELETE FROM Ventas.Maquina WHERE idMaquina = " + idMaquina;
        return super.Ejecutar(sql);
    }
}
