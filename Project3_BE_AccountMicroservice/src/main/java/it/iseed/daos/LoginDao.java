package it.iseed.daos;

import it.iseed.entities.UserEntity;

public interface LoginDao {

	public UserEntity getLoginByIstitutionalEmail(String istEmail);

	public UserEntity getLoginByID(long user_id);
}
