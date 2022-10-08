package uet.oop.bomberman.components.entities.stillobjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.components.entities.Entity;
import uet.oop.bomberman.components.graphics.Animation;
import uet.oop.bomberman.components.graphics.Sprite;
import uet.oop.bomberman.components.graphics.SpriteSheet;
import uet.oop.bomberman.components.maps.LevelMap;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Brick extends Entity {
    private static boolean initialized = false;
    private static List<Image> bricks;
    private static Animation brickExplodes;
    private boolean destroyed = false; // bị phá hủy chưa
    private final static float timeDestroyed = 1000.0f; // thời gian phá hủy
    private final int level;

    public static void init() {
        if (!initialized) {
            bricks = new ArrayList<>();

            try {
                Image image = new Image(LevelMap.class.getResource("/spriteSheet/bomb_explosion.png").toURI().toString());

                SpriteSheet newTiles = new SpriteSheet("/spriteSheet/TilesMap.png", 96, 96);

                bricks.add(new Image(LevelMap.class.getResource("/sprites/map/brick/brick1.png").toURI().toString()));
                bricks.add(new Image(LevelMap.class.getResource("/sprites/map/brick/brick2.png").toURI().toString()));
                bricks.add(new Sprite(16, 16, 3 * 16, newTiles, 16, 16).getFxImage());

                brickExplodes = new Animation(image, 6, 6, timeDestroyed, 17.75f * 2, 54, 17.75f, 18);
            } catch (URISyntaxException | NullPointerException e) {
                System.out.println("brick init");
                e.printStackTrace();
            }
            initialized = true;
        }
    }

    public Brick(double x, double y, int width, int height, int level) {
        super(x, y, width, height);
        this.level = level;
        brickExplodes.reset();
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    @Override
    public void render(GraphicsContext gc) {
        if (!destroyed) {
            gc.drawImage(bricks.get(level - 1), x - camera.getX(), y - camera.getY());
        } else {
            brickExplodes.render(gc, x - camera.getX(), y - camera.getY());
        }
    }

    @Override
    public void update() {
        if(destroyed){
            brickExplodes.update();
        }
    }
}
