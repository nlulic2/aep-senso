package business;
import java.util.ArrayList;
import java.util.List;

import Dto.User;

public class Session {

    private static List<User> sessions = new ArrayList<User>();

    public static void Store(User user) {

        if(sessions.size() > 1) {
            sessions.remove(1);
            sessions.add(user);
        } else {
            sessions.add(user);
        }
    }

    public static void Reset() {
        sessions = new ArrayList<>();
    }

    public static void RemoveAt(int index) {

        if(sessions.size() > 1)
            sessions.remove(index);
    }

    public static User getMain() {

        if(sessions.size() > 0)
            return sessions.get(0);

        return null;
    }

    public static User getSecondPlayer() {

        if(sessions.size() > 1)
            return sessions.get(1);

        return null;
    }

}
