package controller;

import model.Maze;
import model.Node;
import model.Position;

import java.util.ArrayList;
import java.util.Collections;

public class AStar {
    private final Maze maze;
    private Node currentNode = null;
    private ArrayList<Node> openList = new ArrayList<>();
    private ArrayList<Node> closedList = new ArrayList<>();


    public AStar(Maze maze) {
        this.maze = maze;
        openList.add(maze.getStartNode());
    }

    public ArrayList<Node> findPath(){
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
            if(currentNode.equals(maze.getFinishNode()) || isFinishNodeInPath()){
                return getPath();
            }
            //find possible pathways
            ArrayList<Position> availablePaths = getPossiblePaths();
            //find if path is already in the closed list and calc cost
            Node child;
            for (Position p : availablePaths) {
                child = new Node(p);
                if(closedList.contains(child)){
                    continue;
                }
                child.setParent(currentNode);
                child.setG(currentNode);
                child.setH(maze.getFinishNode());
                child.setF(child.getG() + child.getH());

                int ind = openList.indexOf(child);
                if( ind >= 0 && openList.get(ind).getG() < child.getG()){
                    continue;
                }
                openList.add(child);
            }

        }
        return null;
    }

    private boolean isFinishNodeInPath() {
        Position finishNodePos = maze.getFinishNode().getPosition();
        Position currentNodePos = currentNode.getPosition();
        Position parentNodePos;

//        System.out.println("finishNodePos "+ finishNodePos);
//        System.out.println("currentNodePos "+ currentNodePos);
        if(!currentNode.getPosition().equals(maze.getStartNode().getPosition())){
            parentNodePos = currentNode.getParent().getPosition();
//            System.out.println("parentNodePos "+ parentNodePos);
            //x axis
            int mY = (currentNodePos.getY() + parentNodePos.getY())/2;
            int a = finishNodePos.getY();
            int b = parentNodePos.getY();
            if(parentNodePos.getX() == currentNodePos.getX() && finishNodePos.getX() == currentNodePos.getX()) {
                return Math.abs(a - mY) <= Math.abs(b - mY);
            }

            //y axis
            int mX = (currentNodePos.getX() + parentNodePos.getX())/2;
            a = finishNodePos.getX();
            b = parentNodePos.getX();
            if(parentNodePos.getY() == currentNodePos.getY() && finishNodePos.getY() == currentNodePos.getY()) {
                return Math.abs(a - mX) <= Math.abs(b - mX);
            }
        }
        return false;
    }

    private ArrayList<Position> getPossiblePaths() {
        ArrayList<Position> possiblePaths  = new ArrayList<>();
        ArrayList<Position> rocksLocationsByAxis;
        Position currentPosition = currentNode.getPosition();
        Position closeRock;

        //y axis
        rocksLocationsByAxis = maze.findRocksByAxis(currentPosition.getX(), -1);
        //up key
        if(currentPosition.getY() > 0){
            closeRock = null;
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
        if(currentPosition.getY() < maze.getyMax()){
            closeRock = null;
//            rocksLocationsByAxis = maze.findRocksByAxis(currentPosition.getX(), -1);
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
        //x axis
        rocksLocationsByAxis = maze.findRocksByAxis(-1, currentPosition.getY());
        //right key
        if(currentPosition.getX() > 0){
            closeRock = null;
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
        if(currentPosition.getX() < maze.getxMax()){
            closeRock = null;
//            rocksLocationsByAxis = maze.findRocksByAxis(-1, currentPosition.getY());
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

    private ArrayList<Node> getPath() {
        System.out.println("finished");
//        System.out.println(closedList);
        ArrayList<Node> path = new ArrayList<>();
        Node current = currentNode;
        while(current != null){
            path.add(current);
            current = current.getParent();
        }
        Collections.reverse(path);
        return path;
    }
}
