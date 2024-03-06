package com.example.simplesaga.common.security;

import java.util.List;
import java.util.UUID;

public interface TokenInfoProvider {

    String getPublicKey();

    List<UUID> getActualTokenVersions();
    
}
