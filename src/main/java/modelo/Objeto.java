/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author usuario
 */
public class Objeto {
    
    //Atributos
    
    private String tipo;
    private int cantidad;
    
    //Constructor

    public Objeto(String tipo, int cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
    }
    
    //Getters and setter

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
    //No sabia como override el metodo toString. 
    //He encontrado la manera, quitando el static y devolviendo un string.
    
    public String toString(){
        return "Objeto -> "+tipo+"("+cantidad+")";
    }
    
}
