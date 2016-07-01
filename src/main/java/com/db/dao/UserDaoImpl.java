package com.db.dao;

import com.db.model.User;
import com.db.util.DbQueries;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.Collection;

/**
 * @author Pavel Neizhmak
 */
public class UserDaoImpl implements IUserDAO {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createNewUser(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(DbQueries.USERS_INSERT, new String[]{"id"});

            ps.setString(1, user.getUsername());
            ps.setDate(2, user.getLastLoginDate());
            ps.setDate(3, user.getUserCreatedDate());
            ps.setInt(4, user.getAccountStatusId());

            return ps;
        }, keyHolder);

        final Number key = keyHolder.getKey();

        if (key != null) {
            System.out.println("User created with id=" + key);
        } else {
            System.out.println("User creation failed");
        }
    }

    @Override
    public User getById(Long id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        return jdbcTemplate.queryForObject(DbQueries.USER_GET, new String[]{String.valueOf(id)}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setLastLoginDate(rs.getDate("last_login_ts"));
            user.setUserCreatedDate(rs.getDate("user_created_ts"));
            user.setAccountStatusId(rs.getInt("account_status_id"));
            return user;
        });
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Collection getAll() {
        return null;
    }
}
