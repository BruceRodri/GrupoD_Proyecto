package GrupoD_Proyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject; //MANEJO DE OBJETOS

public class GrupoD_Party extends GrupoD_Event {
	protected JSONObject fiestaJSON;
	protected String type, packageName;
	private String lineReader;
	private int opMenu;

	public GrupoD_Party(int day, int month, int year, String place, String type, String eventName, int opMenu,
			String packageName) {
		super(day, month, year, place, eventName);
		this.type = type;
		this.packageName = packageName;
		this.opMenu = opMenu;
		fiestaJSON = new JSONObject();
		lineReader = "";
	}

	public void agregarFiesta() {
		eventName = "fiesta";
		requestEventData();

		System.out.print("Ingrese el tipo de la fiesta: ");
		type = scanner.nextLine();
		do {
			System.out.println("¿Desea comprar un paquete para la fiesta? (Si/No)");
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
		System.out.println("LOS DATOS DE LA FIESTA SON:");
		System.out.println("Cliente/s: " + client);
		System.out.println("Tipo de fiesta: " + type);
		System.out.println("Lugar de la fiesta: " + place);
		System.out.println("Fecha de la fiesta: " + day + "/" + month + "/" + year);
		saveData();
		System.out.println("***************************");
		System.out.println("LECTURA Fiesta.JSON");
		readData();
		System.out.println("***************************");
	}

	@Override
	public void selectpackageName() {
		do {
			System.out.println("ELIJA ALGUNA OPCIÓN");
			System.out.println("[1] PAQUETE ECONÓMICO ($10)");
			System.out.println("[2] PAQUETE FAMILIAR ($40)");
			System.out.println("[3] PAQUETE VIP ($70)");
			System.out.println("[4] SALIR");
			opMenu = scanner.nextInt();
			switch (opMenu) {
			case 1:
				System.out.println("Has elegido paquete económico. Incluye:");
				System.out.println("Acceso a la pista de baile");
				System.out.println("1 bebida gratis");
				System.out.println("Acceso a una montha compartida");
				packageName = "económico";
				opMenu = 4;
				break;
			case 2:
				System.out.println("Has elegido paquete familiar. Incluye:");
				System.out.println("Acceso prioritario a actividades familiares");
				System.out.println("Asientos juntos");
				System.out.println("4 bebidas sin alcohol gratis");
				packageName = "familiar";
				opMenu = 4;
				break;
			case 3:
				System.out.println("Has elegido paquete vip. Incluye:");
				System.out.println("Fotos con el anfitrión o celebridad de la fiesta");
				System.out.println("Servicio de monthero exclusivo");
				System.out.println("Acceso a aperitivo exclusivos");
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

	@Override
	public void saveData() {
		// INICIALIZA JSON
		fiestaJSON = new JSONObject();
		// GUARDAR EN JSON
		fiestaJSON.put("Dia: ", day);
		fiestaJSON.put("Mes: ", month);
		fiestaJSON.put("Año: ", year);
		fiestaJSON.put("Lugar: ", place);
		fiestaJSON.put("Tipo: ", type);
		fiestaJSON.put("Paquete: ", packageName);
		// INICIAR CLIENTE VACIO
		client = "";
		// FOR
		for (int i = 0; i < listPeople.size(); i++) {
			client += listPeople.get(i);
			// EN EL ÚLTIMO ÍNDICE YA NO AÑADE ","
			if (i != listPeople.size() - 1) {
				client += ", ";
			}
		}
		fiestaJSON.put("clients", client);
		// GUARDAR FIESTA EN ARCHIVO .JSON
		try (FileWriter file = new FileWriter("Fiesta.json")) {
			// ESCRITURA JSON
			file.write(fiestaJSON.toJSONString());
			// LIMPIAR BUFFER ARCHIVO
			file.flush();
			System.out.println("Datos de la fiesta han sido guardados");
		} catch (Exception e) { 
			// IMPRIME ERRORES SI NO GUARDA EL ARCHIVO
			System.out.println("El archivo no existe");
		}
	}

	@Override
	public void readData() {
		try (BufferedReader leer = new BufferedReader(new FileReader("Fiesta.json"))) {
			while ((lineReader = leer.readLine()) != null) {
				System.out.println(lineReader);
			}
		} catch (IOException e) {
			System.out.println("No se pudo leer el archivo");
		}
	}
}
