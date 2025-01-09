/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.ArrayList;
import static main.ThisWarOfMine.casa;
/**
 *
 * @author usuario
 */
public class Casa {
    
    //Atributos 
    
    private boolean cama;
    private ArrayList <Objeto> almacen = new ArrayList<Objeto>();

    
    //Constructor
    
    public Casa() {
    }
    
    
    
    
    //FUNCTIONS: 
    
    
    //MOSTRAR ALMACEN
    //Funcion no estatica porque sino no puede acceder al array list.
    
    public  void mostrarAlmacen(){
        if(cama==true){
            System.out.println("Cama: Si");
        }
        else{
           System.out.println("Cama: No"); 
        }
        for(Objeto objetos : almacen){
            System.out.println(objetos.toString());
        }
    }
    
    //Getters and setters

    public boolean isCama() {
        return cama;
    }

    public void setCama(boolean cama) {
        this.cama = cama;
    }

    public ArrayList<Objeto> getAlmacen() {
        return almacen;
    }

    public void setAlmacen(ArrayList<Objeto> almacen) {
        this.almacen = almacen;
    }
    
    
}
