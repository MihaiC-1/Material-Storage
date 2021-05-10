package Logic;
import java.io.*;

public class RepoFile {
    public Material[] materiale = new Material[0];
    public Depozit[] depozit = new Depozit[0];
    public Furnizor[] furnizor = new Furnizor[0];
    public Beneficiar[] beneficiari = new Beneficiar[0];

    public RepoFile(){
        readFile();
    } // Constructorul in care se apeleaza citirea din fisier

    public RepoFile(Material[] mat, Depozit[] dep, Furnizor[] fur, Beneficiar[] bene) {
        this.materiale = mat;
        this.depozit = dep;
        this.furnizor = fur;
        this.beneficiari = bene;
    } // Constructorul cu parametrii

    // Adaugarea unui material la lista de materiale

    private void add_material(Material mat){
        Material[] nw = new Material[this.materiale.length + 1];

        for (int i = 0; i < this.materiale.length; i++)
            nw[i] = this.materiale[i];
        nw[nw.length-1] = mat;
        this.materiale = nw;
    }

    // Adaugarea unui obiect depozit la lista de depozit

    private void add_depozit(Depozit dep) {
        Depozit[] nw = new Depozit[this.depozit.length + 1];

        for (int i = 0; i < this.depozit.length; i++)
            nw[i] = this.depozit[i];
        nw[nw.length-1] = dep;
        this.depozit = nw;
    }

    // Adaugarea unui furnizor la lista de furnizori

    private void add_furnizor(Furnizor fur) {
        Furnizor[] nw = new Furnizor[this.furnizor.length+1];

        for (int i = 0; i < this.furnizor.length; i ++)
            nw[i] = this.furnizor[i];
        nw[nw.length-1] = fur;
        this.furnizor = nw;
    }

    // Adaugarea unui beneficiar la lista de beneficiari

    private void add_beneficiar(Beneficiar ben) {
        Beneficiar[] nw = new Beneficiar[this.beneficiari.length+1];

        for (int i = 0; i < this.beneficiari.length; i++)
            nw[i] = this.beneficiari[i];
        nw[nw.length-1] = ben;
        this.beneficiari = nw;
    }

    // Se citesc pe rand din cele patru fisiere existente si folosind cele 4 functii de adaugare de mai sus
    // se salveaza pe rand informatiile din fisier

    private void readFile(){
                                                                        // gasirea fisierului dupa path
        try { BufferedReader fisIn = new BufferedReader(new FileReader("src/DataBase/Materiale"));
            String st;           // string null

            while ((st = fisIn.readLine()) != null)      // citeste linie cu linie pana la valoarea null
            {                                                     // adica nu mai sunt valori in fisier
                String[] rupt = st.split(",");     // split dupa "|",
                long cod = Long.parseLong(rupt[0]);
                String nume = rupt[1];
                long furnizor = Long.parseLong(rupt[2]);
                add_material(new Material(cod, nume, furnizor));
            }
            fisIn.close();        // se inchide fisierul

            fisIn = new BufferedReader(new FileReader("src/DataBase/Depozit"));
            String dep;

            while((dep = fisIn.readLine()) != null)
            {
                String[] rupt = dep.split(",");
                long cod = Long.parseLong(rupt[0]);
                int cantitate = Integer.parseInt(rupt[1]);
                float pret = Float.parseFloat(rupt[2]);
                String bene = rupt[3];
                String[] benefi = bene.split("&");
                long[] beneficiari = new long[benefi.length];
                for (int k = 0; k < benefi.length; k++)
                    beneficiari[k] = Long.parseLong(benefi[k]);
                add_depozit(new Depozit(cod, cantitate, pret, beneficiari));
            }
            fisIn.close();

            fisIn = new BufferedReader(new FileReader("src/DataBase/Furnizori"));
            String fur;

            while((fur = fisIn.readLine()) != null)
            {
                String[] rupt = fur.split(",");
                long cod = Long.parseLong(rupt[0]);
                String nume = rupt[1];
                add_furnizor(new Furnizor(cod, nume));
            }
            fisIn.close();

            fisIn = new BufferedReader(new FileReader("src/DataBase/Beneficiari"));
            String ben;

            while ((ben = fisIn.readLine()) != null)
            {
                String[] rupt = ben.split(",");

            }
            fisIn.close();
        }
        catch (Exception e)
        {
            System.out.println("Oops! S-a gresit la citirea din fisier!");
            e.printStackTrace();
        }
    }

    // La scrierea in fisier se parcurg pe rand cele 4 liste si se scriu pe cate un rand
    // obiectele in fisierele aferente

    public void writeFile() {
        try {
            PrintStream fileOut = new PrintStream(new FileOutputStream("src/DataBase/Materiale"));

            for (int i = 0; i < this.materiale.length; i++) {
                String cod = String.valueOf(this.materiale[i].getCod_material()) + ",";
                String nume = this.materiale[i].getNume_material() + ",";
                String furnizor = String.valueOf(this.materiale[i].getFurnizor());
                fileOut.print(cod);
                fileOut.print(nume);
                fileOut.print(furnizor);
                fileOut.println();
            }
            fileOut.close();

            fileOut = new PrintStream(new FileOutputStream("src/DataBase/Depozit"));

            for (int i = 0; i < this.depozit.length; i++) {
                String cod = String.valueOf(this.depozit[i].getCod_material()) + ",";
                String cantitate = String.valueOf(this.depozit[i].getCantitate()) + ",";
                String pret = String.valueOf(this.depozit[i].getPretUnitar()) + ",";
                long[] benefi = this.depozit[i].getBeneficiari();
                String[] beneficiari = new String[benefi.length];
                for (int k = 0; k < benefi.length; k++)
                    beneficiari[k] = String.valueOf(benefi[k]);
                fileOut.print(cod);
                fileOut.print(cantitate);
                fileOut.print(pret);
                for (int j = 0; j < beneficiari.length; j++)
                    if (j != beneficiari.length - 1)
                        fileOut.print(beneficiari[j]+"&");
                    else
                        fileOut.print(beneficiari[j]);
                fileOut.println();
            }
            fileOut.close();

            fileOut = new PrintStream(new FileOutputStream("src/DataBase/Furnizori"));

            for (int i = 0; i < this.furnizor.length; i++) {
                String cod = String.valueOf(this.furnizor[i].getCod_furnizor()) + ",";
                String nume = this.furnizor[i].getNume_furnizor();
                fileOut.print(cod);
                fileOut.print(nume);
                fileOut.println();
            }

            fileOut = new PrintStream(new FileOutputStream("src/DataBase/Beneficiari"));

            for (int i = 0; i < this.beneficiari.length; i++) {
                String cod = String.valueOf(this.beneficiari[i].getCod_beneficiar()) + ",";
                String nume = this.beneficiari[i].getNume_beneficiar();
                fileOut.print(cod);
                fileOut.print(nume);
                fileOut.println();
            }
        } catch (Exception e) {
            System.out.println("Oops! S-a gresit la scrierea in fisier!");
            e.printStackTrace();
        }
    }

    // Functiile de get necesare

    public Material[] getMateriale() {
        return this.materiale;
    }

    public Depozit[] getDepozit() {
        return this.depozit;
    }

    public Furnizor[] getFurnizor() {
        return this.furnizor;
    }

    public Beneficiar[] getBeneficiari() {
        return this.beneficiari;
    }
}
