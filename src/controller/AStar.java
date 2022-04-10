package controller;

import enumeration.NodeTypes;
import model.Maze;
import model.Node;
import model.Position;

import java.util.ArrayList;
import java.util.Collections;
/** *****************************************************************************
 *  Name:    Sachin De Silva
 *  UOW ID:   W1761382
 *  IIT ID: 2019801
 *
 *  Description:  will help to find the path using a star algorithm
 *
 *  Written:       02-04-2022
 *  Last updated:  11-02-2022
 *
 **************************************************************************** */
public class AStar {
    private final Maze maze;
    private Node currentNode = null;
    private ArrayList<Node> openList = new ArrayList<>();
    private ArrayList<Node> closedList = new ArrayList<>();

    //user defined constructor to assign maze and add start node to the openlist
    public AStar(Maze maze) {
        this.maze = maze;
        openList.add(maze.getStartNode());
    }

    //this will return the completed path if algorithm failed method will return a null value
    public ArrayList<Node> findPath() {
        //running till openlist is empty or till finding the finish node
        while (!openList.isEmpty()) {
            currentNode = openList.get(0);
            //sorting open list to the lowest cost
            for (Node node : openList) {
                if (currentNode.getF() > node.getF()) {
                    currentNode = node;
                }
            }
            //removing the lowest cost node
            openList.remove(currentNode);
            //adding selected lowest cost node to closed list
            closedList.add(currentNode);

            //found goal or if goal is in the current pathway
            if (currentNode.equals(maze.getFinishNode()) || isFinishNodeInPath()) {
                return getPath();
            }
            //find possible pathways
            ArrayList<Position> availablePaths = getPossiblePaths();
            //find if path is already in the closed list and calc cost
            Node child;
            for (Position p : availablePaths) {
                child = new Node(p);
                //checking if children are in closed list
                if (closedList.contains(child)) {
                    continue;
                }
                //setting up node required values
                child.setParent(currentNode);
                child.setG(currentNode);
                child.setH(maze.getFinishNode());
                child.setF(child.getG() + child.getH());

                //checking if child node is already in the open list if not adding the child node into openlist
                int ind = openList.indexOf(child);
                if (ind >= 0 && openList.get(ind).getG() < child.getG()) {
                    continue;
                }
                openList.add(child);
            }

        }
        return null;
    }

    //finding if finishing node is located in current pathway to solve the task
    private boolean isFinishNodeInPath() {
        Position finishNodePos = maze.getFinishNode().getPosition();
        Position currentNodePos = currentNode.getPosition();
        Position parentNodePos;

//        System.out.println("finishNodePos "+ finishNodePos);
//        System.out.println("currentNodePos "+ currentNodePos);
        if (!currentNode.getPosition().equals(maze.getStartNode().getPosition())) {
            parentNodePos = currentNode.getParent().getPosition();
//            System.out.println("parentNodePos "+ parentNodePos);
            //x axis
            int mY = (currentNodePos.getY() + parentNodePos.getY()) / 2;
            int a = finishNodePos.getY();
            int b = parentNodePos.getY();
            if (parentNodePos.getX() == currentNodePos.getX() && finishNodePos.getX() == currentNodePos.getX()) {
                return Math.abs(a - mY) <= Math.abs(b - mY);
            }

            //y axis
            int mX = (currentNodePos.getX() + parentNodePos.getX()) / 2;
            a = finishNodePos.getX();
            b = parentNodePos.getX();
            if (parentNodePos.getY() == currentNodePos.getY() && finishNodePos.getY() == currentNodePos.getY()) {
                return Math.abs(a - mX) <= Math.abs(b - mX);
            }
        }
        return false;
    }

