package Data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import Model.Theme;

@Database(entities = {Theme.class}, version = 1)
public abstract class ThemeAppDatabase extends RoomDatabase {

    public abstract ThemeDAO getThemeDAO();

}
