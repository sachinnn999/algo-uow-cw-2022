package controller;

import model.Maze;

import java.io.FileNotFoundException;


public class Main {
    private final static String FILE_PATH = "src/resource/maze_file.txt";

    public static void main(String[] args) {
        System.out.println("Hello Snow Runner!");
        try {
            Maze maze = new Maze();
            maze.init(FILE_PATH);
            System.out.println(maze);
            AStar aStar = new AStar(maze);
            aStar.findPath();
        }catch (FileNotFoundException fio){
            System.err.println("File cannot be found!");
        }catch (Exception e){
            System.err.println("Something went wrong! error is \n\t" + e);
        }
    }
}