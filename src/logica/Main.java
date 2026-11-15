package logica;

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
			while(scan.hasNextLine()) {
				String linea = scan.nextLine().trim();
				if(linea.isEmpty()) continue;
				
				String[] partes = linea.split(";");
				 
				if(partes.length < 6) {
					System.out.println("linea invalida ignorada: " + linea);
					continue;
				}
				
				String tipo = partes[0].trim();
				String vidaTexto = partes[1].trim();
				String velocidadTexto = partes[2].trim();
				String fuerzaTexto = partes[3].trim();
				String resistenciaTexto = partes[4].trim();
				int vida = validarNumeros(vidaTexto,1,200,linea);
				int velocidad = validarNumeros(velocidadTexto,1,100,linea);
				int fuerza = validarNumeros(fuerzaTexto,1,100,linea);
				int resistencia = validarNumeros(resistenciaTexto,1,100,linea);
				
				if(vida == -1 || velocidad == -1 || fuerza == -1 || resistencia == -1) {
					System.out.println("Linea invalida ignorada: " + linea);
					continue;
				}
				if(tipo.equalsIgnoreCase("zombi")) {
					if (partes.length != 6) {
						System.out.println("Linea invalida ignorada: " + linea);
						continue;
					}
					
					String fuerzaMordidaTxt = partes[5];
					int fuerzaMordida = validarNumeros(fuerzaMordidaTxt,1,100,linea);
					
					if(fuerzaMordida == -1) {
						System.out.println("Linea invalida ignorada: " + linea);
						continue;
					}
				}else if (tipo.equalsIgnoreCase("vampiro")) {
					if (partes.length != 7) {
						System.out.println("Linea invalida ignorada: " + linea);
						continue;
					}
					String velocidadVueloTxt = partes[5];
					String resistenciaSolTxt = partes[6];
					int velocidadVuelo = validarNumeros(velocidadVueloTxt,1,100,linea);
					int resistenciaSol = validarNumeros(resistenciaSolTxt,1,100,linea);
	
					if(velocidadVuelo == -1 || resistenciaSol == -1) {
						System.out.println("Linea invalida ignorada: " + linea);
						continue;
					}
				}else if (tipo.equalsIgnoreCase("momia")) {
					if (partes.length != 6) {
						System.out.println("Linea invalida ignorada: " + linea);
						continue;
					}
					String longitudVendajesTxt = partes[5];
					int longitudVendajes = validarNumeros(longitudVendajesTxt,1,300,linea);
					
					if (longitudVendajes == -1) {
						System.out.println("Linea invalida ignorada: " + linea);
						continue;
					}
				}else if(tipo.equalsIgnoreCase("hombrelobo")) {
					if (partes.length != 7) {
						System.out.println("Linea invalida ignorada: " + linea);
						continue;
					}
					String ferocidadTxt = partes[5];
					String faseLunarTxt = partes[6];
					int ferocidad = validarNumeros(ferocidadTxt,1,100,linea);
					int faseLunar = validarNumeros(faseLunarTxt,1,4,linea);
					
					if(ferocidad == -1 || faseLunar == -1) {
						System.out.println("Linea invalida ignorada: " + linea);
						continue;
					}
				} else {
					System.out.println("Linea invalida ignorada: " + linea);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(scan !=null) scan.close();
		}
		
	}
	private static int validarNumeros(String numeroTxt, int rangoMin, int rangoMax, String linea) {
		int numero = 0;
		try {
			 numero = Integer.parseInt(numeroTxt);
			if(numero < rangoMin || numero > rangoMax) {
				return -1;
			}
		}catch(NumberFormatException e) {
			return -1;
		}return numero;
		
	}
}
