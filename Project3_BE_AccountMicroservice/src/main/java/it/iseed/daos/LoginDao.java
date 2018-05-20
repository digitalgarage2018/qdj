package it.iseed.daos;

import java.util.List;

import it.iseed.entities.UserEntity;

public interface LoginDao
{
	public UserEntity getLoginByInstitutionalEmail( String instEmail );
	
	public UserEntity getLoginByID( long user_id );
    
	public List<UserEntity> getUsersByExamId( long exam_id );
}