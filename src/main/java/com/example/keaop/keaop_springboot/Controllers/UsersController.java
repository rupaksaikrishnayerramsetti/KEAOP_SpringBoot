package com.example.keaop.keaop_springboot.Controllers;

import java.util.Map;
import java.util.Optional;
import org.springframework.web.bind.annotation.RestController;
import com.example.keaop.keaop_springboot.Helpers.EmailService;
import com.example.keaop.keaop_springboot.Helpers.EmailTemplate;
import com.example.keaop.keaop_springboot.Helpers.JWTService;
import com.example.keaop.keaop_springboot.Helpers.PasswordService;
import com.example.keaop.keaop_springboot.Model.Users;
import com.example.keaop.keaop_springboot.Repository.UsersRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class UsersController {
    private final UsersRepository urepo;
    private final PasswordService ps;
    private final EmailService emailservice;
    private final EmailTemplate emailtemplate;
    private final JWTService JwtService;

    public UsersController(UsersRepository urepo, PasswordService ps, EmailService emailservice, EmailTemplate emailtemplate, JWTService JwtService) {
        this.urepo = urepo;
        this.ps = ps;
        this.emailservice = emailservice;
        this.emailtemplate = emailtemplate;
        this.JwtService = JwtService;
    }

    @PostMapping("/createUser")
    public Boolean CreateUser(@RequestBody Users user) {
        try {
            if(user != null) {
                String password = ps.generatePassword();
                String passwordDigest = ps.hashPassword(password);
                user.setPassword_digest(passwordDigest);
                urepo.save(user);
                String template = emailtemplate.userCredentialsTemplate(user.getEmail(), password);
                Boolean sendEmail = emailservice.sendEmail(user.getEmail(), "These are the credentials for Keep Everything at One Place", template);
                return sendEmail;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @PostMapping("/loginCheck")
    public String LoginCheck(@RequestBody Map<String, String> credentials) {
        try {
            if(credentials != null && credentials.containsKey("email") && credentials.containsKey("password")) {
                String email = credentials.get("email");
                String password = credentials.get("password");
                String passwordDigest = ps.hashPassword(password);
                Users user = urepo.findByEmailAndPasswordDigest(email,passwordDigest);
                if(user != null){
                    String token = JwtService.generateToken(email, user.get_id());
                    return token;
                } else {
                    return "invalid";
                }
            } else {
                return "invalid";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "invalid";
        }
    }

    @GetMapping("/fetchUserDetails")
    public Optional<Users> FetchUserDetails(@RequestHeader Map<String, String> requestHeader) {
        String token = requestHeader.get("authorization");
        if(token!=null && !token.isEmpty()) {
            try {
                Map<String, String> decodedData = JwtService.decodeToken(token);
                Optional<Users> user = urepo.findById(decodedData.get("userId"));
                return user;
            } catch (Exception e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    @PostMapping("/updateUserData")
    public Boolean UpdateUserData(@RequestBody Map<String, String> requestBody) {
        String token = requestBody.get("token");
        if (token != null && !token.isEmpty()) {
            try {
                Map<String, String> decodedData = JwtService.decodeToken(token);
                Optional<Users> optionalUser =  urepo.findById(decodedData.get("userId"));
                if (optionalUser.isPresent()) {
                    Users user = optionalUser.get();
                    user.setUser_name(requestBody.get("user_name"));
                    user.setGender(requestBody.get("gender"));
                    user.setOccupation(requestBody.get("occupation"));
                    user.setPhone_number(requestBody.get("phone_number"));
                    urepo.save(user);
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @PostMapping("/changePassword")
    public Boolean ChangePassword(@RequestBody Map<String, String> requestBody) {
        String token = requestBody.get("token");
        if(token!=null && !token.isEmpty()){
            try {
                Map<String, String> decodedData = JwtService.decodeToken(token);
                String oldPasswordDigest = ps.hashPassword(requestBody.get("password"));
                String newPassword = requestBody.get("newpassword");
                Users user = urepo.findByEmailAndPasswordDigest(decodedData.get("email"), oldPasswordDigest);
                if(user!=null){
                    String newPasswordDigest = ps.hashPassword(newPassword);
                    Optional<Users> optionalUser =  urepo.findById(decodedData.get("userId"));
                    if (optionalUser.isPresent()) {
                        Users userTemp = optionalUser.get();
                        userTemp.setPassword_digest(newPasswordDigest);
                        urepo.save(userTemp);
                        String template = emailtemplate.userCredentialsChangedTemplate(decodedData.get("email"), newPassword);
                        emailservice.sendEmail(decodedData.get("email"), "These are the updated user credentials for Keep Everything at One Place", template);
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
    
    @PostMapping("/forgotPassword")
    public Boolean ForgotPassword(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        try {
            Users user = urepo.findByEmail(email);
            if(user!=null) {
                String password = ps.generatePassword();
                String passwordDigest = ps.hashPassword(password);
                user.setPassword_digest(passwordDigest);
                urepo.save(user);
                String template = emailtemplate.userCredentialsChangedTemplate(email, password);
                emailservice.sendEmail(email, "These are the updated user credentials for Keep Everything at One Place", template);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return false;
    }
    

    @GetMapping("/fetchTotalSalary")
    public int FetchTotalSalary(@RequestHeader Map<String, String> requestHeader) {
        String token = requestHeader.get("authorization");
        if(token!=null && !token.isEmpty()) {
            try {
                Map<String, String> decodedData = JwtService.decodeToken((token));
                Optional<Users> userOptional = urepo.findById(decodedData.get("userId"));
                if(userOptional!=null){
                    Users user = userOptional.get();
                    return user.getSalary();
                }
            } catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }
}
