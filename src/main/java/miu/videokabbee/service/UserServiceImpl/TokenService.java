package miu.videokabbee.service.UserServiceImpl;

import miu.videokabbee.domain.Token;
import miu.videokabbee.repository.TokenRepository;
import miu.videokabbee.service.TokenServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService implements TokenServiceInterface {


    @Autowired
    TokenRepository tokenRepository;


    public void create(Token token){
        tokenRepository.save(token);
    }
    public void deleteToken(String tokenName){
        tokenRepository.deleteTokenByTokenName(tokenName);
    }

   public boolean isTokenBlackListed(String tokenName) {
       return tokenRepository.existsByTokenName(tokenName);
   }

}
