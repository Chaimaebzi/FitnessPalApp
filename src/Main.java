import java.util.*;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FitnessPalAppTest {

    @Test
    public void addMaaltijd_shouldAddMaaltijdToMaaltijdenList() {
        fitnessPalApp app = new fitnessPalApp();
        Maaltijd maaltijd = new Maaltijd("Pannenkoeken", Arrays.asList(new Voedingsmiddel("Eieren", 150), new Voedingsmiddel("Melk", 100)));
        app.addMaaltijd(maaltijd);

        List<Maaltijd> expectedMaaltijden = new ArrayList<>();
        expectedMaaltijden.add(maaltijd);

        assertEquals(expectedMaaltijden, app.getMaaltijden());
    }

    @Test
    public void addTrainingsactiviteit_shouldAddTrainingsactiviteitToTrainingsactiviteitenList() {
        fitnessPalApp app = new fitnessPalApp();
        Trainingsactiviteit trainingsactiviteit = new Trainingsactiviteit("Fietsen", 300);
        app.addTrainingsactiviteit(trainingsactiviteit);

        List<Trainingsactiviteit> expectedTrainingsactiviteiten = new ArrayList<>();
        expectedTrainingsactiviteiten.add(trainingsactiviteit);

        assertEquals(expectedTrainingsactiviteiten, app.getTrainingsactiviteiten());
    }

    @Test
    public void berekenTotaalCalorieenInname_shouldReturnCorrectValue() {
        fitnessPalApp app = new fitnessPalApp();
        Maaltijd maaltijd1 = new Maaltijd("Pannenkoeken", Arrays.asList(new Voedingsmiddel("Eieren", 150), new Voedingsmiddel("Melk", 100)));
        Maaltijd maaltijd2 = new Maaltijd("Broodje kaas", Arrays.asList(new Voedingsmiddel("Kaas", 200), new Voedingsmiddel("Brood", 100)));
        app.addMaaltijd(maaltijd1);
        app.addMaaltijd(maaltijd2);

        int expectedTotaalCalorieenInname = 550;

        assertEquals(expectedTotaalCalorieenInname, app.berekenTotaalCalorieenInname());
    }
}







class main {
    public static void main(String[] args) {
        fitnessPalApp app = new fitnessPalApp();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Wat wil je doen?");
            System.out.println("1. Maaltijd toevoegen");
            System.out.println("2. Trainingsactiviteit toevoegen");
            System.out.println("3. Totaal calorie-inname en -verbruik bekijken");
            System.out.println("4. Stoppen");

            int keuze = scanner.nextInt();
            scanner.nextLine();

            switch (keuze) {
                case 1:
                    addMaaltijd(app, scanner);
                    break;
                case 2:
                    addTrainingsactiviteit(app, scanner);
                    break;
                case 3:
                    toonTotaalCalorieen(app);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ongeldige keuze, probeer opnieuw.");
            }
        }
    }

    public static void addMaaltijd(fitnessPalApp app, Scanner scanner) {
        System.out.println("Voer de naam van de maaltijd in:");
        String naam = scanner.nextLine();
        List<Voedingsmiddel> voedingsmiddelen = new ArrayList<>();
        while (true) {
            System.out.println("Voeg een voedingsmiddel toe (of typ 'stop' om te stoppen):");
            String voedingsmiddelNaam = scanner.nextLine();
            if (voedingsmiddelNaam.equals("stop")) {
                break;
            }
            System.out.println("Voer het aantal calorieën van " + voedingsmiddelNaam + " in:");
            int calorieen = scanner.nextInt();
            scanner.nextLine();
            Voedingsmiddel voedingsmiddel = new Voedingsmiddel(voedingsmiddelNaam, calorieen);
            voedingsmiddelen.add(voedingsmiddel);
        }
        Maaltijd maaltijd = new Maaltijd(naam, voedingsmiddelen);
        app.addMaaltijd(maaltijd);
    }

    public static void addTrainingsactiviteit(fitnessPalApp app, Scanner scanner) {
        System.out.println("Voer de naam van de trainingsactiviteit in:");
        String trainingsactiviteitNaam = scanner.nextLine();
        System.out.println("Voer het aantal calorieën van " + trainingsactiviteitNaam + " in:");
        int calorieen = scanner.nextInt();
        scanner.nextLine();
        Trainingsactiviteit trainingsactiviteit = new Trainingsactiviteit(trainingsactiviteitNaam, calorieen);
        app.addTrainingsactiviteit(trainingsactiviteit);
    }
    public static void toonTotaalCalorieen(fitnessPalApp app) {
        int totaalCalorieenInname = app.berekenTotaalCalorieenInname();
        int totaalCalorieenVerbruik = app.berekenTotaalCalorieenVerbruik();
        System.out.println("Totaal calorie-inname: " + totaalCalorieenInname);
        System.out.println("Totaal calorie-verbruik: " + totaalCalorieenVerbruik);
    }
}

class fitnessPalApp {
    private List<Maaltijd> maaltijden;
    private List<Trainingsactiviteit> trainingsactiviteiten;
    public fitnessPalApp() {
        maaltijden = new ArrayList<>();
        trainingsactiviteiten = new ArrayList<>();
    }

    public void addMaaltijd(Maaltijd maaltijd) {
        maaltijden.add(maaltijd);
    }

    public void addTrainingsactiviteit(Trainingsactiviteit trainingsactiviteit) {
        trainingsactiviteiten.add(trainingsactiviteit);
    }

    public int berekenTotaalCalorieenInname() {
        int totaalCalorieenInname = 0;
        for (Maaltijd maaltijd : maaltijden) {
            totaalCalorieenInname += maaltijd.berekenTotaalCalorieen();
        }
        return totaalCalorieenInname;
    }

    public int berekenTotaalCalorieenVerbruik() {
        int totaalCalorieenVerbruik = 0;
        for (Trainingsactiviteit trainingsactiviteit : trainingsactiviteiten) {
            totaalCalorieenVerbruik += trainingsactiviteit.getCalorieen();
        }
        return totaalCalorieenVerbruik;
    }

    public List<Object> getMaaltijden() {
        return null;
    }

    public Map<Object, Object> getTrainingsactiviteiten() {
        return null;
    }
}

class Maaltijd {
    private String naam;
    private List<Voedingsmiddel> voedingsmiddelen;
    public Maaltijd(String naam, List<Voedingsmiddel> voedingsmiddelen) {
        this.naam = naam;
        this.voedingsmiddelen = voedingsmiddelen;
    }

    public int berekenTotaalCalorieen() {
        int totaalCalorieen = 0;
        for (Voedingsmiddel voedingsmiddel : voedingsmiddelen) {
            totaalCalorieen += voedingsmiddel.getCalorieen();
        }
        return totaalCalorieen;
    }
}

class Trainingsactiviteit {
    private String naam;
    private int calorieen;


    public Trainingsactiviteit(String naam, int calorieen) {
        this.naam = naam;
        this.calorieen = calorieen;
    }

    public int getCalorieen() {
        return calorieen;
    }
}

class Voedingsmiddel {
    private String naam;
    private int calorieen;
    public Voedingsmiddel(String naam, int calorieen) {
        this.naam = naam;
        this.calorieen = calorieen;
    }

    public int getCalorieen() {
        return calorieen;
    }
}



