package dominio;

public class LanzamientoLapidas extends MinijuegoBase {
	private String nombre = "Lanzamiento de Lápidas";
	private String descripcion = "Lanza una lápida lo más lejos posible.";
	private String habilidadPremiada = "Furioso";
	
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
		return (zombi.getFuerza() * zombi.getResistencia()) / 100.0;
	}

	@Override
	public double visitarVampiro(Vampiro vampiro) {
		return vampiro.getFuerza() + vampiro.getVelocidadVuelo() / 4.0;
	}

	@Override
	public double visitarMomia(Momia momia) {
		return momia.getFuerza() + momia.getLongitudVendajes() / 10.0;
	}

	@Override
	public double visitarHombreLobo(HombreLobo hombreLobo) {
		return hombreLobo.getFuerza() + hombreLobo.getFerocidad() * hombreLobo.getFaseLunar() / 8.0;
	}

}
