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
    static String portaavions= "P";
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
        
        /*String tauler[][]=new String[10][10];
        tauler= inserir_vaixells(tauler,10,0,0,0);
        mostrar_tauler(tauler);*/
        
      
        
       /* 
        System.out.println("A quina fila vols disparar");
        int fila=sc.nextInt();
        System.out.println("A quina columna vols disparar");
        int columna=sc.nextInt();
        
        */
        
    }
    
    public static void crear_tauler(String tauler[][]){
     
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
                else if( i>0 && j==0){
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
    
    public static String[][] inserir_vaixells(String tauler[][], int llanxes, int vaixells, int cuirassat, int portaavions){
        int introducidas=0;
        int ejeX,ejeY;
       
        while(introducidas<llanxes){
            ejeX=(int) (Math.random()*(tauler.length));
            ejeY=(int) (Math.random()*(tauler[0].length));
                if(tauler[ejeX][ejeY]!=llanxa){
                      tauler[ejeX][ejeY] = llanxa;
                      introducidas++;
                }
        }
        return tauler;   
    }
    
    
    public static void triar_dificultat(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Nivell facil: 1");
        System.out.println("Nivell mitjà: 2");
        System.out.println("Nivell difícil: 3");
        System.out.println("Nivell personalitzat: 4");
        
        int dificultat=sc.nextInt();
        String tauler[][]=new String[10][10];
        switch (dificultat){
            case 1:
                tauler= inserir_vaixells(tauler,10,3,1,1);
                començar_partida(10,10, tauler);
                break;
            case 2:
                inserir_vaixells(tauler,2,1,1,1);
                //començar_partida(30);
                break;
            case 3:
                inserir_vaixells(tauler,1,1,0,0);
                //1començar_partida(10);
                break;
            case 4:
                nivell_personalitzat();
                break;
        }
    }
    
    public static void començar_partida(int maxintentos, int total_vaixells, String taulerple[][]){
        Scanner sc=new Scanner(System.in);  
        int intentos=0;
        int afonats=0;
        String jugades[][]= new String[taulerple.length][taulerple[0].length];
        mostrar_tauler(jugades);
        while(intentos<=maxintentos || afonats==total_vaixells){
            System.out.println("A quina fila vols disparar:");
            String fila=sc.next();
            System.out.println("A quina columna vols disparar:");
            int columna=sc.nextInt();
            
            int numfila=convertir_lletra_a_numero(fila.toUpperCase());
            
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
        if(afonats==total_vaixells){
            System.out.println("Has guanyat!");
        }else{
            System.out.println("Has perdut!");
        }
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
