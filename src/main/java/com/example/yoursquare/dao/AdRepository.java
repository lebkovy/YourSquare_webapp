package com.example.yoursquare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdRepository {

private Connection connection;

	private String createTableSql = "CREATE TABLE ad("
			+ "id bigint GENERATED BY DEFAULT AS IDENTITY,"
			+ "id INT,"
			+ "title VARCHAR(20),"
			+ "fee INT,"
      + "adress VARCHAR(20),"
      + "city VARCHAR(20),"
      + "zipcode VARCHAR(20),"
      + "space float,"
      + "furnished BOOLEAN,"
      + "active BOOLEAN,"
      + "addDate DATE,"
      + "endDate DATE,"
      + "room INT,"
      + "gallery VARCHAR(20),"
      + "content VARCHAR(20),"
      + "type ENUM(SELL,RENT),"
      + "property ENUM(FLAT,ROOM),"
			+ ")";
	private Statement createTable;


	private String selectByIdSql = "SELECT * FROM ad WHERE id=?";
	private String selectAllSql = "SELECT * FROM ad";

	private PreparedStatement selectById;
	private PreparedStatement selectAll;

	public AdRepository(Connection connection) {
		this.connection = connection;

		try {
			createTable = connection.createStatement();

			boolean tableExists = false;
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
			while(rs.next()){
				if(rs.getString("TABLE_NAME").equalsIgnoreCase("ad")){
					tableExists=true;
					break;
				}
			}
			if(!tableExists)
				createTable.executeUpdate(createTableSql);
			selectById = connection.prepareStatement(selectByIdSql);
			selectAll = connection.prepareStatement(selectAllSql);


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Ad get(int Id){
		try{

			selectById.setInt(1, Id);
			ResultSet rs = selectById.executeQuery();
			while(rs.next()){
				Ad result = new Ad();
				result.setId(rs.getInt("id"));
				result.setTitle(rs.getString("title"));
				result.setFee(rs.getInt("fee"));
				result.setAdress(rs.getString("adress"));
        result.setCity(rs.getString("city"));
        result.setZipcode(rs.getString("zipcode"));
        result.setSpace(rs.getFloat("space"));
        result.setFurnished(rs.getBoolean("furnished"));
        result.setActive(rs.getBoolean("active"));
        result.setaddDate(rs.getDate("addDate"));
        result.setendDate(rs.getDate("endDate"));
        result.setRoom(rs.getInt("room"));
        result.setGallery(rs.getString("gallery"));
        result.setContent(rs.getString("content"));
        result.setType(rs.getEnum("type"));
        result.setProperty(rs.getEnum("property"));
				return result;
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}

	public List<Ad> getAll(){
		try{
			List<Ad> result = new ArrayList<Ad>();
			ResultSet rs = selectAll.executeQuery();
			while(rs.next()){
				Ad a = new Ad();
        a.setId(rs.getInt("id"));
				a.setTitle(rs.getString("title"));
				a.setFee(rs.getInt("fee"));
				a.setAdress(rs.getString("adress"));
        a.setCity(rs.getString("city"));
        a.setZipcode(rs.getString("zipcode"));
        a.setSpace(rs.getFloat("space"));
        a.setFurnished(rs.getBoolean("furnished"));
        a.setActive(rs.getBoolean("active"));
        a.setaddDate(rs.getDate("addDate"));
        a.setendDate(rs.getDate("endDate"));
        a.setRoom(rs.getInt("room"));
        a.setGallery(rs.getString("gallery"));
        a.setContent(rs.getString("content"));
        a.setType(rs.getEnum("type"));
        a.setProperty(rs.getEnum("property"));
				result.add(a);
			}
			return result;
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}

}
