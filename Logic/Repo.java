package Logic;

import java.util.Scanner;

public class Repo {
    private Depozit[] dep = new Depozit[0];
    private Material[] mat = new Material[0];
    private Furnizor[] fur = new Furnizor[0];
    private Beneficiar[] ben = new Beneficiar[0];

    public Repo() {

    } // Constructorul default

    public Repo(Depozit[] dep, Material[] mat, Furnizor[] fur, Beneficiar[] ben) {
        this.dep = dep;
        this.mat = mat;
        this.fur = fur;
        this.ben = ben;
    } // Constructorul cu parametru

    public void add(long cod, String nume, int cantitate, double pret, String nume_furnizor, String[] nume_beneficiari)
    {  // Adaugarea unor noi valori in toate clasele
        Depozit[] dep_new = new Depozit[this.dep.length+1];
        Material[] mat_new = new Material[this.mat.length+1];
        boolean ok = false;
        long cod_furnizor = 0;
        long[] cod_beneficiari = new long[nume_beneficiari.length];

        // Se parcurge lista de beneficiari al noului material si se verifica daca sunt beneficiari noi,
        // daca sunt beneficiari noi se atribuie un cod nou si se adauga

        for (int j = 0; j < nume_beneficiari.length; j++) {
            int i = 0;
            for (i = 0; i < this.ben.length; i++) {
                if (nume_beneficiari[j].equals(this.ben[i].getNume_beneficiar())) {
                    ok = true;
                    break;
                }
            }
            if (!(ok)) {
                System.out.print("Beneficiarul "+nume_beneficiari[j]+" este nou. Atribuitii un cod : ");
                Scanner scn = new Scanner(System.in);
                long cod_beneficiar = scn.nextLong();
                add_beneficiar(cod_beneficiar, nume_beneficiari[j]);
                cod_beneficiari[j] = cod_beneficiar;
            }
            else {
                cod_beneficiari[j] = this.ben[i].getCod_beneficiar();
            }
            ok = false;
        }

        // Se verifica daca furnizorul noului material exista, daca nu exista se atribuie un cod nou si se
        // adauga listei de furnizori existenti

        ok = true;

        for (int i = 0; i < this.fur.length; i++) {
            if (nume_furnizor.equals(this.fur[i].getNume_furnizor()))
                ok = false;
        }
        if (ok){
            System.out.print("Furnizorul "+nume_furnizor+" este nou. Atribuitii un cod : ");
            Scanner scn = new Scanner(System.in);
            cod_furnizor = scn.nextLong();
            add_furnizor(cod_furnizor, nume_furnizor);
        }

        // Se copiaza materialele si atributiile existente si se adauga la final noul material

        for (int i = 0; i < this.mat.length; i++) {
            mat_new[i] = this.mat[i];
            dep_new[i] = this.dep[i];
        }

        mat_new[mat_new.length-1] = new Material(cod, nume, cod_furnizor);
        dep_new[dep_new.length-1] = new Depozit(cod, cantitate, pret, cod_beneficiari);
        this.dep = dep_new;
        this.mat = mat_new;
    }

    public void del(long cod) {
        // Se cauta codul in lista din depozit, daca nu exista se afiseaza un mesaj, daca exista se sterge
        // din lista din depozit si din lista de materiale.
        // Beneficiari si furnizorii raman, deoarece de ei poate mai este nevoie la alte materiale.

        Depozit[] dep_new = new Depozit[this.dep.length-1];
        Material[] mat_new = new Material[this.mat.length-1];
        int i = 0, j = 0;

        while (i < this.dep.length) {
            if (this.dep[i].getCod_material() != cod) {
                dep_new[j] = this.dep[i];
                i++;
                j++;
            }
            else i++;
        }
        i = 0;
        j = 0;
        while (i < this.mat.length) {
            if (this.mat[i].getCod_material() != cod) {
                mat_new[j] = this.mat[i];
                i++;
                j++;
            }
            else i++;
        }

        this.dep = dep_new;
        this.mat = mat_new;
    }

