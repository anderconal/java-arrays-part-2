/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.zabalburu;

import javax.swing.JOptionPane;

/**
 *
 * @author alumno
 */
public class GestionGastos {
    private static Gasto[]gastos=new Gasto[20];
    private static String[]comerciales={
    "Luis","Ana","María","Julio"          
    };
    private static int numComerciales=4;
    private static int numGastos=4;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        inicializar();
        String respuesta;
        int opcion; 
        
        do{
            
        respuesta=JOptionPane.showInputDialog("1 - Nuevo Gasto"+
                "\n2 - Eliminar Gasto"+
                "\n3 - Modificar Importe"+
                "\n4 - Listar Todos Los Gastos"+
                "\n5 - Listar Gastos de un Comercial"+
                "\n6 - Salir"+
                "\n\nOpción [1-6]: ");
        
        opcion=Integer.parseInt(respuesta);
         
            switch(opcion){
            case 1:
                nuevoGasto();
                break;
            case 2: 
                eliminarGasto();
                break;
            case 3:
                modificarImporte();
                break;
            case 4:
                listarGastos();
                break;
            case 5:
                listarGastosComercial();
                break;
            case 6:                
            }
        }while(opcion!=6);
    }
    private static int buscarComercial(String nombre){
        
        int i;
        int respuesta;
        //BÚSQUEDA DESORDENADA
        for(i=0;i<numComerciales&&!nombre.equalsIgnoreCase(comerciales[i]);i++);
        
        if(i<numComerciales){
            respuesta=i;           
        }else{
            respuesta=-1;
        }
        
        return respuesta;
    }

    private static void nuevoGasto() {
        String nombre=JOptionPane.showInputDialog("INTRODUZCA NOMBRE DEL COMERCIAL QUE HA HECHO EL GASTO: ");
        if(buscarComercial(nombre)<=-1){
            JOptionPane.showMessageDialog(null, "NO HAY NINGÚN COMERCIAL CON ESE NOMBRE","ERROR",JOptionPane.ERROR_MESSAGE);
        }else if(buscarComercial(nombre)>=1){
            String concepto,fecha,importeS;
            double importe;
            int i;
            concepto=JOptionPane.showInputDialog("INTRODUZCA CONCEPTO ASOCIADO AL GASTO: ");
            fecha=JOptionPane.showInputDialog("INTRODUZCA FECHA DEL GASTO: ");
            importeS=JOptionPane.showInputDialog("INTRODUZCA IMPORTE DEL GASTO: ");
            
            importe=Double.parseDouble(importeS);
            //BÚSQUEDA ORDENADA
            for(i=0;i<numGastos&&concepto.compareToIgnoreCase(gastos[i].getConcepto())>0;i++);
            
            if(i<numGastos&&concepto.equalsIgnoreCase(gastos[i].getConcepto())){
                JOptionPane.showMessageDialog(null, "CONCEPTO REPETIDO","ERROR",JOptionPane.ERROR_MESSAGE);
            }else{
                gastos[i+1]=gastos[i];
                gastos[i]=new Gasto(concepto,fecha,importe,buscarComercial(nombre));
                numGastos++;
            }
        }
        
    }

    private static void eliminarGasto() {
        String concepto=JOptionPane.showInputDialog("INTRODUZCA CONCEPTO DEL GASTO A ELIMINAR: ");
        int mayor,menor,medio;
        
        menor=0;        
        mayor=numGastos-1;
        medio=(mayor+menor)/2;
        //BÚSQUEDA BINARIA
        while(menor<=mayor&&!concepto.equalsIgnoreCase(gastos[medio].getConcepto())){
            
            if(concepto.compareToIgnoreCase(gastos[medio].getConcepto())>0){
                menor=medio+1;
            }else{
                mayor=medio-1;
            }
            medio = (menor + mayor) / 2;           
        }
        
        if(menor<=mayor){
            
            if(JOptionPane.showConfirmDialog(null,"NOMBRE DEL COMERCIAL: "+comerciales[gastos[medio].getComercial()]+
                    "\nCONCEPTO: "+concepto+
                    "\nFECHA: "+gastos[medio].getFecha()+
                    "\nIMPORTE: "+gastos[medio].getImporte(),"¿ELIMINAR EL GASTO?",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                
                for(;medio<numGastos-1;medio++){
                    gastos[medio]=gastos[medio+1];
                }
                
                numGastos--; 
            }
        
        }else{
               JOptionPane.showMessageDialog(null, "ERROR, CONCEPTO NO ENCONTRADO","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void inicializar() {
        gastos[0]=new Gasto("Agua","20/02/2012",5000,0);
        gastos[1]=new Gasto("Luz","15/03/2013",3000,1);
        gastos[2]=new Gasto("Gas","23/07/2014",1000,2);
        gastos[3]=new Gasto("Basura","19/01/2015",2000,3);
    }

    private static void modificarImporte() {
        String concepto=JOptionPane.showInputDialog(null,"INTRODUZCA CONCEPTO DE GASTO A MODIFICAR");
        String importeS;
        double importe;
        int i;
        //BÚSQUEDA DESORDENADA
        for(i=0;i<numGastos&&!concepto.equalsIgnoreCase(gastos[i].getConcepto());i++);
        
        if (i<numGastos) {
            importeS=JOptionPane.showInputDialog("IMPORTE ACTUAL: "+gastos[i].getImporte()+
                    "\n\nINTRODUZCA NUEVO IMPORTE");
            importe=Double.parseDouble(importeS);
            gastos[i].setImporte(importe);
        }else{
            JOptionPane.showMessageDialog(null,"CONCEPTO NO ENCONTRADO","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void listarGastos() {
        double suma=0;
        for(int i=0;i<numGastos;i++){
            suma+=gastos[i].getImporte();
        }
        
        for(int i=0;i<numGastos;i++){
            System.out.println("\n\nCOMERCIAL: "+comerciales[gastos[i].getComercial()]+
            "\nFECHA: "+gastos[i].getFecha()+
            "\nCONCEPTO: "+gastos[i].getConcepto()+
            "\nIMPORTE: "+gastos[i].getImporte());
        }
        
            System.out.println("\n\nIMPORTE TOTAL: "+suma);
    }

    private static void listarGastosComercial() {
        String nombre=JOptionPane.showInputDialog("INTRODUZCA NOMBRE DEL COMERCIAL: ");
        
        if (buscarComercial(nombre)>=0) {
            int i=0;
            System.out.println("GASTOS DE "+nombre+" :");
                   
            for(;i<numGastos;i++){
            
                if (comerciales[gastos[i].getComercial()].equalsIgnoreCase(nombre)) {
                System.out.println("\n\nFecha: "+gastos[i].getFecha()+
                    "\nConcepto: "+gastos[i].getConcepto()+
                    "\nImporte: "+gastos[i].getImporte());
                }
            }
            
        }else{
            JOptionPane.showMessageDialog(null,"COMERCIAL NO ENCONTRADO","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
}