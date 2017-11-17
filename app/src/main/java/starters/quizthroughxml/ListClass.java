package starters.quizthroughxml;


public class ListClass {
    private String question,answer;

    public ListClass(String question, String answer){
        this.question = question;
        this.answer = answer;

    }


    public void setQuestions(String questions) {
        this.question = question;
    }

    public void setAnswers(String answers) {
        this.answer = answer;
    }

    public String getQuestions() {
        return question;
    }

    public String getAnswers() {
        return answer;
    }
}
