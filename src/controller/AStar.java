package controller;

import model.Maze;
import model.Node;
import model.Position;

import java.util.ArrayList;

public class AStar {
    private final Maze maze;
    private Node currentNode = null;
    private ArrayList<Node> openList = new ArrayList<>();
    private ArrayList<Node> closedList = new ArrayList<>();


    public AStar(Maze maze) {
        this.maze = maze;
//        currentNode = maze.getStartNode();
        openList.add(maze.getStartNode());
    }

    public Object findPath() throws Exception{
        while (!openList.isEmpty()) {
            currentNode = openList.get(0);
            for (Node node : openList) {
                if (currentNode.getF() > node.getF()) {
                    currentNode = node;
                }
            }
            openList.remove(currentNode);
            closedList.add(currentNode);

            //found goal
            if(currentNode.equals(maze.getFinishNode())){
                Object obj = getPath();
                return null;
            }
            //find possible pathways
            ArrayList<Position> availablePaths = getPossiblePaths();
            //find if path is already in the closed list and calc cost
            Node child;
            for (Position p : availablePaths) {
                if(closedList.contains(new Node(p))){
                    continue;
                }
                child = new Node(p);
                child.setParent(currentNode);
                child.setG(currentNode);
                child.setH(maze.getFinishNode());
                child.setF(child.getG() + child.getH());

//                for (Node n: openList) {
//                    if(n.equals(child) && n.getG() < child.getG()){
//                        continue;
//                    }
//                }
                int ind = openList.indexOf(child);
                if( ind >= 0 && openList.get(ind).getG() < child.getG()){
                    continue;
                }
                openList.add(child);
            }

        }
        return null;
    }

    private ArrayList<Position> getPossiblePaths() {
        ArrayList<Position> possiblePaths  = new ArrayList<>();
        ArrayList<Position> rocksLocationsByAxis  = new ArrayList<>();
        Position currentPosition = currentNode.getPosition();
        Position closeRock;

        //up key
        if(currentPosition.getY() > 0){
            closeRock = null;
            rocksLocationsByAxis = maze.findRocksByAxis(currentPosition.getX(), -1);
            for (Position p : rocksLocationsByAxis) {
                if(p.getY() < currentPosition.getY()){
                    if(closeRock == null){
                        closeRock = p;
                    }else if(closeRock.getY() < p.getY()){
                        closeRock = p;
                    }
                }
            }
            if(closeRock != null){
                possiblePaths.add(new Position(closeRock.getX(), closeRock.getY() + 1));
            }else {
                possiblePaths.add(new Position(currentPosition.getX(), 0));
            }
        }
        //down key
        if(currentPosition.getY() <= maze.getyMax()){
            closeRock = null;
            rocksLocationsByAxis = maze.findRocksByAxis(currentPosition.getX(), -1);
            for (Position p : rocksLocationsByAxis) {
                if(p.getY() >= currentPosition.getY()){
                    if(closeRock == null){
                        closeRock = p;
                    }else if(closeRock.getY() > p.getY()){
                        closeRock = p;
                    }
                }
            }
            if(closeRock != null){
                possiblePaths.add(new Position(closeRock.getX(), closeRock.getY() - 1));
            }else {
                possiblePaths.add(new Position(currentPosition.getX(), maze.getyMax()));
            }
        }
        //right key
        if(currentPosition.getX() > 0){
            closeRock = null;
            rocksLocationsByAxis = maze.findRocksByAxis(-1, currentPosition.getY());
            for (Position p : rocksLocationsByAxis) {
                if(p.getX() < currentPosition.getX()){
                    if(closeRock == null){
                        closeRock = p;
                    }else if(closeRock.getX() < p.getX()){
                        closeRock = p;
                    }
                }
            }
            if(closeRock != null){
                possiblePaths.add(new Position(closeRock.getX() + 1, closeRock.getY()));
            }else {
                possiblePaths.add(new Position(0, currentPosition.getY()));
            }
        }
        //left
        if(currentPosition.getX() <= maze.getxMax()){
            closeRock = null;
            rocksLocationsByAxis = maze.findRocksByAxis(-1, currentPosition.getY());
            for (Position p : rocksLocationsByAxis) {
                if(p.getX() >= currentPosition.getX()){
                    if(closeRock == null){
                        closeRock = p;
                    }else if(closeRock.getX() > p.getX()){
                        closeRock = p;
                    }
                }
            }
            if(closeRock != null){
                possiblePaths.add(new Position(closeRock.getX() - 1, closeRock.getY()));
            }else {
                possiblePaths.add(new Position(maze.getxMax(), currentPosition.getY()));
            }
        }
//        System.out.println(response);
        return possiblePaths;
    }

    private Object getPath() {
        //todo
        System.out.println("finished");
        System.out.println(closedList);
        return null;
    }
}
