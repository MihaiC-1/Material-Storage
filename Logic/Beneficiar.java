package Logic;

public class Beneficiar {
    private long cod;
    private String nume_beneficiar;

    Beneficiar() {

    } // Constructorul default

    Beneficiar(long cod, String nume) {
        this.cod = cod;
        this.nume_beneficiar = nume;
    } // Constructorul cu parametrii

    // Functiile get

    public long getCod_beneficiar() {
        return this.cod;
    }

    public String getNume_beneficiar() {
        return this.nume_beneficiar;
    }

    // Functiile set

    public void setCod_beneficiar(long cod_material) {
        this.cod = cod_material;
    }

    public void setNume_beneficiar(String nume_beneficiar) {
        this.nume_beneficiar = nume_beneficiar;
    }
}
