import java.sql.*;

public class DatabaseSolution {
    private static final String USER_NAME = "gq856imUVV";
    private static final String DATABASE_NAME = "gq856imUVV";
    private static final String PASSWORD = "Ro0nSVTFc1";
    private static final String PORT = "3306";
    private static final String SERVER = "remotemysql.com";

    //create connection to the database
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://"+SERVER+":"+PORT, USER_NAME, PASSWORD);
        //1
//        createTable(con);
        //2
//        insertDog(con,"Lucky", 4, "Golden Retriever");
//        insertDog(con,"Dex", 1, "Husky");
//        insertDog(con,"Nicky", 3, "Bichon Frise");
        //3
//        updateDogAge(con,2,"Dex");
        //4
//        deleteDogFromTable(con, "Nicky");
        //5
        getTableContent(con);

        con.close();
    }

    //A.1 create a table
    private static void createTable(Connection con) throws SQLException {
        String statementToExecute = "CREATE TABLE " + DATABASE_NAME + ".`Dogs`(`name` VARCHAR(40), `age` INT NOT NULL,`breed` VARCHAR(30) NOT NULL);";
        con.createStatement().execute(statementToExecute);
    }
    //A.2 insert 3 dogs
    private static void insertDog(Connection con, String name, int age, String breed) throws SQLException {
        String statementToExecute = "INSERT INTO " +DATABASE_NAME+ ".`Dogs`(`name`, `age`, `breed`) VALUES ('" + name + "','" + age + "', '" + breed + "');";
        con.createStatement().execute(statementToExecute);
    }
    //A.3 update second dog age from one value to another
    private static void updateDogAge(Connection con, int age, String name) throws SQLException {
        String statementToExecute ="UPDATE `" + DATABASE_NAME + "`.`Dogs` SET `age`='"+age+"' WHERE `name`='"+name+"';";
        con.createStatement().executeUpdate(statementToExecute);
    }
    //A.4 Delete the third dog from table
    private static void deleteDogFromTable(Connection con, String name) throws SQLException {
        String statementToExecute = "DELETE FROM `" + DATABASE_NAME + "`.`Dogs` WHERE `name`='"+name+"';";
        con.createStatement().execute(statementToExecute);
    }
    //A.5 Query table and print all dogs
    private static void getTableContent(Connection con) throws SQLException {
        String statementToExecute = "SELECT * FROM " + DATABASE_NAME + ".Dogs;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(statementToExecute);
        while(rs.next()){
            //Retrieve by column name
            String name = rs.getString("name");
            int age  = rs.getInt("age");
            String breed = rs.getString("breed");

            //Display values
            System.out.print("Name: " + name);
            System.out.print(", Age: " + age);
            System.out.print(", Breed: " + breed);
        }
        rs.close();
    }
}