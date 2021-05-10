package Logic;

public class Depozit {
    private long cod_material;
    private int cantitate;
    private double pretUnitar;
    private long[] cod_beneficiari;

    Depozit () {

    } // Constructorul default

    Depozit (long cod_material, int cantitate, double pretUnitar, long[] beneficiari) {
        this.cod_material = cod_material;
        this.cantitate = cantitate;
        this.pretUnitar = pretUnitar;
        this.cod_beneficiari = beneficiari;
    } // Constructorul cu parametrii

    public void addBeneficiar(long beneficiar) {
        long[] nou = new long[this.cod_beneficiari.length+1];
        System.arraycopy(this.cod_beneficiari, 0, nou, 0, this.cod_beneficiari.length);
        nou[nou.length-1] = beneficiar;
        this.cod_beneficiari = nou;
    }

    public void update(Depozit dep) {               // Modificarea unei valori depozit cu noile valori
        setCod_material(dep.getCod_material());
        setCantitate(dep.getCantitate());
        setPretUnitar(dep.getPretUnitar());
        setBeneficiari(dep.getBeneficiari());
    }

    // Functiile get

    public long getCod_material() {
        return this.cod_material;
    }

    public int getCantitate() {
        return this.cantitate;
    }

    public double getPretUnitar() {
        return this.pretUnitar;
    }

    public long[] getBeneficiari() {
        return this.cod_beneficiari;
    }

    // Functiile set

    public void setCod_material(long cod) {
        this.cod_material = cod;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public void setPretUnitar(double pretUnitar) {
        this.pretUnitar = pretUnitar;
    }

    public void setBeneficiari(long[] beneficiari) {
        this.cod_beneficiari = beneficiari;
    }
}
