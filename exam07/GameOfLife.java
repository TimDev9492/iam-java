import java.util.Locale;
import java.util.Scanner;

public class GameOfLife {
    
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        // GET USER INPUT
        System.out.println("Enter dimensions of grid:");
        int dimensions = sc.nextInt();

        System.out.println("Enter amount of alive starting cells:");
        int numAlive = sc.nextInt();

        System.out.println("Enter amount of generations to simulate:");
        int generations = sc.nextInt();

        System.out.println();

        // START SIMULATION
        Grid grid = new Grid(dimensions, numAlive);

        // sc.nextLine(); // fix;
        // while (true) {
        //     System.out.println(grid.toString());

        //     String line = sc.nextLine();
        //     if (line.equals("next")) {
        //         grid.tick(1);
        //     } else {
        //         String[] coordinates = line.split(" ");
        //         int x = Integer.parseInt(coordinates[0]);
        //         int y = Integer.parseInt(coordinates[1]);
        //         int aliveNeighbors = grid.getAliveNeighbors(x, y);
        //         System.out.println(String.format("Cell at position (%d, %d) has %d alive neighbors.\n", x, y, aliveNeighbors));
        //     }
        // }

        for (int i = 0; i < generations; i++) {
            // print out current state
            System.out.println(grid.toString());

            // simulate one generation
            grid.tick(1);
        }
    }

}

class Grid {

    boolean[] cellStates;
    int dimensions;

    public Grid(int n, int numAlive) {
        this.dimensions = n;
        generateGrid(numAlive);
    }

    private void generateGrid(int numAlive) {
        this.cellStates = new boolean[this.dimensions * this.dimensions];
        
        int aliveAmount = 0;
        while (aliveAmount < numAlive) {
            // generate random cell number
            int randomCellNumber = (int) Math.floor(Math.random() * this.cellStates.length);
            
            // check if that cell is alive already
            if (this.cellStates[randomCellNumber]) continue;

            // set cellState to alive
            this.cellStates[randomCellNumber] = true;
            aliveAmount++;
        }

        // int index = new CellPos(2, 2, this.dimensions).index;
        // this.cellStates[index] = true;
        // index = new CellPos(3, 2, this.dimensions).index;
        // this.cellStates[index] = true;
        // index = new CellPos(4, 2, this.dimensions).index;
        // this.cellStates[index] = true;

        // index = new CellPos(7, 7, this.dimensions).index;
        // this.cellStates[index] = true;
        // index = new CellPos(7, 8, this.dimensions).index;
        // this.cellStates[index] = true;
        // index = new CellPos(8, 7, this.dimensions).index;
        // this.cellStates[index] = true;
        // index = new CellPos(8, 8, this.dimensions).index;
        // this.cellStates[index] = true;
    }

    public boolean getState(int x, int y) {
        if (Math.min(x, y) < 1 || Math.max(x, y) > this.dimensions) 
            throw new IllegalArgumentException(String.format("Position not valid (dimensions: %d)", this.dimensions));
        CellPos pos = new CellPos(x, y, this.dimensions);
        return this.cellStates[pos.index];
    }

    public int getAliveNeighbors(int x, int y) {
        int aliveNeighbors = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int neighborX = x + i;
                int neighborY = y + j;
                if ((neighborX >= 1 && neighborX <= this.dimensions)
                    && (neighborY >= 1 && neighborY <= this.dimensions)
                    && (neighborX != x || neighborY != y)) {
                    if (this.getState(neighborX, neighborY)) 
                        aliveNeighbors++;
                }
            }
        }
        return aliveNeighbors;
    }

    public String toString() {
        String out = "";
        for (int x = 1; x <= this.dimensions; x++) {
            String row = "";
            for (int y = 1; y <= this.dimensions; y++) {
                boolean alive = this.getState(x, y);
                row += alive ? "# " : ". ";
            }
            out += row + "\n";
        }
        return out;
    }

    public String toStringWithCoordinates() {
        String out = " ";
        for (int i = 1; i <= this.dimensions; i++) out += " " + i;
        out += "\n";
        for (int x = 1; x <= this.dimensions; x++) {
            String row = x + " ";
            for (int y = 1; y <= this.dimensions; y++) {
                boolean alive = this.getState(x, y);
                row += alive ? "# " : ". ";
            }
            out += row + "\n";
        }
        return out;
    }

    public void tick(int generations) {
        for (int n = 0; n < generations; n++) {
            // advance one generation
            boolean[] next = new boolean[this.cellStates.length];

            for (int i = 0; i < this.cellStates.length; i++) {
                CellPos currentPos = new CellPos(i, this.dimensions);
                int aliveNeighbors = getAliveNeighbors(currentPos.x, currentPos.y);
                
                if (aliveNeighbors < 2 || aliveNeighbors > 3) {
                    next[i] = false;
                } else if (aliveNeighbors == 3) {
                    next[i] = true;
                } else {
                    next[i] = this.cellStates[i];
                }
            }

            this.cellStates = next;
        }
    }

}

// helper class to convert between (x, y) and index of one-dimensional array
class CellPos {

    public int x;
    public int y;
    public int index;

    public CellPos(int x, int y, int dimensions) {
        this.x = x;
        this.y = y;
        this.index = (x-1) * dimensions + (y-1);
    }

    public CellPos(int index, int dimensions) {
        this.x = (index / dimensions) + 1;
        this.y = (index % dimensions) + 1;
    }

}
