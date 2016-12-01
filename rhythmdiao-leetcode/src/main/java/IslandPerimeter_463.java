/**
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
 * Grid cells are connected horizontally/vertically (not diagonally).
 * The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
 * The island doesn't have "lakes" (water inside that isn't connected to the water around the island).
 * One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100.
 * Determine the perimeter of the island.
 * [[0,1,0,0],
 * [1,1,1,0],
 * [0,1,0,0],
 * [1,1,0,0]]
 * Answer: 16
 * Explanation: The perimeter is the 16 yellow stripes in the image below:
 */
public final class IslandPerimeter_463 {
    private static int islandPerimeter(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        int result = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int item = grid[i][j];
                if (item == 1) {
                    if (i - 1 < 0) {
                        result++;
                    } else if (grid[i - 1][j] == 0) {
                        result++;
                    }
                    if (j - 1 < 0) {
                        result++;
                    } else if (grid[i][j - 1] == 0) {
                        result++;
                    }
                    if (i + 1 == row) {
                        result++;
                    } else if (grid[i + 1][j] == 0) {
                        result++;
                    }
                    if (j + 1 == column) {
                        result++;
                    } else if (grid[i][j + 1] == 0) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(islandPerimeter(new int[][]{{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}}));
    }
}
