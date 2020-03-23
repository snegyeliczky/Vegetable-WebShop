package com.codecool.shop.dao.JDBCImplementation;

import com.codecool.shop.dao.DataSourceException;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierJDBC extends JDBCBaseConnection implements SupplierDao {
    @Override
    public void add(Supplier supplier) throws DataSourceException {
        try(Connection connection = getConnection()) {
            PreparedStatement stmt =  connection.prepareStatement("INSERT INTO supplier (name, description) VALUES (?,?)");
            stmt.setString(1,supplier.getName());
            stmt.setString(2,supplier.getDescription());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new DataSourceException("Supplier failed to add supplier to the database", e);
        }

    }

    @Override
    public Supplier find(int id) throws DataSourceException {
        try(Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT id,name,description FROM supplier WHERE id=?");
            stmt.setInt(1,id);
            ResultSet rs =  stmt.executeQuery();
            List<Supplier> suppliers = creatSupplierList(rs);
            rs.close();
            stmt.close();
            return suppliers.get(0);
        } catch (SQLException e) {
            throw new DataSourceException("Supplier failed to find the supplier", e);
        }
    }

    @Override
    public void remove(int id) throws DataSourceException {
        try(Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM supplier WHERE id = ?");
            stmt.setInt(1,id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new DataSourceException("Supplier failed to remove to database", e);
        }
    }

    @Override
    public List<Supplier> getAll() throws DataSourceException {
        try(Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM supplier");
            ResultSet rs = stmt.executeQuery();

            List<Supplier> suppliers = creatSupplierList(rs);

            rs.close();
            stmt.close();
            return suppliers;
        } catch (SQLException e) {
            throw new DataSourceException("Supplier failed to create supplier list", e);
        }
    }

    private List<Supplier> creatSupplierList(ResultSet rs) throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();
        while (rs.next()){
            String supplierName = rs.getString("name");
            String supplierDescription = rs.getString("description");
            Supplier supplier = new Supplier(supplierName,supplierDescription);
            suppliers.add(supplier);
        }
        return suppliers;
    }

}
