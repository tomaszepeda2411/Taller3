package dominio;

public class Vampiro extends Monstruo {

	private int velocidadVuelo;
	private int resistenciaSol;

	public Vampiro(int vida, int velocidad, int fuerza, int resistencia, int velocidadVuelo, int resistenciaSol) {
		super("Vampiro", vida, velocidad, fuerza, resistencia);
		this.velocidadVuelo = velocidadVuelo;
		this.resistenciaSol = resistenciaSol;
	}

}
