package com.friend.spiritualcommunication;

public class SpiritualCommunicationItem {
    private int imageRecourse;
    private String theme;
    private String verse;
    private String mainText;

    public SpiritualCommunicationItem(int imageRecourse, String theme, String verse, String mainText) {
        this.imageRecourse = imageRecourse;
        this.theme = theme;
        this.verse = verse;
        this.mainText = mainText;
    }

    public int getImageRecourse() {
        return imageRecourse;
    }

    public String getTheme() {
        return theme;
    }

    public String getVerse() {
        return verse;
    }

    public String getMainText() {
        return mainText;
    }
}
