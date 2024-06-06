package GrupoD_Proyecto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GrupoD_Concierto extends GrupoD_Evento {
	protected String nombreBanda, paquete;
	private int opMenu;
	private final String ARCHIVO_CSV = "DatosEvento.csv"; // VA A ESTAR CONSTANTE HASTA QUE TERMINE EL PROGRAMA
	private File archivo;
	private String lineReader;

	public GrupoD_Concierto(int dia, int mes, int anio, String lugar, String nombreBanda, String nombreEvento,
			String paquete, int opMenu) {
		super(dia, mes, anio, lugar, nombreEvento);
		this.nombreBanda = nombreBanda;
		this.opMenu = opMenu;
		this.paquete = paquete;
		this.archivo = new File(ARCHIVO_CSV);
		lineReader = "";
	}

	public void agregarConcierto() {
		nombreEvento = "concierto";
		pedirDatosEvento();
		System.out.print("Ingrese el nombre de la banda/artista: ");
		nombreBanda = scanner.nextLine();
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
		System.out.println("LOS DATOS DE CONCIERTO SON:");
		System.out.println("Cliente/s: " + cliente);
		System.out.println("Fecha del concierto: " + dia + "/" + mes + "/" + anio);
		System.out.println("Banda/Artista: " + nombreBanda);
		System.out.println("Lugar del concierto: " + lugar);
		guardarDatos();
		System.out.println("***************************");
		leerDatos();
		System.out.println("***************************");
	}

	public void seleccionarPaquete() {
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
				System.out.println("Entrada general al concierto");
				System.out.println("Asiento reservado");
				paquete = "económico";
				opMenu = 4;
				break;
			case 2:
				System.out.println("Has elegido paquete familiar. Incluye:");
				System.out.println("4 entradas generales al concierto");
				System.out.println("Asientos juntos");
				System.out.println("4 bebidas gratis");
				System.out.println("Descuento en alimentos");
				paquete = "familiar";
				opMenu = 4;
				break;
			case 3:
				System.out.println("Has elegido paquete vip. Incluye:");
				System.out.println("Entrada VIP al concierto");
				System.out.println("Asiento en primera fila");
				System.out.println("Estacionamiento VIP");
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

	// SOBREESCRIBIR METODO POLIMORFISMO
	@Override
	public void guardarDatos() {
		// METODO GUARDAR DATOS CSV
		// MANEJO ERRORES ARCHIVOS
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_CSV, true))) {
			// Si el archivo no existe, escribimos la cabecera
			if (archivo.length() == 0) {
				writer.write("tipo evento, banda/artista, dia, mes, año, lugar,paquete");
				writer.newLine(); // SIMULA ENTER
			}
			// Escribimos los datos
			writer.write(nombreEvento + "," + nombreBanda + "," + dia + "/" + mes + "/" + anio + "," + lugar + ","
					+ paquete);
			writer.newLine(); // SIMULA ENTER
			System.out.println("Los datos han sido guardados en " + ARCHIVO_CSV);
		} catch (IOException e) {
			System.out.println("Error al guardar los datos en el archivo CSV: " + e.getMessage());
		}
	}

	// POLIMORFISMO SE UTILIZA PARA MISMO METODO, DIFERENTE LOGICA
	@Override // SOBREESCRIBIR METODO
	public void leerDatos() {
		try (BufferedReader leer = new BufferedReader(new FileReader(ARCHIVO_CSV))) {
			// LINEREADER ES PARA LEER LINEA A LINEA
			while ((lineReader = leer.readLine()) != null) {
				System.out.println(lineReader);
			}
		} catch (IOException e) {
			System.out.println("No se pudo leer el archivo");
		}
	}
}
