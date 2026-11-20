package dominio;

public abstract class Monstruo {
	private String tipo;
	private int vida;
	private int velocidad;
	private int fuerza;
	private int resistencia;
	private boolean usado;

	public Monstruo(String tipo, int vida, int velocidad, int fuerza, int resistencia) {
		this.tipo = tipo;
		this.vida = vida;
		this.velocidad = velocidad;
		this.fuerza = fuerza;
		this.resistencia = resistencia;
	}

	public String getTipo() {
		return tipo;
	}

	public int getVida() {
		return vida;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public int getFuerza() {
		return fuerza;
	}

	public int getResistencia() {
		return resistencia;
	}

	public boolean isUsado() {
		return usado;
	}

	public void setUsado(boolean usado) {
		this.usado = usado;
	}

	public String descripcion() {
		return String.format("%-10s", tipo) + " | Vida " + vida + " | Vel " + velocidad + " | Fue " + fuerza + " | Res "
				+ resistencia;
	}
	public abstract double aceptar(VisitorMinijuego visitor);
}
