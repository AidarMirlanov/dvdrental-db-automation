package com.digital_nomads.beans;

import com.digital_nomads.utils.DBConnection;
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
public class FilmActorBean {
    int actor_id;
    int film_id;
    Timestamp last_update;

    public static List<FilmActorBean> getAllFilmActor () throws SQLException {
        String query = "select * from public.film_actor";
        try(ResultSet resultSet = query(query)) {
            return new BeanProcessor().toBeanList(resultSet,FilmActorBean.class);
        }
    }

    public static FilmActorBean getBy (String column, Object value) throws SQLException {
        String query = "select * from public.film_actor where " + column + " = ?;";
        ResultSet resultSet = DBConnection.query(query, value);
        if (!resultSet.next()) {
            return null;
        } else {
            return new BeanProcessor().toBean(resultSet, FilmActorBean.class);
        }
    }
}
