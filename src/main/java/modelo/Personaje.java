/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Personaje {

    //Atributos
    private String name;
    private int health;
    private int hunger;
    private int sleep;
    private String hability;
    private ArrayList<Objeto> inventory = new ArrayList<Objeto>();

    //Constructor
    //Solo pido el nombre y la habilidad ya que los demas atributos son los 
    //mismos en todos los personajes
    public Personaje(String nombre, String habilidad) {
        this.name = nombre;
        this.hability = habilidad;
        this.health = 10;
        this.hunger = 1;
        this.sleep = 1;
    }

    //Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getSleep() {
        return sleep;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

    public String getHability() {
        return hability;
    }

    public void setHability(String hability) {
        this.hability = hability;
    }

    public ArrayList<Objeto> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Objeto> inventory) {
        this.inventory = inventory;
    }
   

    public void dormir(Casa casa) {
        System.out.println("---------------------------------------------------");
        if (casa.isCama() == true) {
            System.out.println(name + " esta durmiendo mejor gracias a la cama. (-3 sueño)");
            if (sleep > 2) {
                sleep = sleep - 3;
            } else {
                sleep = 0;
            }
        } else {
            System.out.println(name + " esta durmiendo. (-2 sueño)");
            if (sleep > 1) {
                sleep = sleep - 2;
            } else {
                sleep = 0;
            }
        }

        System.out.println("---------------------------------------------------");
    }

    public void vigilar(Casa casa) {

        System.out.println("---------------------------------------------------");
        System.out.println(name + " esta vigilando. (+1 sueño)");
        sleep = sleep + 1;

        int num = (int) Math.floor(Math.random() * 51);

        if (num >= 0 && num <= 5) {
            System.out.println("ATENCION! ");
            System.out.println("Evento: ASALTO");

            boolean haveWeapon = false;
            for (Objeto objeto : casa.getAlmacen()) {
                if (objeto.getTipo().equalsIgnoreCase("ARMA") && objeto.getCantidad() > 0) {
                    haveWeapon=true;
                    objeto.setCantidad(objeto.getCantidad()-1);
                }
            }
            
            if(haveWeapon==true){
            System.out.println("Ha habido un asalto a la casa y " + name + " ha defendido la casa.");
            System.out.println(name + " ha usado una arma para defenderse y se ha roto. (-1 Arma)");
            System.out.println("Gracias a la arma " + name + " no ha salido herido.");    
            }
            else{
            System.out.println("Ha habido un asalto a la casa y " + name + " ha defendido la casa.");
            System.out.println(name + " ha salido herido al no tener arma. (-2 Salud)");
            health = health - 2; 
            }
            
        }

        if (num >= 40 && num <= 50) {
            System.out.println("ATENCION! ");
            System.out.println("Evento: COMERCIANTE");

            //HABILIDAD ELOCUENCIA
            if (hability.equalsIgnoreCase("ELOCUENCIA")) {
                System.out.println("Un comerciante ha pasado por vuestra casa y " + name + " ha usado su elocuencia y le ha comprado 3 de comida y 3 de componentes.");

                Objeto comida = new Objeto("COMIDA", 3);
                inventory.add(comida);
                Objeto componente = new Objeto("COMPONENTE", 3);
                inventory.add(componente);
            } else {
                System.out.println("Un comerciante ha pasado por vuestra casa y " + name + " le ha comprado 2 de comida y 2 de componentes.");

                Objeto comida = new Objeto("COMIDA", 2);
                inventory.add(comida);
                Objeto componente = new Objeto("COMPONENTE", 2);
                inventory.add(componente);
            }

        }
        System.out.println("---------------------------------------------------");
    }

    public void explorar(Ubicacion ubicacion) {

        Scanner sc = new Scanner(System.in);

        System.out.println("---------------------------------------------------");
        System.out.println(name + " esta explorando. (+2 sueño y +1 hambre)");
        sleep = sleep + 2;
        hunger = hunger + 1;
        System.out.println("");

        int daño = (int) Math.floor(Math.random() * (ubicacion.getDifficulty() + 1));
        int espacioMochila;

        //HABILIDAD RAPIDEZ
        if (hability.equalsIgnoreCase("RAPIDEZ")) {
            espacioMochila = 7;
        } else {
            espacioMochila = 5;
        }

        int selec;

        do {
            int n = 0;
            System.out.println("LOOT DE LA ZONA:");
            for (Objeto objeto : ubicacion.getLoot()) {
                System.out.println(n + ") " + objeto.toString());
                n++;
            }
            System.out.println("");
            System.out.println("Espacio restante en la mochila : " + espacioMochila);
            System.out.print("Escoje un objeto de la zona para agregar a la mochila: ");
            selec = sc.nextInt();
            if (!ubicacion.getLoot().isEmpty()) {
                if (selec >= 0 && selec < ubicacion.getLoot().size()) {
                    ubicacion.getLoot().get(selec).setCantidad(ubicacion.getLoot().get(selec).getCantidad() - 1);
                    inventory.add(ubicacion.getLoot().get(selec));
                    System.out.println("Objeto añadido.");
                    if (ubicacion.getLoot().get(selec).getCantidad() == 0) {
                        ubicacion.getLoot().remove(selec);
                    }
                }
                espacioMochila--;
            }
        } while (espacioMochila > 0 && !ubicacion.getLoot().isEmpty());

        //HABILIDAD SIGILO
        if (hability.equalsIgnoreCase("SIGILO")) {
            if (daño > 0) {
                if (daño == 1) {
                    daño = 0;
                    System.out.println("Al lootear la zona " + name + " ha sufrido lesiones.");
                    System.out.println("Gracias a su sigilo ha sufrido menos daño. (-" + daño + " salud).");
                    health = health - daño;
                } else {
                    daño = daño - 2;
                    System.out.println("Al lootear la zona " + name + " ha sufrido lesiones. (-" + daño + " salud).");
                    System.out.println("Gracias a su sigilo ha sufrido menos daño. (-" + daño + " salud).");
                    health = health - daño;
                }
            }
        } else {
            if (daño > 0) {
                System.out.println("Al lootear la zona " + name + " ha sufrido lesiones. (-" + daño + " salud).");
                health = health - daño;
            }
        }

    }

}
