/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chrislancer.ecuacionesLineales.negocio;

import com.chrislancer.ecuacionesLineales.negocio.Exceptions.ExceptionOrdenInvalido;
import com.chrislancer.ecuacionesLineales.negocio.Exceptions.GetIndexElementoInvalido;
import com.chrislancer.ecuacionesLineales.negocio.Exceptions.GetIndexFilaInvalido;

/**
 *
 * @author chris
 */
public class SistemaMatricialEcuacionesLineales {

    private final MatrizAmpliada matrizAmpliada;
    private final int numeroIncognitas;
    private final int numeroEcuaciones;

    public SistemaMatricialEcuacionesLineales() {
        this.numeroEcuaciones = 2;
        this.numeroIncognitas = 2;
        this.matrizAmpliada = new MatrizAmpliada();

    }

    public SistemaMatricialEcuacionesLineales(int numIncognitas, int numEcuaciones) throws ExceptionOrdenInvalido {        
       
        this.numeroIncognitas = numIncognitas;
        this.numeroEcuaciones = numEcuaciones;
            
        this.matrizAmpliada = new MatrizAmpliada(numEcuaciones, numIncognitas + 1);      
    }
    
    public void cargarMatriz(double[] valores) {

        if (valores.length == (this.numeroEcuaciones * (this.numeroIncognitas + 1))) {
            double[] fila = new double[numeroIncognitas + 1];

            int i = 0;
            int j = -1;
            for (int k = 0; k < valores.length; k++) {
                j +=1;
                fila[j] = valores[k];   
                
                if (j == numeroIncognitas) {
                    
                    this.matrizAmpliada.setFila(fila, i);
                    i+=1;                    
                    j=-1;
                    fila = new double[numeroIncognitas + 1];
                }
            }

            System.out.println(this.matrizAmpliada);
        }

    }

    public void escalonarMatriz(){
        EliminacionGauss gauss = new EliminacionGauss(this.matrizAmpliada);
        
        for(int i=0; i<this.numeroIncognitas - 1; i++){
            try{
                gauss.pivotacionColumna(i);
                gauss.eliminacionGaussColumna(i);
            
            }catch(GetIndexElementoInvalido | GetIndexFilaInvalido ex){
                System.out.println(ex.getMessage());
            }
        }
        //System.out.println(this.matrizAmpliada);
    }
    
    
    public byte comprobarSoluciones(){
        //TEOREMA DE ROUCHE-FROBENIUS
        this.matrizAmpliada.calcularRangos();
        
        final int RMs = this.matrizAmpliada.getRangoMatrizSistema();
        //System.out.println("Rango matriz del sistema: " + RMs);
        final int RMa = this.matrizAmpliada.getRangoMatrizAmpliada();
        //System.out.println("Rango matriz ampliada: " + RMa);
        
        if( RMs < RMa ){
            return -1;
        }else if( (RMs == RMa) && (RMa == this.numeroEcuaciones) ){
            return 1;
        }else{
            return 0;
        }
        
    }
    
    public double[] resolver2x2(){
        //APLICAR CRAMER
        
        final double[] solucion = {0,0};
        
        if(this.numeroIncognitas == 2 && this.numeroEcuaciones == 2){
            // [ a b c ]
            // [ d e f ]
            //determinante = a*e - b*d
            final double[][] A = this.matrizAmpliada.getMatrizA();
            final double[] b = this.matrizAmpliada.getVector_b();
                    
            double determinante = A[0][0] * A[1][1] - A[0][1] * A[1][0];
            
            if(determinante != 0){
                //x = (c*d - b*f) / determinante
                solucion[0] = ( b[0] * A[1][1] - A[0][1] * b[1]) / determinante;    
                //y = (a*f - c*d) / determinante
                solucion[1] = ( A[0][0] * b[1] - b[0]* A[1][0]) / determinante;
            }
        }
        return solucion;
    }
}
