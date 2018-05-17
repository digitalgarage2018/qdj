package it.iseed.daos;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.iseed.entities.ExamEntity;
import it.iseed.entities.MaterialEntity;
import it.iseed.entities.QuestionEntity;
import it.iseed.entities.SessionEntity;

@Repository
@Transactional
public class ProfessorDaoImpl implements ProfessorDao
{
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public boolean insertMaterial( long exam_id, List<MaterialEntity> material )
    {
        boolean res = false;
        try {
            ExamEntity exam = entityManager.find( ExamEntity.class, exam_id );
            
            List<MaterialEntity> ml = exam.getMaterial_list();
            // Remove the same objects.
            for (int i = ml.size() - 1; i >= 0; i--) {
                MaterialEntity me = ml.get( i );
                boolean found = false;
                for (int j = material.size() - 1; j >= 0; j--) {
                    if (material.get( j ).getFile().equals( me.getFile() ) &&
                        material.get( j ).getType().equals( me.getType() )) {
                        material.remove( j );
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    ml.remove( i );
                }
            }
            
            // Insert the remaining objects.
            for (MaterialEntity m : material) {
                ml.add( m );
            }
            
            exam.setMaterial_list( ml );
            entityManager.flush();
            res = true;
        } catch( Exception e ) {
            //e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean insertQuestions( long exam_id, List<QuestionEntity> questions )
    {
        boolean res = false;
        
        try {
            ExamEntity exam = entityManager.find( ExamEntity.class, exam_id );
            List<QuestionEntity> ql = exam.getQuestion_list();
            // Remove the same objects.
            for (int i = ql.size() - 1; i >= 0; i--) {
                QuestionEntity qe = ql.get( i );
                boolean found = false;
                for (int j = questions.size() - 1; j >= 0; j--) {
                    QuestionEntity q = questions.get( j );
                    // TODO se potessi confrontare gli id sarebbe meglio.
                    if (q.getQuestion().equals( qe.getQuestion() ) &&
                        q.getAnswer1().equals( qe.getAnswer1() ) &&
                        q.getAnswer2().equals( qe.getAnswer2() ) &&
                        q.getAnswer3().equals( qe.getAnswer3() ) &&
                        q.getAnswer4().equals( qe.getAnswer4() ) &&
                        q.getCorrect_answer() == qe.getCorrect_answer()) {
                        questions.remove( j );
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    ql.remove( i );
                }
            }
            
            // Insert the remaining objects.
            for (QuestionEntity q : questions) {
                ql.add( q );
            }
            
            exam.setQuestion_list( ql );
            entityManager.flush();
            res = true;
        } catch( Exception e ){
            e.printStackTrace();
        }
        return res;
    }
    
    @Override
    public List<MaterialEntity> getMaterial( long exam_id )
    {
        ExamEntity exam = entityManager.find( ExamEntity.class, exam_id );
        // Force Hibernate to load the list of materials associated to the exam.
        return exam.getMaterial_list();
    }
    
    @Override
    public List<QuestionEntity> getQuestions( long exam_id )
    {
        ExamEntity exam = entityManager.find( ExamEntity.class, exam_id );
        // Force Hibernate to load the list of questions associated to the exam.
        return exam.getQuestion_list();
    }

    @Override
    public boolean insertSession( long exam_id, Date date_start, Date date_end )
    {
        try {
            ExamEntity exam = entityManager.find( ExamEntity.class, exam_id );
            SessionEntity session = new SessionEntity( date_start, date_end );
            exam.addSession( session );
            entityManager.flush();
            return true;
        } catch ( Exception e ) {
            // Empty body.
        }
        return false;
    }

    @Override
    public List<SessionEntity> getAllSessions( long exam_id )
    {
        ExamEntity exam = entityManager.find( ExamEntity.class, exam_id );
        // Force Hibernate to load the list of sessions associated to the exam.
        return exam.getSession_list();
    }
}
