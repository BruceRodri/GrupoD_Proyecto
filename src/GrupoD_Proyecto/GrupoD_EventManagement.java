package GrupoD_Proyecto;

import java.util.Scanner;

public class GrupoD_EventManagement {
	// ATRIBUTOS
	private Scanner scanner;
	private int opMenu;
	private GrupoD_Concert conciert;
	private GrupoD_Party fiesta;

	// CONSTRUCTOR
	public GrupoD_EventManagement(int opMenu) {
		this.opMenu = opMenu;
		scanner = new Scanner(System.in);
		conciert = new GrupoD_Concert(0, 0, 0, "", "", "", "", 0);
		fiesta = new GrupoD_Party(0, 0, 0, "", "", "", 0, "");
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
				conciert.addConcert();
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
