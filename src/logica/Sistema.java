package logica;

public interface Sistema {
	public boolean agregarJugador(String nombre, String puntajeTexto);
	public String agregarMonstruo(String[] partes,String linea);
	public int cantidadJugadores();
	public int cantidadMonstruos();
}
