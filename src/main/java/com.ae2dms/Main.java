package com.ae2dms;

import com.ae2dms.controller.MainController;
import com.ae2dms.model.GameEngine;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Class {@link Main} is the root of the class hierarchy.
 *
 * <p>Main class is the root of the class hierarchy. Mainly for internal
 * use within the framework. Main class is a Model of a MVC pattern. Its
 * Controller is Class {@link com.ae2dms.controller.MainController}.
 * Its View is Sokoban.fxml</p>
 *
 * <p>Main Class is to use Sokoban.fxml page to load main page.
 * It contains two buttons, one textfield box and one background image.
 * It extends Application class.</p>
 *
 * @author Tianqi XIA
 * @since 2020/10/25
 * @version 2.1
 */
public class Main extends Application {
    private static Scene scene;

    /**
     * <p>Main Method is used to start and pass
     * the string parameter from command line.</p>
     *
     * @param args String from the command line
     * @since 2020/10/25
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * <p>start method is to use Sokoban.fxml page to load main page.
     * It contains three buttons, one textfield box and one imageView and one animation.</p>
     *
     * @param primaryStage javaFX uses Stage box to load page.
     * @throws Exception In case it needs to throw exception.
     * @since 2020/10/25
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        ImageView title = new ImageView("image/title.png");
        Path path = new Path();
        path.getElements().add(new MoveTo(200,80));
        path.getElements().add(new CubicCurveTo(400, 80, 400,80,400,80));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setPath(path);

        pathTransition.setNode(title);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();

        Group root1 = new Group();

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Sokoban.fxml"));
        Parent root = loader.load();
        root1.getChildren().addAll(title,root);
        primaryStage.setTitle(GameEngine.GAME_NAME);
        Scene scene = new Scene(root1, Color.rgb(35,64,125));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        Image image = new Image("image/Sokoban.png");
        primaryStage.getIcons().add(image);
        primaryStage.show();
        MainController mainController = loader.getController();
        mainController.setImg();
        primaryStage.setOnCloseRequest(event -> System.exit(0));

    }
}

