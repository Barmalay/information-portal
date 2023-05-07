package com.mai.webApplication.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PasswordForm {

    @NotEmpty
    private String currentPassword;

    @NotEmpty
    @Size(min = 4, max = 30)
    private String newPassword;

    @NotEmpty
    private String newPasswordConfirm;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }

    public boolean passwordsMatch() {
        return newPassword.equals(newPasswordConfirm);
    }

    public boolean oldAndNewPasswordsMatch() {
        return currentPassword.equals(newPassword);
    }

}

