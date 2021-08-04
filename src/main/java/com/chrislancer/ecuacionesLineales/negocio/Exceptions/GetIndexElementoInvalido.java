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
public class GetIndexElementoInvalido extends Exception{

    /**
     * Creates a new instance of <code>ElmentoMatriz</code> without detail
     * message.
     */
    public GetIndexElementoInvalido() {
    }

    /**
     * Constructs an instance of <code>ElmentoMatriz</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public GetIndexElementoInvalido(String msg) {
        super(msg);
    }
}
