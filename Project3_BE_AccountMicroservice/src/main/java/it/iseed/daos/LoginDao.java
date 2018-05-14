package it.iseed.daos;

import java.util.List;

import it.iseed.entities.ExamEntity;
import it.iseed.entities.UserEntity;

public interface LoginDao {

	public UserEntity getLoginByInstitutionalEmail( String istEmail);

	public UserEntity getLoginByID(long user_id);
	
	public List<ExamEntity> getAllExams();
	
}
