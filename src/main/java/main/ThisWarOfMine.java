/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package main;

import java.util.ArrayList;
import java.util.Scanner;
import modelo.Personaje;
import modelo.Casa;
import modelo.Objeto;
import modelo.Ubicacion;

/**
 *
 * @author usuario
 */
public class ThisWarOfMine {

    public static Casa casa = new Casa();

    public static void main(String[] args) {

        //Variables
        //Array lis donde se alamcenaran los personajes con los que juguemos
        ArrayList<Personaje> personajes = new ArrayList<Personaje>();

        //ArrayList donde se alamcenaran los numeros que indicaran que personajes
        //ha escojido el jugador para poder añadirlos en el Arraylist personajes
        ArrayList<Integer> seleccionPersonajes = new ArrayList<Integer>();

        //Metodos
        getCharacters(seleccionPersonajes);

        generateCharacters(personajes, seleccionPersonajes);

        nuevaPartida(personajes);

    }

    //METODOS
    //Generar personajes
    public static void generateCharacters(ArrayList<Personaje> personajes, ArrayList<Integer> seleccionPersonajes) {

        //Depende de que numeros haya escojido el jugador, se añadiran ciertos
        //personajes en el arraList personajes.
        //Con el if se va añadiendo cada personaje dependiendo de si se encuentra en
        //el arraylist de selecciones
        if (seleccionPersonajes.contains(0)) {
            Personaje Arica = new Personaje("Arica", "SIGILO");
            personajes.add(Arica);
            System.out.println("Arica se ha unido al equipo.");
        }
        if (seleccionPersonajes.contains(1)) {
            Personaje Bruno = new Personaje("Bruno", "COCINERO");
            personajes.add(Bruno);
            System.out.println("Bruno se ha unido al equipo.");
        }
        if (seleccionPersonajes.contains(2)) {
            Personaje Katia = new Personaje("Katia", "ELOCUENCIA");
            personajes.add(Katia);
            System.out.println("Katia se ha unido al equipo.");
        }
        if (seleccionPersonajes.contains(3)) {
            Personaje Pavel = new Personaje("Pavel", "RAPIDEZ");
            personajes.add(Pavel);
            System.out.println("Pavel se ha unido al equipo.");
        }

    }

    //Seleccinar que personajes usar
    public static void getCharacters(ArrayList<Integer> seleccionPersonajes) {
        Scanner sc = new Scanner(System.in);

        //Variables
        //Integer donde guardare la seleccion
        Integer selec;
        //Boolean para verificar si la seleccion es valida
        boolean validacion = false;
        //ArrayList donde guardo las opciones disponibles
        ArrayList<Integer> opciones = new ArrayList<Integer>();
        opciones.add((Integer) 0);
        opciones.add((Integer) 1);
        opciones.add((Integer) 2);
        opciones.add((Integer) 3);

        //For de 3 ciclos para pedri 3 personajes
        for (int n = 0; n < 3; n++) {
            //Reinicio validacion como false
            validacion = false;

            //Bucle do while en caso de que la seleccion no sea valida volver a pedir
            do {
                //Prints
                System.out.println("---------------------------------------------------");
                System.out.println("PERSONAJES DISPONIBLES:");

                //Solo hago print de los personajes que no esten ya introducidos en la seleccion
                if (!seleccionPersonajes.contains(0)) {
                    System.out.println("0 - Arica");
                }
                if (!seleccionPersonajes.contains(1)) {
                    System.out.println("1 - Bruno");
                }
                if (!seleccionPersonajes.contains(2)) {
                    System.out.println("2 - Katia");
                }
                if (!seleccionPersonajes.contains(3)) {
                    System.out.println("3 - Pavel");
                }
                System.out.println("---------------------------------------------------");
                System.out.print("Seleccion n." + (n + 1) + " : ");
                selec = sc.nextInt();

                //Si la seleccion se encuentra dentro de las opciones , se elimina de las opciones disponibles
                //Se añade en la seleccion y validacion se cambia a true.
                if (opciones.contains(selec)) {
                    opciones.remove(selec);
                    seleccionPersonajes.add(selec);
                    validacion = true;
                }
            } while (validacion == false);
        }
    }

