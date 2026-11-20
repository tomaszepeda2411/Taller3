package dominio;

public class CazaCalabazas extends MinijuegoBase {
	private String nombre = "Caza de Calabazas";
	private String descripcion = "Muerde la mayor cantidad de calabazas del huerto en 60 segundos.";
	private String habilidadPremiada = "Mordedor";
	

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
		return (zombi.getFuerzaMordida() * zombi.getVida() / 100.0) + (zombi.getResistencia() / 2.0);
	}

	@Override
	public double visitarVampiro(Vampiro vampiro) {
		return (vampiro.getFuerza() + vampiro.getVelocidad()) / 3.0;
	}

	@Override
	public double visitarMomia(Momia momia) {
		return momia.getLongitudVendajes() * momia.getFuerza() / 200.0;
	}

	@Override
	public double visitarHombreLobo(HombreLobo hombreLobo) {
		return (hombreLobo.getFerocidad() * hombreLobo.getFaseLunar() / 4.0) + (hombreLobo.getVelocidad() / 4.0);
	}

}
