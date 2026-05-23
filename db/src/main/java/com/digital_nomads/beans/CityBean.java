package com.digital_nomads.beans;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.dbutils.BeanProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static com.digital_nomads.utils.DBConnection.query;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class CityBean {

    int city_id;
    String city;
    int country_id;
    Timestamp last_update;


    public static List<CityBean> getAllCity () throws SQLException {
String query = "select * from public.city";
try(ResultSet resultSet = query(query)) {
    return new BeanProcessor().toBeanList(resultSet,CityBean.class);
}
    }

    public static CityBean getBy (String column, String value) throws SQLException {
        String query = "select * from public.city where " + column + " = ?;";
        ResultSet resultSet = query(query, value);
        if (!resultSet.next()) {
            return null;
        } else {
            return new BeanProcessor().toBean(resultSet, CityBean.class);
        }
    }

}
