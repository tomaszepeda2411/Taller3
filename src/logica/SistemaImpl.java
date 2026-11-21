package logica;

import java.util.ArrayList;
import java.util.LinkedList;

import dominio.*;

public class SistemaImpl implements Sistema {
	private ArrayList<Jugador> jugadores = new ArrayList<>();
	private ArrayList<Monstruo> monstruos = new ArrayList<>();
	private ArrayList<Minijuego> minijuegos = new ArrayList<>();
	private ArrayList<Monstruo> disponibles = new ArrayList<>();
	private boolean ultimaEfectiva;
	private LinkedList<Partida> historial = new LinkedList<>();
	private String jugadorActual;
	private int puntajeActual;
	

	public SistemaImpl() {
		minijuegos.add(new CarreraCementerio());
		minijuegos.add(new CazaCalabazas());
		minijuegos.add(new LanzamientoLapidas());
		minijuegos.add(new SobrevivirAmanecer());
	}

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

			String fuerzaMordidaTxt = partes[5].trim();
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
			String velocidadVueloTxt = partes[5].trim();
			String resistenciaSolTxt = partes[6].trim();
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
			String longitudVendajesTxt = partes[5].trim();
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
			String ferocidadTxt = partes[5].trim();
			String faseLunarTxt = partes[6].trim();
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
		for(Minijuego m : minijuegos) {
			m.setJugado(false);
			m.setPuntaje(0);
		}
		historial.clear();
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

	@Override
	public ArrayList<String> listarMinijuegos() {
			
			ArrayList<String> minijuegosString = new ArrayList<>();
			for(Minijuego m : minijuegos) {
				String linea;
				boolean estado = m.isJugado();
				if(!estado) {
					 linea = String.format("%-25s",m.getNombre()) +  " [DISPONIBLE]";
				}else {
				 linea = String.format("%-25s", m.getNombre())+ " [JUGADO - " + m.getPuntaje() + " pts]";
				}
				
				minijuegosString.add(linea);
			}
			return minijuegosString;
	}

	@Override
	public int estadoMinijuego(int numero) {
		if(numero < 1 || numero> minijuegos.size()) {
			return -1;
		}else if(minijuegos.get(numero-1).isJugado()) {
			return -2;
		}
	
		return 0;
		
	}

	@Override
	public String nombreMinijuego(int numero) {
		return minijuegos.get(numero-1).getNombre();
	}

	@Override
	public String descripcionMinijuego(int numero) {
		return minijuegos.get(numero-1).getDescripcion();
	}

	@Override
	public ArrayList<String> monstruosDisponibles() {
		disponibles.clear();
		ArrayList<String> lineas = new ArrayList<>();
		for(Monstruo m : monstruos) {
			if(!m.isUsado()) {
				disponibles.add(m);
				lineas.add(m.descripcion());
			}
		}
 		return lineas;
	}

	@Override
	public double calcularPotencial(int minijuego, int monstruo) {
		Monstruo m = disponibles.get(monstruo-1);
		Minijuego mj = minijuegos.get(minijuego-1);
		return m.aceptar(mj);
	}

	@Override
	public ArrayList<String> habilidades(int monstruo) {
		Monstruo m = disponibles.get(monstruo-1);
		ArrayList<String> listaHabilidades = new ArrayList<>();
		if(m instanceof Mordedor) {
			listaHabilidades.add("Morder");
		}
		if(m instanceof Volador) {
			listaHabilidades.add("Volar");
		}
		if(m instanceof Regenerable) {
			listaHabilidades.add("Regenerarse");
		}
		if(m instanceof Furioso) {
			listaHabilidades.add("Enfurecerse");
		}
		return listaHabilidades;
	}

	@Override
	public String usarHabilidades(int monstruo, String habilidad) {
		Monstruo m = disponibles.get(monstruo-1);
		if(habilidad.equalsIgnoreCase("Morder")) {
			return ((Mordedor)m).morder();
		}
		if(habilidad.equalsIgnoreCase("Volar")) {
			return ((Volador)m).volar();
		}
		if(habilidad.equalsIgnoreCase("Regenerarse")) {
			return ((Regenerable)m).regenerarse();
		}
		else{
			return ((Furioso)m).enfurecerse();
		}
		
	}

