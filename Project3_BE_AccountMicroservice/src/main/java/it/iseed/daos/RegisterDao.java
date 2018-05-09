package it.iseed.daos;

import com.project.model.UserEntity;

public interface RegisterDao {

    public Long getCheckedUser(UserEntity newUser);

    public UserEntity insertNewUser(UserEntity userEntity);
}
