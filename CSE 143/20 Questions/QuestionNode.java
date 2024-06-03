// Deepesh Singh
// CSE 143
// Section AR - Adithi Raghavan
// Project: 20 Questions
// Class QuestionNode can be used to store some given
// text, which is either a question/answer, and its corresponding
// yes and no responses.

public class QuestionNode {

   // question/answer
   public String data;
   
   // response if answer to question is yes
   // null - if data is an answer
   public QuestionNode yes; 
   
   // response if answer to question is no
   // null - if data is an answer
   public QuestionNode no; 
   
   // post: constructs node with a possible answer to some question
   // parameters: String answer - answer to some question
   public QuestionNode(String answer) {
      this(answer, null, null);
   }
   
   // post: constructs node with a question data value & it's two
   //       corresponding responses
   // parameters: String question - question being asked
   //             QuestionNode yes - response if answer to question is yes
   //             QuestionNode no - response if answer to question is no
   public QuestionNode(String question, QuestionNode yes, QuestionNode no) {
      this.data = question;
      this.yes = yes;
      this.no = no;
   }
}