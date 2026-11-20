package dominio;

public interface VisitorMinijuego  {
	double visitarZombi(Zombi zombi);
	double visitarVampiro(Vampiro vampiro);
	double visitarMomia(Momia momia);
	double visitarHombreLobo(HombreLobo hombreLobo);
}
