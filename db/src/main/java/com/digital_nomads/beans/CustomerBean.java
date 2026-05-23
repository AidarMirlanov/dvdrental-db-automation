package com.digital_nomads.beans;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.dbutils.BeanProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
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
public class CustomerBean {

    int customer_id;
    int store_id;
    String first_name;
    String last_name;
    String email;
    String address_id;
    boolean activebool;
    Date create_date;
    Timestamp last_update;


    public static List<CustomerBean> getAllCustomer () throws SQLException {
        String query = "select * from public.customer";
        try(ResultSet resultSet = query(query)) {
            return new BeanProcessor().toBeanList(resultSet,CustomerBean.class);
        }
    }

    public static CustomerBean getBy (String column, String value) throws SQLException {
        String query = "select * from public.customer where " + column + " = ?;";
        ResultSet resultSet = query(query, value);
        if (!resultSet.next()) {
            return null;
        } else {
            return new BeanProcessor().toBean(resultSet, CustomerBean.class);
        }
    }
}
