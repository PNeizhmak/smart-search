package com.db.dao;

import com.db.model.SocialNetwork;
import com.db.model.User;
import com.db.util.DbQueries;
import com.util.DateUtils;
import com.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.Collection;

/**
 * @author Pavel Neizhmak
 */
@Component
public class UserDaoImpl implements IUserDao {

    @Autowired
    private DataSource dataSource;

    @Override
    public void createNewUser(String login, String password, String email) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(DbQueries.USER_INSERT, new String[]{"id"});
            final int activeAccountStatusId = 1;

            ps.setString(1, login);
            ps.setString(2, email);
            ps.setTimestamp(3, DateUtils.newTimestamp());
            ps.setTimestamp(4, DateUtils.newTimestamp());
            ps.setInt(5, activeAccountStatusId);

            return ps;
        }, keyHolder);

        final Number key = keyHolder.getKey();

        if (key != null) {
            System.out.println("User created with id=" + key);
            try {
                storePassword(key.longValue(), password);
                System.out.println("Password has been recorded");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("User creation failed");
        }
    }

    @Override
    public User getByName(String name) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        try {
            return jdbcTemplate.queryForObject(DbQueries.USER_GET, new String[]{name}, (rs, rowNum) -> {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setLastLoginDate(rs.getTimestamp("last_login_ts"));
                user.setUserCreatedDate(rs.getTimestamp("user_created_ts"));
                user.setAccountStatusId(rs.getInt("account_status_id"));
                return user;
            });
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void updateLoginTs(Long id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.update(DbQueries.USER_UPDATE_LAST_LOGIN_TS, ps -> {
            ps.setTimestamp(1, DateUtils.newTimestamp());
            ps.setLong(2, id);
        });
    }

    @Override
    public String getPassword(Long id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        return jdbcTemplate.queryForObject(DbQueries.USER_GET_PASSWORD, new String[]{String.valueOf(id)}, (rs, rowNum) -> {
            return rs.getString("password");
        });
    }

    private void storePassword(Long userId, String password) throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        final String encryptedPass = PasswordUtils.encryptPassword(password);

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(DbQueries.USER_INSERT_PASSWORD);

            ps.setLong(1, userId);
            ps.setString(2, encryptedPass);

            return ps;
        });
    }

    @Override
    public void storeUserSocialId(Long ssUserId, String socialNetwork, Long socialNetworkUserId) {

        Long networkDbId = Long.valueOf(SocialNetwork.valueOf(socialNetwork.toUpperCase()).getNetworkDbId());

        final boolean isExist = checkRecordExists(ssUserId, networkDbId);

        if(!isExist) {
            insertUserInSocialNetwork(ssUserId, socialNetworkUserId, networkDbId);
            System.out.println("user has been inserted. Network: " + socialNetwork + ", id: " + socialNetworkUserId);
        }
    }

    /**
     * Checks record is exists
     *
     * @param ssUserId user id
     * @param networkDbId social network id
     * @return true if record is exists
     */
    private boolean checkRecordExists(Long ssUserId, Long networkDbId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        RowCountCallbackHandler countCallback = new RowCountCallbackHandler();

        jdbcTemplate.query(DbQueries.USER_CHECK_SOCIAL_NETWORK_EXISTS, ps -> {

            ps.setLong(1, ssUserId);
            ps.setLong(2, networkDbId);

        }, countCallback);

        return countCallback.getRowCount() == 1;
    }

    private void insertUserInSocialNetwork(Long ssUserId, Long socialNetworkUserId, Long networkDbId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(DbQueries.USER_STORE_SOCIAL_NETWORK_ID);

            ps.setLong(1, ssUserId);
            ps.setLong(2, socialNetworkUserId);
            ps.setLong(3, networkDbId);

            return ps;
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
