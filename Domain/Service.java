package Domain;

import Logic.Repo;
import Logic.RepoFile;

import java.util.Arrays;

import java.util.Scanner;

public class Service {
    private Repo repo = new Repo();

    public Service() {
        RepoFile rep = new RepoFile();
        this.repo.setDep(rep.getDepozit());
        this.repo.setMat(rep.getMateriale());
        this.repo.setFur(rep.getFurnizor());
        this.repo.setBen(rep.getBeneficiari());
    } // Costructorul ce salveaza datele citite din fisier cu ajutorul lui RepoFile

    public void save_dataBase() {
        RepoFile rep = new RepoFile(repo.getMateriale(), repo.getDepozit(), repo.getFurnizori(), repo.getBeneficiari());
        rep.writeFile();
    } // Declararea unui RepoFile cu valorile din Repo si scrierea acestora in fisier

    public void add_material() {
        // Se citesc de la tastatura datele necesare pentru un nou material si se adauga la listele de obiecte
        // din repo.

        Scanner sc = new Scanner(System.in);
        System.out.println("Adaugati datele pentru un nou material: ");
        System.out.print("Cod : ");
        long cod = Long.parseLong(sc.nextLine());
        System.out.print("Nume : ");
        String nume = sc.nextLine();
        System.out.print("Cantitate : ");
        int cantitate = Integer.parseInt(sc.nextLine());
        System.out.print("Pret : ");
        double pret = Double.parseDouble(sc.nextLine());
        System.out.print("Furnizor : ");
        String furnizor = sc.nextLine();
        System.out.print("Lista de beneficiari : ");
        String st = sc.nextLine();
        String[] beneficiari = st.split(", ");
        repo.add(cod, nume, cantitate, pret, furnizor, beneficiari);
        System.out.println("S-a adaugat un materrialul!");
    }

    public void del_material() {
        // Citirea unui cod de la tastatura si stergerea acestuia

        Scanner sc = new Scanner(System.in);
        System.out.println("Codul materialului pe care doriti sa il stergeti : ");
        long cod = Long.parseLong(sc.nextLine());
        repo.del(cod);
        System.out.println("S-a sters un material!");
    }

    public void update_material() {
        // Se citeste codul materialului ce se va modifica si se modifica doar atributul
        // dorit prin citirea unui String de la tastatura si prelucrarea acestuia cu un switch.

        Scanner sc = new Scanner(System.in);
        System.out.print("Codul materialului pe care doriti la il modificati : ");
        long cod = Long.parseLong(sc.nextLine());
        int i = 0;
        while (i < repo.getLength_material() && cod != repo.getCod_depozit(i))
            i++;

        if (i == repo.getLength_material())
            System.out.println("Nu s-a gasit material cu acest cod.");
        else {
            System.out.print("Ce doriti sa modificati la acest material : ");
            String st = sc.nextLine();
            switch (st) {
                case "cod" -> {                       // pentru un cod nou se modifica doar codul la apel
                    System.out.print("Noul cod : ");
                    long cod_nou = Long.parseLong(sc.nextLine());
                    repo.update(cod, cod_nou, repo.getNume_material(i), repo.getCantitate(i), repo.getPret(i),
                            repo.getCod_furnizor(i), repo.getCod_beneficiari(i));
                    System.out.println("Modificarea sa efectuat.");
                }
                case "nume" -> {                     // pentru nume se citeste o noua valoare si se modifica doar el
                    System.out.print("Noul nume : ");
                    String nume = sc.nextLine();
                    repo.update(cod, cod, nume, repo.getCantitate(i), repo.getPret(i),
                            repo.getCod_furnizor(i), repo.getCod_beneficiari(i));
                    System.out.println("Modificarea sa efectuat.");
                }
                case "cantitate" -> {               // pentru cantitate se citeste o valoare noua
                    System.out.print("Noua cantitate : ");
                    int cantitate = Integer.parseInt(sc.nextLine());
                    repo.update(cod, cod, repo.getNume_material(i), cantitate, repo.getPret(i),
                            repo.getCod_furnizor(i), repo.getCod_beneficiari(i));
                    System.out.println("Modificarea sa efectuat.");
                }
                case "pret" -> {                   // citirea unui pret nou si doar el se se schimba la apelare
                    System.out.print("Noul pret : ");
                    double pret = Double.parseDouble(sc.nextLine());
                    repo.update(cod, cod, repo.getNume_material(i), repo.getCantitate(i), pret,
                            repo.getCod_furnizor(i), repo.getCod_beneficiari(i));
                    System.out.println("Modificarea sa efectuat.");
                }
                case "furnizor" -> {                     // citirea unui furnizor nou si doar el este nou in linia
                    System.out.print("Noul furnizor : ");                  // de apelare
                    long furnizor = Long.parseLong(sc.nextLine());
                    repo.update(cod, cod, repo.getNume_material(i), repo.getCantitate(i), repo.getPret(i),
                            furnizor, repo.getCod_beneficiari(i));
                    System.out.println("Modificarea sa efectuat.");
                }
                case "beneficiari" -> {              // se citeste o lista de nume de beneficiri si doar aceasta lista
                    System.out.print("Noua lista de beneficiari : ");    // este noua in linia de apel
                    String[] rupt = sc.nextLine().split(", ");
                    long[] beneficiari = new long[rupt.length];
                    for (int k = 0; k < rupt.length; k++)
                        beneficiari[k] = Long.parseLong(rupt[k]);
                    repo.update(cod, cod, repo.getNume_material(i), repo.getCantitate(i), repo.getPret(i),
                            repo.getCod_furnizor(i), beneficiari);
                    System.out.println("Modificarea sa efectuat.");
                }
                default -> System.out.println("Nu avem acest atribut la materiale!");
            }
        }
    }

