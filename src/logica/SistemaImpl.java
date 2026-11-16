package logica;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import dominio.*;

public class SistemaImpl implements Sistema {
	ArrayList<Jugador> jugadores = new ArrayList<>();

	@Override
	public boolean agregarJugador(String nombre, String puntajeTexto) {
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

}
