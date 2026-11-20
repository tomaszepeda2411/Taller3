package dominio;

public class Momia extends Monstruo implements Regenerable,Furioso {
	private int longitudVendajes;

	public Momia(int vida, int velocidad, int fuerza, int resistencia, int longitudVendajes) {
		super("Momia", vida, velocidad, fuerza, resistencia);
		this.longitudVendajes = longitudVendajes;
	}

	@Override
	public String regenerarse() {
		return "Se te caen las vendas, pero te colocas unas nuevas...";
	}

	@Override
	public String enfurecerse() {
		return "Alguien te pisa una de tus vendas y entras en cólera...";
	}

	public int getLongitudVendajes() {
		return longitudVendajes;
	}
	
	@Override
	public String descripcion() {
		return super.descripcion() + " | Vendajes " + longitudVendajes;
	}
}
