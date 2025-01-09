/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.ArrayList;
/**
 *
 * @author usuario
 */
public class Ubicacion {
    
    //Atributos
    private ArrayList <Objeto> loot = new ArrayList<Objeto>();
    private int peligrosidad;
    //Extra mio
    private String tipo;
    

    public Ubicacion(int turno) {
        this.peligrosidad=(int)Math.floor(Math.random()*turno)+1;
        this.loot=generarLoot(turno);
        this.tipo=generarTipo();
    }
    
    
    //Getters and setters

    public ArrayList<Objeto> getLoot() {
        return loot;
    }

    public void setLoot(ArrayList<Objeto> loot) {
        this.loot = loot;
    }

    public int getPeligrosidad() {
        return peligrosidad;
    }

    public void setPeligrosidad(int peligrosidad) {
        this.peligrosidad = peligrosidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
    //Funciones
    
    public ArrayList <Objeto> generarLoot(int turno){
        
        int num ;
        
        ArrayList <Objeto> loot =new ArrayList<Objeto>();
        
        Objeto arma= new Objeto("ARMA",1);
        loot.add(arma);
        Objeto comida= new Objeto("COMIDA",1);
        loot.add(comida);
        Objeto medicina= new Objeto("MEDICINA",1);
        loot.add(medicina);
        Objeto componente= new Objeto("COMPONENTE",1);
        loot.add(componente);
        
        int cantidad= (int)(Math.floor(Math.random()*10)+1)-turno;
        
        if (cantidad>0){
            for(int n =0; n<cantidad;n++){
                
                num=(int)Math.floor(Math.random()*100)+1;
                
                //10% armas añade arma
                if(num<11){
                loot.get(0).setCantidad(loot.get(0).getCantidad()+1);
                }
                //30% comida añade comida
                if(10<num&&num<41){
                loot.get(1).setCantidad(loot.get(1).getCantidad()+1);    
                }
                //15% medicina añade medicina
                if(40<num&&num<56){
                loot.get(2).setCantidad(loot.get(2).getCantidad()+1);    
                }
                //45% componentes añade componente
                if(55<num&&num<101){
                loot.get(3).setCantidad(loot.get(3).getCantidad()+1);    
                }
            }
            
        }
        
        return loot;
    }

    public String generarTipo(){
        
        String tipo;
        
        int num=(int)Math.floor(Math.random()*6);
        
        switch (num) {
            case 0:
                tipo="Ciudad";
                break;
            case 1:
                tipo="Suburbios";
                break;
            case 2:
                tipo="Zona Industrial";
                break;
            case 3:
                tipo="Pueblo";
                break;
            case 4:
                tipo="Bosque";
                break;
            case 5:
                tipo="Playa";
                break;
            default:
                throw new AssertionError();
        }
        
        
        
        return tipo;
    }
}
