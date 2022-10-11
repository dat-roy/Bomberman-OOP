package uet.oop.bomberman.components.entities.stillobjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.components.entities.Entity;
import uet.oop.bomberman.components.graphics.SpriteSheet;
import uet.oop.bomberman.components.maps.LevelMap;

import java.net.URISyntaxException;
import java.util.LinkedList;

public class Grass extends Entity {
    private static LinkedList<Image> grasses;
    private static boolean initialized = false;
    private final int level;

    public static void init() {
        if (!initialized) {
            grasses = new LinkedList<>();
<<<<<<< HEAD
            //SpriteSheet newTiles = new SpriteSheet("/textures/TilesMap.png", 96, 96);
=======
            SpriteSheet newTiles = new SpriteSheet("/spriteSheet/TilesMap.png", 96, 96);
>>>>>>> 5d2e4bfbd8f825b5b9e8c77337522f942424bdf4
            try {
                grasses.add(new Image(LevelMap.class.getResource("/sprites/map/grass/grass" +  1 + ".png").toURI().toString()));
                grasses.add(new Image(LevelMap.class.getResource("/sprites/map/grass/grass" +  2 + ".png").toURI().toString()));
                grasses.add(new Image(LevelMap.class.getResource("/sprites/map/grass/grass" +  3 + ".png").toURI().toString()));
            } catch (URISyntaxException e) {
                System.out.println("grass init");
                throw new RuntimeException(e);
            }
            initialized =true;
        }
    }

    public Grass(int x, int y, int level) {
        super(x, y);
        this.level = level;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(grasses.get(level - 1), x - camera.getX(), y - camera.getY());
    }

    @Override
    public void update() {

    }
}
