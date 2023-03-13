package miu.videokabbee.repository;


import miu.videokabbee.domain.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends MongoRepository<Token,String> {

    boolean existsByTokenName(String tokenName);
    Token deleteTokenByTokenName(String tokenName);
}
