package com.INovus.forms;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Alexander Abugaliev on 17.01.17.
 */
public class SingUpForm {
    @NotNull
    @Size(min = 4, message = "{constraint.Size.SingUpForm.username}")
    @Pattern(regexp = "[0-9a-zA-Z]*", message = "{constraint.Pattern.SingUpForm.username}")
    private String username;
    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "{constraint.Pattern.SingUpForm.password}")
    private String password;
    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "{constraint.Pattern.SingUpForm.password}")
    private String passwordConfirmation;
    @AssertTrue(message = "{constraint.AssertTrue.SingUpForm.passwordConfirmed}")
    private Boolean passwordConfirmed;

    public SingUpForm() {
        this.passwordConfirmed = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        this.checkConfirmation();
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
        this.checkConfirmation();
    }

    public Boolean getPasswordConfirmed() {
        return passwordConfirmed;
    }

    public void setPasswordConfirmed(Boolean passwordConfirmed) {
        this.passwordConfirmed = passwordConfirmed;
    }

    public void checkConfirmation() {
        if (password == null || passwordConfirmation == null) {
            passwordConfirmed = false;
        } else {
            if (password.equals(passwordConfirmation)) {
                passwordConfirmed = true;
            } else {
                passwordConfirmed = false;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SingUpForm)) return false;

        SingUpForm that = (SingUpForm) o;

        if (getUsername() != null ? !getUsername().equals(that.getUsername()) : that.getUsername() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(that.getPassword()) : that.getPassword() != null)
            return false;
        if (getPasswordConfirmation() != null ? !getPasswordConfirmation().equals(that.getPasswordConfirmation()) : that.getPasswordConfirmation() != null)
            return false;
        return getPasswordConfirmed() != null ? getPasswordConfirmed().equals(that.getPasswordConfirmed()) : that.getPasswordConfirmed() == null;

    }

    @Override
    public int hashCode() {
        int result = getUsername() != null ? getUsername().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getPasswordConfirmation() != null ? getPasswordConfirmation().hashCode() : 0);
        result = 31 * result + (getPasswordConfirmed() != null ? getPasswordConfirmed().hashCode() : 0);
        return result;
    }
}
