package solution;

public class Evaluator {
    public static int countNeighbours(int x, int y, char[][] board) {
        int star = 0;   // alive neighbour count
        int height = board.length;
        int width = board[0].length;

        // Loop thru matrix of coordinates (offsets to current x,y)
        // NOTE: there is no offset of 0,0 in Constants.X_DELTA, Constants.Y_DELTA as that is 
        // current x,y we are checking for its neighbours hence it is being skipped here
        for (int i = 0; i < Constants.X_DELTA.length; i++) {
            // Get the position to examine
            int lX = x + Constants.X_DELTA[i];
            int lY = y + Constants.Y_DELTA[i];
            if ((lX < 0) || (lX >= width))
                continue;
            if ((lY < 0) || (lY >= height))
                continue;
            if (board[lY][lX] == Constants.STAR)
                star++;
        }

        return star;
    }
}
