package com.formacion.juanjosecanotena.jjct_calculaimc;

import java.util.Comparator;

/**
 * Created by juanjosecanotena on 23/2/17.
 */

public class ComparaIMC implements Comparator<RegistroIMC> {

    @Override
    public int compare(RegistroIMC o1, RegistroIMC o2) {
        return (o1.getImc() > o2.getImc())? 1 : -1 ;
    }
}
