package dominio;

public class Momia extends Monstruo {
	private int longitudVendajes;

	public Momia(int vida, int velocidad, int fuerza, int resistencia, int longitudVendajes) {
		super("Momia", vida, velocidad, fuerza, resistencia);
		this.longitudVendajes = longitudVendajes;
	}

}
