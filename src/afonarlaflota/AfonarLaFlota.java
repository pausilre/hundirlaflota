/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afonarlaflota;

import java.util.Scanner;

/**
 *
 * @author paula
 */
public class AfonarLaFlota {
    static String llanxa = "L";
    static String vaixell= "B";
    static String cuirassat= "Z";
    static String portaavio= "P";
    static String aigua= "A";
    static String buit= "-";
    static String tocat_afonat= "X";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("En quin nivell de dificultat vols jugar?");
        triar_dificultat();
    }
    
    public static void mostrar_tauler(String tauler[][]){
        int extraX = tauler.length +1;
        int extraY= tauler[0].length +1;
        char initchar = 'A';
        String taulernew[][] = new String[extraX][extraY];
        for(int i=0; i<taulernew.length; i++){
            for(int j=0; j<taulernew[0].length; j++){
                if(i==0 && j==0){
                    System.out.print("  ");
                }
                else if(i==0){
                    System.out.print(j-1 + " ");
                }
                else if(i>0 && j==0){
                    System.out.print(initchar + " ");
                    initchar++;
                }
                else{
                    if(tauler[i-1][j-1]!=null){
                        System.out.print(tauler[i-1][j-1] + " ");
                    }else{
                        System.out.print("- ");
                    }
                }
            }
            System.out.println();
        }
    }
    public static int convertir_lletra_a_numero(String lletra){
        String abecedari = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return abecedari.indexOf(lletra);
    }
    
    public static String[][] inserir_vaixells(String tauler[][], int llanxes, int vaixells, int cuirassats, int portaavions){
        tauler=inserir_tipus_vaixells(tauler, 5, portaavio, portaavions, false);
        tauler=inserir_tipus_vaixells(tauler, 4, cuirassat, cuirassats, true);
        tauler=inserir_tipus_vaixells(tauler, 3, vaixell, vaixells, true);
        tauler=inserir_tipus_vaixells(tauler, 1, llanxa, llanxes, true);
        
        return tauler;   
    }
    
    public static String[][] inserir_tipus_vaixells(String tauler[][], int longitud, String valor_vaixell, int total_vaixells, boolean es_horizontal ){
        int introducidas=0;
        int ejeX,ejeY;
        
        while(introducidas<total_vaixells){
            ejeX=(int) (Math.random()*(tauler.length));
            ejeY=(int) (Math.random()*(tauler[0].length));
            if(longitud>1){
                if(es_horizontal){
                    while(ejeY>=tauler[0].length-(longitud-1)){
                        ejeY=(int) (Math.random()*(tauler[0].length));
                    }
                }else{
                    while(ejeX>=tauler.length-(longitud-1)){
                        ejeX=(int) (Math.random()*(tauler.length));
                    }
                }
            }
            if(puc_inserir(tauler,longitud, ejeX, ejeY,es_horizontal)){
                emplenar_valor(tauler, longitud, ejeX, ejeY, es_horizontal, valor_vaixell);
                introducidas++; 
            }
        }
        return tauler;
    }

    public static String[][] emplenar_valor (String tauler[][], int longitud, int ejeX, int ejeY, boolean es_horizontal, String valor_vaixell){
        for(int i=0; i<longitud; i++){
            if(es_horizontal){
                tauler[ejeX][ejeY+i]=valor_vaixell;
            }else{
                tauler[ejeX+i][ejeY]=valor_vaixell;
            }
        }
        return tauler;
    }
    
    public static boolean puc_inserir (String tauler[][], int longitud, int ejeX, int ejeY, boolean es_horizontal){
        boolean puc=true;
        int cont=0;
        while(cont<longitud && puc){
            if(es_horizontal){
                puc=tauler[ejeX][ejeY+cont]== null;
            }else{
                puc=tauler[ejeX+cont][ejeY]== null;       
            }
            cont++;
        }
        return puc;
    }

    public static void triar_dificultat(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Nivell facil: 1");
        System.out.println("Nivell mitjà: 2");
        System.out.println("Nivell difícil: 3");
        System.out.println("Nivell personalitzat: 4");
        int totalafonats;
      
        int dificultat=sc.nextInt();
          while(dificultat<1 || dificultat>4){
              System.out.println("Elige entre el 1 y el 4");
              dificultat=sc.nextInt();
          }
        String tauler[][]=new String[10][10];
        switch (dificultat){
            case 1:
                tauler= inserir_vaixells(tauler,5,3,1,1);
                totalafonats=total_afonats(5,3,1,1);
                començar_partida(10,10,tauler,totalafonats);
                break;
            case 2:
                tauler= inserir_vaixells(tauler,2,1,1,1);
                totalafonats=total_afonats(2,1,1,1);
                començar_partida(30,5,tauler,totalafonats);
                break;
            case 3:
                tauler= inserir_vaixells(tauler,1,1,0,0);
                totalafonats=total_afonats(1,1,0,0);
                començar_partida(10,2, tauler,totalafonats);
                break;
            case 4:
                nivell_personalitzat();
                break;
        }
    }
    
    public static void començar_partida(int maxintentos, int total_vaixells, String taulerple[][], int totalafonats){
        Scanner sc=new Scanner(System.in);  
        int intentos=0;
        int afonats=0;
        String jugades[][]= new String[taulerple.length][taulerple[0].length];
        mostrar_tauler(jugades);
        while(intentos<maxintentos || afonats==totalafonats){
            System.out.println("Este es tu intento numero "+ (intentos+1));
            System.out.println("A quina fila vols disparar:");
            String fila=sc.next();
            int numfila=convertir_lletra_a_numero(fila.toUpperCase());
            
            while(numfila<0 || numfila>taulerple.length-1){
                System.out.println("Error, tria una fila vàlida");
                System.out.println("A quina fila vols disparar:");
                fila=sc.next();
                numfila=convertir_lletra_a_numero(fila.toUpperCase());
            }
            
            System.out.println("A quina columna vols disparar:");
            int columna=sc.nextInt();
            
            while(columna<0 || columna>taulerple[0].length-1){
                System.out.println("Error, tria una columna vàlida");
                System.out.println("A quina columna vols disparar:");
                columna=sc.nextInt();
            }
            
            if(taulerple[numfila][columna]!=null){
                jugades[numfila][columna]= tocat_afonat;
                afonats++;
                System.out.println("Tocat!");
            }else{
                jugades[numfila][columna]= aigua;
                System.out.println("Aigua!");
            }
            intentos++;
            mostrar_tauler(jugades);
            
        };
        mostrar_tauler(taulerple);
        if(afonats==totalafonats){
            System.out.println("Has guanyat!");
        }else{
            System.out.println("Has perdut!");
        }
    }
    
    public static int total_afonats(int llanxes, int vaixells, int cuirassats, int portaavions){
        return (llanxes*1) + (vaixells*3) + (cuirassats*4) + (portaavions*5);
    }

      
    public static void nivell_personalitzat(){
            Scanner sc= new Scanner (System.in);
            System.out.println("Cuantes files vols tindre?");
            int triafila=sc.nextInt();
            System.out.println("Cuantes columnes vols tindre?");
            int triacolumna=sc.nextInt();
            System.out.println("Cuants intents vols tindre?");
            int triaintents=sc.nextInt();
           
           
            int intentos=0;
        
            while(intentos<=triaintents){

            };
    }
    
 
}