    //this will return  list which contains all accessible pathways
    private ArrayList<Position> getPossiblePaths() {
        ArrayList<Position> possiblePaths = new ArrayList<>();
        ArrayList<Position> rocksLocationsByAxis;
        Position currentPosition = currentNode.getPosition();
        Position closeRock;

        //y axis
        rocksLocationsByAxis = maze.findRocksByAxis(currentPosition.getX(), -1);
        //up key
        if (currentPosition.getY() > 0) {
            closeRock = null;
            for (Position p : rocksLocationsByAxis) {
                if (p.getY() < currentPosition.getY()) {
                    if (closeRock == null) {
                        closeRock = p;
                    } else if (closeRock.getY() < p.getY()) {
                        closeRock = p;
                    }
                }
            }
            if (closeRock != null) {
                possiblePaths.add(new Position(closeRock.getX(), closeRock.getY() + 1));
            } else {
                possiblePaths.add(new Position(currentPosition.getX(), 0));
            }
        }
        //down key
        if (currentPosition.getY() < maze.getyMax()) {
            closeRock = null;
//            rocksLocationsByAxis = maze.findRocksByAxis(currentPosition.getX(), -1);
            for (Position p : rocksLocationsByAxis) {
                if (p.getY() >= currentPosition.getY()) {
                    if (closeRock == null) {
                        closeRock = p;
                    } else if (closeRock.getY() > p.getY()) {
                        closeRock = p;
                    }
                }
            }
            if (closeRock != null) {
                possiblePaths.add(new Position(closeRock.getX(), closeRock.getY() - 1));
            } else {
                possiblePaths.add(new Position(currentPosition.getX(), maze.getyMax()));
            }
        }
        //x axis
        rocksLocationsByAxis = maze.findRocksByAxis(-1, currentPosition.getY());
        //right key
        if (currentPosition.getX() > 0) {
            closeRock = null;
            for (Position p : rocksLocationsByAxis) {
                if (p.getX() < currentPosition.getX()) {
                    if (closeRock == null) {
                        closeRock = p;
                    } else if (closeRock.getX() < p.getX()) {
                        closeRock = p;
                    }
                }
            }
            if (closeRock != null) {
                possiblePaths.add(new Position(closeRock.getX() + 1, closeRock.getY()));
            } else {
                possiblePaths.add(new Position(0, currentPosition.getY()));
            }
        }
        //left
        if (currentPosition.getX() < maze.getxMax()) {
            closeRock = null;
//            rocksLocationsByAxis = maze.findRocksByAxis(-1, currentPosition.getY());
            for (Position p : rocksLocationsByAxis) {
                if (p.getX() >= currentPosition.getX()) {
                    if (closeRock == null) {
                        closeRock = p;
                    } else if (closeRock.getX() > p.getX()) {
                        closeRock = p;
                    }
                }
            }
            if (closeRock != null) {
                possiblePaths.add(new Position(closeRock.getX() - 1, closeRock.getY()));
            } else {
                possiblePaths.add(new Position(maze.getxMax(), currentPosition.getY()));
            }
        }
//        System.out.println(response);
        return possiblePaths;
    }

    //this will help to return and finalize the path
    private ArrayList<Node> getPath() {
//        System.out.println("finished");
//        System.out.println(closedList);
        ArrayList<Node> path = new ArrayList<>();
        Node current = maze.getFinishNode();
        current.setParent(currentNode.getParent());
        //reversing the from finish node to get the path
        while (current != null) {
            path.add(current);
            current = current.getParent();
        }
        Collections.reverse(path);
        return path;
    }

    //this will help to display a meaningful path map
    public static void displayPath(ArrayList<Node> path) {
        System.out.println("\n=======Direction=======");
        if (path == null || path.isEmpty()) {
            System.out.println("Oops! App couldn't find a path.");
            return;
        }
        Node node, parent;
        for (int count = 0; count < path.size(); count++) {
            System.out.printf("%5d. ", count + 1);
            node = path.get(count);
            if (node.getType() == NodeTypes.START) {
                System.out.println("Start at ("+ node.getPosition().getX() +", "+ node.getPosition().getY()+ ")");
                continue;
            }
            parent = node.getParent();
            if (node.getPosition().getY() == parent.getPosition().getY()) {
                if (node.getPosition().getX() > parent.getPosition().getX()) {
                    System.out.println("Move right to (" + node.getPosition().getX() +", "+ node.getPosition().getY()+ ")");
                } else {
                    System.out.println("Move left to (" + node.getPosition().getX() +", "+ node.getPosition().getY()+ ")");                }
            }
            if (node.getPosition().getX() == parent.getPosition().getX()) {
                if (node.getPosition().getY() > parent.getPosition().getY()) {
                    System.out.println("Move up to (" + node.getPosition().getX() +", "+ node.getPosition().getY()+ ")");
                } else {
                    System.out.println("Move down to (" + node.getPosition().getX() +", "+ node.getPosition().getY()+ ")");
                }
            }
        }
        System.out.println("Done!");
    }
}