    //Nueva Partida
    public static void nuevaPartida(ArrayList<Personaje> personajes) {

        //Variable int para ir manejando los turnos
        int turno = 1;

        //Bucle do while que hara odos los dias hasta que un personaje se muera
        do {
            //Print del dia y de los objetos que estan en el almacen
            System.out.println("---------------------------------------------------");
            System.out.println("DIA " + turno);
            System.out.println("---------------------------------------------------");
            System.out.println("Objetos en el almacen:");
            if (casa.getAlmacen().isEmpty()) {
                System.out.println("-Almacen vacio-");
            } else {
                casa.mostrarAlmacen();
            }
            System.out.println("---------------------------------------------------");

            //Genero una ubicacion y la guardo en un objeto ubicacion
            Ubicacion ubicacion = generarUbicacion(turno);

            //Muestro el tipo , la peligrosidad y los objetos que puedes encontrar
            System.out.println("Ubicacion a explorar: " + ubicacion.getTipo());
            System.out.println("Nivel de la ubicacion: " + ubicacion.getPeligrosidad());
            System.out.println("Loot de la ubicacion: ");
            for (Objeto objeto : ubicacion.getLoot()) {
                System.out.println(objeto.toString());
            }

            //Metodo para escojer lso roles de cada personaje
            escogerRoles(personajes, ubicacion);

            //Metodo para manejar el final del dia
            finDia(personajes);

            //Se le suma 1 al turno
            turno++;

            //Condicion para que siga el juego, que el metodo chackSalud devuelva false
        } while (checkSalud(personajes) == false);

        //En caso de que algun personaje haya muerto , recorro todos los personajes con un for
        //y el personaje que tenga menos o 0 de vida saldra en un mensaje anunciano su muerte.
        for (Personaje personaje : personajes) {
            if (personaje.getSalud() <= 0) {
                System.out.println(personaje.getNombre() + " ha muerto.");
            }
        }
        System.out.println("La partida ha finalizado.");
    }

    //Detectar si algun personaje a muerto
    public static boolean checkSalud(ArrayList<Personaje> personajes) {
        Scanner sc = new Scanner(System.in);
        //Boolean que devolvere
        boolean personajeMuerto = false;
        //Recorro el array de jugadores y voy personaje por personaje mirando su salud,
        //si alguno tiene 0 o menos de salud el boolean se vuelve true.
        for (Personaje personaje : personajes) {
            if (personaje.getSalud() <= 0) {
                personajeMuerto = true;
            }
        }
        //Devolvemos el boolean
        return personajeMuerto;
    }

    //Generamos un objeto ubicacion
    public static Ubicacion generarUbicacion(int turno) {
        Ubicacion ubicacion = new Ubicacion(turno);
        return ubicacion;
    }

