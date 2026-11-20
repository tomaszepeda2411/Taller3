package dominio;

public interface Minijuego extends VisitorMinijuego{
	String getNombre();
	String getDescripcion();
	String getHabilidadPremiada();
	boolean isJugado();
	void setJugado(boolean jugado);
	int getPuntaje();
	void setPuntaje(int puntaje);
}	
