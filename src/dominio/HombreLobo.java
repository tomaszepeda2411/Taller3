package dominio;

public class HombreLobo extends Monstruo {
	private int ferocidad;
	private int faseLunar;

	public HombreLobo(int vida, int velocidad, int fuerza, int resistencia, int ferocidad, int faseLunar) {
		super("HombreLobo", vida, velocidad, fuerza, resistencia);
		this.ferocidad = ferocidad;
		this.faseLunar = faseLunar;
	}

}
