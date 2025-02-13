/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hungk
 */
public class FAQ {
    private int faqID;         
    private String type;       
    private String question;    
    private String answer; 

    public FAQ() {
    }

    public FAQ(int faqID, String type, String question, String answer) {
        this.faqID = faqID;
        this.type = type;
        this.question = question;
        this.answer = answer;
    }

    public int getFaqID() {
        return faqID;
    }

    public void setFaqID(int faqID) {
        this.faqID = faqID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "FAQ{" + "faqID=" + faqID + ", type=" + type + ", question=" + question + ", answer=" + answer + '}';
    }
    
    
}
