package com.tenXen.client.controller.component;

import com.tenXen.client.controller.ChatControl;
import com.tenXen.client.util.LayoutUtil;
import com.tenXen.common.util.StringUtil;
import com.tenXen.core.model.UserFriendModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by wt on 2016/11/8.
 */
public class FriendItemControl {

    @FXML
    private AnchorPane itemPane;
    @FXML
    private ImageView headImage;
    @FXML
    private Label userName;
    @FXML
    private Label signature;

    private UserFriendModel model;

    public FriendItemControl(UserFriendModel model) {
        this.model = model;
    }

    public AnchorPane create() {
        FXMLLoader loader = LayoutUtil.load(LayoutUtil.FRIEND_ITEM);
        loader.setController(this);
        AnchorPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pane;
    }

    @FXML
    private void initialize() {
        if (model != null) {
            if (StringUtil.isBlank(model.getFriend_nickname())) {
                this.userName.setText(model.getFriend_userName());
            } else {
                this.userName.setText(model.getFriend_nickname());
            }
            this.signature.setText(model.getFriend_signature());
            Image image = new Image("/image/qo_48X48.jpg");
            this.headImage.setImage(image);
            this.itemPane.setOnMouseEntered(event -> onMouseEnter());
            this.itemPane.setOnMouseExited(event -> onMouseExit());
            this.itemPane.setOnMouseClicked(mouseEvent -> onMouseClick(mouseEvent));
        }
    }

    private void onMouseEnter() {
        Platform.runLater(() -> this.itemPane.setStyle("-fx-background-color:#E0FFFF"));
    }

    private void onMouseExit() {
        Platform.runLater(() -> this.itemPane.setStyle("-fx-background-color:#FFFFFF"));
    }

    private void onMouseClick(MouseEvent mouseEvent) {
        Platform.runLater(() -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                if (mouseEvent.getClickCount() == 2) {
                    ChatControl.getInstance().createChatBox(model.getFriend_userName());
                    ChatControl.getInstance().showToFront(model.getFriend_userName());
                }
            }
        });
    }

}
