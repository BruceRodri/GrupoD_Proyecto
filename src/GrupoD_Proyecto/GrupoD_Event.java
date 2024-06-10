package GrupoD_Proyecto;

import java.util.ArrayList; //LIBRERIA ARRAYLIST
import java.util.Scanner;

public abstract class GrupoD_Event {
	// ATRIBUTOS

	protected String place, eventName, band, personName, personID, client;
	protected int day, month, year;
	protected Scanner scanner;
	private int nPeople;
	// DATOS DEL ARRAY <STRING> DEPENDE DEL TIPO DE DATO QUE SE VAYA A GUARDAR
	protected ArrayList<String> listPeople = new ArrayList<String>();

	// CONSTRUCTOR
	public GrupoD_Event(int day, int month, int year, String place, String eventName) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.place = place;
		this.eventName = eventName;
		band = "";
		nPeople = 0;
		personName = "";
		personID = "";
		client = "";
		scanner = new Scanner(System.in);
	}

	public void requestEventData() {
		do {
			System.out.print("Ingrese el día del evento (1-31): ");
			day = scanner.nextInt();
		} while (day < 1 || day > 31);
		do {
			System.out.print("Ingrese el mes del evento (1-12): ");
			month = scanner.nextInt();
		} while (month < 1 || month > 12);
		do {
			System.out.print("Ingrese el año del evento (2024): ");
			year = scanner.nextInt();
		} while (year < 2024 || year >= 2025);
		scanner = new Scanner(System.in); // Limpiar el buffer
		System.out.print("Ingrese el lugar del evento: ");
		place = scanner.nextLine();
		do {
			System.out.println("Ingrese cuantas personas desea comprar el boleto [1-4]");
			nPeople = scanner.nextInt();
		} while (nPeople < 1 || nPeople > 4);
		// FOR DE PERSONAS
		for (int i = 0; i < nPeople; i++) {
			scanner = new Scanner(System.in); // LIMPIA BUFFER
			System.out.print("Nombre de la persona: ");
			personName = scanner.nextLine();
			do {
				System.out.print("Ingrese cédula (10 dígitos) ");
				personID = scanner.nextLine();
			} while (personID.length() != 10);
			client = personName + " - " + personID;
			listPeople.add(client); // NOMBRE.ADD(DATO A GUARDAR)
		}
	}

	// METODO POLIMORFISMO
	public abstract void saveData();

	public abstract void readData();

	public abstract void selectpackageName();
}
