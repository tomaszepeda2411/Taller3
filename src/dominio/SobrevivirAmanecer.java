package dominio;

public class SobrevivirAmanecer extends MinijuegoBase {
	private String nombre = "Sobrevivir al Amanecer";
	private String descripcion = "Aguanta en pie cuando salgan los primeros rayos de sol.";
	private String habilidadPremiada = "Regenerable";

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String getHabilidadPremiada() {
		return habilidadPremiada;
	}

	@Override
	public double visitarZombi(Zombi zombi) {
		return zombi.getResistencia() * zombi.getVida() / 100.0;
	}

	@Override
	public double visitarVampiro(Vampiro vampiro) {
		return vampiro.getResistencia() * vampiro.getResistenciaSol() / 100.0;
	}

	@Override
	public double visitarMomia(Momia momia) {
		return (momia.getResistencia() + momia.getVida()) / 2.0 * (1 + momia.getLongitudVendajes() / 300.0);
	}

	@Override
	public double visitarHombreLobo(HombreLobo hombreLobo) {
		return (hombreLobo.getResistencia() + hombreLobo.getVida()) / 2.0 * (5 - hombreLobo.getFaseLunar()) / 4.0;
	}

}