	@Override
	public int jugar(int minijuego, int monstruo, String habilidad) {
		Monstruo m = disponibles.get(monstruo-1);
		Minijuego mj = minijuegos.get(minijuego-1);
		
		double potencial = m.aceptar(mj);
		
		// traducir el nombre del menú al nombre de la interfaz
	    String interfaz = "Ninguna";
	    if (habilidad.equals("Morder")) {
	        interfaz = "Mordedor";
	    } else if (habilidad.equals("Volar")) {
	        interfaz = "Volador";
	    } else if (habilidad.equals("Regenerarse")) {
	        interfaz = "Regenerable";
	    } else if (habilidad.equals("Enfurecerse")) {
	        interfaz = "Furioso";
	    }
	    ultimaEfectiva = mj.getHabilidadPremiada().equalsIgnoreCase(interfaz);
	    double multiplicador = 1.0;
	    if(ultimaEfectiva) {
	    	multiplicador = 1.5;
	    }
	    
	    int puntaje = (int) Math.round(potencial * 10 * multiplicador);
	    
	    m.setUsado(true);
	    mj.setJugado(true);
	    mj.setPuntaje(puntaje);
	    puntajeActual += puntaje;
	    historial.add(new Partida(mj.getNombre(), m.getTipo(), habilidad,puntaje));
		return puntaje;
	}

	@Override
	public boolean HabilidadEfectiva() {
		return ultimaEfectiva;
	}

	@Override
	public ArrayList<String> historial() {
		ArrayList<String> lineas = new ArrayList<>();
		int i = 1;
		for(Partida p : historial) {
			lineas.add(i+ ". " + p.getMinijuego() + " | " + p.getMonstruo() 
			+ " | Habilidad: " + p.getHabilidad() + " | " + p.getPuntaje() + " pts");
			i++;
		}
		lineas.add("Puntaje acumulado: " + puntajeActual + " pts");
		return lineas;
	}

	@Override
	public ArrayList<String> ranking() {
		ArrayList<Jugador> copia = new ArrayList<> (jugadores);
		
		for(int i = 0; i<copia.size() - 1 ; i++) {
			for(int j = 0; j<copia.size() -1-i;j++) {
				if(copia.get(j).getPuntaje() < copia.get(j+1).getPuntaje()) {
					Jugador temp = copia.get(j);
					copia.set(j, copia.get(j+1));
					copia.set(j+1, temp);
				}
			}
		}
		ArrayList<String> lineas = new ArrayList<>();
		for(int i = 0; i<copia.size();i++) {
			lineas.add((i + 1) + ". " + String.format("%-15s", copia.get(i).getNombre()) + copia.get(i).getPuntaje());
		}
		return lineas;
	}

	@Override
	public String terminarSesion() {
		if(historial.isEmpty()) {
			return "No jugaste ningun minijuego, no se guardo registro.";
		}
		for (Jugador j : jugadores) {
			if(j.getNombre().equalsIgnoreCase(jugadorActual)) {
				if(puntajeActual > j.getPuntaje()) {
					int anterior = j.getPuntaje();
					j.setPuntaje(puntajeActual);
					return "Sesion terminada. Puntaje total: " + puntajeActual + " puntos. Superaste tu record (" + anterior + ")!.";
				}
				return "Sesion terminada. Puntaje total: " + puntajeActual + " puntos. No superaste tu record (" + j.getPuntaje() + ").";
			}
		}
		jugadores.add(new Jugador(jugadorActual, puntajeActual));
		return "Sesion terminada. Puntaje total: " + puntajeActual + " puntos. Nuevo jugador registrado.";
	}

	@Override
	public ArrayList<String> lineasRegistros() {
		ArrayList<String> lineas = new ArrayList<>();
		for(Jugador j : jugadores) {
			lineas.add(j.getNombre() + ";" + j.getPuntaje());
		}
		return lineas;
	}

}
