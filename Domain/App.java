package Domain;

import java.util.Scanner;

import UserInterface.Console;

public class App {

    public static int citire_optiune()            // citirea unui numar intreg de la tastatura
    {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }    // functia pentru citirea unei comenzi de la tastatura

    public static void main(String[] args) {      // crearea de obiecte necesare si prelucrarea comenzilor
        Service serv = new Service();           // pentru meniul citit
        Console con = new Console();
        int op;
        boolean ok = true;

        while (ok) {                      // terminarea programului doar cu comanda Exit din meniu
            con.Meniu();
            op = citire_optiune();

            switch (op) {
                case 1 -> serv.add_material();
                case 2 -> serv.del_material();
                case 3 -> serv.update_material();
                case 4 -> serv.inventar_complet();
                case 5 -> serv.materiale_cmin();
                case 6 -> serv.materiale_epuizate();
                case 7 -> serv.materiale_scumpe();
                case 8 -> serv.furnizori_materiale();
                case 9 -> serv.beneficiari_materiale();
                case 10 -> {
                    ok = false;
                    serv.save_dataBase();
                    System.out.println("La revedere!!");
                }
                default -> System.out.println("Nu exista optiune!!");
            }
        }
    }
}
