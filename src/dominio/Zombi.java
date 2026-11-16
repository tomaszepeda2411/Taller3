package dominio;

public class Zombi extends Monstruo {

	private int fuerzaMordida;

	public Zombi(int vida, int velocidad, int fuerza, int resistencia, int fuerzaMordida) {
		super("Zombi", vida, velocidad, fuerza, resistencia);
		this.fuerzaMordida = fuerzaMordida;
	}

}
