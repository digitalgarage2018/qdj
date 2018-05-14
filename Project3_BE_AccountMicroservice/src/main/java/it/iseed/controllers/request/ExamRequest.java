
package it.iseed.controllers.request;

public class ExamRequest
{
    private String a;
    private String b;
    
    public ExamRequest() {
        super();
    }
    
    public String getA() {
        return a;
    }
    
    public void setA(String a) {
        this.a = a;
    }
    
    public String getB() {
        return b;
    }
    
    public void setB(String b) {
        this.b = b;
    }
    
    @Override
    public String toString() {
        return "MyExam [a=" + a + ", b=" + b + "]";
    }
}