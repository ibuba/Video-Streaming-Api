package miu.videokabbee.service;

import miu.videokabbee.domain.Token;

public interface TokenServiceInterface {
    public void create(Token token);
    public void deleteToken(String tokenName);
    public boolean isTokenBlackListed(String tokenName);
}
