/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.zabalburu;

/**
 *
 * @author alumno
 */
public class Gasto {
    private String concepto;
    private String fecha;
    private double importe;
    private int comercial;
    
    public Gasto(String concepto,String fecha,double importe,int comercial){
        this.concepto=concepto;
        this.fecha=fecha;
        this.importe=importe;
        this.comercial=comercial;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public int getComercial() {
        return comercial;
    }

    public void setComercial(int comercial) {
        this.comercial = comercial;
    }
    
    
}
