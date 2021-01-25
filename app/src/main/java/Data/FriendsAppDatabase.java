package Data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import Model.Friend;

@Database(entities = {Friend.class}, version = 1)
public abstract class FriendsAppDatabase extends RoomDatabase {

    public abstract FriendDAO getFriendDAO();
}
