package afonarlaflota;

import java.util.Scanner;
/**
 *
 * @author paula
 */
public class AfonarLaFlota {
    /**
     * @param args the command line arguments
     */
    
    //Funció main on sols mostrarà la benvinguda al joc i la clau inicial per a que el jugador trie el nivell.
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Benvingut al joc d'afonar la flota! \n");
        System.out.println("En quin nivell de dificultat vols jugar? \n");
        triar_dificultat();
    }
    
    //Funció que mostra un tauler amb les coordenades per a que l'usuari tinga clar les posicions que n'hi han.
    public static void mostrar_tauler(String tauler[][]){
        String buit= "-";
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
                        System.out.print(buit + " ");
                    }
                    if(j>9){
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }
    
    //Funció que rep una lletra i la pasa a nombre.
    public static int convertir_lletra_a_nombre(String lletra){
        String abecedari = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return abecedari.indexOf(lletra);
    }
    
    //Funció que emplena el tauler amb cada tipus de vaixell, segons la cantitat que volguem de cadascú.
    public static String[][] inserir_vaixells(String tauler[][], int llanxes, int vaixells, int cuirassats, int portaavions){
        
        String llanxa = "L";
        String vaixell= "B";
        String cuirassat= "Z";
        String portaavio= "P";
        
        tauler=inserir_tipus_vaixells(tauler, 5, portaavio, portaavions, "vertical");
        tauler=inserir_tipus_vaixells(tauler, 4, cuirassat, cuirassats, "horizontal");
        tauler=inserir_tipus_vaixells(tauler, 3, vaixell, vaixells, "horizontal");
        tauler=inserir_tipus_vaixells(tauler, 1, llanxa, llanxes, "horizontal");
        
        return tauler;   
    }
    
    
    //Funció que emplena el tauler de viaxells en posicions randoms tenint en compte la quantitat de vaixells, la seua longitud i el seu eje.
    public static String[][] inserir_tipus_vaixells(String tauler[][], int longitud, String valor_vaixell, int total_vaixells, String orientacio ){
        int introducidas=0;
        int ejeX,ejeY;
        
        while(introducidas<total_vaixells){
            ejeX=(int) (Math.random()*(tauler.length));
            ejeY=(int) (Math.random()*(tauler[0].length));
            if(longitud>1){
                if(orientacio=="horizontal"){
                    while(ejeY>=tauler[0].length-(longitud-1)){
                        ejeY=(int) (Math.random()*(tauler[0].length));
                    }
                }else{
                    while(ejeX>=tauler.length-(longitud-1)){
                        ejeX=(int) (Math.random()*(tauler.length));
                    }
                }
            }
            if(puc_inserir(tauler,longitud, ejeX, ejeY,orientacio)){
                emplenar_valor(tauler, longitud, ejeX, ejeY, orientacio, valor_vaixell);
                introducidas++; 
            }
        }
        return tauler;
    }

    //Funció que donant-li un tauler buit, ens retorna un tauler ple amb el valor String del vaixell.
    public static String[][] emplenar_valor (String tauler[][], int longitud, int ejeX, int ejeY, String orientacio, String valor_vaixell){
        for(int i=0; i<longitud; i++){
            if(orientacio=="horizontal"){
                tauler[ejeX][ejeY+i]=valor_vaixell;
            }else{
                tauler[ejeX+i][ejeY]=valor_vaixell;
            }
        }
        return tauler;
    }
    
    //Funció que comprova si es pot inserir un vaixell segons la seua grandària comprobant el eje afectat segons siga horizontal o vertical.
    public static boolean puc_inserir (String tauler[][], int longitud, int ejeX, int ejeY, String orientacio){
        boolean puc=true;
        int cont=0;
        while(cont<longitud && puc){
            if(orientacio=="horizontal"){
                puc=tauler[ejeX][ejeY+cont]== null;
            }else{
                puc=tauler[ejeX+cont][ejeY]== null;       
            }
            cont++;
        }
        return puc;
    }

    //Funció per a mostrar per pantalla el misatge indicant el nivell triat.
    public static void missatge_dificultat(int dificultat){
         switch(dificultat){
            case 1:
                System.out.print("Has triat el nivell fàcil");
                break;
            case 2:
                System.out.print("Has triat el nivell mitjà");
                break;
            case 3:
                System.out.print("Has triat el nivell difícil");
                break;
            case 4:
                System.out.print("Has triat el nivell personalitzat");
                break;
        }
         System.out.println(", que començe el joc!");
         System.out.println();
    }
    
    
    //Funció per a triar la dificultat, li donen un tauler buit i segons la dificultat, utilitzant altres funcions l'emplenem amb uns valors o altres.
    public static void triar_dificultat(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Nivell facil: 1");
        System.out.println("Nivell mitjà: 2");
        System.out.println("Nivell difícil: 3");
        System.out.println("Nivell personalitzat: 4");
        System.out.println();
        int totalafonats;
      
        int dificultat=sc.nextInt();
          while(dificultat<1 || dificultat>4){
              System.out.println("Elige entre el 1 y el 4");
              dificultat=sc.nextInt();
          }
          
        missatge_dificultat(dificultat);
        
        
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
    
    //Funció on trobem la interacció amb l'usuari, demana files i columnes vàlides, enmagatzema la posició de dispar i mostra el resultat en funció de si ha tocat o no un vaixell, a més del nombre de l'intent.
    public static void començar_partida(int maxintentos, int total_vaixells, String taulerple[][], int totalafonats){
        Scanner sc=new Scanner(System.in);  
        int intentos=0;
        int afonats=0;
        String aigua= "A";
        String tocat_afonat= "X";
        String jugades[][]= new String[taulerple.length][taulerple[0].length];
        mostrar_tauler(jugades);
        while(intentos<maxintentos || afonats==totalafonats){
            System.out.println("Este es tu intento numero "+ (intentos+1));
            System.out.println("A quina fila vols disparar:");
            String fila=sc.next();
            int numfila=convertir_lletra_a_nombre(fila.toUpperCase());
            
            while(numfila<0 || numfila>taulerple.length-1){
                System.out.println("Error, tria una fila vàlida");
                System.out.println("A quina fila vols disparar:");
                fila=sc.next();
                numfila=convertir_lletra_a_nombre(fila.toUpperCase());
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
    
    //Funció per a saber el mínim d'intents necessaris per afonar el conjunt de vaixells.
    public static int total_afonats(int llanxes, int vaixells, int cuirassats, int portaavions){
        return (llanxes*1) + (vaixells*3) + (cuirassats*4) + (portaavions*5);
    }

    //Funció que comprova que els intents que tria l'usuari siguen suficients per a afonar tots els vaixells.
    public static int intents_necesaris(int triaintents, int llanxes, int vaixells, int cuirassats, int portaavions){
        Scanner sc= new Scanner (System.in);
        while(triaintents<total_afonats(llanxes,vaixells,cuirassats,portaavions)){
            System.out.println("No es posible que guanyes de cap forma, puja el nombre de intents");
            System.out.println("Cuants intents vols tindre?");
            triaintents=sc.nextInt();
        }
        return triaintents;
    }
     
    //Funció que comprova un mínim d'amplada per a poder afegir el cuirassat quan ja n'hi ha un portaavions.
    public static int amplada_correcta(int portaavions,int cuirassats, int nombrecolumna){
        Scanner sc= new Scanner (System.in);
        while(portaavions>0 && cuirassats>0 && nombrecolumna<9){
            System.out.println("Es necessari un tauler més gran per a que càpiguen tots els vaixells (mínim 9 columnes)");
            System.out.println("Cuantes columnes vols tindre?");
            nombrecolumna=sc.nextInt();
        }
        return nombrecolumna;
    }
    
    //Funció que retorna el total de caselles del tauler.
    public static int total_tauler(int ejeX, int ejeY){
        return ejeX*ejeY;
    }
    
    //Funció que retorna un tauler correcte, es a dir, que no hi hagen més posicions ocupades per vaixells, que posicions existents al tauler.
    public static String[][] tauler_correcte(int totalafonats, int totaltauler, int nombrefila,int nombrecolumna,int portaavions, int cuirassats ){
        Scanner sc= new Scanner (System.in);
        
        while(totalafonats>totaltauler){
            System.out.println("En aquest tauler no caben tots els vaixells");
            System.out.println("Cuantes files vols tindre?");
            nombrefila=sc.nextInt();
            System.out.println("Cuantes columnes vols tindre?");
            nombrecolumna=sc.nextInt();
        }
        /*
        while(nombrefila>26){
            System.out.println("El tauler es molt gran, deus inserir un nombre de files menor a 27");
            System.out.println("Cuantes files vols tindre?");
            nombrefila=sc.nextInt();
        }*/
        while(nombrefila<5 || nombrecolumna<5){
            System.out.println("El tauler es molt xicotet, mínim ha de ser de 5x5");
            System.out.println("Cuantes files vols tindre?");
            nombrefila=sc.nextInt();
            System.out.println("Cuantes columnes vols tindre?");
            nombrecolumna=sc.nextInt(); 
        }
        
        nombrecolumna=amplada_correcta(portaavions,cuirassats,nombrecolumna);
        
        String tauler[][]=new String [nombrefila][nombrecolumna];
        return tauler;
    }
    
   
    //Funció específica per al nivell personalitzat, on l'usuari pot triar el nombre de files,columnes, i nombre de vaixells de cada tipus, així com el nombre d'intents. 
    public static void nivell_personalitzat(){
        
        Scanner sc= new Scanner (System.in);
        System.out.println("Cuantes files vols tindre?");
        int nombrefila=sc.nextInt();
        System.out.println("Cuantes columnes vols tindre?");
        int nombrecolumna=sc.nextInt();
        System.out.println("Cuantes llanxes vols tindre?");
        int llanxes=sc.nextInt();
        System.out.println("Cuants vaixells vols tindre?");
        int vaixells=sc.nextInt();
        System.out.println("Cuants cuirassats vols tindre?");
        int cuirassats=sc.nextInt();
        System.out.println("Cuants portaavions vols tindre?");
        int portaavions=sc.nextInt();
        System.out.println("Cuants intents vols tindre?");
        int triaintents=sc.nextInt();
        
        int total_vaixells= llanxes+vaixells+cuirassats+portaavions;
        int totalafonats=total_afonats(llanxes,vaixells,cuirassats,portaavions);
        int totaltauler= total_tauler(nombrefila,nombrecolumna);
        String taulerpersonalitzat[][]=tauler_correcte(totalafonats, totaltauler, nombrefila, nombrecolumna, portaavions, cuirassats);
        
        triaintents=intents_necesaris(triaintents,llanxes,vaixells,cuirassats,portaavions);
        taulerpersonalitzat= inserir_vaixells(taulerpersonalitzat,llanxes,vaixells,cuirassats,portaavions);

        començar_partida(triaintents,total_vaixells,taulerpersonalitzat,totalafonats);
    }
}
