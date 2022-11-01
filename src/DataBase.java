import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private List<Client> users = new ArrayList<>();

    public DataBase() {
        users.add(new Client("John Dou", 23, "john@gmail.com", "qwerty123"));
        users.add(new Client("Jane Dou", 19, "jane@gmail.com", "asdfg456"));
        users.add(new Client("Tim Jonson", 16, "tim@gmail.com", "jffk741"));
        users.add(new Client("Lily Potter", 26, "lily@gmail.com", "harry934"));
    }

    public void addUser(String name, int age, String email, String password) {
        users.add(new Client(name, age, email, password));
    }

    public void removeUser(Client user) {
        users.remove(user);
    }

    public boolean checkInDataBase(String email, String password) {
        for (Client c : users) {
            if (c.getEmail().equals(email) && c.getEncryptedPassword() == password.hashCode())
                return true;
        }
        return false;
    }
}
