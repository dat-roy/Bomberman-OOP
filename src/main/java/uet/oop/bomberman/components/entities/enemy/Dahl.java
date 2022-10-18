package uet.oop.bomberman.components.entities.enemy;

import uet.oop.bomberman.components.graphics.Animation;
import uet.oop.bomberman.components.graphics.SpriteSheet;
import uet.oop.bomberman.config.Direction;
import uet.oop.bomberman.config.GameConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dahl extends Enemy {
    public Dahl(double x, double y) {
        super(x, y);

        animationLeft = new Animation(SpriteSheet.enemy, 3, 3, 1000, 0, 96, 32, 32);
        animationRight = new Animation(SpriteSheet.enemy, 3, 3, 1000, 96, 96, 32, 32);
        animationDeath = new Animation(SpriteSheet.enemy, 4, 4, 1000, 192, 96, 32, 32);
        animationLeft.setLoop(true);
        animationRight.setLoop(true);
        randomAnimation = false;
        initDirectionList();
        lastDirection = directionList.get(r.nextInt(directionList.size()));
        speed = 2;
        score = 400;
    }

    @Override
    protected void move() {
        int j = (int) (x / GameConfig.TILE_SIZE);
        int i = (int) (y / GameConfig.TILE_SIZE);
        if (j * GameConfig.TILE_SIZE == x && i * GameConfig.TILE_SIZE == y) {
            moveX = 0;
            moveY = 0;
            canMoveR = checkMapHash(i, j + 1);
            canMoveL = checkMapHash(i, j - 1);
            canMoveU = checkMapHash(i - 1, j);
            canMoveD = checkMapHash(i + 1, j);

            checkMove();
            if (moveY == 0 && moveX == 0 && directionList.size() != 0) {
                int ran = r.nextInt(directionList.size());
                lastDirection = directionList.get(ran);
            }
        }
        x += moveX;
        y += moveY;
    }
}
