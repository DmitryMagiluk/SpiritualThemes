package Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Model.Friend;

@Dao
public interface FriendDAO {

    @Insert
    long addFriend(Friend friend);

    @Update
    void updateFriend(Friend friend);

    @Delete
    void deleteFriend(Friend friend);


    @Query("select * from friends")
    List<Friend> detAllFriends();

    @Query("select * from friends where friend_id ==:friendId ")
    Friend getFriend(long friendId);

}




