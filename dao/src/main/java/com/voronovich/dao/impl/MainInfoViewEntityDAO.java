package com.voronovich.dao.impl;

import org.apache.log4j.Logger;
import com.voronovich.dao.ADAO;
import com.voronovich.dao.IMainInfoViewEntity;
import com.voronovich.entity.MainInfoViewEntity;
import com.voronovich.connection.ConnectionCreator;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 * Class implements part of the DAO/DTO - view
 * by MainInfoEntity and AdditionalInfoEntity
 *
 * @author Dmitry V
 * @version 1.0
 */
public class MainInfoViewEntityDAO extends ADAO implements IMainInfoViewEntity {

    private static Logger log = Logger.getLogger(MainInfoEntityDAO.class);

    /**
     * Separate part of common logic for future expansion
     *
     * @param rs Object ResultSet
     * @param mI Object MainInfoViewEntity
     * @return MainInfoViewEntity
     * @throws SQLException
     */
    private MainInfoViewEntity getMainInfoViewEntity(ResultSet rs, MainInfoViewEntity mI) throws SQLException {
        mI.setId(rs.getInt("ID"));
        mI.setBrand(rs.getString("Brand"));
        mI.setModel(rs.getString("Model"));
        mI.setPrice(rs.getDouble("Price"));
        mI.setReleaseDate(rs.getString("ReleaseDate"));
        mI.setImg(rs.getString("Picture"));
        mI.setFk_Catalog(rs.getInt("FK_Catalog"));
        mI.setTitle(rs.getString("Title"));
        mI.setValue(rs.getString("Value"));
        return mI;
    }

    @Override
    public List<String> getByID(int id) {

        List<String> list = new ArrayList<>();
        List<MainInfoViewEntity> list1 = new ArrayList<>();
        String sql = "SELECT * FROM mainInfoView WHERE ID = ? ;";
        try (Connection connection = ConnectionCreator.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                MainInfoViewEntity mainInfoViewEntity = new MainInfoViewEntity();
                getMainInfoViewEntity(rs, mainInfoViewEntity);
                list1.add(mainInfoViewEntity);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            log.error(e);
        }
        int listElement = 0;
        list.add(list1.get(listElement).getId() + "");
        list.add(list1.get(listElement).getBrand());
        list.add(list1.get(listElement).getModel());
        list.add(Double.toString(list1.get(listElement).getPrice()));
        list.add(list1.get(listElement).getReleaseDate());
        list.add(list1.get(listElement).getImg());
        for (MainInfoViewEntity mainInfoViewEntity : list1) {
            list.add(mainInfoViewEntity.getTitle());
            list.add(mainInfoViewEntity.getValue());
        }
        return list;
    }
}
