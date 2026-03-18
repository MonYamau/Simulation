package main.java.service;

import main.java.gamemap.Coordinates;

import java.util.Objects;

public class Node {
    private final Coordinates coordinates;
    private Node parent;

    public Node(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(coordinates, node.coordinates) && Objects.equals(parent, node.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, parent);
    }
}
