package OVchipkaart;

public class Card
{
    private double saldo;
    private int cardId;

    // Maakt een nieuwe OV-chipkaart aan met een kaart-ID en beginsaldo
    public Card(int cardId, double beginSaldo)
    {
        this.cardId = cardId;
        this.saldo = beginSaldo;
    }

    // Trekt het instaptarief af van het saldo bij het inchecken
    public void inchecken(double instapTarief)
    {
        if (saldo >= instapTarief)
        {
            saldo -= instapTarief;
            System.out.printf("Ingecheckt. Saldo na instaptarief: EUR %.2f%n", saldo);
        }
        else
        {
            System.out.println("Onvoldoende saldo om in te checken!");
        }
    }

    // Bevestigt dat de kaart heeft ingecheckt
    public void checkIn()
    {
        System.out.println("Kaart " + cardId + " heeft ingecheckt.");
    }

    // Bevestigt dat de kaart heeft uitgecheckt
    public void checkOut()
    {
        System.out.println("Kaart " + cardId + " heeft uitgecheckt.");
    }

    // Geeft het huidige saldo terug
    public double getSaldo()
    {
        return saldo;
    }

    // Stelt een nieuw saldo in — nooit lager dan 0
    public void setSaldo(double saldo)
    {
        this.saldo = Math.max(0, saldo);
    }

    // Geeft het kaart-ID terug
    public int getCardId()
    {
        return cardId;
    }
}