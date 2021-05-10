package Logic;

public class Furnizor {
    private long cod;
    private String nume_furnizor;

    Furnizor() {

    } // Constructorul default

    Furnizor(long cod_material, String nume) {
        this.cod = cod_material;
        this.nume_furnizor = nume;
    } // Constructor cu parametrii

    // Functiile get

    public long getCod_furnizor() {
        return this.cod;
    }

    public String getNume_furnizor() {
        return this.nume_furnizor;
    }

    // Functiile set

    public void setCod_furnizor(long cod_material) {
        this.cod = cod_material;
    }

    public void setNume_furnizor(String nume_furnizor) {
        this.nume_furnizor = nume_furnizor;
    }
}
