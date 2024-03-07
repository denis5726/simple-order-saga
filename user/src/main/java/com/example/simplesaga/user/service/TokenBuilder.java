package com.example.simplesaga.user.service;

import com.example.simplesaga.user.entity.User;

public interface TokenBuilder {

    String buildToken(User user);
}
