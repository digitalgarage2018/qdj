package it.iseed.services;

import com.project.dao.RegisterDao;
import com.project.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    RegisterDao registerDao;

    public boolean checkUser(UserEntity newUser) {
        Long checkedUser = registerDao.getCheckedUser(newUser);

        if(checkedUser>0){
            return false;
        }
        return true;
    }

    public UserEntity insertNewUser(UserEntity newUser) {

        String mail = getNewIstitutionalEmail(newUser);
        newUser.setIstitutional_email(mail);

        return registerDao.insertNewUser(newUser);
    }

    private String getNewIstitutionalEmail(UserEntity newUser){

        String n = newUser.getName().substring(0,1);
        String s = newUser.getSurname();
        String email = ""+n+"."+s+"@studenti.unimarina.it";

        return email;
    }

}
