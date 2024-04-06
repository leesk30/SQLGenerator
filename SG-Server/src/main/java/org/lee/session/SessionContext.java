package org.lee.session;

public class SessionContext {
    private String name;

    public SessionContext(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void destroy(){

    }
}

