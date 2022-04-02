package model;

import enumeration.NodeTypes;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Maze {
    private ArrayList<Position> rocks = new ArrayList<>();
    private Node startNode;
    private Node finishNode;
    private int xMax;
    private int yMax;

    public Maze() {
    }

    public void init(String filePath) throws Exception {
        // pass the path to the file as a parameter
        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        int y = 0;
        String[] temp;
        int xMax = 0,yMax = 0;
        while (sc.hasNextLine()) {
            temp = sc.nextLine().split("");
            xMax = temp.length;
            System.out.println(Arrays.toString(temp));
            for (int x = 0; x < temp.length; x++) {
                if (temp[x].equalsIgnoreCase(NodeTypes.START.getCode())) {
//                    startNode = new Node(NodeTypes.START, new Position(x, y));
                    startNode = new Node(NodeTypes.START, new Position(x, y));
                }else if (temp[x].equalsIgnoreCase(NodeTypes.FINISH.getCode())) {
//                    endNode = new Node(NodeTypes.FINSIH, new Position(x, y));
                    finishNode = new Node(NodeTypes.FINISH, new Position(x, y));
                }else if (temp[x].equalsIgnoreCase(NodeTypes.ROCK.getCode())){
                    rocks.add(new Position(x,y));
                }
            }
            y++;
        }
        this.xMax = xMax;
        this.yMax = y;
    }

    public ArrayList<Position> getRocks() {
        return rocks;
    }

    public void setRocks(ArrayList<Position> rocks) {
        this.rocks = rocks;
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getFinishNode() {
        return finishNode;
    }

    public void setFinishNode(Node finishNode) {
        this.finishNode = finishNode;
    }

    public int getxMax() {
        return xMax;
    }

    public void setxMax(int xMax) {
        this.xMax = xMax;
    }

    public int getyMax() {
        return yMax;
    }

    public void setyMax(int yMax) {
        this.yMax = yMax;
    }

    @Override
    public String toString() {
        return "Maze{" +
                "rocks=" + rocks +
                ", startNode=" + startNode +
                ", finishNode=" + finishNode +
                ", xMax=" + xMax +
                ", yMax=" + yMax +
                '}';
    }
}