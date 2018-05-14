
package it.iseed.controllers.request;

import java.util.List;

public class QuestionExamRequest
{
    private long id_exam;
    private List<QuestionAnswers> questionAnswers;
    
    
    public QuestionExamRequest() {
        super();
    }
    
    public QuestionExamRequest( long id_exam, List<QuestionAnswers> questionAnswers )
    {
        super();
        this.id_exam = id_exam;
        this.questionAnswers = questionAnswers;
    }
    
    public long getId_exam() {
        return id_exam;
    }

    public void setId_exam(long id_exam) {
        this.id_exam = id_exam;
    }
    
    public List<QuestionAnswers> getQuestionAnswers() {
        return questionAnswers;
    }
    
    public void setQuestionAnswers( List<QuestionAnswers> qa ) {
        this.questionAnswers = qa;
    }
    
    @Override
    public String toString() {
        return "QuestionExamRequest [id_exam=" + id_exam + ", questionAnswers=" + questionAnswers + "]";
    }
    
    public static class QuestionAnswers
    {
        private String question;
        private int weight;
        private List<String> answers;
        private int correct_answer;
        
        public QuestionAnswers() {
            super();
        }
        
        public QuestionAnswers( String question, int weight, List<String> answers, int correct_answer )
        {
            super();
            this.question = question;
            this.weight = weight;
            this.answers = answers;
            this.correct_answer = correct_answer;
        }
        
        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public List<String> getAnswers() {
            return answers;
        }

        public void setAnswers(List<String> answers) {
            this.answers = answers;
        }

        public int getCorrect_answer() {
            return correct_answer;
        }

        public void setCorrect_answer(int correct_answer) {
            this.correct_answer = correct_answer;
        }
        
        @Override
        public String toString() {
            return "QuestionAnswers [question=" + question + ", weight=" + weight
                    + ", answers=" + answers + ", correct_answer=" + correct_answer + "]";
        }
    }
}