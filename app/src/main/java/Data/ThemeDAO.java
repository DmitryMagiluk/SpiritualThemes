package Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Model.Theme;

@Dao
public interface ThemeDAO {

    @Insert
    public long addTheme(Theme theme);

    @Update
    public void updateTheme(Theme theme);

    @Delete
    public void deleteTheme(Theme theme);


    @Query("select * from themes")
    public List<Theme> getAllThemes();

    @Query("select * from themes where theme_id ==:themeId")
    public Theme getTheme(long themeId);
}
