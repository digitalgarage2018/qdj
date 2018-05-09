package it.iseed.services;

import it.iseed.daos.RegisterDao;
import it.iseed.entities.UserEntity;
import it.iseed.util.ResponseTransferObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    RegisterDao registerDao;

    public boolean checkUser(UserEntity newUser) {

        Long checkedUser = null;

        try{
            checkedUser = registerDao.getCheckedUser(newUser);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        if(checkedUser>0){
            return false;
        }
        return true;
    }

    public ResponseTransferObject insertNewUser(UserEntity newUser) {

        ResponseTransferObject response = new ResponseTransferObject(ResponseTransferObject.ResponseState.NOCHANGE.getDescription(), ResponseTransferObject.ResponseState.NOCHANGE, null,null);

        newUser.setIstitutional_email(getNewIstitutionalEmail(newUser));

        try{

            registerDao.insertNewUser(newUser);
            response.setState(ResponseTransferObject.ResponseState.SUCCESS.getCode());
            response.setMessage("Studente registrato con successo !");
            response.setResult(newUser);

        }catch (Exception e){
            response.setMessage("Eccezione nel registerDao: " + e.getMessage());
            response.setState(ResponseTransferObject.ResponseState.EXCEPTION.getCode());
        }
        return response;
    }

    private String getNewIstitutionalEmail(UserEntity newUser){

        String n = newUser.getName().substring(0,1);
        String s = newUser.getSurname();
        String email = ""+n+"."+s+"@studenti.unimarina.it";

        return email;
    }

}
