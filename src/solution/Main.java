package solution;

public class Main {
    public static void main(String[] args) throws Exception {
        String file = "gol/test.gol";
        // "gol/test.gol";
        // "gol/glider.gol";

        if (args.length <= 0) {
            System.err.println("Missing GOL file. Using default test file");
            // System.exit(1);
        } else
            file = args[0];

        GameOfLife gol = new GameOfLife(file);
        gol.readFile();

        // Print first gen
        gol.printBoard();

        // Print four more gens
        for (int i = 0; i < 5; i++) {
            System.out.println("\n-------------------\n");
            gol = gol.nextGeneration();
            gol.printBoard();
        }

    }
}
