package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

	static Scanner scan;
	static Scanner teclado = new Scanner(System.in);
	static Sistema sistema = new SistemaImpl();

	public static void main(String[] args) {
		System.out.println("Cargando archivos...");

		leerMonstruos();

		if (sistema.cantidadMonstruos() == 0) {
			System.out.println("No se ha encontrado el archivo Monstruos.txt o no tiene monstruos validos");
			return;
		}
		leerRegistros();
		System.out.println("=========================================");
		System.out.println("             SPOOKY SEASON             ");
		System.out.println("=========================================");
		menu();
	}

	private static void menu() {
		int opcion = 0;
		String nombre;
		boolean valido;
		do {
			do {
				System.out.print("Ingrese su nombre de usuario: ");
				nombre = teclado.nextLine().trim();
				valido = sistema.validarNombre(nombre);

				if (!valido) {
					System.out.println("[!] El nombre no puede estar vacio ni contener ';'.");
				}
			} while (!valido);

			int puntaje = sistema.obtenerPuntaje(nombre);
			if (puntaje < 0) {
				System.out.println("Bienvenido " + nombre + " es tu primera vez jugando.");
			} else {
				System.out.println("Bienvenido de vuelta, " + nombre + "! Tu récord es de: " + puntaje + " puntos.");
			}
			sistema.iniciarSesion(nombre);
			do {

				System.out.println();
				System.out.println("----- MENÚ PRINCIPAL (" + nombre + ") -----");
				System.out.println("1) Jugar un minijuego");
				System.out.println("2) Ver catálogo de monstruos");
				System.out.println("3) Ver historial de la sesión");
				System.out.println("4) Ver ranking de jugadores");
				System.out.println("5) Terminar sesión");
				System.out.println("6) Salir");
				System.out.print("> ");
				opcion = 0;
				try {
					opcion = Integer.parseInt(teclado.nextLine());
					switch (opcion) {
					case 1:
						jugarMinijuego();
						break;
					case 2:
						verCatalogo();
						break;
					case 3:
						verHistorial();
						break;
					case 4:
						verRanking();
						break;
					case 5:
						break;
					case 6:
						break;
					default:
						System.out.println("[!] Ingrese un numero valido");
					}
				} catch (NumberFormatException e) {
					System.out.println("[!] Debe ingresar un número. Intente nuevamente.");

				}

			} while (opcion != 5 && opcion != 6);

		} while (opcion != 6);

	}

	private static void verRanking() {
		ArrayList<String> lineas = sistema.ranking();
		System.out.println("-----RANKING-----");
		if(lineas.isEmpty()) {
			System.out.println("Aun no hay jugadores registrados.");
		}
		for(String linea: lineas) {
			System.out.println(linea);
		}
		
	}

	private static void verHistorial() {
		ArrayList<String> lineas = sistema.historial();
		System.out.println("-----HISTORIAL DE LA SESION-----");
		if (lineas.size() == 1) {
			System.out.println("Aun no has jugado ningun minijuego.");
		}
		for(String linea : lineas) {
			System.out.println(linea);
		}
		
	}

	private static void jugarMinijuego() {
		int opcion = 0;
		do {
			opcion = -1;

			System.out.println("------ MINIJUEGOS ------");
			ArrayList<String> minijuegos = sistema.listarMinijuegos();
			for (int i = 0; i < minijuegos.size(); i++) {
				System.out.println((i + 1) + "- " + minijuegos.get(i));
			}
			System.out.println("0- Volver");
			System.out.print("> ");
			try {
				opcion = Integer.parseInt(teclado.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("[!] Ingrese un numero");
				continue;
			}
			if (opcion == 0) {
				continue;
			}
			
			int estado = sistema.estadoMinijuego(opcion);
			if (estado == -1) {
				System.out.println("[!] Opcion fuera de rango");
			} else if (estado == -2) {
				System.out.println("[!] Ya has jugado este minijuego");
			} else {
				System.out.println();
				System.out.println(sistema.nombreMinijuego(opcion) + ": " + sistema.descripcionMinijuego(opcion));
				elegirMonstruo(opcion);
	
				
			}
		} while (opcion != 0);
	}

	private static String elegirHabilidad(int monstruo) {
		ArrayList<String> habilidades = sistema.habilidades(monstruo);
		int opcion;
		do {
			opcion = -1;
			
			System.out.println();
			System.out.println("¿Quieres usar una habilidad?");
			for(int i=0; i<habilidades.size();i++) {
				System.out.println((i + 1) + ") " + habilidades.get(i));
			}
			System.out.println("0) No usar habilidad");
			System.out.print("> ");
			
			try {
				opcion = Integer.parseInt(teclado.nextLine());
			}catch (NumberFormatException e) {
				 System.out.println("[!] Ingrese un numero");
		            continue;
			}
			if(opcion == 0) {
				return "Ninguna";
			}
			if(opcion<1 || opcion > habilidades.size()) {
				System.out.println("[!] Opcion fuera de rango");
	            continue;
			}
			String elegida = habilidades.get(opcion-1);
			System.out.println(sistema.usarHabilidades(monstruo, elegida));
			return elegida;
		}while(true);
	}

	private static void elegirMonstruo(int minijuego) {
		ArrayList<String> lista = sistema.monstruosDisponibles();
		if(lista.isEmpty()) {
			System.out.println("[!] No quedan monstruos disponibles");
			return;
		}
		int opcion;
		do {
			opcion = -1;
			
			System.out.println();
			System.out.println("Elige el monstruo en el que te convertiras:");
			for(int i = 0; i<lista.size();i++) {
				System.out.println((i+1) + ") " + lista.get(i));
			}
			System.out.println("0) Volver");
			System.out.print("> ");
			try {
				opcion = Integer.parseInt(teclado.nextLine().trim());
			}catch (NumberFormatException e) {
				System.out.println("[!] Ingrese un numero");
				continue;
			}
			if(opcion == 0) {
				return;
			}
			if(opcion <1 || opcion > lista.size()) {
				System.out.println("[!] Opcion fuera de rango");
				continue;
			}
			double potencial = sistema.calcularPotencial(minijuego, opcion);
			System.out.println();
			System.out.println("Te transformas en el monstruo elegido...");
			System.out.println("Potencial: " + String.format("%.2f", potencial));
			String habilidad = elegirHabilidad(opcion);
			int puntaje = sistema.jugar(minijuego, opcion, habilidad);
			if(!habilidad.equals("Ninguna")) {
				if(sistema.HabilidadEfectiva()) {
					System.out.println("¡La habilidad fue efectiva! Puntaje x1.5");
				} else {
					System.out.println("La habilidad no tuvo efecto en este minijuego.");
				}
			}
			System.out.println();
			System.out.println(">> Puntaje obtenido: " + puntaje + " puntos");
			return;
		}while(true);
	}

	private static void verCatalogo() {
		ArrayList<String> lineas = sistema.catalogo();
		System.out.println("----- CATÁLOGO DE MONSTRUOS -----");
		for (int i = 0; i < lineas.size(); i++) {
			System.out.println((i + 1) + "- " + lineas.get(i));
		}
	}

	private static void leerRegistros() {
		File txtRegistros = new File("Registros.txt");
		int num = 0;
		scan = null; // por si el scanner apunta a uno cerrado
		try {
			scan = new Scanner(txtRegistros);

			while (scan.hasNextLine()) {

				String linea = scan.nextLine().trim();

				if (linea.isEmpty())
					continue;
				String[] partes = linea.split(";");

				if (partes.length != 2)
					continue;

				String nombre = partes[0].trim();
				String puntajeTexto = partes[1].trim();

				sistema.agregarJugador(nombre, puntajeTexto);

			}
			num = sistema.cantidadJugadores();

		} catch (FileNotFoundException e) {
			// no existe se parte con lista vacia
		} finally {
			if (scan != null) {

				scan.close();
			}
		}
		System.out.println("> " + num + " jugadores cargados desde Registros.txt");

	}

	private static void leerMonstruos() {
		File txtMonstruos = new File("Monstruos.txt");
		int num = 0;
		scan = null;// por si el scan apunta a uno cerrado
		try {
			scan = new Scanner(txtMonstruos);
			while (scan.hasNextLine()) {
				String linea = scan.nextLine().trim();
				if (linea.isEmpty())
					continue;

				String[] partes = linea.split(";");

				String motivo = sistema.agregarMonstruo(partes, linea);
				if (motivo != null) {
					System.out.println(motivo);
				}
			}
			num = sistema.cantidadMonstruos();
			System.out.println("> " + num + " monstruos cargados desde Monstruos.txt");

		} catch (FileNotFoundException e) {
			// no existe se parte con lista vacia
		} finally {
			if (scan != null) {
				scan.close();
			}
		}
	}
}
