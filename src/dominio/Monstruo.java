package dominio;

public abstract class Monstruo {
	private String tipo;
	private int vida;
	private int velocidad;
	private int fuerza;
	private int resistencia;

	public Monstruo(String tipo, int vida, int velocidad, int fuerza, int resistencia) {
		this.tipo = tipo;
		this.vida = vida;
		this.velocidad = velocidad;
		this.fuerza = fuerza;
		this.resistencia = resistencia;
	}

}
