package co.edu.uptc.view;

public class MenuCli {

    public static final String RESET = "\u001B[0m";
    public static final String PURPLE = "\u001B[35m";

    public void systemMenu(){
        System.out.println(PURPLE + "============== MENÚ DE OPCIONES ==============" + RESET);
        System.out.println(PURPLE + "1. Buscar archivo (comodines permitidos)" + RESET);
        System.out.println(PURPLE + "2. Mostrar tamaño total de la carpeta inicial" + RESET);
        System.out.println(PURPLE + "3. Listar contenido de un subdirectorio" + RESET);
        System.out.println(PURPLE + "4. Borrar un archivo (¡cuidado!)" + RESET);
        System.out.println(PURPLE + "5. Salir" + RESET);
    }

}
