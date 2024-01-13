package DatabaseFunction;

public class testDATABAE {
    private String MYSQL_URL = MYSQLDATABASE.getDatabaseURL();
    private String MYSQL_USERNAME = MYSQLDATABASE.getDatabaseUsername();
    private String MYSQL_PASSWORD = MYSQLDATABASE.getDatabasePassword();
    public static  void main(String[] args) {
        System.out.println(MYSQLDATABASE.getDatabaseURL());
        System.out.println(MYSQLDATABASE.getDatabaseUsername());
        System.out.println(MYSQLDATABASE.getDatabasePassword());

    }
}
