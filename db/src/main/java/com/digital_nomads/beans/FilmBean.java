package com.digital_nomads.beans;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.dbutils.BeanProcessor;

import java.math.BigDecimal;
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
public class FilmBean {

    int film_id;
    String title;
    String description;
    int release_year;
    short language_id;
    short rental_duration;
    BigDecimal rental_rate;
    int length;
    BigDecimal replacement_cost;
    String rating;
    Timestamp last_update;
    String special_features;


    public static List<FilmBean> getAllFilm() throws SQLException {
        String query = "select * from public.film ";
        try (ResultSet resultSet = query(query)) {
            return new BeanProcessor().toBeanList(resultSet, FilmBean.class);
        }
    }

    public static FilmBean getBy(String column, Object value) throws SQLException {
        String query = "select * from public.film where " + column + " = ?;";
        ResultSet resultSet = query(query, value);
        if (!resultSet.next()) {
            return null;
        } else {
            return new BeanProcessor().toBean(resultSet, FilmBean.class);
        }
    }
}
