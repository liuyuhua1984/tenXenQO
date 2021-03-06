package com.tenXen.client.controller.component;

import com.tenXen.client.util.LayoutUtil;
import com.tenXen.client.worker.EmotionWorker;
import com.tenXen.common.util.DateUtil;
import com.tenXen.common.util.StringUtil;
import com.tenXen.core.model.MessageModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by wt on 2016/10/31.
 */
public class ChatEmotionControl {

    @FXML
    private Label userName;
    @FXML
    private Label createTime;
    @FXML
    private ImageView emotion;
    @FXML
    private AnchorPane chatEmotion;

    private MessageModel messageModel;

    public ChatEmotionControl(MessageModel messageModel) {
        this.messageModel = messageModel;
    }

    public AnchorPane create() {
        FXMLLoader loader = LayoutUtil.load(LayoutUtil.CHAT_EMOTION);
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
        if (this.messageModel != null) {
            if (StringUtil.isBlank(this.messageModel.getNickname())) {
                this.userName.setText(this.messageModel.getUserName());
            } else {
                this.userName.setText(this.messageModel.getNickname());
            }
            this.createTime.setText(DateUtil.dateStr4(this.messageModel.getCreateTime()));
            try {
                Image emotion = EmotionWorker.getInstance().getEmotion(this.messageModel.getContent());
                this.emotion.setImage(emotion);
//                this.emotion.setFitWidth(emotion.getWidth());
//                this.emotion.setFitHeight(emotion.getHeight());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
