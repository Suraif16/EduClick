package Model;


public class Admin {
    private String adminName;
    private String email;
    private String password;

    public Admin(int a,int b,int c,int d) {
        this.
    }


    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*constructor*/

    public Admin(String adminName , String email){
        this.adminName = adminName;
        this.email = email;
    }


    public static int getcountteacher(int a) {
        return a;
    }

    public static int gettodaycountteacher(int b) {
        return b;
    }

    public static int getcountstudent(int c) {
        return c;
    }

    public static int gettodaycountstudent(int d) {
        return d;
    }
}