    //Roles
    public static void escogerRoles(ArrayList<Personaje> personajes, Ubicacion ubicacion) {

        Scanner sc = new Scanner(System.in);

        //ArrayList donde se guardaran los roles de los personajes
        ArrayList<Personaje> roles = new ArrayList<Personaje>();

        //Guardar la opcion que ingresa el usuario
        Integer num;
        //Para ir validando si la opcion es valida o no
        boolean validacion = false;

        //Ir manejando que opciones quedan
        ArrayList<Integer> opciones = new ArrayList<Integer>();
        //Añado las opciones que pueden haber
        opciones.add(0);
        opciones.add(1);
        opciones.add(2);

        //Hago print de los personajes que se pueden escojer
        System.out.println("---------------------------------------------------");
        System.out.println("Selecciona los roles de cada personaje:");
        for (int n = 0; n < 3; n++) {
            System.out.println(n + " - " + personajes.get(n).getNombre());
        }

        //Hago bucles para cada pregunta por si mp se introduce una opcion valida
        //Voy guardando cada personaje en una posicion del arraylist roles
        //Las posiciones tienen importancia ya que el personaje que este en posicion 0 dormira
        //el que esten en la 1 vigilara y el que este en la 2 explorara
        do {
            System.out.println("1) Personaje que se quedara durmiendo:");
            num = sc.nextInt();
            if (opciones.contains(num)) {
                roles.add(personajes.get(num));
                validacion = true;
                opciones.remove(num);
            } else {
                System.out.println("Opcion no valida");
            }
        } while (validacion == false);
        validacion = false;

        do {
            System.out.println("2) Personaje que se quedara vigilando la casa:");
            num = sc.nextInt();
            if (opciones.contains(num)) {
                roles.add(personajes.get(num));
                validacion = true;
                opciones.remove(num);
            } else {
                System.out.println("Opcion no valida");
            }
        } while (validacion == false);
        validacion = false;

        do {
            System.out.println("3) Personaje que ira a explorar:");
            num = sc.nextInt();
            if (opciones.contains(num)) {
                roles.add(personajes.get(num));
                validacion = true;
                opciones.remove(num);
            } else {
                System.out.println("Opcion no valida");
            }
        } while (validacion == false);
        validacion = false;

        System.out.println("ROLES ESCOJIDOS");

        //Llamo a la funcion que cada personaje tiene que llevar a cabo
        System.out.println("---------------------------------------------------");
        roles.get(0).dormir();
        roles.get(1).vigilar();
        //En explorar me llevo la ubicacion ya que sera necesaria para cojer el loot.
        roles.get(2).explorar(ubicacion);

    }

