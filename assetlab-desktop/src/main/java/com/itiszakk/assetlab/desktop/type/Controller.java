package com.itiszakk.assetlab.desktop.type;

import javafx.fxml.FXML;

public interface Controller {

    String getId();

    @FXML
    default void initialize() {}
}
