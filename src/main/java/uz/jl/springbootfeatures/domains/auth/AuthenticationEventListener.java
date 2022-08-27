package uz.jl.springbootfeatures.domains.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.jl.springbootfeatures.exceptions.GenericNotFoundException;
import uz.jl.springbootfeatures.repository.AuthUserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Configuration

public class AuthenticationEventListener {

    private final AuthUserRepository repository;

//    @EventListener
//    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {
//
//        String username = (String) event.getAuthentication().getPrincipal();
//        AuthUser authUser =  repository.findByUsername(username).orElseThrow();
//
//        authUser.setLoginTryCount(authUser.getLoginTryCount()+1);
//
//        if(authUser.getLoginTryCount() == 3){
//            authUser.setStatus(AuthUser.Status.BLOCKED);
//            unBlock();
//            authUser.setLoginTryCount(0);
//            authUser.setStatus(AuthUser.Status.NOT_ACTIVE);
//        }
//
//
//    }


//    @Scheduled(fixedDelay = 2000)
//    public void unBlock(){
//        System.out.println("here user's status again changed into not active");
//    }



        // update the failed login count for the user
        // ...
    }




