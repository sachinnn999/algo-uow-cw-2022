package model;

import enumeration.NodeTypes;
import exception.CommonException;

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
        int xMax = -1;
        while (sc.hasNextLine()) {
            temp = sc.nextLine().split("");
            if(xMax >= 0 && xMax != temp.length){
                throw new CommonException("File is not in format");
            }
            xMax = temp.length;
            System.out.println(Arrays.toString(temp));
            for (int x = 0; x < temp.length; x++) {
                if (temp[x].equalsIgnoreCase(NodeTypes.START.getCode())) {
                    startNode = new Node(NodeTypes.START, new Position(x, y));
                }else if (temp[x].equalsIgnoreCase(NodeTypes.FINISH.getCode())) {
                    finishNode = new Node(NodeTypes.FINISH, new Position(x, y));
                }else if (temp[x].equalsIgnoreCase(NodeTypes.ROCK.getCode())){
                    rocks.add(new Position(x,y));
                }
            }
            y++;
        }
        this.xMax = xMax - 1;
        this.yMax = y- 1;
    }

    public ArrayList<Position> findRocksByAxis(int x, int y){
        ArrayList<Position> blocks = new ArrayList<>();
        if(x >= 0){
            for (Position p: rocks) {
                if(p.getX() == x){
                    blocks.add(p);
                }
            }
        }
        if(y >= 0){
            for (Position p: rocks) {
                if(p.getY() == y){
                    blocks.add(p);
                }
            }
        }
        return blocks;
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
