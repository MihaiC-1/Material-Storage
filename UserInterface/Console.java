package UserInterface;

public class Console {
    public Console() {

    } // Constructorul default

    public void Meniu() {
        System.out.println("Alegeti una din optiunile de mai jos : ");
        System.out.println("\t 1. Adaugare material in depozit.");
        System.out.println("\t 2. Stergere material din depozit.");
        System.out.println("\t 3. Modificare material din depozit.");
        System.out.println("\t 4. Inventar complet.");
        System.out.println("\t 5. Materiale cu cantitate cel mult minim citit.");
        System.out.println("\t 6. Materiale epuizate.");
        System.out.println("\t 7. Materiale foarte scumpe.");
        System.out.println("\t 8. Furnizori si materialele furnizate.");
        System.out.println("\t 9. Beneficiarii si materialele cumparate.");
        System.out.println("\t 10. Exit.");
        System.out.print("Optiunea? : ");
    } // Afisarea meniului de optiuni necesare.
}
