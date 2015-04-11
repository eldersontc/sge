package com.sge.base.constantes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class Constantes {
    
    public static final String ENT_INV = "ENTRADA DE INVENTARIO";
    public static final String SAL_INV = "SALIDA DE INVENTARIO";
    
    public static List<String> getEntidades(){
        List<String> entidades = new ArrayList<>();
        entidades.add(ENT_INV);
        entidades.add(SAL_INV);
        return entidades;
    }
}
