/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

/**
 *
 * @author admin
 */
public class UserError {

    private String userID_ERROR;
    private String fullName_ERROR;
    private String roleID_ERROR;
    private String password_ERROR;
    private String confirm_ERROR;
    private String Error;

    public UserError() {
        this.userID_ERROR = "";
        this.fullName_ERROR = "";
        this.roleID_ERROR = "";
        this.password_ERROR ="";
        this.confirm_ERROR="";
        this.Error = "";
    }

    public UserError(String userID_ERROR, String fullName_ERROR, String roleID_ERROR, String password_ERROR, String confirm_ERROR, String Error) {
        this.userID_ERROR = userID_ERROR;
        this.fullName_ERROR = fullName_ERROR;
        this.roleID_ERROR = roleID_ERROR;
        this.password_ERROR = password_ERROR;
        this.confirm_ERROR = confirm_ERROR;
        this.Error = Error;
    }

    public String getUserID_ERROR() {
        return userID_ERROR;
    }

    public void setUserID_ERROR(String userID_ERROR) {
        this.userID_ERROR = userID_ERROR;
    }

    public String getFullName_ERROR() {
        return fullName_ERROR;
    }

    public void setFullName_ERROR(String fullName_ERROR) {
        this.fullName_ERROR = fullName_ERROR;
    }

    public String getRoleID_ERROR() {
        return roleID_ERROR;
    }

    public void setRoleID_ERROR(String roleID_ERROR) {
        this.roleID_ERROR = roleID_ERROR;
    }

    public String getPassword_ERROR() {
        return password_ERROR;
    }

    public void setPassword_ERROR(String password_ERROR) {
        this.password_ERROR = password_ERROR;
    }

    public String getConfirm_ERROR() {
        return confirm_ERROR;
    }

    public void setConfirm_ERROR(String confirm_ERROR) {
        this.confirm_ERROR = confirm_ERROR;
    }

    public String getError() {
        return Error;
    }

    public void setError(String Error) {
        this.Error = Error;
    }

    
    
    
}
