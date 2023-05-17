import java.sql.*;
import java.util.Scanner;

public class ClienteDB {

    public static void main(String[] args)throws  ClassNotFoundException, SQLException {



        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/develop?user=root&&password=";
        Connection conn = DriverManager.getConnection(url);



        String query = "select * from cliente";
        ResultSet resultSet = conn.createStatement().executeQuery(query);


        String query1 = "select * from cliente where ciudad = 'Lima'";
        ResultSet resultSet1 = conn.createStatement().executeQuery(query1);


        System.out.println("-------------------- Select de toda la Tabla con Statement ---------------------------");
        int count=1;
        while(resultSet.next()) {
            String toPrint = "El resultado de la fila " + count + "  es  "+ "Id : "+resultSet.getInt("id") + " " + resultSet.getString("nombres")
                        + " " + resultSet.getString("apellido_paterno")
                        + " " + resultSet.getString("apellido_materno")
                        + " " + resultSet.getString("fecha_nacimiento")
                    + " " + resultSet.getString("correo")
                        + " " + resultSet.getString("ciudad");
            System.out.println(toPrint);
            count++;
        }

        System.out.println("-------------------- Select de Ciudad = Lima con Statement ----------------------------");


        int count1=1;
        while(resultSet1.next()) {
            String toPrint1 = "El resultado de la fila " + count1 + "  es : " + "Id : "+resultSet1.getInt("id") + " " + resultSet1.getString("nombres")
                    + " " + resultSet1.getString("apellido_paterno")
                    + " " + resultSet1.getString("apellido_materno")
                    + " " + resultSet1.getString("fecha_nacimiento")
                    + " " + resultSet1.getString("correo")
                    + " " + resultSet1.getString("ciudad");
            System.out.println(toPrint1);
            count1++;
        }


        System.out.println("-------------------- Actualizar Correo por el Id con PrepareStatement ---------------------------");



        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id del cliente que desea actualizar");
        String upId = scanner.nextLine();

        System.out.println("Ingrese el nuevo correo electronico ");
        String upCorreo = scanner.nextLine();



        String updateStr = "Update  cliente set correo = ? where id = ?";

        PreparedStatement pstmt = conn.prepareStatement(updateStr);
        pstmt.setString(1,upCorreo);
        pstmt.setInt(2,Integer.parseInt(upId));

        int upCo= pstmt.executeUpdate();
        System.out.println("Se Actualizó " +upCo+ " filas") ;


        System.out.println("-------------------- Ingresar un nuevo Registro de Cliente  con Statement ---------------------------");


        System.out.println("Ingrese un Nombre a ingresar:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el apellido paterno:");
        String apPat = scanner.nextLine();
        System.out.println("Ingrese el apellido materno:");
        String apMat = scanner.nextLine();
        System.out.println("Ingrese la fecha de nacimiento en formato Año-Mes-Dia:");
        String fecNac = scanner.nextLine();
        System.out.println("Ingrese un correo:");
        String correo = scanner.nextLine();
        System.out.println("Ingrese la ciudad:");
        String ciudad = scanner.nextLine();




        String createCliente = "insert into cliente(nombres, apellido_paterno, apellido_materno, fecha_nacimiento, correo, ciudad)values('"+nombre+"','"+apPat+"','"+apMat+"','"+fecNac+"','"+correo+"','"+ciudad+"')";
        Statement stmt2 = conn.createStatement();
        int nuevoCliente = stmt2.executeUpdate(createCliente);
        System.out.println("Se agregó " +nuevoCliente+ " filas") ;


        System.out.println("-------------------- Eliminar un cliente por el ID con PrepareStatement ---------------------------");


        System.out.println("Ingrese un Id a eliminar:");
        String eliminar = scanner.nextLine();


        String deleteStr = "Delete from cliente"+" where id = ?";

        PreparedStatement pstmt1 = conn.prepareStatement(deleteStr);
        pstmt1.setInt(1,Integer.parseInt(eliminar));

        int delId= pstmt1.executeUpdate();
        System.out.println("Se eliminó " +delId+ " filas") ;


        }
    }

