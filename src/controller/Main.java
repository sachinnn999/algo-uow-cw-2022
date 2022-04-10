package controller;

import exception.CommonException;
import model.Maze;
import model.Node;

import java.io.FileNotFoundException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/** *****************************************************************************
 *  Name:    Sachin De Silva
 *  UOW ID:   W1761382
 *  IIT ID: 2019801
 *
 *  Description:  main execution of a-star implementation is here. user can get initialize the maze
 *              astar implementation execution and result can be get from here
 *
 *  Written:       02-04-2022
 *  Last updated:  11-02-2022
 *
 **************************************************************************** */

public class Main {
    //file name with path has to be define here
    private final static String FILE_PATH = "src/resource/maze15_2.txt";
//    private final static String FILE_PATH = "src/resource/puzzle_1280.txt";

    public static void main(String[] args) {
        System.out.println("Hello Snow Runner!");
        try {
            Instant startInstant = Instant.now();
            //initializing the maze
            Maze maze = new Maze();
            maze.init(FILE_PATH);
            Instant mazeInstant = Instant.now();
//            System.out.println(maze);
            //initializing the a-star
            AStar aStar = new AStar(maze);
            //calling to find the path
            ArrayList<Node> path = aStar.findPath();
            Instant pathInstant = Instant.now();
            //calling to display path
            AStar.displayPath(path);
            Instant displayInstant = Instant.now();
            //performance report
            System.out.println("\n=======Performance Analysis=======");
            System.out.println(String.format("%-20s : %4d ms","File Read Time ", ChronoUnit.MILLIS.between(startInstant, mazeInstant)));
            System.out.println(String.format("%-20s : %4d ms","Path Finder Time ", ChronoUnit.MILLIS.between(mazeInstant, pathInstant)));
            System.out.println(String.format("%-20s : %4d ms","Display Path Time ", ChronoUnit.MILLIS.between(pathInstant, displayInstant)));
            System.out.println(String.format("%-20s : %4d ms","Total Time ", ChronoUnit.MILLIS.between(startInstant, displayInstant)));
        }catch (CommonException ce){
            System.err.println(ce.getMessage());
        }catch (FileNotFoundException fio){
            System.err.println("File cannot be found!");
        }catch (Exception e){
            System.err.println("Something went wrong!\n\terror is " + e);
        }
    }
}