    public static void finDia(ArrayList<Personaje> personajes) {
        Scanner sc = new Scanner(System.in);
        //Mover recursos a casa y aumentar 1 de hambre
        boolean tener = false;
        //Boolean para detectar si algun personaje ha llegado a hambre o sueño 5 y ha muerto
        boolean muerto = false;

        //Miramos el inventario de cada personaje con un for
        for (Personaje personaje : personajes) {
            //Si el inventario no esta vacio procedemos
            if (!personaje.getInventario().isEmpty()) {
                //Reccoreemos el inventario objeto por objeto
                for (Objeto objeto : personaje.getInventario()) {
                    tener = false;

                    //Si el objeto ya lo tenemos en el inventario le sumamos a cantidad 1
                    for (Objeto objetoAlmacen : casa.getAlmacen()) {
                        if (objeto.getTipo().equalsIgnoreCase(objetoAlmacen.getTipo())) {
                            objetoAlmacen.setCantidad(objetoAlmacen.getCantidad() + 1);
                            tener = true;
                        }
                    }
                    //Si no lo tenemos lo añadimos y ponemos su cantidad a 1
                    if (tener == false) {
                        casa.getAlmacen().add(objeto);
                        objeto.setCantidad(1);

                    }
                }
                //Vaciamos el inventario del personaje
                personaje.getInventario().clear();

            }

            personaje.setHambre(personaje.getHambre() + 1);

            //Miramos si el personaje esta muerto o no 
            if (personaje.getHambre() >= 5 | personaje.getSueño() >= 5) {
                muerto = true;
            }
        }
        if (muerto == false) {
            //Variable donde se guardara la seleccion del juagdor
            Integer selec;

            ArrayList<Integer> opcionesFinDia = new ArrayList<Integer>();
            Integer opc;
            do {
                //Ver que opciones se pueden hacer y cuales no.

                for (Objeto objeto : casa.getAlmacen()) {

                    //Añadir opcions 1 (Gastar 1 de comida para restar 1 de hambre)               
                    if (objeto.getTipo().equalsIgnoreCase("COMIDA")) {
                        if (objeto.getCantidad() >= 1) {
                            opc = 1;
                            opcionesFinDia.add(opc);
                        }
                    }

                    //Añadir opciones 2,3 y4 . 
                    //2 y 3: Gatsar 5 componentes a cambio de 2 comida o 1 medicina.
                    //4: Gastar 1'0 comp a camio de una cama.
                    if (objeto.getTipo().equalsIgnoreCase("COMPONENTE")) {
                        if (objeto.getCantidad() >= 5) {
                            opc = 2;
                            opcionesFinDia.add(opc);
                            opc = 3;
                            opcionesFinDia.add(opc);
                        }
                        if (objeto.getCantidad() >= 10) {
                            opc = 4;
                            opcionesFinDia.add(opc);
                        }
                    }

                    if (objeto.getTipo().equalsIgnoreCase("MEDICINA")) {
                        if (objeto.getCantidad() >= 1) {
                            opc = 5;
                            opcionesFinDia.add(opc);

                        }

                    }
                }
                //Print del menu de opciones fin de dia y del estado de los personajes
                System.out.println("---------------------------------------------------");
                System.out.println("OPCIONES FIN DE DIA:");
                System.out.println("Estado de los personajes:");
                for (Personaje personaje : personajes) {
                    System.out.println(personaje.getNombre() + " -> Salud: " + personaje.getSalud() + ", Hambre: " + personaje.getHambre() + ", Sueño: " + personaje.getSueño());
                }
                System.out.println("---------------------------------------------------");
                System.out.println("Selecciona la accion que quieras hacer:");

                if (opcionesFinDia.contains(1)) {
                    System.out.println("1) Comer: Gastar 1 de comida a cambio de disminuir en 1 la hambre de un jugador.");
                }
                if (opcionesFinDia.contains(2)) {
                    System.out.println("2) Generar comida: Gastar 5 componentes a cambio de 2 de comida.");
                }
                if (opcionesFinDia.contains(3)) {
                    System.out.println("3) Generar medicina: Gatar 5 componentes a cambio de 1 de medicina.");
                }
                if (opcionesFinDia.contains(4)) {
                    System.out.println("4) Gastar 10 componentes a cambio de crear una cama par la casa.");
                }
                if (opcionesFinDia.contains(5)) {
                    System.out.println("5) Recuperar salud: Gastar 1 de medicina para aumentar en 1 la salud de un jugador.");
                }

                System.out.println("6) Dormir (Empezar el siguiente dia).");

                //Pido la opcion y la guardo
                System.out.print("Seleccion: ");
                selec = sc.nextInt();
                //Si la opcion sleccionada esta dentro de las opciones posibles sigo
                if (opcionesFinDia.contains(selec)) {
                    //Swicth para redireccionar al metedo escojido
                    switch (selec) {
                        case 1:
                            restarHambre(personajes);
                            break;
                        case 2:
                            generarComida();
                            break;
                        case 3:
                            generarMedicina();
                            break;
                        case 4:
                            generarCama();
                            break;
                        case 5:
                            recuperarSalud(personajes);
                            break;
                        case 6:
                            System.out.println("FIN DIA");
                            break;
                        default:
                            throw new AssertionError();
                    }
                } else {
                    System.out.println("Seleccion no valida.");
                }
                //Limpio las opciones possibles
                opcionesFinDia.clear();
            } while (selec != 6);
        }
        //Recorro los personajes para hacer la actualizacion de fin de dia
        for (Personaje personaje : personajes) {

            if (personaje.getHambre() > 0) {
                if (personaje.getHambre() >= 5) {
                System.out.println(personaje.getNombre() + " ha muerto debido al hambre.");
                personaje.setSalud(personaje.getSalud() - 10);
                break;
                }
                personaje.setSalud(personaje.getSalud() - personaje.getHambre());
                System.out.println(personaje.getNombre() + " ha perdido " + personaje.getHambre() + " puntos de salud debido al hambre.");
            }
            if (personaje.getSueño() > 1) {
                if (personaje.getSueño() >= 5) {
                System.out.println(personaje.getNombre() + " ha muerto debido al sueño.");
                personaje.setSalud(personaje.getSalud() - 10);
                break;
            }
                personaje.setSalud(personaje.getSalud() - (int) (Math.floor(personaje.getSueño() / 2)));
                System.out.println(personaje.getNombre() + " ha perdido " + Math.floor(personaje.getSueño() / 2) + " puntos de salud debido al sueño.");
            }
            
            
        }
    }

