package Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "friends")
public class Friend {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "friend_id")
    private int id;

    @ColumnInfo(name = "friend_name")
    private String name;

    @ColumnInfo(name = "friend_note")
    private String note;

    @ColumnInfo(name = "friend_indexingSharedPrefProgress")
    private int indexingSharedPrefProgress;

    public Friend(int id, String name, String note, int indexingSharedPrefProgress) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.indexingSharedPrefProgress = indexingSharedPrefProgress;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    public int getIndexingSharedPrefProgress() {
        return indexingSharedPrefProgress;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setIndexingSharedPrefProgress(int indexingSharedPrefProgress) {
        this.indexingSharedPrefProgress = indexingSharedPrefProgress;
    }
}
