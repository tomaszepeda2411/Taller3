package dominio;

public class HombreLobo extends Monstruo implements Mordedor, Furioso {
	private int ferocidad;
	private int faseLunar;

	public HombreLobo(int vida, int velocidad, int fuerza, int resistencia, int ferocidad, int faseLunar) {
		super("HombreLobo", vida, velocidad, fuerza, resistencia);
		this.ferocidad = ferocidad;
		this.faseLunar = faseLunar;
	}

	@Override
	public String morder() {
		return "Te comes una pata de oveja que tenías guardada...";
	}

	@Override
	public String enfurecerse() {
		return "Alguien toca tu cola y te vuelves loco...";
	}

	public int getFerocidad() {
		return ferocidad;
	}

	public int getFaseLunar() {
		return faseLunar;
	}

	@Override
	public String descripcion() {
		return super.descripcion() + " | Ferocidad " + ferocidad + " | Fase lunar " + faseLunar;
	}
}
