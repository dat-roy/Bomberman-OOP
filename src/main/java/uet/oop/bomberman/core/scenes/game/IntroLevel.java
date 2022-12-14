package uet.oop.bomberman.core.scenes.game;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import uet.oop.bomberman.config.GameConfig;
import uet.oop.bomberman.core.scenes.PlayScene;

import java.net.URISyntaxException;

public class IntroLevel extends Group {
    private static final int DEFAULT_TIME = 2000;
    private final Animation animation;
    private final Text title;

    private static class SingletonHelper {
        private static final IntroLevel INSTANCE = new IntroLevel();
    }

    public static IntroLevel getInstance() {
        return IntroLevel.SingletonHelper.INSTANCE;
    }

    private IntroLevel() {
        Image bkgImage = null;
        Image loadImage = null;
        try {
            bkgImage = new Image(IntroLevel.class.getResource("/UI/introLevel.png").toURI().toString());
            loadImage = new Image(IntroLevel.class.getResource("/UI/loading.png").toURI().toString());
        } catch (URISyntaxException e) {
            System.out.println("intro level");
        }

        ImageView background = new ImageView(bkgImage);
        background.setFitHeight(GameConfig.SCENE_HEIGHT);
        background.setFitWidth(GameConfig.SCENE_WIDTH);

        title = new Text();
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        title.setFill(Color.SNOW);
        title.setLayoutX(100);
        title.setLayoutY(150);

        ImageView loading = new ImageView(loadImage);
        loading.setViewport(new Rectangle2D(0, 0, 74, 74));
        loading.setFitWidth(85);
        loading.setFitHeight(85);
        loading.setLayoutX(100);
        loading.setLayoutY(350);

        animation = new SpriteAnimation(
                loading, Duration.millis(DEFAULT_TIME),
                18, 6,
                0, 0,
                74, 74
        );
        animation.setOnFinished(event -> {
            setVisible(false);
            setDisable(true);
            PlayScene.getInstance().setStatus(PlayScene.STATUS.PLAYING);
        });

        getChildren().addAll(background, title, loading);
    }

    public void reset(int level) {
        animation.setCycleCount(1);
        animation.play();
        title.setText("Stage " + level);
        setVisible(true);
        setDisable(false);
    }

    /**
     * @return time in second.
     */
    public static int getDefaultTime() {
        return DEFAULT_TIME / 1000;
    }
}
