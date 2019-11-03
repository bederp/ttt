import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

class SquareSkin extends StackPane {
    static final Image noughtImage = new Image(
            "http://icons.iconarchive.com/icons/double-j-design/origami-colored-pencil/128/green-cd-icon.png"
    );
    static final Image crossImage = new Image(
            "http://icons.iconarchive.com/icons/double-j-design/origami-colored-pencil/128/blue-cross-icon.png"
    );

    private final ImageView imageView = new ImageView();

    SquareSkin(final Square square) {
        getStyleClass().add("square");

        imageView.setMouseTransparent(false);

        getChildren().setAll(imageView);
        setPrefSize(crossImage.getHeight() + 20, crossImage.getHeight() + 20);

        setOnMousePressed(mouseEvent -> square.pressed());
    }

    void setState(Square.State state) {
        switch (state) {
            case EMPTY:
                imageView.setImage(null);
                break;
            case NOUGHT:
                imageView.setImage(noughtImage);
                break;
            case CROSS:
                imageView.setImage(crossImage);
                break;
        }
    }
}
