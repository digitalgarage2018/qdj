package it.iseed.daos;


import it.iseed.entities.UserEntity;

public interface RegisterDao {

    public Long getCheckedUser(UserEntity newUser);

    public UserEntity insertNewUser(UserEntity userEntity);
}
