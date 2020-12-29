package Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "themes")
public class Theme {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "theme_id")
    private long id;

    @ColumnInfo(name = "theme_topic")
    private String topic;

    @ColumnInfo(name = "theme_text")
    private String text;

    @ColumnInfo(name = "theme_main_verse")
    private String mainVerse;

    @ColumnInfo(name = "theme_image_path")
    private int imagePath;


    public Theme(long id, String topic, String text, String mainVerse, int imagePath) {
        this.id = id;
        this.topic = topic;
        this.text = text;
        this.mainVerse = mainVerse;
        this.imagePath = imagePath;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }


    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }


    public String getMainVerse() {
        return mainVerse;
    }
    public void setMainVerse(String mainVerse) {
        this.mainVerse = mainVerse;
    }


    public int getImagePath() {
        return imagePath;
    }
    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }
}
