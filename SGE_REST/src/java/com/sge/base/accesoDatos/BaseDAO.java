package com.sge.base.accesoDatos;

import java.io.File;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author elderson
 */
public class BaseDAO {

    private Session sesion;
    private Transaction transaccion;

    @SuppressWarnings("deprecation")
    public void AbrirSesion(List<String> recursos) {
        try {
            // Se lee el archivo de configuración.
            Configuration cfg = new Configuration().configure(new File("/home/elderson/SGE_CONF/Configuracion.xml"));
            for (String recurso : recursos) {
                cfg.addResource(recurso);
            }
            sesion = cfg.buildSessionFactory().openSession();
        } catch (Throwable ex) {
            System.err.println("La creación de la sesión falló." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void CerrarSesion() {
        if (sesion != null && sesion.isOpen()) {
            sesion.close();
        }
    }

    public void IniciarTransaccion(List<String> recursos) {
        if (sesion == null) {
            AbrirSesion(recursos);
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

    public <T> T ObtenerLista(Class clase, List<Object[]> filtros) {
        Criteria criteria = sesion.createCriteria(clase);
        for (Object[] filtro : filtros) {
            criteria.add(Restrictions.eq(filtro[0].toString(), filtro[1]));
        }
        return (T)criteria.list();
    }
    
    public int Ejecutar(String sql) {
        return sesion.createSQLQuery(sql).executeUpdate();
    }

    public Session getSesion() {
        return sesion;
    }
    
    public void AsignarSesion(BaseDAO baseDAO) 
    {
        this.sesion = baseDAO.getSesion();
    }
}
