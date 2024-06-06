package GrupoD_Proyecto;

import java.util.ArrayList; //LIBRERIA ARRAYLIST
import java.util.Scanner;

public abstract class GrupoD_Evento {
	// ATRIBUTOS

	protected String lugar, nombreEvento, banda, nombrePersona, cedulaPersona, cliente;
	protected int dia, mes, anio;
	protected Scanner scanner;
	private int nPersonas;
	// DATOS DEL ARRAY <STRING> DEPENDE DEL TIPO DE DATO QUE SE VAYA A GUARDAR
	protected ArrayList<String> listaPersonas = new ArrayList();

	// CONSTRUCTOR
	public GrupoD_Evento(int dia, int mes, int anio, String lugar, String nombreEvento) {
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
		this.lugar = lugar;
		this.nombreEvento = nombreEvento;
		banda = "";
		nombreEvento = "";
		nPersonas = 0;
		nombrePersona = "";
		cedulaPersona = "";
		cliente = "";
		scanner = new Scanner(System.in);
	}

	public void pedirDatosEvento() {
		do {
			System.out.print("Ingrese el día del evento (1-31): ");
			dia = scanner.nextInt();
		} while (dia < 1 || dia > 31);
		do {
			System.out.print("Ingrese el mes del evento (1-12): ");
			mes = scanner.nextInt();
		} while (mes < 1 || mes > 12);
		do {
			System.out.print("Ingrese el año del evento (2024): ");
			anio = scanner.nextInt();
		} while (anio < 2024 || anio >= 2025);
		scanner = new Scanner(System.in); // Limpiar el buffer
		System.out.print("Ingrese el lugar del evento: ");
		lugar = scanner.nextLine();
		do {
			System.out.println("Ingrese cuantas personas desea comprar el boleto [1-4]");
			nPersonas = scanner.nextInt();
		} while (nPersonas < 1 || nPersonas > 4);
		// FOR DE PERSONAS
		for (int i = 0; i < nPersonas; i++) {
			scanner = new Scanner(System.in); // LIMPIA BUFFER
			System.out.print("Nombre de la persona: ");
			nombrePersona = scanner.nextLine();
			do {
				System.out.print("Ingrese cédula (10 dígitos) ");
				cedulaPersona = scanner.nextLine();
			} while (cedulaPersona.length() != 10);
			cliente = nombrePersona + " - " + cedulaPersona;
			listaPersonas.add(cliente); // NOMBRE.ADD(DATO A GUARDAR)
		}
	}

	// METODO POLIMORFISMO
	public abstract void guardarDatos();

	public abstract void leerDatos();

	public abstract void seleccionarPaquete();
}
