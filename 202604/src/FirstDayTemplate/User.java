package FirstDayTemplate;

class User {
    private Integer id;
    private String username;
    private String email;
    private String password; // 优化：使用 String

    public User(Integer id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void printInfo() {
        System.out.println("用户ID: " + id + ", 用户名: " + username + ", 邮箱: " + email);
    }

    // 优化：方法只负责“验证”，不负责“询问”。这样这个方法以后在网页版也能用。
    public boolean checkPassword(String input) {
        return this.password.equals(input);
    }

    public String getUsername() {
        return username;
    }
}
