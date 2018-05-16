
package it.iseed.controllers.response;

import it.iseed.entities.QuestionEntity;

public class ExamQuestionResponse
{
    private String question;
    private int weight;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int correct_answer;
    
    public ExamQuestionResponse() {
        super();
    }
    
    public ExamQuestionResponse( QuestionEntity entity )
    {
        this.question       = entity.getQuestion();
        this.weight         = entity.getWeight();
        this.answer1        = entity.getAnswer1();
        this.answer2        = entity.getAnswer2();
        this.answer3        = entity.getAnswer3();
        this.answer4        = entity.getAnswer4();
        this.correct_answer = entity.getCorrect_answer();
    }
    
    public String getQuestion() {
        return question;
    }
    
    public void setQuestion( String question ) {
        this.question = question;
    }
    
    public int getWeight() {
        return weight;
    }
    
    public void setWeight( int weight ) {
        this.weight = weight;
    }
    
    public String getAnswer1() {
        return answer1;
    }
    
    public void setAnswer1( String answer1 ) {
        this.answer1 = answer1;
    }
    
    public String getAnswer2() {
        return answer2;
    }
    
    public void setAnswer2( String answer2 ) {
        this.answer2 = answer2;
    }
    
    public String getAnswer3() {
        return answer3;
    }
    
    public void setAnswer3( String answer3 ) {
        this.answer3 = answer3;
    }
    
    public String getAnswer4() {
        return answer4;
    }
    
    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }
    
    public int getCorrect_answer() {
        return correct_answer;
    }
    
    public void setCorrect_answer( int correct_answer ) {
        this.correct_answer = correct_answer;
    }
    
    @Override
    public String toString() {
        return "ExamQuestionResponse [question=" + question + ", weight=" + weight + ", answer1=" + answer1
                + ", answer2=" + answer2 + ", answer3=" + answer3 + ", answer4=" + answer4 + ", correct_answer="
                + correct_answer + "]";
    }
}