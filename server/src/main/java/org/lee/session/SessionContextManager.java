package org.lee.session;

import java.util.HashMap;
import java.util.Map;

public class SessionContextManager {
    private final Map<String, SessionContext> mapping = new HashMap<>();

    public void createNewSession(String token){
        if(mapping.containsKey(token)){
            throw new RuntimeException("Duplicate session token");
        }
        mapping.put(token, new SessionContext(token));
    }

    public SessionContext getSession(String token){
        return mapping.containsKey(token) ? mapping.get(token) : new SessionContext(token);
    }

    public void destroySession(String token){
        if(!mapping.containsKey(token)){
            throw new RuntimeException("Session doesn't exists or Session has already been destroyed.");
        }
        SessionContext sessionContext = mapping.remove(token);
        sessionContext.destroy();
    }

    public boolean hasSession(String token){
        return mapping.containsKey(token);
    }
}
