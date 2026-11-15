package taller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	static Scanner scan;
	public static void main(String[] args) {
		leerMonstruos();
		leerRegistros();
	}

	private static void leerRegistros() {
		File txtRegistros = new File("Registros.txt");
		try {
			scan = new Scanner(txtRegistros);
			while(scan.hasNextLine()) {		
				
				String linea = scan.nextLine().trim();
				
				if(linea.isEmpty()) continue;
				String[] partes = linea.split(";");
				
				if(partes.length !=2) continue;
				
				String nombre = partes[0].trim();
				String puntajeTexto = partes[1].trim();
				
				if(nombre.isEmpty() || puntajeTexto.isEmpty()) continue;
				
				try {
					int puntaje = Integer.parseInt(puntajeTexto);
					if(puntaje <0) continue;
				} catch(NumberFormatException e) {	
					System.out.println("Puntaje no valido, se ignora la linea");
				}	
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(scan != null) scan.close();
		}
	}

	private static void leerMonstruos() {
		File txtMonstruos = new File("Monstruos.txt");
		try {
			scan = new Scanner(txtMonstruos);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
