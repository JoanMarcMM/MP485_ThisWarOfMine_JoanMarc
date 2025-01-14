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
 *
 *
 * Cambios made by Joan Marc 
 * 
 * - Las armas son utiles: Si se hace un asalto quien
 * este vigilando usara automaticamente una arma para defenderse, de esta manera
 * no perdera salud pero el arma se perdera.
 *
 * -Si tienes una arma en el almacen en el momento de asignar roles, te aparecera
 * la opcion de darle una a quien vaya a explorar. Si se le da, este ira armado y
 * se podra defender en la zona sufriendo menos daño. Si este mismo personaje al 
 * lootear ha cojido una arma, tambien la utilizara.
 * 
 * -Lo que sube , baja. Al ser mas utiles ,he hecho que las armas tengan un spawn rate de 5% y he 
 * aumentado el de componentes a 50%
 * 
 * - La cama es util: Si hay una cama construida, quien duerma ,dormira mejor, y
 * se le restara 3 de sueño.
 * 
 * Nuevo personaje Mario: Habilidad mercenario. Si esta vigilando y atacan, luchara
 * contra los enemigos y terminara con ellos, asi quedandose con su loot. Tambien tiene experiencia en
 * crear armas y por lo tanto crearlas a partir de componentes costara 1 menos.
 *
 * 
 * 
 *
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
        if (seleccionPersonajes.contains(3)) {
            Personaje Pavel = new Personaje("Mario", "MERCENARIO");
            personajes.add(Pavel);
            System.out.println("Mario se ha unido al equipo.");
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
        opciones.add((Integer) 4);

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
                    System.out.println("0 - Arica - Habilidad: Sigilo - Menos daño al explorar.");
                }
                if (!seleccionPersonajes.contains(1)) {
                    System.out.println("1 - Bruno - Habilidad: Cocinero - Recupera mas hambre al comer.");
                }
                if (!seleccionPersonajes.contains(2)) {
                    System.out.println("2 - Katia - Habilidad: Elocuencia - Sabe negociar mejores gangas.");
                }
                if (!seleccionPersonajes.contains(3)) {
                    System.out.println("3 - Pavel - Habilidad: Rapidez - Mas espacio de inventario.");
                }
                //Nuevo personaje, si esta vigilando y atacan defendera la casa a muerte terminando con los enemigos y quedandose con su loot.
                if (!seleccionPersonajes.contains(4)) {
                    System.out.println("4 - Mario - Habilidad: Mercenario - Sed se sangre y experiencia en crear armas.");
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
            System.out.println("Ubicacion a explorar: " + ubicacion.getType());
            System.out.println("Nivel de la ubicacion: " + ubicacion.getDifficulty());
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
            if (personaje.getHealth() <= 0) {
                System.out.println(personaje.getName() + " ha muerto.");
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
            if (personaje.getHealth() <= 0) {
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

        //Boolean para ver si tenemos arma en el almacen.
        boolean weapon = false;

        //Char para validar la opcion del usuario
        String decisionWeapon;

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
            System.out.println(n + " - " + personajes.get(n).getName());
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
        
        //Veo si tenemos armas en el almacen de casa
        //Si la tenemos y su cantidad es 1 o mas indicamos el boolean como true
        for (Objeto objeto : casa.getAlmacen()) {
            if (objeto.getTipo().equalsIgnoreCase("ARMA")&&objeto.getCantidad()>=1) {
                
                weapon = true;
            }

        }
        
        //Si tenemos armas en casa se hace lo siguiente
        if (weapon == true) {
            //Hacemos un do while para seguir preguntando si responde algo que no sea coherente
            do {
                //Preguntamos al ususario si quiere que el personaje que vaya a explorar se lleve un arma para defenderse y sufrir menos daño.
                System.out.println("Puedes asignarle una arma a " + roles.get(2).getName() + " para que se defienda mientras explora.");
                System.out.println("Si lo hace recibira menos daño al explorar. (S/N)");
                decisionWeapon = sc.nextLine();
            } while (!decisionWeapon.equalsIgnoreCase("S") | !decisionWeapon.equalsIgnoreCase("N"));
            
            //Si indica que si se hace lo siguiente
            if (decisionWeapon.equalsIgnoreCase("S")) {
                //Se busca el objeto en el almacen
                for (Objeto objeto : casa.getAlmacen()) {
                    if (objeto.getTipo().equalsIgnoreCase("ARMA")) {
                        //Se le resta uno en cantidad
                        objeto.setCantidad(objeto.getCantidad()-1);
                        
                        //Creamos el objeto 
                        Objeto arma = new Objeto ("ARMA",1);
                        //Le añadimos al inventario 
                        roles.get(2).getInventory().add(arma);
                        System.out.println(roles.get(2).getName()+" se ha llevado una arma para explorar.");
                        System.out.println("Quedan "+objeto.getCantidad()+" armas en el almacen.");
                    }

                }
            }
            else{
                System.out.println(roles.get(2).getName()+" no se ha llevado una arma para explorar.");
            }
        }

        System.out.println("ROLES ESCOJIDOS");
        //Llamo a la funcion que cada personaje tiene que llevar a cabo
        System.out.println("---------------------------------------------------");
        roles.get(0).dormir(casa);
        roles.get(1).vigilar(casa);
        //En explorar me llevo la ubicacion ya que sera necesaria para cojer el loot.
        roles.get(2).explorar(ubicacion);

    }

    public static void finDia(ArrayList<Personaje> personajes) {
        Scanner sc = new Scanner(System.in);
        //Mover recursos a casa y aumentar 1 de hambre
        boolean tener = false;
        //Boolean para detectar si algun personaje ha llegado a hambre o sueño 5 y ha muerto
        boolean muerto = false;
        //Boolean para detectar si tenemos algun mercenario en el equipo
        boolean mercenary = false;
                
        //Miramos el inventario de cada personaje con un for
        for (Personaje personaje : personajes) {
            if(personaje.getHability().equalsIgnoreCase("MERCENARIO")){
                mercenary=true;
            }
            //Si el inventario no esta vacio procedemos
            if (!personaje.getInventory().isEmpty()) {
                //Reccoreemos el inventario objeto por objeto
                for (Objeto objeto : personaje.getInventory()) {
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
                personaje.getInventory().clear();

            }

            personaje.setHunger(personaje.getHunger() + 1);

            //Miramos si el personaje esta muerto o no 
            if (personaje.getHunger() >= 5 | personaje.getSleep() >= 5) {
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

                    //Añadir opciones 2,3 ,4 y5 . 
                    //Si tenemos mercenario en el quipo:
                    //   2:Gastar 2 de componentes para 1 arma.
                    //Si NO tenemos mercenario en el equipo:
                    //   2:Gastar 3 de componentes para 1 arma.
                    //3 y 4: Gatsar 5 componentes a cambio de 2 comida o 1 medicina.
                    //5: Gastar 6 comp a camio de una cama.
                    if (objeto.getTipo().equalsIgnoreCase("COMPONENTE")) {
                        if(mercenary==false){
                         if (objeto.getCantidad() >= 3) {
                            opc = 2;
                            opcionesFinDia.add(opc);
                        }   
                        }
                        else if(mercenary==true){
                         if (objeto.getCantidad() >= 2) {
                            opc = 2;
                            opcionesFinDia.add(opc);
                        }   
                        }
                        if (objeto.getCantidad() >= 5) {
                            opc = 3;
                            opcionesFinDia.add(opc);
                            opc = 4;
                            opcionesFinDia.add(opc);
                        }
                        if (objeto.getCantidad() >= 6) {
                            opc = 5;
                            opcionesFinDia.add(opc);
                        }
                    }
                    //6: Gastar 1 de medicina para curar
                    if (objeto.getTipo().equalsIgnoreCase("MEDICINA")) {
                        if (objeto.getCantidad() >= 1) {
                            opc = 6;
                            opcionesFinDia.add(opc);

                        }

                    }
                }
                //Print del menu de opciones fin de dia y del estado de los personajes
                System.out.println("---------------------------------------------------");
                System.out.println("Ha todos los personajes que no han estado durmiendo se les suma 1 de sueño debido al cansancio general.");
                System.out.println("---------------------------------------------------");
                System.out.println("OPCIONES FIN DE DIA:");
                System.out.println("Estado de los personajes:");
                for (Personaje personaje : personajes) {
                    System.out.println(personaje.getName() + " -> Salud: " + personaje.getHealth() + ", Hambre: " + personaje.getHunger() + ", Sueño: " + personaje.getSleep());
                }
                System.out.println("---------------------------------------------------");
                System.out.println("Selecciona la accion que quieras hacer:");

                if (opcionesFinDia.contains(1)) {
                    System.out.println("1) Comer: Gastar 1 de comida a cambio de disminuir en 1 la hambre de un jugador.");
                }
                if(mercenary==false){
                 if (opcionesFinDia.contains(2)) {
                    System.out.println("2) Generar arma: Gastar 3 componentes a cambio de 1 arma.");
                }   
                }
                else if(mercenary==true){
                 if (opcionesFinDia.contains(2)) {
                    System.out.println("2) Generar arma [BOOST MERCENARIO]: Gastar 2 componentes a cambio de 1 arma.");
                }   
                }
                
                if (opcionesFinDia.contains(3)) {
                    System.out.println("3) Generar comida: Gastar 5 componentes a cambio de 2 de comida.");
                }
                if (opcionesFinDia.contains(4)) {
                    System.out.println("4) Generar medicina: Gatar 5 componentes a cambio de 1 de medicina.");
                }
                if (opcionesFinDia.contains(5)) {
                    System.out.println("5) Gastar 10 componentes a cambio de crear una cama par la casa.");
                }
                if (opcionesFinDia.contains(6)) {
                    System.out.println("6) Recuperar salud: Gastar 1 de medicina para aumentar en 1 la salud de un jugador.");
                }

                System.out.println("7) Dormir (Empezar el siguiente dia).");

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
                            generarArma();
                            break;
                        case 3:
                            generarComida();
                            break;
                        case 4:
                            generarMedicina();
                            break;
                        case 5:
                            generarCama();
                            break;
                        case 6:
                            recuperarSalud(personajes);
                            break;
                        case 7:
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
            } while (selec != 7);
        }
        //Recorro los personajes para hacer la actualizacion de fin de dia
        for (Personaje personaje : personajes) {

            if (personaje.getHunger() > 0) {
                if (personaje.getHunger() >= 5) {
                    System.out.println(personaje.getName() + " ha muerto debido al hambre.");
                    personaje.setHealth(personaje.getHealth() - 10);
                    break;
                }
                personaje.setHealth(personaje.getHealth() - personaje.getHunger());
                System.out.println(personaje.getName() + " ha perdido " + personaje.getHunger() + " puntos de salud debido al hambre.");
            }
            if (personaje.getSleep() > 1) {
                if (personaje.getSleep() >= 5) {
                    System.out.println(personaje.getName() + " ha muerto debido al sueño.");
                    personaje.setHealth(personaje.getHealth() - 10);
                    break;
                }
                personaje.setHealth(personaje.getHealth() - (int) (Math.floor(personaje.getSleep() / 2)));
                System.out.println(personaje.getName() + " ha perdido " + Math.floor(personaje.getSleep() / 2) + " puntos de salud debido al sueño.");
            }

        }
    }

    public static void restarHambre(ArrayList<Personaje> personajes) {
        Scanner sc = new Scanner(System.in);

        System.out.println("A que personaje le quieres restar 1 de hambre?");

        int n = 0;
        Integer selec;
        for (Personaje personaje : personajes) {
            System.out.println(n + ") " + personaje.getName());
            n++;
        }
        selec = sc.nextInt();

        if (selec >= 0 && selec <= 2) {

            //HABILIDAD COCINERO
            if (personajes.get(selec).getHability().equalsIgnoreCase("COCINERO")) {
                personajes.get(selec).setHunger(personajes.get(selec).getHunger() - 2);
                System.out.println("Gracias a sus habilidades como cocinero a le ha restado 2 de hambre a " + personajes.get(selec).getName() + ".");
                for (Objeto objeto : casa.getAlmacen()) {
                    if (objeto.getTipo().equalsIgnoreCase("COMIDA")) {
                        objeto.setCantidad(objeto.getCantidad() - 1);
                    }
                }
            } else {
                personajes.get(selec).setHunger(personajes.get(selec).getHunger() - 1);
                System.out.println("Se le ha restado 1 de hambre a " + personajes.get(selec).getName() + ".");
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

    public static void generarArma() {

        System.out.println("Generando arma...");

        boolean haveGun = false;

        for (Objeto objeto : casa.getAlmacen()) {
            if (objeto.getTipo().equalsIgnoreCase("ARMA")) {
                objeto.setCantidad(objeto.getCantidad() + 1);
                haveGun = true;
            }
            if (objeto.getTipo().equalsIgnoreCase("COMPONENTE")) {
                objeto.setCantidad(objeto.getCantidad() - 3);
            }
        }

        if (haveGun == false) {
            Objeto objeto = new Objeto("ARMA", 1);
            casa.getAlmacen().add(objeto);
        }

        System.out.println("Arma generada.");
        System.out.println("---------------------------------------------------");
        System.out.println("Objetos en el almacen:");
        casa.mostrarAlmacen();
        System.out.println("---------------------------------------------------");

    }

    public static void generarComida() {

        System.out.println("Generando comida...");

        boolean haveFood = false;

        for (Objeto objeto : casa.getAlmacen()) {
            if (objeto.getTipo().equalsIgnoreCase("COMIDA")) {
                objeto.setCantidad(objeto.getCantidad() + 2);
                haveFood = true;
            }
            if (objeto.getTipo().equalsIgnoreCase("COMPONENTE")) {
                objeto.setCantidad(objeto.getCantidad() - 5);
            }
        }

        if (haveFood == false) {
            Objeto objeto = new Objeto("COMIDA", 1);
            casa.getAlmacen().add(objeto);
        }

        System.out.println("Comida generada.");
        System.out.println("---------------------------------------------------");
        System.out.println("Objetos en el almacen:");
        casa.mostrarAlmacen();
        System.out.println("---------------------------------------------------");

    }

    public static void generarMedicina() {

        System.out.println("Generando medicina...");

        boolean haveMedicine = false;

        for (Objeto objeto : casa.getAlmacen()) {
            if (objeto.getTipo().equalsIgnoreCase("MEDICINA")) {
                objeto.setCantidad(objeto.getCantidad() + 1);
                haveMedicine = true;
            }
            if (objeto.getTipo().equalsIgnoreCase("COMPONENTE")) {
                objeto.setCantidad(objeto.getCantidad() - 5);
            }
        }

        if (haveMedicine == false) {
            Objeto objeto = new Objeto("MEDICINA", 1);
            casa.getAlmacen().add(objeto);
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
                objeto.setCantidad(objeto.getCantidad() - 6);
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
            System.out.println(n + ") " + personaje.getName());
            n++;
        }
        selec = sc.nextInt();

        if (selec >= 0 && selec <= 2) {
            if (personajes.get(selec).getHealth() == 10) {
                System.out.println("Este personaje ya tiene la salud al maximo.");
            } else {
                personajes.get(selec).setHealth(personajes.get(selec).getHealth() + 1);
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
