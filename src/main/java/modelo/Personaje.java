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
    private String nombre;
    private int salud;
    private int hambre;
    private int sueño;
    private String habilidad;
    private ArrayList<Objeto> inventario = new ArrayList<Objeto>();

    //Constructor
    //Solo pido el nombre y la habilidad ya que los demas atributos son los 
    //mismos en todos los personajes
    public Personaje(String nombre, String habilidad) {
        this.nombre = nombre;
        this.habilidad = habilidad;
        this.salud = 10;
        this.hambre = 1;
        this.sueño = 1;
    }

    //Getters and setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getHambre() {
        return hambre;
    }

    public void setHambre(int hambre) {
        this.hambre = hambre;
    }

    public int getSueño() {
        return sueño;
    }

    public void setSueño(int sueño) {
        this.sueño = sueño;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public ArrayList<Objeto> getInventario() {
        return inventario;
    }

    public void setInventario(ArrayList<Objeto> inventario) {
        this.inventario = inventario;
    }

    public void dormir() {
        System.out.println("---------------------------------------------------");
        System.out.println(nombre + " esta durmiendo. (-2 sueño)");
        if (sueño > 1) {
            sueño = sueño - 2;
        } else {
            sueño = 0;
        }
        System.out.println("---------------------------------------------------");
    }

    public void vigilar() {

        System.out.println("---------------------------------------------------");
        System.out.println(nombre + " esta vigilando. (+1 sueño)");
        sueño = sueño + 1;

        int num = (int) Math.floor(Math.random() * 51);

        if (num >= 0 && num <= 5) {
            System.out.println("ATENCION! ");
            System.out.println("Evento: ASALTO");
            System.out.println("Ha habido un asalto a la casa y " + nombre + " ha salido herido. (-2 salud).");
            salud = salud - 2;
        }

        if (num >= 40 && num <= 50) {
            System.out.println("ATENCION! ");
            System.out.println("Evento: COMERCIANTE");

            //HABILIDAD ELOCUENCIA
            if (habilidad.equalsIgnoreCase("ELOCUENCIA")) {
                System.out.println("Un comerciante ha pasado por vuestra casa y " + nombre + " ha usado su elocuencia y le ha comprado 3 de comida y 3 de componentes.");

                Objeto comida = new Objeto("COMIDA", 3);
                inventario.add(comida);
                Objeto componente = new Objeto("COMPONENTE", 3);
                inventario.add(componente);
            } else {
                System.out.println("Un comerciante ha pasado por vuestra casa y " + nombre + " le ha comprado 2 de comida y 2 de componentes.");

                Objeto comida = new Objeto("COMIDA", 2);
                inventario.add(comida);
                Objeto componente = new Objeto("COMPONENTE", 2);
                inventario.add(componente);
            }

        }
        System.out.println("---------------------------------------------------");
    }

    public void explorar(Ubicacion ubicacion) {

        Scanner sc = new Scanner(System.in);

        System.out.println("---------------------------------------------------");
        System.out.println(nombre + " esta explorando. (+2 sueño y +1 hambre)");
        sueño = sueño + 2;
        hambre = hambre + 1;
        System.out.println("");

        int daño = (int) Math.floor(Math.random() * (ubicacion.getPeligrosidad() + 1));
        int espacioMochila;

        //HABILIDAD RAPIDEZ
        if (habilidad.equalsIgnoreCase("RAPIDEZ")) {
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
                    inventario.add(ubicacion.getLoot().get(selec));
                    System.out.println("Objeto añadido.");
                    if (ubicacion.getLoot().get(selec).getCantidad() == 0) {
                        ubicacion.getLoot().remove(selec);
                    }
                }
                espacioMochila--;
            }
        } while (espacioMochila > 0 && !ubicacion.getLoot().isEmpty());
        
        //HABILIDAD SIGILO
        
        if (habilidad.equalsIgnoreCase("SIGILO")) {
            if (daño > 0) {
                if (daño == 1) {
                    daño = 0;
                    System.out.println("Al lootear la zona " + nombre + " ha sufrido lesiones.");
                    System.out.println("Gracias a su sigilo ha sufrido menos daño. (-" + daño + " salud).");
                    salud = salud - daño;
                } else {
                    daño = daño - 2;
                    System.out.println("Al lootear la zona " + nombre + " ha sufrido lesiones. (-" + daño + " salud).");
                    System.out.println("Gracias a su sigilo ha sufrido menos daño. (-" + daño + " salud).");
                    salud = salud - daño;
                }
            }
        } else {
            if (daño > 0) {
                System.out.println("Al lootear la zona " + nombre + " ha sufrido lesiones. (-" + daño + " salud).");
                salud = salud - daño;
            }
        }

    }

}
