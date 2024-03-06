package com.example.simplesaga.common.security;

import java.util.List;
import java.util.UUID;

public interface AuthServiceClient {

    String getPublicKey();

    List<UUID> getActualTokenVersions();

}
