package game;

import java.util.List;

public class Grid {
    private int sizeX;
    private int sizeY;

    private char[][] cells;
    private char[][] cellsNextIter; // next iter of cells

    private final char DATA = '*';
    private final char BLANK_DATA = ' ';

    public Grid(int sizeX, int sizeY, int startX, int startY, List<String> dataStrList) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        this.cells = new char[sizeX][sizeY];
        this.cellsNextIter = new char[sizeX][sizeY];

        // Init blank grid
        for(int row = 0; row < sizeX; row++) 
        {
            for(int col = 0; col < sizeY; col++)  
            {
                cells[row][col] = BLANK_DATA;
                cellsNextIter[row][col] = BLANK_DATA;
            }
        }


        // Populate grid w data
        int dataRowIdx = 0;         // current data row index
        while(dataRowIdx < dataStrList.size())
        {
            int dataColIdx = 0;     // current data col index
            while(dataColIdx < dataStrList.get(dataRowIdx).length())
            {
                cells[startX+dataRowIdx][startY+dataColIdx] = dataStrList.get(dataRowIdx).charAt(dataColIdx);
                ++dataColIdx;
            }
            ++dataRowIdx;
        }
    }

    // Fn. to draw grid 
    public void drawGrid() {
        for(int row = 0; row < sizeX; row++) {
            System.out.printf("|");     // Print borders at start of row

            for(int col = 0; col < sizeY; col++) 
                System.out.printf("%s", cells[row][col]);
                
            System.out.printf("|\n");   // Print borders at end of row and move to new line
        }
    }
//public int debug = 0;
    // Fn. to count a cell's neighbour (live ones)
    public int cellNeighbourCt(int cellX, int cellY) {
        int count = 0;
        
        //if(debug < 10)
        //System.out.printf("CELL (%d, %d): |%c|\n-------------\n", cellX, cellY, cells[cellX][cellY]);
        // Loop thru cell's neighbours (see lecturer's code for more optimal mtd)
        for(int focusRow = cellX-1; focusRow <= cellX+1; focusRow++)
        {
            // If out of bounds, skip 
            if(focusRow < 0 || focusRow >= sizeX)
                continue;
                
            for(int focusCol = cellY-1; focusCol <= cellY+1; focusCol++)
            {
                // If out of bounds, skip 
                if(focusCol < 0 || focusCol >= sizeY )
                    continue;
                // If this is cell itself, not its neighbour
                if(focusRow == cellX && focusCol == cellY)
                    continue; 
                
                // Add neighbour count if it's alive
                if(cells[focusRow][focusCol] == DATA)
                    ++count;

                // if(debug < 10)
                //     System.out.printf("Nbr cell (%d, %d): |%c|\n", focusRow, focusCol, cells[focusRow][focusCol]);
            }
        }   
        
        //if(debug < 11)debug++;
        return count;
    }

    // fns. to set next iter of cells alive/dead
    public void setCellNextAlive(int x, int y) {
        cellsNextIter[x][y] = DATA;
    }
    public void setCellNextSurvive(int x, int y) {
        cellsNextIter[x][y] = cells[x][y];
    }
    public void setCellNextDead(int x, int y) {
        cellsNextIter[x][y] = BLANK_DATA;
    }

    // fn. to update cells to next iteration
    public void updateCells() {
        for(int row = 0; row < sizeX; row++) 
        {
            for(int col = 0; col < sizeY; col++)  
            {
                cells[row][col] = cellsNextIter[row][col];
                cellsNextIter[row][col] = BLANK_DATA;
            }
        }
    }
}
