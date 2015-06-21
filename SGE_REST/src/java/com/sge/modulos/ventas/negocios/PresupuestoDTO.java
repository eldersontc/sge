package com.sge.modulos.ventas.negocios;

import com.sge.base.negocios.BaseDTO;
import com.sge.modulos.administracion.accesoDatos.NumeracionDAO;
import com.sge.modulos.ventas.accesoDatos.CotizacionDAO;
import com.sge.modulos.ventas.accesoDatos.ItemPresupuestoDAO;
import com.sge.modulos.ventas.accesoDatos.PresupuestoDAO;
import com.sge.modulos.ventas.entidades.ItemPresupuesto;
import com.sge.modulos.ventas.entidades.Presupuesto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class PresupuestoDTO extends BaseDTO {

    PresupuestoDAO presupuestoDAO;
    ItemPresupuestoDAO itemPresupuestoDAO;
    CotizacionDAO cotizacionDAO;
    NumeracionDAO numeracionDAO;

    public PresupuestoDTO(int idUsuario) {
        super(idUsuario);
    }

    public List<Presupuesto> ObtenerPresupuestos(String filtro) {
        List<Presupuesto> lista;
        try {
            presupuestoDAO = new PresupuestoDAO();
            presupuestoDAO.AbrirSesion();
            lista = presupuestoDAO.ObtenerPresupuestos(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            presupuestoDAO.CerrarSesion();
        }
        return lista;
    }

    public Presupuesto ObtenerPresupuesto(int idPresupuesto) {
        Presupuesto presupuesto = null;
        try {
            presupuestoDAO = new PresupuestoDAO();
            presupuestoDAO.AbrirSesion();
            presupuesto = presupuestoDAO.ObtenerPorId(Presupuesto.class, idPresupuesto);

            itemPresupuestoDAO = new ItemPresupuestoDAO();
            itemPresupuestoDAO.AsignarSesion(presupuestoDAO);

            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idPresupuesto", idPresupuesto});
            List<ItemPresupuesto> items = itemPresupuestoDAO.ObtenerLista(ItemPresupuesto.class, filtros);

            presupuesto.setItems(items);
        } catch (Exception e) {
            throw e;
        } finally {
            presupuestoDAO.CerrarSesion();
        }
        return presupuesto;
    }

    public boolean RegistrarPresupuesto(Presupuesto presupuesto) {
        try {
            presupuestoDAO = new PresupuestoDAO();
            presupuestoDAO.IniciarTransaccion();

            numeracionDAO = new NumeracionDAO();
            numeracionDAO.AsignarSesion(presupuestoDAO);

            if (!presupuesto.isNumeracionManual()) {
                presupuesto.setNumero(numeracionDAO.GenerarNumeracion(presupuesto.getIdNumeracion()));
            }

            presupuesto.setEstado("PENDIENTE DE APROBACIÓN");

            presupuestoDAO.Agregar(presupuesto);

            itemPresupuestoDAO = new ItemPresupuestoDAO();
            itemPresupuestoDAO.AsignarSesion(presupuestoDAO);

            cotizacionDAO = new CotizacionDAO();
            cotizacionDAO.AsignarSesion(presupuestoDAO);

            for (ItemPresupuesto item : presupuesto.getItems()) {
                item.setIdPresupuesto(presupuesto.getIdPresupuesto());
                itemPresupuestoDAO.Agregar(item);
                cotizacionDAO.ActualizarIdYNumeroPresupuesto(item.getIdCotizacion(), presupuesto.getIdPresupuesto(), presupuesto.getNumero());
                cotizacionDAO.ActualizarEstadoCotizacion(item.getIdCotizacion(), "EN PRESUPUESTO");
            }

            presupuestoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            presupuestoDAO.AbortarTransaccion();
            throw e;
        } finally {
            presupuestoDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarPresupuesto(Presupuesto presupuesto) {
        try {
            presupuestoDAO = new PresupuestoDAO();
            presupuestoDAO.IniciarTransaccion();
            presupuestoDAO.ActualizarPresupuesto(presupuesto.getIdPresupuesto(), presupuesto.getIdCliente(), presupuesto.getRazonSocialCliente(), presupuesto.getIdMoneda(), presupuesto.getSimboloMoneda(), presupuesto.getNumeroOrdenCompra(), presupuesto.getInstrucciones(), presupuesto.getTotal());

            itemPresupuestoDAO = new ItemPresupuestoDAO();
            itemPresupuestoDAO.AsignarSesion(presupuestoDAO);

            cotizacionDAO = new CotizacionDAO();
            cotizacionDAO.AsignarSesion(presupuestoDAO);

            for (ItemPresupuesto item : presupuesto.getItems()) {
                if (item.isAgregar()) {
                    item.setIdPresupuesto(presupuesto.getIdPresupuesto());
                    itemPresupuestoDAO.Agregar(item);
                    cotizacionDAO.ActualizarIdYNumeroPresupuesto(item.getIdCotizacion(), presupuesto.getIdPresupuesto(), presupuesto.getNumero());
                    cotizacionDAO.ActualizarEstadoCotizacion(item.getIdCotizacion(), "EN PRESUPUESTO");
                }
                if (item.isActualizar()) {
                    itemPresupuestoDAO.ActualizarItemPresupuesto(item.getIdItemPresupuesto(), item.getRecargo(), item.getTotal());
                }
                if (item.isEliminar()) {
                    cotizacionDAO.ActualizarIdYNumeroPresupuesto(item.getIdCotizacion(), 0, "");
                    cotizacionDAO.ActualizarEstadoCotizacion(item.getIdCotizacion(), "APROBADO");
                    itemPresupuestoDAO.EliminarItemPresupuesto(item.getIdItemPresupuesto());
                }
            }

            presupuestoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            presupuestoDAO.AbortarTransaccion();
            throw e;
        } finally {
            presupuestoDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarPresupuesto(int idPresupuesto) {
        try {
            presupuestoDAO = new PresupuestoDAO();
            presupuestoDAO.IniciarTransaccion();
            presupuestoDAO.EliminarPresupuesto(idPresupuesto);

            itemPresupuestoDAO = new ItemPresupuestoDAO();
            itemPresupuestoDAO.AsignarSesion(presupuestoDAO);

            cotizacionDAO = new CotizacionDAO();
            cotizacionDAO.AsignarSesion(presupuestoDAO);

            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idPresupuesto", idPresupuesto});
            List<ItemPresupuesto> items = itemPresupuestoDAO.ObtenerLista(ItemPresupuesto.class, filtros);

            for (ItemPresupuesto item : items) {
                cotizacionDAO.ActualizarIdYNumeroPresupuesto(item.getIdCotizacion(), 0, "");
                cotizacionDAO.ActualizarEstadoCotizacion(item.getIdCotizacion(), "APROBADO");
                itemPresupuestoDAO.EliminarItemPresupuesto(item.getIdItemPresupuesto());
            }

            presupuestoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            presupuestoDAO.AbortarTransaccion();
            throw e;
        } finally {
            presupuestoDAO.CerrarSesion();
        }
        return true;
    }

    public boolean CambiarEstadoPresupuesto(int idPresupuesto, int idEstado) throws Exception {
        try {
            presupuestoDAO = new PresupuestoDAO();
            presupuestoDAO.IniciarTransaccion();

            Presupuesto presupuesto = presupuestoDAO.ObtenerPorId(Presupuesto.class, idPresupuesto);

            String estado = "";

            switch (idEstado) {
                case 1:
                    if (presupuesto.getEstado().equals("PENDIENTE DE APROBACIÓN") || presupuesto.getEstado().equals("RECHAZADO")) {
                        estado = "APROBADO";
                    } else {
                        throw new Exception("SÓLO SE PUEDE APROBAR CUANDO ESTÁ EN ESTADO : PENDIENTE DE APROBACIÓN Ó RECHAZADO.");
                    }
                    break;
                case 2:
                    if (presupuesto.getEstado().equals("APROBADO")) {
                        estado = "PENDIENTE DE APROBACIÓN";
                    } else {
                        throw new Exception("SÓLO SE PUEDE DESAPROBAR CUANDO ESTÁ EN ESTADO : APROBADO.");
                    }
                    break;
                case 3:
                    if (presupuesto.getEstado().equals("APROBADO")) {
                        estado = "ENVIADO AL CLIENTE";
                    } else {
                        throw new Exception("SÓLO SE PUEDE ENVIAR CUANDO ESTÁ EN ESTADO : APROBADO.");
                    }
                    break;
                case 4:
                    if (presupuesto.getEstado().equals("ENVIADO AL CLIENTE")) {
                        estado = "ACEPTADO";
                    } else {
                        throw new Exception("SÓLO SE PUEDE ACEPTAR CUANDO ESTÁ EN ESTADO : ENVIADO AL CLIENTE.");
                    }
                    break;
                case 5:
                    if (presupuesto.getEstado().equals("ENVIADO AL CLIENTE")) {
                        estado = "RECHAZADO";
                    } else {
                        throw new Exception("SÓLO SE PUEDE RECHAZAR CUANDO ESTÁ EN ESTADO : ENVIADO AL CLIENTE.");
                    }
                    break;
            }

            presupuestoDAO.ActualizarEstadoPresupuesto(idPresupuesto, estado);

            presupuestoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            presupuestoDAO.AbortarTransaccion();
            throw e;
        } finally {
            presupuestoDAO.CerrarSesion();
        }
        return true;
    }

    public boolean AprobarPresupuesto(int idPresupuesto) {
        try {
            presupuestoDAO = new PresupuestoDAO();
            presupuestoDAO.IniciarTransaccion();
            presupuestoDAO.ActualizarEstadoPresupuesto(idPresupuesto, "APROBADO");
            presupuestoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            presupuestoDAO.AbortarTransaccion();
            throw e;
        } finally {
            presupuestoDAO.CerrarSesion();
        }
        return true;
    }

    public boolean DesaprobarPresupuesto(int idPresupuesto) {
        try {
            presupuestoDAO = new PresupuestoDAO();
            presupuestoDAO.IniciarTransaccion();
            presupuestoDAO.ActualizarEstadoPresupuesto(idPresupuesto, "PENDIENTE DE APROBACIÓN");
            presupuestoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            presupuestoDAO.AbortarTransaccion();
            throw e;
        } finally {
            presupuestoDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EnviarPresupuesto(int idPresupuesto) {
        try {
            presupuestoDAO = new PresupuestoDAO();
            presupuestoDAO.IniciarTransaccion();
            presupuestoDAO.ActualizarEstadoPresupuesto(idPresupuesto, "ENVIADO AL CLIENTE");
            presupuestoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            presupuestoDAO.AbortarTransaccion();
            throw e;
        } finally {
            presupuestoDAO.CerrarSesion();
        }
        return true;
    }

    public boolean AceptarPresupuesto(int idPresupuesto) {
        try {
            presupuestoDAO = new PresupuestoDAO();
            presupuestoDAO.IniciarTransaccion();
            presupuestoDAO.ActualizarEstadoPresupuesto(idPresupuesto, "ACEPTADO");
            presupuestoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            presupuestoDAO.AbortarTransaccion();
            throw e;
        } finally {
            presupuestoDAO.CerrarSesion();
        }
        return true;
    }

    public boolean RechazarPresupuesto(int idPresupuesto) {
        try {
            presupuestoDAO = new PresupuestoDAO();
            presupuestoDAO.IniciarTransaccion();
            presupuestoDAO.ActualizarEstadoPresupuesto(idPresupuesto, "RECHAZADO");
            presupuestoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            presupuestoDAO.AbortarTransaccion();
            throw e;
        } finally {
            presupuestoDAO.CerrarSesion();
        }
        return true;
    }
}
