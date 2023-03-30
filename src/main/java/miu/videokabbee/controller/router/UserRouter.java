package miu.videokabbee.controller.router;

public class UserRouter {
    public static final String userList= "/userList";   //getting all users
    public static final String userId= "/{id}";        //getting users by Id
    public static final String restByEmail = "/resetEmail";     // resetting using email
    public static final String resetBySms = "/resetBySms";         // resetting using Sms
    public static final String newUser = "/register";           // posting/adding new Video
    public static final String resetPassword = "/resetPassword";         // resetting Password
    public static final String verify = "/verify-otp";         // verifying using otp
    public static final String refreshToken = "/refreshToken";         // getting refresh token
    public static final String update = "/update";         // updating user profile
    public static final String logout = "/logout";         // logging user out

}
