package GrupoD_Proyecto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GrupoD_Concert extends GrupoD_Event {
	protected String bandName, packageName;
	private int opMenu;
	private final String file_CSV = "DatosEvento.csv"; // VA A ESTAR CONSTANTE HASTA QUE TERMINE EL PROGRAMA
	private File file;
	private String lineReader;

	public GrupoD_Concert(int day, int month, int year, String place, String bandName, String eventName,
			String packageName, int opMenu) {
		super(day, month, year, place, eventName);
		this.bandName = bandName;
		this.opMenu = opMenu;
		this.packageName = packageName;
		this.file = new File(file_CSV);
		lineReader = "";
	}

	public void addConcert() {
		eventName = "Concierto";
		requestEventData();
		System.out.print("Ingrese el nombre de la banda/artista: ");
		bandName = scanner.nextLine();
		do {
			System.out.println("¿Desea comprar un paquete para el concierto? (Si/No)");
			packageName = scanner.nextLine().trim().toLowerCase(); // ELIMINAR ESPACIOS EN BLANCO Y CONVERTIR EN MINUSCULA
		} while (!packageName.equals("si") && !packageName.equals("no"));
		if (packageName.equalsIgnoreCase("si")) {
			selectpackageName();
		} else {
			packageName = "NINGUNO";
		}
		client = "";
		for (int i = 0; i < listPeople.size(); i++) {
			client += listPeople.get(i);
			// EN EL ÚLTIMO ÍNDICE YA NO AÑADE ","
			if (i != listPeople.size() - 1) {
				client += ", ";
			}
		}
		System.out.println("***************************");
		System.out.println("LOS DATOS DE conciert SON:");
		System.out.println("Cliente/s: " + client);
		System.out.println("Fecha del conciert: " + day + "/" + month + "/" + year);
		System.out.println("Banda/Artista: " + bandName);
		System.out.println("Lugar del conciert: " + place);
		saveData();
		System.out.println("***************************");
		readData();
		System.out.println("***************************");
	}

	public void selectpackageName() {
		do {
			System.out.println("ELIJA ALGUNA OPCIÓN");
			System.out.println("[1] PAQUETE ECONÓMICO ($30)");
			System.out.println("[2] PAQUETE FAMILIAR ($80)");
			System.out.println("[3] PAQUETE VIP ($150)");
			System.out.println("[4] SALIR");
			opMenu = scanner.nextInt();
			switch (opMenu) {
			case 1:
				System.out.println("Has elegido paquete económico. Incluye:");
				System.out.println("Entrada general al conciert");
				System.out.println("Asiento reservado");
				packageName = "económico";
				opMenu = 4;
				break;
			case 2:
				System.out.println("Has elegido paquete familiar. Incluye:");
				System.out.println("4 entradas generales al conciert");
				System.out.println("Asientos juntos");
				System.out.println("4 bebidas gratis");
				System.out.println("Descuento en alimentos");
				packageName = "familiar";
				opMenu = 4;
				break;
			case 3:
				System.out.println("Has elegido paquete vip. Incluye:");
				System.out.println("Entrada VIP al concierto");
				System.out.println("Asiento en primera fila");
				System.out.println("Estacionamiento VIP");
				packageName = "VIP";
				opMenu = 4;
				break;
			case 4:
				System.out.println("Ha salido, muchas gracias");
				break;
			default:
				System.out.println("Ingrese una opcion correcta 1-4");
			}
		} while (opMenu != 4);
	}

	// SOBREESCRIBIR METODO POLIMORFISMO
	@Override
	public void saveData() {
		// METODO GUARDAR DATOS CSV
		// MANEJO ERRORES fileS
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_CSV, true))) {
			//SI EL ARCHIVO NO EXISTE, SE ESCRIBE LA CABECERA
			if (file.length() == 0) {
				writer.write("type evento, band/artista, day, month, año, place,packageName");
				writer.newLine(); // SIMULA ENTER
			}
			//ESCRIBIR DATOS
			writer.write(eventName + "," + bandName + "," + day + "/" + month + "/" + year + "," + place + ","
					+ packageName);
			writer.newLine(); // SIMULA ENTER
			System.out.println("Los datos han sido guardados en " + file_CSV);
		} catch (IOException e) {
			System.out.println("Error al guardar los datos en el file CSV");
		}
	}

	// POLIMORFISMO SE UTILIZA PARA MISMO METODO, DIFERENTE LOGICA
	@Override // SOBREESCRIBIR METODO
	public void readData() {
		try (BufferedReader leer = new BufferedReader(new FileReader(file_CSV))) {
			// LINEREADER ES PARA LEER LINEA A LINEA
			while ((lineReader = leer.readLine()) != null) {
				System.out.println(lineReader);
			}
		} catch (IOException e) {
			System.out.println("No se pudo leer el file");
		}
	}
}
