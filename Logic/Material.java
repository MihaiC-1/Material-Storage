package Logic;

public class Material {
    private long cod_material;
    private String nume_material;
    private long cod_furnizor;

    Material() {

    } // Constructorul default

    Material(long cod, String nume, long cod_furnizor) {
        this.cod_material = cod;
        this.nume_material = nume;
        this.cod_furnizor = cod_furnizor;
    }  // Constructorul cu parametrii

    public void update(Material mat) {             // Modificarea unui obiect material cu noile valori
        setCod_material(mat.getCod_material());
        setNume_material(mat.getNume_material());
        setFurnizor(mat.getFurnizor());
    }

    // Functiile get necesare

    public String getNume_material() {
        return this.nume_material;
    }

    public long getCod_material() {
        return this.cod_material;
    }

    public long getFurnizor() {
        return this.cod_furnizor;
    }

    // Functiile set necesare

    private void setCod_material(long cod) {
        this.cod_material = cod;
    }

    private void setNume_material(String nume) {
        this.nume_material = nume;
    }

    private void setFurnizor(long cod_fur) {
        this.cod_furnizor = cod_fur;
    }
}
