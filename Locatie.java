package OVchipkaart;

public class Locatie {
    private String naam;
    private double x, y;

    public Locatie(String naam, double x, double y) {
        this.naam = naam;
        this.x = x;
        this.y = y;
    }

    public String getNaam() 
    { 
	return naam; 
    }

    public double getX()
    {
        return x;
    }
 
    public double getY()
    {
        return y;
    }
}
