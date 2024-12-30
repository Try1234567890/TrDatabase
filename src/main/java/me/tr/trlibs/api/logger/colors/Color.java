package me.tr.trlibs.api.logger.colors;

public enum Color {
    DARK_BLUE("#0000AA", new int[]{0, 0, 170}),
    DARK_GREEN("#00AA00", new int[]{0, 170, 0}),
    DARK_AQUA("#00AAAA", new int[]{0, 170, 170}),
    DARK_RED("#AA0000", new int[]{170, 0, 0}),
    DARK_PURPLE("#AA00AA", new int[]{170, 0, 170}),
    GOLD("#FFAA00", new int[]{255, 170, 0}),
    GRAY("#AAAAAA", new int[]{170, 170, 170}),
    DARK_GRAY("#555555", new int[]{85, 85, 85}),
    BLUE("#5555FF", new int[]{85, 85, 255}),
    BLACK("#000000", new int[]{0, 0, 0}),
    GREEN("#55FF55", new int[]{85, 255, 85}),
    AQUA("#55FFFF", new int[]{85, 255, 255}),
    RED("#FF5555", new int[]{255, 85, 85}),
    PURPLE("#FF55FF", new int[]{255, 85, 255}),
    YELLOW("#FFFF55", new int[]{255, 255, 85}),
    WHITE("#FFFFFF", new int[]{255, 255, 255}),
    ORANGE("#FFA500", new int[]{255, 165, 0}),
    PINK("#FFC0CB", new int[]{255, 192, 203}),
    LIME("#00FF00", new int[]{0, 255, 0}),
    CYAN("#00FFFF", new int[]{0, 255, 255}),
    LIGHT_BLUE("#ADD8E6", new int[]{173, 216, 230}),
    LIGHT_GREEN("#90EE90", new int[]{144, 238, 144}),
    LIGHT_AQUA("#E0FFFF", new int[]{224, 255, 255}),
    LIGHT_RED("#FFA07A", new int[]{255, 160, 122}),
    LIGHT_PURPLE("#DDA0DD", new int[]{221, 160, 221}),
    LIGHT_GRAY("#D3D3D3", new int[]{211, 211, 211}),
    BROWN("#8B4513", new int[]{139, 69, 19}),
    BEIGE("#F5F5DC", new int[]{245, 245, 220}),
    IVORY("#FFFFF0", new int[]{255, 255, 240}),
    OLIVE("#808000", new int[]{128, 128, 0}),
    TEAL("#008080", new int[]{0, 128, 128}),
    MAGENTA("#FF00FF", new int[]{255, 0, 255}),
    MAROON("#800000", new int[]{128, 0, 0}),
    NAVY("#000080", new int[]{0, 0, 128}),
    SILVER("#C0C0C0", new int[]{192, 192, 192}),
    TAN("#D2B48C", new int[]{210, 180, 140}),
    ALTO("#cecece", new int[]{206, 206, 206}),
    BRONZE("#CD7F32", new int[]{205, 127, 50}),
    RESET("#ffffff", new int[]{255, 255, 255});

    private final String hex;
    private final int[] rgb;

    Color(String hex, int[] rgb) {
        this.hex = hex;
        this.rgb = rgb;
    }

    public String toHex() {
        return hex;
    }

    public String toRGB() {
        return "rgb(" + rgb[0] + "," + rgb[1] + "," + rgb[2] + ")";
    }

    public String toAnsi() {
        return "\u001B[38;2;" +
                Integer.parseInt(toHex().substring(1, 3), 16) + ";" +
                Integer.parseInt(toHex().substring(3, 5), 16) + ";" +
                Integer.parseInt(toHex().substring(5, 7), 16) + "m";
    }
}
