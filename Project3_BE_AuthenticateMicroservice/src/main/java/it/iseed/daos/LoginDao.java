package it.iseed.daos;

import it.iseed.entities.ExamEntity;
import it.iseed.entities.UserEntity;

import java.util.List;

public interface LoginDao {

	public UserEntity getLoginByIstitutionalEmail(String istEmail) throws Exception;

	public UserEntity getLoginByID(long user_id);

	public List<ExamEntity> getAllExams();
}
