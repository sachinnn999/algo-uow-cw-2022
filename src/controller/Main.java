package controller;

import exception.CommonException;
import model.Maze;
import model.Node;

import java.io.FileNotFoundException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class Main {
    private final static String FILE_PATH = "src/resource/maze10_5.txt";
//    private final static String FILE_PATH = "src/resource/puzzle_1280.txt";

    public static void main(String[] args) {
        System.out.println("Hello Snow Runner!");
        try {
            Instant startInstant = Instant.now();
            Maze maze = new Maze();
            maze.init(FILE_PATH);
            Instant mazeInstant = Instant.now();
//            System.out.println(maze);
            AStar aStar = new AStar(maze);
            ArrayList<Node> path = aStar.findPath();
            Instant pathInstant = Instant.now();
            AStar.displayPath(path);
            Instant displayInstant = Instant.now();
            System.out.println("\n=======Performance Analysis=======");
            System.out.println(String.format("%-20s : %4d ms","File Read Time ", ChronoUnit.MILLIS.between(startInstant, mazeInstant)));
            System.out.println(String.format("%-20s : %4d ms","Path Finder Time ", ChronoUnit.MILLIS.between(mazeInstant, pathInstant)));
            System.out.println(String.format("%-20s : %4d ms","Display Path Time ", ChronoUnit.MILLIS.between(pathInstant, displayInstant)));
        }catch (CommonException ce){
            System.err.println(ce.getMessage());
        }catch (FileNotFoundException fio){
            System.err.println("File cannot be found!");
        }catch (Exception e){
            System.err.println("Something went wrong!\n\terror is " + e);
        }
    }
}