    private void top_tabel() { // Capul de tabel standard pentru afisare
        System.out.format("+------------+------------------------------------------+-----------+-----------+-" +
                "---------------------+-------" +
                "-----------------------------------+%n");
        System.out.format("|    COD     |                Denumire                  | Cantitate |    Pret   | " +
                "       Furnizor      |       " +
                "        Beneficiari                |%n");
        System.out.format("+------------+------------------------------------------+-----------+-----------+-" +
                "---------------------+-------" +
                "-----------------------------------+%n");
    }

    // Formatul pentru tabelele de afisare, standard
    private String align_format() {
        return "| %10d | %-40s | %9d | %9.2f | %-20s | %-40s |%n";
    }

    private void between() { // separatorul de linii din tabel
        System.out.format("+------------+------------------------------------------+-" +
                "----------+-----------+----------------" +
                "------+------------------------------------------+%n");
    }

    private void display_mat(int i, int j) { // afisarea unui material in tabel
        String leftAlignFormat = align_format();

        long[] coduri_ben = repo.getCod_beneficiari(i);
        String[] aux = new String[coduri_ben.length];
        for (int index = 0; index < coduri_ben.length; index++)      // copierea listei de beneficiari de la o
            aux[index] = repo.getNume_beneficiar(coduri_ben[index]); // pozitie data

        long cod_furnizor = repo.getCod_furnizor(j);                 // copierea furnizorului de la o pozitie data
        String nume_furnizor = repo.getNume_furnizor(cod_furnizor);

        if (aux.length <= 2) {           // daca sunt maxim doi beneficiari atunci materialul va avea nevoie doar
            String st;                   // de un singur rand in tabel
            if (aux.length == 1)
                st = aux[0];
            else
                st = aux[0] + ", " + aux[1];
            System.out.format(leftAlignFormat, repo.getCod_depozit(j), repo.getNume_material(j),
                    repo.getCantitate(i), repo.getPret(i), nume_furnizor, st);
        }
        else {                       // daca sunt mai mult de 2 beneficiari atunci se vor afisa cate doi beneficiari
            int k = 0;                         // pe cate o linie
            while (k < aux.length-1) {
                String st = aux[k] + ", " + aux[k+1];
                if (k  == 0) {
                    System.out.format(leftAlignFormat, repo.getCod_depozit(j), repo.getNume_material(j),
                            repo.getCantitate(i), repo.getPret(i), nume_furnizor, st);
                }
                else {
                    String AlignFormat = "| %-10s | %-40s | %-9s | %-9s | %-20s | %-40s |%n";
                    System.out.format(AlignFormat, " ", " ", " ", " ", " ", st);
                }
                k = k+2;
            }
            if (k == aux.length-1) {     // in caza de numar impar de beneficiari se afiseaza ultimul
                String st = aux[k];                // pe o linie noua
                String AlignFormat = "| %-10s | %-40s | %-9s | %-9s | %-20s | %-40s |%n";
                System.out.format(AlignFormat, " ", " ", " ", " ", " ", st);
            }
        }
    }

    public void inventar_complet() {                 // afisarea tuturor materialelor salvate
        int len_dep = repo.getLength_depozit();
        int len_mat = repo.getLength_material();

        top_tabel();

        for (int i = 0; i < len_dep; i++) {
            long cod = repo.getCod_depozit(i);
            int j = 0;

            while (j < len_mat && repo.getCod_material(j) != cod)
                j++;

            if (j != len_mat) {
                display_mat(i, j);
                between();
            }
            else
                System.out.println("Nepotrivire de cod.");
        }
    }

