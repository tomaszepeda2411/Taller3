package logica;

import java.util.ArrayList;

import dominio.*;

public class SistemaImpl implements Sistema {
	private ArrayList<Jugador> jugadores = new ArrayList<>();
	private ArrayList<Monstruo> monstruos = new ArrayList<>();
	private String jugadorActual;
	private int puntajeActual;

	@Override
	public boolean agregarJugador(String nombre, String puntajeTexto) {
		nombre = nombre.trim();
		if (nombre.isEmpty() || puntajeTexto.isEmpty())
			return false;

		try {
			int puntaje = Integer.parseInt(puntajeTexto);
			if (puntaje < 0)
				return false;
			Jugador j = new Jugador(nombre, puntaje);
			jugadores.add(j);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	@Override
	public int cantidadJugadores() {
		return jugadores.size();
	}

	@Override
	public int cantidadMonstruos() {
		return monstruos.size();
	}

	@Override
	public String agregarMonstruo(String[] partes, String linea) {
		if (partes.length < 6) {
			return "Linea invalida por falta o sobra campos: " + linea;

		}

		String tipo = partes[0].trim();
		String vidaTexto = partes[1].trim();
		String velocidadTexto = partes[2].trim();
		String fuerzaTexto = partes[3].trim();
		String resistenciaTexto = partes[4].trim();
		int vida = validarNumeros(vidaTexto, 1, 200);
		int velocidad = validarNumeros(velocidadTexto, 1, 100);
		int fuerza = validarNumeros(fuerzaTexto, 1, 100);
		int resistencia = validarNumeros(resistenciaTexto, 1, 100);

		if (vida == -1 || velocidad == -1 || fuerza == -1 || resistencia == -1) {
			return "Linea invalida atributos fuera de rango o invalido: " + linea;
		}
		if (tipo.equalsIgnoreCase("zombi")) {
			if (partes.length != 6) {
				return "Linea invalida por falta o sobra campos: " + linea;
			}

			String fuerzaMordidaTxt = partes[5];
			int fuerzaMordida = validarNumeros(fuerzaMordidaTxt, 1, 100);

			if (fuerzaMordida == -1) {
				return "Linea invalida atributos fuera de rango o invalido: " + linea;
			}
			Zombi z = new Zombi(vida, velocidad, fuerza, resistencia, fuerzaMordida);
			monstruos.add(z);
		} else if (tipo.equalsIgnoreCase("vampiro")) {
			if (partes.length != 7) {
				return "Linea invalida por falta o sobra campos: " + linea;
			}
			String velocidadVueloTxt = partes[5];
			String resistenciaSolTxt = partes[6];
			int velocidadVuelo = validarNumeros(velocidadVueloTxt, 1, 100);
			int resistenciaSol = validarNumeros(resistenciaSolTxt, 1, 100);

			if (velocidadVuelo == -1 || resistenciaSol == -1) {
				return "Linea invalida atributos fuera de rango o invalido: " + linea;
			}
			Vampiro v = new Vampiro(vida, velocidad, fuerza, resistencia, velocidadVuelo, resistenciaSol);
			monstruos.add(v);
		} else if (tipo.equalsIgnoreCase("momia")) {
			if (partes.length != 6) {
				return "Linea invalida por falta o sobra campos: " + linea;
			}
			String longitudVendajesTxt = partes[5];
			int longitudVendajes = validarNumeros(longitudVendajesTxt, 1, 300);

			if (longitudVendajes == -1) {
				return "Linea invalida atributos fuera de rango o invalido: " + linea;
			}
			Momia m = new Momia(vida, velocidad, fuerza, resistencia, longitudVendajes);
			monstruos.add(m);
		} else if (tipo.equalsIgnoreCase("hombrelobo")) {
			if (partes.length != 7) {
				return "Linea invalida por falta o sobra campos: " + linea;
			}
			String ferocidadTxt = partes[5];
			String faseLunarTxt = partes[6];
			int ferocidad = validarNumeros(ferocidadTxt, 1, 100);
			int faseLunar = validarNumeros(faseLunarTxt, 1, 4);

			if (ferocidad == -1 || faseLunar == -1) {
				return "Linea invalida atributos fuera de rango o invalido: " + linea;
			}
			HombreLobo h = new HombreLobo(vida, velocidad, fuerza, resistencia, ferocidad, faseLunar);
			monstruos.add(h);
		} else {
			return "Linea invalida tipo no reconocido: " + linea;
		}

		return null;
	}

	private int validarNumeros(String numeroTxt, int rangoMin, int rangoMax) {
		int numero = 0;
		try {
			numero = Integer.parseInt(numeroTxt);
			if (numero < rangoMin || numero > rangoMax) {
				return -1;
			}
		} catch (NumberFormatException e) {
			return -1;
		}
		return numero;

	}

	@Override
	public boolean validarNombre(String nombre) {
		if (nombre.isBlank() || nombre.contains(";")) {
			return false;
		}
		return true;
	}

	@Override
	public int obtenerPuntaje(String nombre) {
		for (Jugador j : jugadores) {
			if (j.getNombre().equalsIgnoreCase(nombre)) {
				return j.getPuntaje();
			}
		}
		return -1;
	}

	@Override
	public void iniciarSesion(String nombre) {
		jugadorActual = nombre;
		puntajeActual = 0;
		for (Monstruo m : monstruos) {
			m.setUsado(false);
		}

	}

	@Override
	public ArrayList<String> catalogo() {
		ArrayList<String> lineas = new ArrayList<>();
		for (Monstruo m : monstruos) {
			String linea = m.descripcion();

			String habilidades = "";

			if (m instanceof Mordedor) {
				if (!habilidades.isEmpty()) {
					habilidades += ", ";
				}
				habilidades += "Morder";
			}
			if (m instanceof Volador) {
				if (!habilidades.isEmpty()) {
					habilidades += ", ";
				}
				habilidades += "Volar";
			}
			if (m instanceof Regenerable) {
				if (!habilidades.isEmpty()) {
					habilidades += ", ";
				}
				habilidades += "Regenerarse";
			}
			if (m instanceof Furioso) {
				if (!habilidades.isEmpty()) {
					habilidades += ", ";
				}
				habilidades += "Enfurecerse";
			}
			String estado = "";
			if (m.isUsado()) {
				estado = "[USADO]";
			} else {
				estado = "[DISPONIBLE]";
			}
			linea += " | Habilidades: " + habilidades + " | " + estado;
			lineas.add(linea);
		}
		return lineas;
	}

}
