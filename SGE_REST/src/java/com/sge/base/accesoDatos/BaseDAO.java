package com.sge.base.accesoDatos;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.jdbc.Work;

/**
 *
 * @author elderson
 */
public class BaseDAO {

    private Session sesion;
    private Transaction transaccion;
    private static SessionFactory fabricaSesiones;

    private List<String> getRecursos(){
        List<String> recursos = new ArrayList<>();
        // ADMINISTRACION
        recursos.add("com/sge/modulos/administracion/mapeos/Usuario.hbm.xml");
        recursos.add("com/sge/modulos/administracion/mapeos/Empleado.hbm.xml");
        recursos.add("com/sge/modulos/administracion/mapeos/Moneda.hbm.xml");
        recursos.add("com/sge/modulos/administracion/mapeos/Reporte.hbm.xml");
        recursos.add("com/sge/modulos/administracion/mapeos/ItemReporte.hbm.xml");
        recursos.add("com/sge/modulos/administracion/mapeos/ValorDefinido.hbm.xml");
        recursos.add("com/sge/modulos/administracion/mapeos/Numeracion.hbm.xml");
        // COMPRAS
        recursos.add("com/sge/modulos/compras/mapeos/Proveedor.hbm.xml");
        // INVENTARIOS
        recursos.add("com/sge/modulos/inventarios/mapeos/Almacen.hbm.xml");
        recursos.add("com/sge/modulos/inventarios/mapeos/Unidad.hbm.xml");
        recursos.add("com/sge/modulos/inventarios/mapeos/Producto.hbm.xml");
        recursos.add("com/sge/modulos/inventarios/mapeos/ProductoAlmacen.hbm.xml");
        recursos.add("com/sge/modulos/inventarios/mapeos/ProductoUnidad.hbm.xml");
        recursos.add("com/sge/modulos/inventarios/mapeos/EntradaInventario.hbm.xml");
        recursos.add("com/sge/modulos/inventarios/mapeos/ItemEntradaInventario.hbm.xml");
        recursos.add("com/sge/modulos/inventarios/mapeos/SalidaInventario.hbm.xml");
        recursos.add("com/sge/modulos/inventarios/mapeos/ItemSalidaInventario.hbm.xml");
        // VENTAS
        recursos.add("com/sge/modulos/ventas/mapeos/Cliente.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/DireccionCliente.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/ContactoCliente.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/PlantillaSolicitudCotizacion.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/ItemPlantillaSolicitudCotizacion.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/Servicio.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/Maquina.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/ServicioUnidad.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/ServicioMaquina.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/SolicitudCotizacion.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/ItemSolicitudCotizacion.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/FormaPago.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/ListaPrecioProducto.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/ItemListaPrecioProducto.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/UnidadListaPrecioProducto.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/EscalaListaPrecioProducto.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/ListaPrecioServicio.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/ItemListaPrecioServicio.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/UnidadListaPrecioServicio.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/EscalaListaPrecioServicio.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/ListaPrecioMaquina.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/ItemListaPrecioMaquina.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/EscalaListaPrecioMaquina.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/Cotizacion.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/ItemCotizacion.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/MetodoImpresion.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/Presupuesto.hbm.xml");
        recursos.add("com/sge/modulos/ventas/mapeos/ItemPresupuesto.hbm.xml");
        return recursos;
    }
    
    private SessionFactory getFabricaSesiones(){
        if(fabricaSesiones == null){
            Configuration cfg = new Configuration().configure(new File("/home/elderson/SGE_CONF/Configuracion.xml"));
            for (String recurso : getRecursos()) {
                cfg.addResource(recurso);
            }
            fabricaSesiones = cfg.buildSessionFactory();
        }
        return fabricaSesiones;
    }
    
    public void AbrirSesion() {
        sesion = getFabricaSesiones().openSession();
    }

    public void CerrarSesion() {
        if (sesion != null && sesion.isOpen()) {
            sesion.close();
        }
    }

    public void IniciarTransaccion() {
        if (sesion == null) {
            AbrirSesion();
        }
        transaccion = sesion.beginTransaction();
    }

    public void ConfirmarTransaccion() {
        transaccion.commit();
    }

    public void AbortarTransaccion() {
        transaccion.rollback();
    }

    public void Agregar(Object obj) {
        sesion.save(obj);
    }

    public void Actualizar(Object obj) {
        sesion.update(obj);
    }

    public <T> T ObtenerPorId(Class clase, int id) {
        return (T) sesion.get(clase, id);
    }

    public List<Object[]> ObtenerLista(String sql) {
        return sesion.createSQLQuery(sql).list();
    }

    public <T> T ObtenerLista(String sql, Class clase) {
        return (T)sesion.createSQLQuery(sql).addEntity(clase).list();
    }
    
    public <T> T ObtenerLista(Class clase, List<Object[]> filtros) {
        Criteria criteria = sesion.createCriteria(clase);
        for (Object[] filtro : filtros) {
            criteria.add(Restrictions.eq(filtro[0].toString(), filtro[1]));
        }
        return (T) criteria.list();
    }

    public int Ejecutar(String sql) {
        return sesion.createSQLQuery(sql).executeUpdate();
    }

    public void EjecutarFuncion(String sql) {
        sesion.doWork(new Work() {

            @Override
            public void execute(Connection cnctn) throws SQLException {
                cnctn.prepareCall(sql).executeQuery();
            }
        });
    }

    public Connection getConexion() throws Exception {
        SessionFactoryImplementor sfi = (SessionFactoryImplementor) sesion.getSessionFactory();
        ConnectionProvider cp = sfi.getConnectionProvider();
        Connection conn = cp.getConnection();
        return conn;
    }

    public Session getSesion() {
        return sesion;
    }

    public void AsignarSesion(BaseDAO baseDAO) {
        this.sesion = baseDAO.getSesion();
    }
}
