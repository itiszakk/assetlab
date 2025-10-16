package com.itiszakk.assetlab.desktop.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import com.itiszakk.assetlab.desktop.controller.MainController;
import com.itiszakk.assetlab.system.util.TextUtils;

import javafx.stage.FileChooser;

public class FileUtils {

    private static final Set<String> IMAGE_EXTENSIONS = Set.of(
            "jpg",
            "jpeg",
            "png",
            "gif",
            "jfif",
            "bmp"
    );

    private static final Preferences PREFERENCES = Preferences.userNodeForPackage(MainController.class);

    private static final String LAST_DIRECTION_KEY = "last_directory";

    private static final String FILE_CHOOSER_TITLE = "utils.file_chooser.title";

    private static final String DOT = ".";

    private FileUtils() {

    }

    public static List<File> load() {

        FileChooser fileChooser = createFileChooser();

        List<File> files = fileChooser.showOpenMultipleDialog(null);
        if (CollectionUtils.isEmpty(files)) {
            return Collections.emptyList();
        }

        File directory = files.getFirst().getParentFile();
        PREFERENCES.put(LAST_DIRECTION_KEY, directory.getAbsolutePath());

        return files;
    }

    private static FileChooser createFileChooser() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(TextUtils.getText(FILE_CHOOSER_TITLE));

        String lastDirectory = PREFERENCES.get(LAST_DIRECTION_KEY, null);
        if (lastDirectory != null) {
            fileChooser.setInitialDirectory(new File(lastDirectory));
        }

        return fileChooser;
    }

    public static Pair<String, String> splitFileName(String filename) {
        String[] parts = StringUtils.split(filename, DOT);
        return Pair.of(parts[0], parts[1]);
    }

    public static boolean isImageExtension(String extension) {
        return IMAGE_EXTENSIONS.contains(extension);
    }

    public static BufferedImage toImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}