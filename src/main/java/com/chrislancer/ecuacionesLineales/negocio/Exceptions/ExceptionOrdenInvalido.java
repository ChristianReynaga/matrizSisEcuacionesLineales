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
public class ExceptionOrdenInvalido extends Exception {

    /**
     * Creates a new instance of <code>ExceptionOrdenInvalido</code> without
     * detail message.
     */
    public ExceptionOrdenInvalido() {
    }

    /**
     * Constructs an instance of <code>ExceptionOrdenInvalido</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExceptionOrdenInvalido(String msg) {
        super(msg);
    }
}
