/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chrislancer.ecuacionesLineales.negocio.Exceptions;

/**
 *
 * @author chris
 */
public class GetIndexFilaInvalido extends Exception {

    /**
     * Creates a new instance of <code>GetIndexFilaInvalido</code> without
     * detail message.
     */
    public GetIndexFilaInvalido() {
    }

    /**
     * Constructs an instance of <code>GetIndexFilaInvalido</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public GetIndexFilaInvalido(String msg) {
        super(msg);
    }
}