    public void update(long cod, long cod_nou, String nume, int cantitate, double pret, long furnizor,
                       long[] beneficiari) {
        // Se cauta codul dat si daca se gaseste se modifica acel material si depozit cu noile valori
        // daca nu se afiseaza un mesaj.

        int i = 0;
        while (this.mat[i].getCod_material() != cod)
            i++;
        this.mat[i].update(new Material(cod_nou, nume, furnizor));

        int j = 0;
        while (this.dep[j].getCod_material() != cod)
            j++;
        this.dep[j].update(new Depozit(cod_nou, cantitate, pret, beneficiari));
    }

    private void add_beneficiar(long cod, String bene) {
        // Se copiaza lista de beneficiari si in coada ei se adauga un nou beneficiar
        // dupa care se modifica lista veche cu cea noua.

        Beneficiar[] ben_new = new Beneficiar[this.ben.length+1];

        for (int i = 0; i < this.ben.length; i++)
            ben_new[i] = this.ben[i];

        ben_new[ben_new.length-1] = new Beneficiar(cod, bene);
        this.ben = ben_new;
    }

    private void add_furnizor(long cod, String fur) {
        // Se copiaza lista de furnizori si in coada ei se adauga un furnizor nou, dupa care
        // se salveaza noua lista in loc de cea originala.

        Furnizor[] fur_new = new Furnizor[this.fur.length+1];

        for (int i = 0; i < this.fur.length; i++)
            fur_new[i] = this.fur[i];

        fur_new[fur_new.length-1] = new Furnizor(cod, fur);
        this.fur = fur_new;
    }

    public void setDep(Depozit[] dep) {
        this.dep = dep;
    }

    public void setMat(Material[] mat) {
        this.mat = mat;
    }

    // Functiile de set necesare

    public void setFur(Furnizor[] fur) { this.fur = fur; }

    public void setBen(Beneficiar[] ben) { this.ben = ben; }

    // Functiile de get necesare

    public int getLength_material() {
        return this.mat.length;
    }

    public int getLength_depozit() {
        return this.dep.length;
    }

    public int getLength_furnizori() { return this.fur.length; }

    public int getLength_beneficiari() { return this.ben.length; }

    public long getCod_depozit(int index) {
        return this.dep[index].getCod_material();
    }

    public long getCod_material(int index) {
        return this.mat[index].getCod_material();
    }

    public String getNume_material(int index) {
        return this.mat[index].getNume_material();
    }

    public int getCantitate(int index) {
        return this.dep[index].getCantitate();
    }

    public double getPret(int index) {
        return this.dep[index].getPretUnitar();
    }

    public long getCod_furnizor(int index) {
        return this.mat[index].getFurnizor();
    }

    public long[] getCod_beneficiari(int index) {
        return this.dep[index].getBeneficiari();
    }

    public String getNume_furnizor(long cod) {
        for (int i = 0; i < this.fur.length; i ++)
            if (cod == this.fur[i].getCod_furnizor())
                return this.fur[i].getNume_furnizor();
        return "";
    }

    public String getNume_beneficiar(long cod) {
        for (int i = 0; i < this.ben.length; i ++)
            if (cod == this.ben[i].getCod_beneficiar())
                return this.ben[i].getNume_beneficiar();

        return "Nu exista";
    }

    public long[] getCodurile_furnizoriilor() {
        long[] nou = new long[getLength_furnizori()];
        for (int i = 0; i < getLength_furnizori(); i++)
            nou[i] = this.fur[i].getCod_furnizor();
        return nou;
    }

    public long[] getCodurile_beneficiarilor() {
        long[] nou = new long[getLength_beneficiari()];
        for (int i = 0; i < getLength_beneficiari(); i++)
            nou[i] = this.ben[i].getCod_beneficiar();
        return nou;
    }

    public Material[] getMateriale() {
        return this.mat;
    }

    public Depozit[] getDepozit() {
        return this.dep;
    }

    public Furnizor[] getFurnizori() { return this.fur; }

    public Beneficiar[] getBeneficiari() { return this.ben; }
}
