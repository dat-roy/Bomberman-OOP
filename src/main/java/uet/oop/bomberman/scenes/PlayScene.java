package uet.oop.bomberman.scenes;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import uet.oop.bomberman.config.GameConfig;
import uet.oop.bomberman.core.SceneManager;

public class PlayScene {
    private Group root;
    private Canvas canvas;
    private GraphicsContext gc;

    private Button playButton;

    double x = GameConfig.WIDTH, y = GameConfig.HEIGHT;

    public PlayScene() {
        canvas = new Canvas(GameConfig.WIDTH, GameConfig.HEIGHT);
        gc = canvas.getGraphicsContext2D();
        root = new Group();

        playButton = new Button("BACK");
        playButton.setOnAction(event -> {
            SceneManager.getInstance().setCurrentScene(SceneManager.SCENES.MENU);
        });

        root.getChildren().addAll(canvas, playButton);
    }

    public Group getRoot() {
        return root;
    }

    public void update() {
        x -= 0.5;
        y -= 0.5;
        gc.clearRect(0, 0, GameConfig.WIDTH, GameConfig.HEIGHT);
        gc.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        gc.fillRect(x, y, GameConfig.WIDTH / 2.0, GameConfig.HEIGHT / 2.0);
    }

    public  void render() {

    }
}
