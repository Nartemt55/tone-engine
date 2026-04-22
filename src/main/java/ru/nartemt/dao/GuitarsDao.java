package ru.nartemt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.nartemt.model.entity.EquipmentType;
import ru.nartemt.model.entity.guitar.Guitar;
import ru.nartemt.model.entity.guitar.settings.BodyShape;
import ru.nartemt.model.entity.guitar.settings.PickupConfig;
import ru.nartemt.model.entity.guitar.settings.Tuning;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GuitarsDao {

    private final DataSource dataSource;

    @Autowired
    public GuitarsDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Guitar> findAll() {
        String sql = " SELECT me.brand, me.model, g.body_shape, g.body_wood, g.pickup_config " +
                "FROM musical_equipment as me " +
                "JOIN guitars AS g ON me.id = g.id ";

        List<Guitar> guitars = new ArrayList<>();

        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Guitar guitar = new Guitar();

                guitar.setBrand(resultSet.getString("brand"));
                guitar.setModel(resultSet.getString("model"));


                String bodyShape = resultSet.getString("body_shape");
                guitar.setBodyShape(BodyShape.valueOf(bodyShape));

                guitar.setBodyWood(resultSet.getString("body_wood"));

                String pickupConfig = resultSet.getString("pickup_config");
                guitar.setPickupConfig(PickupConfig.valueOf(pickupConfig));


                guitars.add(guitar);
            }
            return guitars;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