    public static void restarHambre(ArrayList<Personaje> personajes) {
        Scanner sc = new Scanner(System.in);

        System.out.println("A que personaje le quieres restar 1 de hambre?");

        int n = 0;
        Integer selec;
        for (Personaje personaje : personajes) {
            System.out.println(n + ") " + personaje.getNombre());
            n++;
        }
        selec = sc.nextInt();

        if (selec >= 0 && selec <= 2) {
            
            //HABILIDAD COCINERO
            
            if(personajes.get(selec).getHabilidad().equalsIgnoreCase("COCINERO")){
            personajes.get(selec).setHambre(personajes.get(selec).getHambre() - 2);
            System.out.println("Gracias a sus habilidades como cocinero a le ha restado 2 de hambre a " + personajes.get(selec).getNombre() + ".");
            for (Objeto objeto : casa.getAlmacen()) {
                if (objeto.getTipo().equalsIgnoreCase("COMIDA")) {
                    objeto.setCantidad(objeto.getCantidad() - 1);
                }
            }    
            }
            else{
            personajes.get(selec).setHambre(personajes.get(selec).getHambre() - 1);
            System.out.println("Se le ha restado 1 de hambre a " + personajes.get(selec).getNombre() + ".");
            for (Objeto objeto : casa.getAlmacen()) {
                if (objeto.getTipo().equalsIgnoreCase("COMIDA")) {
                    objeto.setCantidad(objeto.getCantidad() - 1);
                }
            }
            }
        } else {
            System.out.println("Seleccion no valdia.");
        }
    }

    public static void generarComida() {

        System.out.println("Generando comida...");

        for (Objeto objeto : casa.getAlmacen()) {
            if (objeto.getTipo().equalsIgnoreCase("COMIDA")) {
                objeto.setCantidad(objeto.getCantidad() + 2);
            }
            if (objeto.getTipo().equalsIgnoreCase("COMPONENTE")) {
                objeto.setCantidad(objeto.getCantidad() - 5);
            }
        }

        System.out.println("Comida generada.");
        System.out.println("---------------------------------------------------");
        System.out.println("Objetos en el almacen:");
        casa.mostrarAlmacen();
        System.out.println("---------------------------------------------------");

    }

    public static void generarMedicina() {

        System.out.println("Generando medicina...");

        for (Objeto objeto : casa.getAlmacen()) {
            if (objeto.getTipo().equalsIgnoreCase("MEDICINA")) {
                objeto.setCantidad(objeto.getCantidad() + 1);
            }
            if (objeto.getTipo().equalsIgnoreCase("COMPONENTE")) {
                objeto.setCantidad(objeto.getCantidad() - 5);
            }
        }

        System.out.println("Medicina generada.");
        System.out.println("---------------------------------------------------");
        System.out.println("Objetos en el almacen:");
        casa.mostrarAlmacen();

        System.out.println("---------------------------------------------------");

    }

    public static void generarCama() {

        System.out.println("Construyendo cama...");

        casa.setCama(true);
        for (Objeto objeto : casa.getAlmacen()) {
            if (objeto.getTipo().equalsIgnoreCase("COMPONENTE")) {
                objeto.setCantidad(objeto.getCantidad() - 10);
            }
        }

        System.out.println("Cama construida.");
        System.out.println("---------------------------------------------------");
        System.out.println("Objetos en el almacen:");
        casa.mostrarAlmacen();

        System.out.println("---------------------------------------------------");

    }

    public static void recuperarSalud(ArrayList<Personaje> personajes) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Que personaje quieres que recupere 1 de salud?");

        int n = 0;
        Integer selec;
        for (Personaje personaje : personajes) {
            System.out.println(n + ") " + personaje.getNombre());
            n++;
        }
        selec = sc.nextInt();

        if (selec >= 0 && selec <= 2) {
            if (personajes.get(selec).getSalud() == 10) {
                System.out.println("Este personaje ya tiene la salud al maximo.");
            } else {
                personajes.get(selec).setSalud(personajes.get(selec).getSalud() + 1);
            }
        } else {
            System.out.println("Seleccion no valdia.");
        }

        for (Objeto objeto : casa.getAlmacen()) {
            if (objeto.getTipo().equalsIgnoreCase("MEDICINA")) {
                objeto.setCantidad(objeto.getCantidad() - 1);
            }
        }

    }
}
