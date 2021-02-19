package ipoteka_calculator_database;

import jdbc.JdbcUtil;

import java.sql.*;

public class IpotekaJDBC {

    public static void mainDatabase() {
        String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
        String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/orcl"; //ServiceName ile qosulma
        String user = "SANAN";
        String password = "12345";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcUrl, user, password);
            connection.setAutoCommit(false);

            System.out.println("Bazaya Qosuldu");
            addCustomer(connection);
            addCreditInfo(connection);
            addMonthlyPaymentInfo(connection);
            showCustomer(connection);
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC driver tapilmadi !!!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Oracle DB xetasi bash verdi !!! , " + e.getMessage());
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs, ps, connection);
        }
    }


    public static void showCustomer(Connection connection) {
        String sql = "select *\n" +
                "from sanan.customer\n";
//                "order by employee_id,first_name, last_name";

        CustomerTest customer = new CustomerTest();

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setSurname(rs.getString("surname"));
                customer.setBirthday(rs.getDate("birth_date").toLocalDate());
                customer.setGender(rs.getString("gender"));

//                System.out.printf("%s %s %s %s %s\n",
//                        customer.getId(),
//                        customer.getName(),
//                        customer.getSurname(),
//                        customer.getBirthday(),
//                        customer.getGender()
//                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs, ps, null);
        }
    }

    public static void addCustomer(Connection connection) {

        String sql = "insert into sanan.customer(id, name, surname, gender,birth_date)\n" +
                "values(customer_seq.nextval,?,?,?,?)";

        CustomerTest customer = CustomerTest.getInstance();
        CreditTest credit = CreditTest.getInstance();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getSurname());
            ps.setString(3, String.valueOf(customer.getGender()));
            ps.setDate(4, Date.valueOf(customer.getBirthday()));
            CreditTest.getInstance().setCustomerId(customer.getId());
            MonthlyPaymentTest.getInstance().setCreditId(credit.getId());

            int rows = ps.executeUpdate();

            if (rows == 1) {
                System.out.println("Ugurla elave olundu");
            } else {
                System.out.println("Elave olunmadi");
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            JdbcUtil.close(null, ps, null);
        }
    }


    public static void addCreditInfo(Connection connection) {

        String sql = "insert into sanan.credit(id,customer_id, home_price, initial_payment, \n" +
                "credit_amount, interest_amount,action_date)\n " +
                "values(credit_seq.nextval,?,?,?,?,?,?)";

        CreditTest credit = CreditTest.getInstance();

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, credit.getCustomerId());
            ps.setBigDecimal(2, credit.getMenzilinDeyeri());
            ps.setBigDecimal(3, credit.getIlkinOdenis());
            ps.setBigDecimal(4, credit.getKreditMeblegi());
            ps.setBigDecimal(5, credit.getFaizMeblegi());
            ps.setDate(6, Date.valueOf(credit.getActionDate()));

            int rows = ps.executeUpdate();

            if (rows == 1) {
                System.out.println("Ugurla elave olundu");
            } else {
                System.out.println("Elave olunmadi");
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            JdbcUtil.close(null, ps, null);
        }
    }

    public static void addMonthlyPaymentInfo(Connection connection) {

        String sql = "insert into sanan.monthly_payment(id,credit_id, payment_date, base_amount, \n" +
                " interest_amount,total_amount)\n " +
                "values(monthly_payment_seq.nextval,?,?,?,?,?)";

        MonthlyPaymentTest monthlyPayment = MonthlyPaymentTest.getInstance();

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, monthlyPayment.getCreditId());
            ps.setDate(2, Date.valueOf(monthlyPayment.getPaymentDate()));
            ps.setBigDecimal(3, monthlyPayment.getEsasMebleg());
            ps.setBigDecimal(4, monthlyPayment.getFaizMeblegi());
            ps.setBigDecimal(5, monthlyPayment.getUmumiMebleg());

            int rows = ps.executeUpdate();

            if (rows == 1) {
                System.out.println("Ugurla elave olundu");
            } else {
                System.out.println("Elave olunmadi");
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            JdbcUtil.close(null, ps, null);
        }
    }
}
