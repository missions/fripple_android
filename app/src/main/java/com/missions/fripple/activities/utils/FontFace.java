package com.missions.fripple.activities.utils;

/**
 * Created by User on 11/2/2015.
 */
public enum FontFace {
    QUAD_NORMAL("Quadranta-Regular.otf"),
    QUAD_BOLD("Quadranta-Bold.otf"),
    ROCKO("RockoFLF.ttf");

    private String font_path;

    FontFace(String font_path) {
        this.font_path = font_path;
    }

    public String getFontFacePath() {
        return font_path;
    }

}
