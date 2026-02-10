package insurancemanagement.dao.sales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import insurancemanagement.exception.sales.RecordNotFoundException;
import insurancemanagement.exception.sales.SalesManagementException;
import insurancemanagement.vo.sales.SalesVO;

public class SalesDAO implements ISalesDAO {

    //INSERT 
    // created_date -> DB DEFAULT CURRENT_DATE
    // updated_date -> NULL
    public boolean saveSales(SalesVO salesvo) throws SalesManagementException {

        boolean flag = false;
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);

            String query =
                "insert into sales (sales_id, agent_id, customer_id, policy_id, sales_date) "
              + "values (?,?,?,?,?)";

            stmt = connection.prepareStatement(query);

            stmt.setString(1, salesvo.getSalesId());
            stmt.setString(2, salesvo.getAgentId());
            stmt.setString(3, salesvo.getCustomerId());
            stmt.setInt(4, salesvo.getPolicyId());
            stmt.setDate(5, new java.sql.Date(salesvo.getSalesDate().getTime()));

            int i = stmt.executeUpdate();
            if (i != 0) {
                flag = true;
            }

        } catch (SQLException e) {
            throw new SalesManagementException("Error when adding sales");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new SalesManagementException("Error when closing resources");
            }
        }
        return flag;
    }

    //FETCH BY ID
    public SalesVO fetchSalesById(String salesId) throws RecordNotFoundException {

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        SalesVO salesvo = new SalesVO();

        try {
            connection = DriverManager.getConnection(url, userName, password);

            String query = "select * from sales where sales_id=?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, salesId);

            rs = stmt.executeQuery();

            while (rs.next()) {
                salesvo.setSalesId(rs.getString("sales_id"));
                salesvo.setAgentId(rs.getString("agent_id"));
                salesvo.setCustomerId(rs.getString("customer_id"));
                salesvo.setPolicyId(rs.getInt("policy_id"));
                salesvo.setSalesDate(rs.getDate("sales_date"));
                salesvo.setCreatedDate(rs.getDate("created_date"));
                salesvo.setUpdatedDate(rs.getDate("updated_date"));
            }

        } catch (SQLException e) {
            throw new RecordNotFoundException("Error when fetching sales");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new RecordNotFoundException("Error when closing resources");
            }
        }
        return salesvo;
    }

    //FETCH ALL
    public List<SalesVO> fetchAllSales() throws RecordNotFoundException {

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<SalesVO> list = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(url, userName, password);

            String query = "select * from sales";
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {

                SalesVO salesvo = new SalesVO();

                salesvo.setSalesId(rs.getString("sales_id"));
                salesvo.setAgentId(rs.getString("agent_id"));
                salesvo.setCustomerId(rs.getString("customer_id"));
                salesvo.setPolicyId(rs.getInt("policy_id"));
                salesvo.setSalesDate(rs.getDate("sales_date"));
                salesvo.setCreatedDate(rs.getDate("created_date"));
                salesvo.setUpdatedDate(rs.getDate("updated_date"));

                list.add(salesvo);
            }

        } catch (SQLException e) {
            throw new RecordNotFoundException("Error when fetching sales records");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new RecordNotFoundException("Error when closing resources");
            }
        }
        return list;
    }

    //UPDATE
    // updated_date -> SQL CURRENT_DATE
    public boolean updateSales(SalesVO salesvo) throws SalesManagementException {

        boolean flag = false;
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);

            String query =
                "update sales set agent_id=?, customer_id=?, policy_id=?, "
              + "sales_date=?, updated_date=CURRENT_DATE "
              + "where sales_id=?";

            stmt = connection.prepareStatement(query);

            stmt.setString(1, salesvo.getAgentId());
            stmt.setString(2, salesvo.getCustomerId());
            stmt.setInt(3, salesvo.getPolicyId());
            stmt.setDate(4, new java.sql.Date(salesvo.getSalesDate().getTime()));
            stmt.setString(5, salesvo.getSalesId());

            int i = stmt.executeUpdate();
            if (i != 0) {
                flag = true;
            }

        } catch (SQLException e) {
            throw new SalesManagementException("Error when updating sales");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new SalesManagementException("Error when closing resources");
            }
        }
        return flag;
    }

    //DELETE
    public boolean deleteSales(String salesId) throws SalesManagementException {

        boolean flag = false;
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);

            String query = "delete from sales where sales_id=?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, salesId);

            int i = stmt.executeUpdate();
            if (i != 0) {
                flag = true;
            }

        } catch (SQLException e) {
            throw new SalesManagementException("Error when deleting sales");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new SalesManagementException("Error when closing resources");
            }
        }
        return flag;
    }
}
