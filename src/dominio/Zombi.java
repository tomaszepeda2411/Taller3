package dominio;

public class Zombi extends Monstruo implements Mordedor, Regenerable {

	private int fuerzaMordida;

	public Zombi(int vida, int velocidad, int fuerza, int resistencia, int fuerzaMordida) {
		super("Zombi", vida, velocidad, fuerza, resistencia);
		this.fuerzaMordida = fuerzaMordida;
	}

	@Override
	public String morder() {
		return "Te comes un cerebro que tenías en el bolsillo...";
	}

	@Override
	public String regenerarse() {
		return "Se te cae un brazo pero te lo colocas de nuevo...";
	}

	public int getFuerzaMordida() {
		return fuerzaMordida;
	}

	@Override
	public String descripcion() {
		return super.descripcion() + " | Mordida " + fuerzaMordida;
	}

	@Override
	public double aceptar(VisitorMinijuego visitor) {

		return visitor.visitarZombi(this);
	}
}
