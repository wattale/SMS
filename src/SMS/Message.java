/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SMS;

import java.io.Serializable;

/**
 *
 * @author Ashan
 */
public class Message implements Serializable {
    private String cmd;
    private Student student;
    private LoginDetails details;
    private boolean saved;
    private boolean upDated;
    private boolean changedPWord;

    public boolean isChangedPWord() {
        return changedPWord;
    }

    public void setChangedPWord(boolean changedPWord) {
        this.changedPWord = changedPWord;
    }

    public boolean isUpDated() {
        return upDated;
    }

    public void setUpDated(boolean upDated) {
        this.upDated = upDated;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }


    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public LoginDetails getDetails() {
        return details;
    }

    public void setDetails(LoginDetails details) {
        this.details = details;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
