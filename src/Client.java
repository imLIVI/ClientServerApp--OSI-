public class Client {
    private String name;
    private int age;
    private String email;
    private String password;

    public Client(String name, int age, String email, String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public int getEncryptedPassword() {
        return this.password.hashCode();
    }
}
