package labs.Lab29a;

import java.awt.*;
import java.util.*;

public class Maze {

    private ArrayList<Wall> walls;
    private char[][] maze;
    private int len;
    private int startR;
    private int startC;
    private int endR;
    private int endC;

    public static void main(String...args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter side length ->");
        int size = sc.nextInt();
        System.out.println();
        Maze m = new Maze(size);
        m.primAlg();
        //printMaze();
    }

    public Maze(int size) {
        len = size;
        maze = new char[len][len];
        startR = (int)(Math.random()*len);
        startC = (int)(Math.random()*len);
    }

    public char[][] getMaze() {
        return maze;
    }

    public void primAlg() {
        //Initialize random weight array

        walls = new ArrayList<>();
        for(char[] chars : maze)
            Arrays.fill(chars, '#');
        maze[startR][startC] = ' ';

        addSurroundingWalls(startR, startC);
        while (walls.size() > 0) {
            //System.out.println(walls);
            int index = (int)(Math.random()*walls.size());
            Wall randWall = walls.get(index);
            //System.out.println(randWall);
            int r = randWall.coords.y;
            int c = randWall.coords.x;
            int dir = randWall.dir;
            if (dir == 0 && r > 0 && r < len - 1)
                testCell(r-1, c, r+1, c);
            else if (c > 0 && c < len-1)
                testCell(r, c-1, r, c+1);

            walls.remove(index);
            //printMaze();
        }
    }

    public void printMaze() {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++)
                System.out.print(maze[i][j]);
            System.out.println();
        }
    }

    private void addSurroundingWalls(int r, int c) {
        if (r > 1 && maze[r-1][c] == '#')
            walls.add(new Wall(c, r - 1, 0));
        if (c > 1 && maze[r][c-1] == '#')
            walls.add(new Wall(c - 1, r, 1));
        if (r < len - 1 && maze[r+1][c] == '#')
            walls.add(new Wall(c, r + 1, 0));
        if (c < len - 1 && maze[r][c+1] == '#')
            walls.add(new Wall(c + 1, r, 1));
    }

    private void testCell(int r1, int c1, int r2, int c2) {
        char firstCell = maze[r1][c1];
        char secondCell = maze[r2][c2];
        //System.out.println(firstCell + " / " + secondCell);
        if (firstCell != secondCell) {
            //Only one is visited
            maze[(r1+r2)/2][(c1+c2)/2] = ' ';
            if (firstCell == ' ') {
                //First cell is visited
                maze[r2][c2] = ' ';
                addSurroundingWalls(r2, c2);
            }
            else {
                //Second cell visited
                maze[r1][c1] = ' ';
                addSurroundingWalls(r1, c1);
            }
        }
    }

    public boolean solve() {
        int rand = (int)(Math.random()*len*len);
        int num = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (maze[i][j] == ' ') {
                    if (num == rand) {
                        startR = i;
                        startC = j;
                    }
                    num++;
                }
                else
                    rand--;
            }
        }


        while (endR == 0 && endC == 0) {
            rand = (int)(Math.random()*len*len);
            num = 0;
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    if (maze[i][j] == ' ') {
                        if (num == rand) {
                            endR = i;
                            endC = j;
                        }
                        num++;
                    }
                    else
                        rand--;
                }
            }
        }

        System.out.println("Solving (" + startR + ", " + startC + ") to (" + endR + ", " + endC + ")");

        return solve(startR, startC);
    }

    private boolean solve(int r, int c) {
        maze[r][c] = '*';

        if (r == endR && c == endC)
            return true;

        if (c > 0 && maze[r][c-1] == ' '  && solve(r, c-1))
            return true;

        if (c < len-1 && maze[r][c+1] == ' ' && solve(r, c+1))
            return true;

        if (r > 0 && maze[r-1][c] == ' ' && solve(r-1, c))
            return true;

        if (r < len-1 && maze[r+1][c] == ' ' && solve(r+1, c))
            return true;

        maze[r][c] = ' ';
        return false;
    }
}

class Wall {
    public Point coords;
    public int dir;

    public Wall(int x, int y, int dir) {
        coords = new Point(x, y);
        this.dir = dir;
    }
}