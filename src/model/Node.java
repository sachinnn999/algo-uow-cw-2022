package model;

import enumeration.NodeTypes;

public class Node {
    private NodeTypes type = NodeTypes.SNOW;
    private Position position;
//    private boolean isBlock = false;
    private int g;
    private int f;
    private int h;
    private Node parent;

    public Node() {
    }

    public Node(NodeTypes type, Position position) {
        if(type != null) {
            this.type = type;
        }
        this.position = position;
    }

    public Node(Position pos) {
        this.position = position;
    }


    public NodeTypes getType() {
        return type;
    }

    public void setType(NodeTypes type) {
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Node{" +
                "position=" + position +
                ", g=" + g +
                ", f=" + f +
                ", h=" + h +
                ", parent=" + parent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return  position.equals(node.getPosition());
    }
}