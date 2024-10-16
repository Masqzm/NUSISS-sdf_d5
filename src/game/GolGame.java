package game;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GolGame {
    private static final int ITERATIONS = 5;   // total iterations game will run
    public static void main(String[] args) throws FileNotFoundException, IOException {
        ReadTest rt = new ReadTest();
        //rt.read("gol/test.gol");
        rt.read("gol/glider.gol");
        
        int currentIter = 0;
        int gridSizeX = Integer.parseInt(rt.gridSize[0]);
        int gridSizeY = Integer.parseInt(rt.gridSize[1]);
        int gridStartX = Integer.parseInt(rt.gridStart[0]);
        int gridStartY = Integer.parseInt(rt.gridStart[1]);

        // DEBUG
        // System.out.println(gridSizeX);
        // System.out.println(gridSizeY);
        // System.out.println(gridStartX);
        // System.out.println(gridStartY);
        // for (String data : rt.gridData) {
        //     System.out.println(data);
        // }
        
        Grid grid = new Grid(gridSizeX, gridSizeY, gridStartX, gridStartY, rt.gridData);
        
        while(currentIter < ITERATIONS)
        {
            // Draw current iter
            System.out.println("ITERATION " + (currentIter+1));
            grid.drawGrid();
            System.out.println();
            System.out.println();

            // Apply rules for next iter 
            for(int row = 0; row < gridSizeX; row++) {
                for(int col = 0; col < gridSizeY; col++) {
                    int neighboursCt = grid.cellNeighbourCt(row, col);
                    //System.out.println("row: " + row + " col: " + col + " | NbrCt: " + neighboursCt);
                    
                    if(neighboursCt > 1 && neighboursCt <= 3)   
                    { 
                        // Rule2: Each cell with neighboursCt > 1 & neighboursCt <= 3 survives
                        grid.setCellNextSurvive(row, col);

                        // Rule4: Each dead cell neighboursCt = 3 becomes populated 
                        if(neighboursCt == 3)
                            grid.setCellNextAlive(row, col);
                    }
                    // Rule1: Each cell with neighboursCt <= 1 dies
                    // Rule3: Each cell with neighboursCt >=4 dies 
                    else
                        grid.setCellNextDead(row, col);
                              
                }
            }

            grid.updateCells();

            ++currentIter;
        }

    }
}