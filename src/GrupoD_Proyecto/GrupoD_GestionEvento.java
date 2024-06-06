package GrupoD_Proyecto;

import java.util.Scanner;

public class GrupoD_GestionEvento {
	// ATRIBUTOS
	private Scanner scanner;
	private int opMenu;
	private GrupoD_Concierto concierto;
	private GrupoD_Fiesta fiesta;

	// CONSTRUCTOR
	public GrupoD_GestionEvento(int opMenu) {
		this.opMenu = opMenu;
		scanner = new Scanner(System.in);
		concierto = new GrupoD_Concierto(0, 0, 0, "", "", "", "", 0);
		fiesta = new GrupoD_Fiesta(0, 0, 0, "", "", "", 0, "");
	}

	public void ejecutarGestionEventos() {

		do {

			System.out.println("SALON DE EVENTOS");
			System.out.println("ELIJA ALGUNA OPCION");
			System.out.println("[1] CONCIERTO");
			System.out.println("[2] FIESTA");
			System.out.println("[3] SALIR");
			opMenu = scanner.nextInt();
			scanner = new Scanner(System.in); // LIMPIAR BUFFER

			switch (opMenu) {
			case 1:
				concierto.agregarConcierto();
				break;

			case 2:
				fiesta.agregarFiesta();
				break;
			case 3:
				System.out.println("Ha salido del programa");
				break;
			default:
				System.out.println("Ingrese alguna opción disponible 1-3");
				break;
			}
		} while (opMenu != 3);

		scanner.close();
	}
}
