package dominio;

public abstract class MinijuegoBase implements Minijuego {
	private boolean jugado;
	private int puntaje;
	
	public boolean isJugado() {
		return jugado;
	}
	public void setJugado(boolean jugado) {
		this.jugado = jugado;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	
}