    public void materiale_cmin() {                           // afisarea materialelor ce au o cantitate maxima
        Scanner sc = new Scanner(System.in);
        System.out.print("Cantitatea maxima va fi de : ");
        int cmin = Integer.parseInt(sc.nextLine());

        int len_rep = repo.getLength_depozit();
        int len_mat = repo.getLength_material();

        top_tabel();

        for (int i = 0; i < len_rep; i++) {
            long cod = repo.getCod_depozit(i);
            int j = 0;
            while (j < len_mat && repo.getCod_material(j) != cod)
                j++;

            if (j != len_mat) {
                if (repo.getCantitate(i) <= cmin) {
                    display_mat(i, j);
                    between();
                }
            }
            else
                System.out.println("Nepotrivire de cod.");
        }
    }

    public void materiale_epuizate() {                // afisarea materialelor cu cantitatea egala cu 0
        int len_rep = repo.getLength_depozit();
        int len_mat = repo.getLength_material();

        top_tabel();

        for (int i = 0; i < len_rep; i++) {
            long cod = repo.getCod_depozit(i);
            int j = 0;
            while (j < len_mat && repo.getCod_material(j) != cod)
                j++;

            if (j != len_mat) {
                if (repo.getCantitate(i) == 0) {
                    display_mat(i, j);
                    between();
                }
            }
            else
                System.out.println("Nepotrivire de cod.");
        }
    }

    public void materiale_scumpe() {                   // afisarea materialelor ce au pretul mai mare ca 150
        int len_rep = repo.getLength_depozit();
        int len_mat = repo.getLength_material();

        top_tabel();

        for (int i = 0; i < len_rep; i++) {
            long cod = repo.getCod_depozit(i);
            int j = 0;
            while (j < len_mat && repo.getCod_material(j) != cod)
                j++;

            if (j != len_mat) {
                if (repo.getPret(j) >= 150.00) {           // verificarea pretului
                    display_mat(i, j);
                    between();
                }
            }
            else
                System.out.println("Nepotrivire de cod.");
        }
    }

    public void furnizori_materiale() {                    // se ia lista de furnizori existenta in fisier
        long[] coduri = repo.getCodurile_furnizoriilor();   // se parcurg pe rand materialele existente
                                                            // si pentru fiecare furnizor se afiseaza pe o linie
                                                            // denumirea materialelor si codurile lor
        int len_mat = repo.getLength_material();

        String LeftAlign = "| %-20s | %-40s | %10d |%n";  // formatul unei linii din tabel

        System.out.format("+----------------------+------------------------------------------+------------+%n");
        System.out.format("|       Furnizor       |                  Denumire                |     COD    |%n");
        System.out.format("+----------------------+------------------------------------------+------------+%n");
                                                                    // capul de tabel
        for (int i = 0; i < coduri.length; i++){
            int count = 0;
            for (int j = 0; j < len_mat; j++) {
                if (coduri[i] == repo.getCod_furnizor(j)) {
                    if (count == 0) {
                        System.out.format(LeftAlign, repo.getNume_furnizor(coduri[i]), repo.getNume_material(j),
                                repo.getCod_material(j));
                        count = 1;
                    }
                    else
                        System.out.format(LeftAlign, "", repo.getNume_material(j), repo.getCod_material(j));
                }
            }
            System.out.format("+----------------------+------------------------------------------+------------+%n");
        }
    }

    public void beneficiari_materiale() {                          // se parcurge lista de beneficiari cum este in
        long[] beneficiari = repo.getCodurile_beneficiarilor();   // fisier si pentru fiecare beneficiar se cauta in
                                                                // lista de beneficiari al fiecarui material codul
                                                                // aferent si se afiseaza pe cate o linie de tabel
        int len_mat = repo.getLength_material();
        int len_dep = repo.getLength_depozit();

        String LeftAlign = "| %-25s | %-40s | %10d |%n";    // formatul unei linii din tabel

        System.out.format("+---------------------------+------------------------------------------+------------+%n");
        System.out.format("|       Beneficiari         |                 Denumire                 |     COD    |%n");
        System.out.format("+---------------------------+------------------------------------------+------------+%n");
                                                                // capul de tabel

        for (int k = 0; k < beneficiari.length; k++){
            int count = 0;
            for (int i = 0; i < len_dep; i++) {                   // extragerea de informatii necesare pentru o
                long cod = repo.getCod_depozit(i);               // linie de tabel
                int j = 0;
                while (j < len_mat && cod != repo.getCod_material(j))
                    j++;

                int x = 0;
                while (x < repo.getCod_beneficiari(i).length && beneficiari[k] != repo.getCod_beneficiari(i)[x])
                    x++;

                if (x != repo.getCod_beneficiari(i).length) {
                    if (count == 0) {
                        System.out.format(LeftAlign, repo.getNume_beneficiar(beneficiari[k]), repo.getNume_material(j),
                                cod);
                        count = 1;
                    }
                    else System.out.format(LeftAlign, "", repo.getNume_material(j), cod);
                }
            }
            System.out.format("+---------------------------+------------------------------------------+-" +
                    "-----------+%n");    // separatorul de linie in tabel
        }
    }
}