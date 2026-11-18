package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	static Scanner scan;
	static Sistema sistema = new SistemaImpl();

	public static void main(String[] args) {
		System.out.println("Cargando archivos...");
		leerMonstruos();
		if (sistema.cantidadMonstruos() == 0) {
			System.out.println("No se ha encontrado el archivo Monstruos.txt o no tiene monstruos validos");
			return;
		}
		leerRegistros();

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

		} finally {
			if (scan != null) {

				scan.close();
			}
		}System.out.println("> " + num + " jugadores cargados desde Registros.txt");

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

		} finally {
			if (scan != null) {
				scan.close();
			}
		}
	}
}
