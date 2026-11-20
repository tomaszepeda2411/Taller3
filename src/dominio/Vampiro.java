package dominio;

public class Vampiro extends Monstruo implements Mordedor, Volador {

	private int velocidadVuelo;
	private int resistenciaSol;

	public Vampiro(int vida, int velocidad, int fuerza, int resistencia, int velocidadVuelo, int resistenciaSol) {
		super("Vampiro", vida, velocidad, fuerza, resistencia);
		this.velocidadVuelo = velocidadVuelo;
		this.resistenciaSol = resistenciaSol;
	}

	@Override
	public String morder() {
		return "Chupas la sangre de uno de los espectadores...";
	}

	@Override
	public String volar() {
		return "Te transformaste en murciélago y usas tus alas...";
	}

	public int getVelocidadVuelo() {
		return velocidadVuelo;
	}

	public int getResistenciaSol() {
		return resistenciaSol;
	}

	@Override
	public String descripcion() {
		return super.descripcion() + " | Vuelo " + velocidadVuelo + " | Res Sol " + resistenciaSol;
	}

	@Override
	public double aceptar(VisitorMinijuego visitor) {
		return visitor.visitarVampiro(this);
	}
}
