package lk.ijse.gym_management.Utill;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.layout.Pane;

public class ChangeValidUtill {
    public static void changeColours(Pane pane, JFXTextField txt, boolean isValid) {
        pane.setStyle(pane.getStyle()+";-fx-border-color: #c9c9c9;");
        txt.setStyle(txt.getStyle()+";-jfx-focus-color: #c9c9c9;");
        if(!isValid){
            pane.setStyle(pane.getStyle()+";-fx-border-color: red;");
            txt.setStyle(txt.getStyle()+";-jfx-focus-color: red;");
        }
    }
}
