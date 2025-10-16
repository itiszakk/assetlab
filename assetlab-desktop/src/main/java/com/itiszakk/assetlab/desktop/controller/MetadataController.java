package com.itiszakk.assetlab.desktop.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.itiszakk.assetlab.core.service.AssetMetadataService;
import com.itiszakk.assetlab.core.service.AssetService;
import com.itiszakk.assetlab.core.service.TagService;
import com.itiszakk.assetlab.core.type.Asset;
import com.itiszakk.assetlab.core.type.AssetMetadata;
import com.itiszakk.assetlab.core.type.Tag;
import com.itiszakk.assetlab.core.util.FormatUtils;
import com.itiszakk.assetlab.desktop.controller.listener.AssetSelectionListener;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MetadataController implements AssetSelectionListener {

    private static final String EMPTY_TAG_CONTAINER_ID = "$emptyTagContainer";

    private static final String EMPTY_TAG_FIELD_ID = "$emptyTagField";

    private static final String TAG_CONTAINER_ID_PREFIX = "$tag";

    private static final String TAG_CONTAINER_ID_DELIMITER = ".";

    private final AssetService assetService;

    private final AssetMetadataService assetMetadataService;

    private final TagService tagService;

    private final ObjectProperty<AssetMetadata> metadataProperty = new SimpleObjectProperty<>();

    @FXML
    private VBox metadataContainer;

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label extensionLabel;

    @FXML
    private Label pathLabel;

    @FXML
    private Label resolutionLabel;

    @FXML
    private Label sizeLabel;

    @FXML
    private TextField displayNameField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private Label timestampLabel;

    @FXML
    private FlowPane tagsPane;

    @Inject
    public MetadataController(AssetService assetService,
                              AssetMetadataService assetMetadataService,
                              TagService tagService) {
        this.assetService = assetService;
        this.assetMetadataService = assetMetadataService;
        this.tagService = tagService;
    }

    @FXML
    private void initialize() {
        metadataContainer.disableProperty().bind(Bindings.isNull(metadataProperty));
    }

    @Override
    public void onAssetSelected(String id) {

        Asset asset = assetService.load(id);
        idLabel.setText(id);
        nameLabel.setText(asset.getName());
        extensionLabel.setText(asset.getExtension());
        pathLabel.setText(asset.getPath());
        sizeLabel.setText(FormatUtils.formatBytes(asset.getSize()));

        String resolution = FormatUtils.formatResolution(asset.getWidth(), asset.getHeight());
        if (StringUtils.isNotEmpty(resolution)) {
            resolutionLabel.setText(resolution);
        }

        AssetMetadata metadata = assetMetadataService.load(id);
        metadataProperty.set(metadata);
        displayNameField.setText(metadata.getAssetDisplayName());
        descriptionArea.setText(metadata.getDescription());
        timestampLabel.setText(FormatUtils.formatLocalDateTime(metadata.getTimestamp()));

        clearTagsPane();

        List<Tag> tags = metadata.getTags();
        if (CollectionUtils.isNotEmpty(tags)) {

            List<HBox> tagContainers = tags.stream()
                    .map(Tag::getName)
                    .map(this::createTagContainer)
                    .toList();

            tagsPane.getChildren().addAll(0, tagContainers);
        }
    }

    @Override
    public void onClearSelection() {
        metadataProperty.set(null);
        idLabel.setText(null);
        nameLabel.setText(null);
        extensionLabel.setText(null);
        pathLabel.setText(null);
        resolutionLabel.setText(null);
        sizeLabel.setText(null);
        displayNameField.setText(null);
        descriptionArea.setText(null);
        timestampLabel.setText(null);
        clearTagsPane();
    }

    private void clearTagsPane() {
        Node button = tagsPane.getChildren().getLast();
        tagsPane.getChildren().clear();
        tagsPane.getChildren().add(button);
    }

    @FXML
    private void saveMetadata() {
        AssetMetadata metadata = metadataProperty.get();
        metadata.setAssetDisplayName(displayNameField.getText());
        metadata.setDescription(descriptionArea.getText());
        metadata.setTimestamp(LocalDateTime.now());
        metadata.setTags(collectTags(metadata.getAssetId()));

        assetMetadataService.save(metadata);
    }

    private List<Tag> collectTags(String assetId) {
        return tagsPane.getChildren().stream()
                .filter(node -> !isNodeWithId(node, EMPTY_TAG_CONTAINER_ID))
                .filter(node -> isNodeIdStarsWith(node, TAG_CONTAINER_ID_PREFIX))
                .map(container -> Tag.builder()
                        .assetId(assetId)
                        .name(extractTagName(container))
                        .build())
                .toList();
    }

    @FXML
    private void deleteAsset() {
        String assetId = metadataProperty.get().getAssetId();
        assetService.delete(assetId);
        assetMetadataService.delete(assetId);
    }

    @FXML
    private void upsertTag() {
        if (isEmptyTagCreated()) {
            saveTag();
        } else {
            createEmptyTag();
        }
    }

    private void saveTag() {

        TextField field = getEmptyTagField();
        if (field == null) {
            return;
        }

        AssetMetadata metadata = metadataProperty.get();
        String tagName = field.getText();

        if (StringUtils.isNotEmpty(tagName)) {

            int index = tagsPane.getChildren().size() - 2;
            tagsPane.getChildren().add(index, createTagContainer(tagName));

            Tag tag = Tag.builder()
                    .assetId(metadata.getAssetId())
                    .name(tagName)
                    .build();

            tagService.save(tag);
        }

        tagsPane.getChildren().removeIf(node -> isNodeWithId(node, EMPTY_TAG_CONTAINER_ID));
    }

    private void createEmptyTag() {

        TextField field = new TextField();
        field.setId(EMPTY_TAG_FIELD_ID);

        HBox container = new HBox();
        container.setId(EMPTY_TAG_CONTAINER_ID);
        container.getChildren().add(field);

        int index = tagsPane.getChildren().size() - 1;
        tagsPane.getChildren().add(index, container);
    }

    private void deleteTag(String tagName) {
        tagsPane.getChildren().removeIf(node -> isNodeWithId(node, buildTagContainerId(tagName)));
        tagService.delete(metadataProperty.get().getAssetId(), tagName);
    }

    private HBox createTagContainer(String tagName) {

        Label text = new Label(tagName);

        Button button = new Button("x");
        button.getStyleClass().add("delete-tag-button");
        button.setOnAction(event -> deleteTag(tagName));

        HBox container = new HBox();
        container.setId(buildTagContainerId(tagName));
        container.getStyleClass().add("tag-container");
        container.getChildren().add(text);
        container.getChildren().add(button);

        return container;
    }

    private boolean isEmptyTagCreated() {
        return tagsPane.getChildren().stream().anyMatch(node -> isNodeWithId(node, EMPTY_TAG_CONTAINER_ID));
    }

    private HBox getEmptyTagContainer() {
        return tagsPane.getChildren().stream()
                .filter(node -> isNodeWithId(node, EMPTY_TAG_CONTAINER_ID))
                .map(HBox.class::cast)
                .findFirst()
                .orElse(null);
    }

    private TextField getEmptyTagField() {

        HBox container = getEmptyTagContainer();
        if (container == null) {
            return null;
        }

        return container.getChildren().stream()
                .filter(node -> isNodeWithId(node, EMPTY_TAG_FIELD_ID))
                .map(TextField.class::cast)
                .findFirst()
                .orElse(null);
    }

    private static boolean isNodeWithId(Node node, String id) {
        return node.getId() != null && node.getId().equals(id);
    }

    private static boolean isNodeIdStarsWith(Node node, String prefix) {
        return node.getId() != null && node.getId().startsWith(prefix);
    }

    private static String buildTagContainerId(String tagName) {
        return StringUtils.joinWith(TAG_CONTAINER_ID_DELIMITER, TAG_CONTAINER_ID_PREFIX, tagName);
    }

    private static String extractTagName(Node container) {
        return StringUtils.substringAfter(container.getId(), TAG_CONTAINER_ID_DELIMITER);
    }
}
