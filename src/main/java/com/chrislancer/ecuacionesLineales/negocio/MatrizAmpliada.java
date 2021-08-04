/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chrislancer.ecuacionesLineales.negocio;                                                                                                                                                                                                                                             

import com.chrislancer.ecuacionesLineales.negocio.Exceptions.GetIndexFilaInvalido;
import com.chrislancer.ecuacionesLineales.negocio.Exceptions.GetIndexElementoInvalido;
import com.chrislancer.ecuacionesLineales.negocio.Exceptions.ExceptionOrdenInvalido;
import java.util.Arrays;


/**
 *
 * @author chris
 */
public class MatrizAmpliada {
    
    private double[][] A; //matriz
    private double[] b;   //vector columna
    private final int numFilas;  //numero filas
    private final int numColA_b; //numero columnas matriz ampliada
    private final int numColA;  //numero columnas matriz del sistema
    
    private int rangoA;
    private int rangoA_b;
    
    public MatrizAmpliada(){
        this.numColA = 2;
        this.numFilas = 2;
        this.numColA_b = 3;
            
        this.A = new double[numFilas][numColA];   
        this.b = new double[numFilas];
    }
    

    public MatrizAmpliada(int numeroFilas, int numeroColumnas) throws ExceptionOrdenInvalido {
        if(numeroFilas > 1  && numeroColumnas > 1){
            this.numFilas = numeroFilas;
            this.numColA_b = numeroColumnas;
            this.numColA = numeroColumnas - 1;
            
            this.A = new double[numFilas][numColA];   
            this.b = new double[numFilas];
        }else{
            throw new ExceptionOrdenInvalido("orden invalido [" + numeroFilas + "" + numeroColumnas + "]" );
        }
    }

    public double[][] getMatrizA() {
        return A;
    }

    public double[] getVector_b(){
        return b;
    }
    
    public int getNumeroFilas() {
        return numFilas;
    }
    
    public int getRangoMatrizSistema(){
        return this.rangoA;
    }
    
    public int getRangoMatrizAmpliada(){
        return this.rangoA_b;
    }
    
    public int getNumeroColumnas(){
        return numColA_b;
    }
      
    public void setElemento(int i, int j, double elemento){
        if ( i >= 0 && i < this.numFilas) {            
            if(j >= 0 && j < this.numColA){                
             this.A[i][j] = elemento;   
             
            }else if(j == this.numColA_b - 1){
                this.b[i] = elemento;
            }            
        }              
    }
    
    public void setFila(double[] fila, int i){
        if (i >= 0 && i < this.numFilas){
            if(fila.length == this.numColA_b ){                
                this.A[i] = Arrays.copyOf(fila, fila.length - 1);  
                this.b[i] = fila[numColA_b-1];
            }         
        }        
    }
    
    public double getElemento(int i, int j) throws GetIndexElementoInvalido{
        if ( i >= 0 && i < this.numFilas){
            if(j >= 0 && j < this.numColA ){
                return this.A[i][j];
            }else if( j == this.numColA_b - 1){
                return this.b[i];
            }else{
                throw new GetIndexElementoInvalido("Error al obtener elemento en la posicion: [" + i + j + "]");
            }                    
        }else{
            throw new GetIndexElementoInvalido("Error al obtener elemento en la posicion: [" + i + j + "]");
        }
    }
    
    public double[] getFila(int i) throws GetIndexFilaInvalido {
        if (i >= 0 && i < this.numFilas){            
 
            double[] newFila = new double[this.numColA_b];
            
            System.arraycopy(A[i], 0, newFila, 0, this.numColA);
            newFila[this.numColA_b-1] = b[i];
            
         return newFila;   
        }else{
            throw new GetIndexFilaInvalido("Error al obtener fila de la posicion: " + i);
        }        
    }
    
    public void calcularRangos(){
        this.rangoA = this.numFilas;
        this.rangoA_b = this.numFilas;
        
        for(int i=0; i < this.numFilas; i ++){
            if(esFilaNula(A[i])){
                this.rangoA -=1;
                if(this.b[i] == 0.0){
                    this.rangoA_b -=1;
                }               
            }              
        }        
    }
    

    private boolean esFilaNula(double[] fila){        
        
        for(int j=0; j < fila.length; j++){
            if(fila[j] != 0.0){
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public String toString() {
        String matrizText = "";
        
        for( int i=0; i < A.length; i++ ){            
            matrizText += "[ ";
            for(double elem : A[i]){
                matrizText += elem + " ";
            }
            matrizText += this.b[i] + " ";
            
            matrizText += "] \n";
        }
        
        //matrizText += "]";
        return "Matriz{ \n" + matrizText + ", filas=" + numFilas + "\n columnas=" + numColA_b + "}";
    }
   
    
}
