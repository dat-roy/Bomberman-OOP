package uet.oop.bomberman.core.scenes.menu;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.config.GameConfig;
import uet.oop.bomberman.core.sound.BackgroundMusic;

import java.util.Objects;

public class Setting extends VBox {
    public Setting(double prefWidth, double prefHeight) {
        setAlignment(Pos.CENTER);
        setSpacing(8);

        Text title = new Text("Setting");
        title.setFont(new Font("/font1.ttf", 22));
        title.setStyle("-fx-font-weight: bold");
        title.setFill(Color.WHITE);

        TabPane tabPane = new TabPane();
        tabPane.setStyle("-fx-background-color: rgba(128, 128, 128, 1); -fx-open-tab-animation: grow");
        tabPane.setMaxWidth(prefWidth);
        tabPane.setMaxHeight(prefHeight);
        tabPane.setTabMaxWidth(prefWidth / 3);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // Music Tab
        Tab musicTab = new Tab("Music");
        GridPane musicContent = new GridPane();
        musicContent.setAlignment(Pos.CENTER);
        musicContent.setVgap(20);
        musicContent.setHgap(20);
        musicContent.setPadding(new Insets(5, 0, 5, 0));
        musicTab.setContent(musicContent);

        Text mute = new Text("Mute All");
        CheckBox isMute = new CheckBox();
        isMute.setOnMouseClicked(event -> {
            BackgroundMusic.getInstance().setMute(isMute.isSelected());
        });
        musicContent.add(mute, 0, 0);
        musicContent.add(isMute, 1, 0);

        Text volume = new Text("Volume");
        Slider sliderVolume = new Slider();
        {
            BackgroundMusic backgroundMusic = BackgroundMusic.getInstance();
            sliderVolume.setOrientation(Orientation.HORIZONTAL);
            sliderVolume.setValue(backgroundMusic.getVolumn() * 100);
            sliderVolume.setMin(0);
            sliderVolume.setMax(100);
            sliderVolume.setBlockIncrement(20);
            sliderVolume.setMajorTickUnit(20);
            sliderVolume.setMinorTickCount(1);
            sliderVolume.setShowTickLabels(true);
            sliderVolume.setShowTickMarks(true);
            sliderVolume.valueProperty().addListener(observable -> {
                backgroundMusic.setVolume(sliderVolume.getValue() / 100);
            });
        }
        musicContent.add(volume, 0, 1);
        musicContent.add(sliderVolume, 1,1);

        Text theme = new Text("Theme Music");
        ComboBox<String> choiceTheme = new ComboBox<>();
        choiceTheme.getItems().addAll("DEFAULT", "CUSTOMS");
        choiceTheme.setValue("CUSTOMS");
        choiceTheme.setOnAction(event -> {
            if (Objects.equals(choiceTheme.getValue(), BackgroundMusic.THEME.CUSTOMS.toString())) {
                BackgroundMusic.getInstance().setTheme(BackgroundMusic.THEME.CUSTOMS);
            } else {
                BackgroundMusic.getInstance().setTheme(BackgroundMusic.THEME.DEFAULT);
            }
        });
        musicContent.add(theme, 0, 2);
        musicContent.add(choiceTheme, 1, 2);

        // Graphics Tab
        Tab graphicsTab = new Tab("Graphics");
        GridPane graphicsContent = new GridPane();
        graphicsContent.setAlignment(Pos.CENTER);
        graphicsTab.setContent(graphicsContent);

        Text zoom = new Text("Zoom");
        Slider sliderZoom = new Slider();
        {
            sliderZoom.setOrientation(Orientation.HORIZONTAL);
            sliderZoom.setValue(GameConfig.ZOOM);
            sliderZoom.setMin(1);
            sliderZoom.setMax(1.5);
            sliderZoom.setBlockIncrement(0.25);
            sliderZoom.setMajorTickUnit(0.25);
            sliderZoom.setMinorTickCount(0);
            sliderZoom.setShowTickLabels(true);
            sliderZoom.setShowTickMarks(true);
            sliderZoom.setSnapToTicks(true);
            sliderZoom.valueProperty().addListener(observable -> {
                GameConfig.ZOOM = Math.round(sliderZoom.getValue() * 4) / 4.0;
                if (GameConfig.ZOOM == 1.5) {
                    GameConfig.ZOOM = 1.4;
                }
                Main.zoom();
            });
        }
        graphicsContent.add(zoom, 0,0);
        graphicsContent.add(sliderZoom, 1, 0);

        // Controls Tab
        Tab controlsTab = new Tab("Controls");

        tabPane.getTabs().addAll(musicTab, graphicsTab, controlsTab);
        getChildren().addAll(title, tabPane);
    }

}
