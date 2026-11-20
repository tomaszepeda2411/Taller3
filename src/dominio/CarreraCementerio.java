package dominio;

public class CarreraCementerio extends MinijuegoBase{
	private String nombre = "Carrera del Cementerio";
	private String descripcion = "Recorre el cementerio de punta a punta antes de que el sepulturero te vea.";
	private String habilidadPremiada = "Volador";
	
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
		return zombi.getVelocidad() * zombi.getResistencia() /100.0;
	}
	@Override
	public double visitarVampiro(Vampiro vampiro) {
		return ((vampiro.getVelocidad() + (2* vampiro.getVelocidadVuelo())) / 2.0);
	}
	@Override
	public double visitarMomia(Momia momia) {
		return (momia.getVelocidad() * 100.0) / (100+ momia.getLongitudVendajes());
	}
	@Override
	public double visitarHombreLobo(HombreLobo hombreLobo) {
		return (hombreLobo.getVelocidad() + hombreLobo.getFerocidad() / 5.0) * hombreLobo.getFaseLunar() / 4.0;
	}
}
