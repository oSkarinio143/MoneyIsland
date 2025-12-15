package pl.oskarinio.moneyisland.auth.domain.service;


import pl.oskarinio.moneyisland.auth.domain.dto.User;
import pl.oskarinio.moneyisland.auth.domain.dto.UserServiceData;
import pl.oskarinio.moneyisland.auth.domain.dto.form.LoginForm;
import pl.oskarinio.moneyisland.auth.domain.port.PasswordEncoderPort;
import pl.oskarinio.moneyisland.auth.domain.port.UserManagement;
import pl.oskarinio.moneyisland.auth.domain.dto.RefreshToken;
import pl.oskarinio.moneyisland.shared.exception.UsernameNotMatchingPassword;

public class LoginDomainService {

    private final UserManagement userManagement;
    private final PasswordEncoderPort passwordEncoderPort;

    public LoginDomainService(UserManagement userManagement, PasswordEncoderPort passwordEncoderPort) {
        this.userManagement = userManagement;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    public UserServiceData loginUser(LoginForm loginForm){
        User user = getUser(loginForm);
        UserServiceData userServiceData = getUserServiceData(user);
        this.userManagement.generateAndSetTokens(userServiceData);
        RefreshToken refreshToken = this.userManagement.getRefreshToken(userServiceData.getRefreshToken());
        this.userManagement.setRefreshToken(user, refreshToken);
        return userServiceData;
    }

    private User getUser(LoginForm loginForm){
        User user = this.userManagement.getUserByUsernameOrThrow(loginForm.getUsername());
        checkUsernameMatchingPassword(user, loginForm);
        return user;
    }

    private UserServiceData getUserServiceData(User user){
        UserServiceData userServiceData = new UserServiceData(user.getUsername(), user.getEmail(), user.getPassword());
        userServiceData.setRoles(user.getRoles());
        return userServiceData;
    }

    private void checkUsernameMatchingPassword(User user, LoginForm loginForm){
        if(!isPasswordMatching(loginForm.getPassword(), user.getPassword()))
            throw new UsernameNotMatchingPassword();
    }

    private boolean isPasswordMatching(String loginPassword, String databasePassword){
        return passwordEncoderPort.matches(loginPassword, databasePassword);
    }
}
