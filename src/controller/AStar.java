package controller;

import enumeration.NodeTypes;
import model.Maze;
import model.Node;
import model.Position;

import java.util.ArrayList;

public class AStar {
    private Maze maze;
    private Node currentNode = null;
    private ArrayList<Node> openList = new ArrayList<>();
    private ArrayList<Node> closedList = new ArrayList<>();


    public AStar(Maze maze) {
        this.maze = maze;
//        currentNode = maze.getStartNode();
        openList.add(maze.getStartNode());
    }

    public Object findPath() throws Exception{
        while (openList.isEmpty()) {
            currentNode = openList.get(0);
            closedList.add(currentNode);
            for (int i = 0; i < openList.size(); i++){
                if(currentNode.getF() > openList.get(i).getF()){
                    currentNode = openList.get(i);
                }
            }
            openList.remove(currentNode);
            closedList.add(currentNode);

            //found goal
            if(currentNode.equals(maze.getFinishNode())){

            }
        }
        return null;
    }
}
