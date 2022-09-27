package uet.oop.bomberman.components.entities;

import uet.oop.bomberman.config.Direction;

public interface Movable {
    public void move(int steps, Direction direction);
}