package com.itiszakk.assetlab.core.util;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

public class FormatUtils {

    private static final String ZERO_BYTES = "0 B";

    private static final String[] SIZE_UNITS = {"B", "KB", "MB", "GB", "TB"};

    private static final DecimalFormat SIZE_FORMAT = new DecimalFormat("#.##");

    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    private static final String RESOLUTION_DELIMITER = "x";

    private FormatUtils() {

    }

    public static String formatLocalDateTime(LocalDateTime ldt) {
        return TIMESTAMP_FORMAT.format(ldt);
    }

    public static String formatBytes(long bytes) {

        if (bytes == 0) {
            return ZERO_BYTES;
        }

        int unit = (int) (Math.log(bytes) / Math.log(1024));
        double size = bytes / Math.pow(1024, unit);
        return StringUtils.joinWith(StringUtils.SPACE, SIZE_FORMAT.format(size), SIZE_UNITS[unit]);
    }

    public static String formatResolution(int width, int height) {
        return width > 0 && height > 0
                ? StringUtils.joinWith(RESOLUTION_DELIMITER, width, height)
                : StringUtils.EMPTY;
    }
}
