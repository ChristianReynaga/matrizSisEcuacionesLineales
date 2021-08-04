package com.chrislancer.ecuacionesLineales.presentacion;

import com.chrislancer.ecuacionesLineales.negocio.SistemaMatricialEcuacionesLineales;

import java.io.*;
import java.util.Arrays;

public class Principal {

    final static String path = "\\src\\main\\java\\com\\chrislancer\\ecuacionesLineales\\datos\\";

    final static File fileEcuacionesIncompatibles = new File ( System.getProperties().getProperty("user.dir") + path + "incompatibles.txt");
    final static File fileEcuacionesIndeterminadas =new File ( System.getProperties().getProperty("user.dir") + path + "indeterminados.txt");
    final static File fileEcuacionesCompDeterminadas = new File ( System.getProperties().getProperty("user.dir") + path + "compatiblesDeterminados.txt");

    public static void escribirEnArchivo(File ruta, String linea){
        FileWriter fichero = null;
        try {
            fichero = new FileWriter(ruta, true);
            PrintWriter writer = new PrintWriter(fichero);
            writer.println(linea);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void resolver(double[] valores, String linea){
        SistemaMatricialEcuacionesLineales M = new SistemaMatricialEcuacionesLineales();
        M.cargarMatriz(valores);
        M.escalonarMatriz();
        byte opciones = M.comprobarSoluciones();

        switch(opciones){
            case -1 -> {
                System.out.println("Sistema incompatible. No tiene solucion");
                escribirEnArchivo(fileEcuacionesIncompatibles, linea);
            }
            case 0 -> {
                System.out.println("Sistema compatible indeterminado. Soluciones infinitas");
                escribirEnArchivo(fileEcuacionesIndeterminadas, linea);
            }
            case 1 -> {
                System.out.println("Sistema compatible determinado. Tiene solucion unica");
                double[] solucion = M.resolver2x2();
                System.out.println("Solucion: [ x=" + solucion[0] + ", y=" + solucion[1] + " ]");
                escribirEnArchivo(fileEcuacionesCompDeterminadas,linea);
            }
        }
    }

    public static void  main(String[] args){

        File fileEcuaciones2x2 = new File ( System.getProperties().getProperty("user.dir") + path + "ecuaciones2x2.txt");

        FileReader fReader = null;
        try {
            fReader = new FileReader(fileEcuaciones2x2);
            BufferedReader buffer = new BufferedReader(fReader);

            String linea;
            while( (linea = buffer.readLine() ) !=null ){
                String[] numberString = linea.split(" ");

                //Ecuaciones de 2 incognitas X 2 variables = 4, +2 de los num. independ.
                double[] valores = new double[6];
                for(int k=0; k<valores.length; k++){
                    valores[k] = Double.parseDouble(numberString[k]);
                }
                resolver(valores, linea);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if( fReader != null ){
                    fReader.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }
}
