package logica;

import java.util.ArrayList;

public interface Sistema {
	boolean agregarJugador(String nombre, String puntajeTexto);

	String agregarMonstruo(String[] partes, String linea);

	int cantidadJugadores();

	int cantidadMonstruos();

	boolean validarNombre(String nombre);

	int obtenerPuntaje(String nombre);

	void iniciarSesion(String nombre);

	ArrayList<String> catalogo();
}
