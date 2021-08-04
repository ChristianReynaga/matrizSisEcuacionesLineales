/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chrislancer.ecuacionesLineales.negocio;

import com.chrislancer.ecuacionesLineales.negocio.Exceptions.GetIndexFilaInvalido;
import com.chrislancer.ecuacionesLineales.negocio.Exceptions.GetIndexElementoInvalido;

/**
 *
 * @author chris
 */
public class EliminacionGauss {
    
    private final MatrizAmpliada A;    

    public EliminacionGauss(MatrizAmpliada A) {
        this.A = A;
    }

    /**
     * 
     * @param posPivote el elemento pivote se mueve por la diagonal principal => pivote[posPivote][posPivote]
     * @throws GetIndexElementoInvalido 
     * @throws GetIndexFilaInvalido 
     */
    public void pivotacionColumna(int posPivote) throws GetIndexElementoInvalido, GetIndexFilaInvalido{
        //columna 0
        double mayor = A.getElemento(posPivote, posPivote);                        
        int indexFilaMayor = posPivote;
        
        //nos movemos por la columna del pivote
        for( int i = posPivote; i < A.getNumeroFilas(); i ++){           
            if( Math.abs(A.getElemento(i, posPivote)) > Math.abs(mayor) ){
                mayor = A.getElemento(i, posPivote);
                indexFilaMayor = i;                
            }
        }
        
        if(indexFilaMayor != posPivote){
            double[] filaTemp = A.getFila(posPivote);
            A.setFila((double[])A.getFila(indexFilaMayor), posPivote);
            A.setFila(filaTemp, indexFilaMayor);
        }
        
    }
    
    public void eliminacionGaussColumna(int posPivote) throws GetIndexElementoInvalido, GetIndexFilaInvalido {
        //anulamos los elementos de la columna a partir del pivote
        double valorPivote = A.getElemento(posPivote, posPivote );
        
        for(int i=posPivote + 1; i < A.getNumeroFilas(); i++){                        
            double valorActual = A.getElemento(i, posPivote); 
            double[] filaActualizada = operarFila( A.getFila(i), A.getFila(posPivote), valorActual, valorPivote);
            A.setFila( filaActualizada, i);
        }
    }
    
    private double[] operarFila(double[] filaActual, double[] filaPivote, double valorActual, double valorPivote){
        
        double[] nuevaFila = new double[filaActual.length];
        
        for(int j = 0; j < nuevaFila.length; j ++){
            nuevaFila[j] = filaActual[j] - filaPivote[j] * (valorActual / valorPivote);
            //nuevaFila[j] = Math.round(nuevaFila[j]*100.0)/100.0;
        }        
        return nuevaFila;
    }
    
}
