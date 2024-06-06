package GrupoD_Proyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject; //MANEJO DE OBJETOS

public class GrupoD_Fiesta extends GrupoD_Evento {
	protected JSONObject fiestaJSON;
	protected String tipo, paquete;
	private String lineReader;
	private int opMenu;

	public GrupoD_Fiesta(int dia, int mes, int anio, String lugar, String tipo, String nombreEvento, int opMenu,
			String paquete) {
		super(dia, mes, anio, lugar, nombreEvento);
		this.tipo = tipo;
		this.paquete = paquete;
		this.opMenu = opMenu;
		fiestaJSON = new JSONObject();
		lineReader = "";
	}

	public void agregarFiesta() {
		nombreEvento = "fiesta";
		pedirDatosEvento();

		System.out.print("Ingrese el tipo de la fiesta: ");
		tipo = scanner.nextLine();
		do {
			System.out.println("¿Desea comprar un paquete para el concierto? (Si/No)");
			paquete = scanner.nextLine().trim().toLowerCase(); // ELIMINAR ESPACIOS EN BLANCO Y CONVERTIR EN MINUSCULA
		} while (!paquete.equals("si") && !paquete.equals("no"));
		if (paquete.equalsIgnoreCase("si")) {
			seleccionarPaquete();
		} else {
			paquete = "NINGUNO";
		}
		cliente = "";
		for (int i = 0; i < listaPersonas.size(); i++) {
			cliente += listaPersonas.get(i);
			// EN EL ÚLTIMO ÍNDICE YA NO AÑADE ","
			if (i != listaPersonas.size() - 1) {
				cliente += ", ";
			}
		}
		System.out.println("***************************");
		System.out.println("LOS DATOS DE LA FIESTA SON:");
		System.out.println("Cliente/s: " + cliente);
		System.out.println("Tipo de fiesta: " + tipo);
		System.out.println("Lugar de la fiesta: " + lugar);
		System.out.println("Fecha de la fiesta: " + dia + "/" + mes + "/" + anio);
		guardarDatos();
		System.out.println("***************************");
		System.out.println("LECTURA Fiesta.JSON");
		leerDatos();
		System.out.println("***************************");
	}

	@Override
	public void seleccionarPaquete() {
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
				System.out.println("Acceso a una mesa compartida");
				paquete = "económico";
				opMenu = 4;
				break;
			case 2:
				System.out.println("Has elegido paquete familiar. Incluye:");
				System.out.println("Acceso prioritario a actividades familiares");
				System.out.println("Asientos juntos");
				System.out.println("4 bebidas sin alcohol gratis");
				paquete = "familiar";
				opMenu = 4;
				break;
			case 3:
				System.out.println("Has elegido paquete vip. Incluye:");
				System.out.println("Fotos con el anfitrión o celebridad de la fiesta");
				System.out.println("Servicio de mesero exclusivo");
				System.out.println("Acceso a aperitivo exclusivos");
				paquete = "VIP";
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
	public void guardarDatos() {
		// INICIALIZA JSON
		fiestaJSON = new JSONObject();
		// GUARDAR EN JSON
		fiestaJSON.put("Dia", dia);
		fiestaJSON.put("Mes", mes);
		fiestaJSON.put("Año", anio);
		fiestaJSON.put("Lugar", lugar);
		fiestaJSON.put("Tipo", tipo);
		fiestaJSON.put("Paquete", paquete);
		// INICIAR CLIENTE VACIO
		cliente = "";
		for (int i = 0; i < listaPersonas.size(); i++) {
			cliente += listaPersonas.get(i);
			// EN EL ÚLTIMO ÍNDICE YA NO AÑADE ","
			if (i != listaPersonas.size() - 1) {
				cliente += ", ";
			}
		}
		fiestaJSON.put("Clientes", cliente);
		// GUARDAR FIESTA EN ARCHIVO .JSON
		try (FileWriter file = new FileWriter("Fiesta.json")) {
			// ESCRITURA JSON
			file.write(fiestaJSON.toJSONString());
			// LIMPIAR BUFFER ARCHIVO
			file.flush();
			System.out.println("Datos de la fiesta han sido guardados");
		} catch (Exception e) { //
			// IMPRIME ERRORES SI NO GUARDA EL ARCHIVO
			System.out.println("El archivo no existe");
		}
	}

	@Override
	public void leerDatos() {
		try (BufferedReader leer = new BufferedReader(new FileReader("Fiesta.json"))) {
			while ((lineReader = leer.readLine()) != null) {
				System.out.println(lineReader);
			}
		} catch (IOException e) {
			System.out.println("No se pudo leer el archivo");
		}
	}
}
