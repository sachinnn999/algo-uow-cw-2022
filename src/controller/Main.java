package controller;

import exception.CommonException;
import model.Maze;
import model.Node;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Main {
    private final static String FILE_PATH = "src/resource/maze10_5.txt";
//    private final static String FILE_PATH = "src/resource/puzzle_1280.txt";

    public static void main(String[] args) {
        System.out.println("Hello Snow Runner!");
        try {
            Maze maze = new Maze();
            maze.init(FILE_PATH);
//            System.out.println(maze);
            AStar aStar = new AStar(maze);
            ArrayList<Node> path = aStar.findPath();
            displayPath(path);
//            System.out.println(path);
        }catch (CommonException ce){
            System.err.println(ce.getMessage());
        }catch (FileNotFoundException fio){
            System.err.println("File cannot be found!");
        }catch (Exception e){
            System.err.println("Something went wrong!\n\terror is " + e);
        }
    }

    private static void displayPath(ArrayList<Node> path) {
        if(path == null){
            System.out.println("Oops! App couldn't find a path.");
            return ;
        }
        for (Node node: path) {
            System.out.println(node);
        }
    }
}