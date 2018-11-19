package com.alpha.AlphaPractice_11_11_2018.Comparator;

public final class User {
    private String username;
    private String email;
    private byte[] passwordHash;
    public String getUsername() { return username;}
    public void setUsername(String username) { this.username = username;}
    public String getEmail() { return email;}
    public void setEmail(String email) { this.email = email;}
    public byte[] getPasswordHash() { return passwordHash;}
    public void setPasswordHash(byte[] passwordHash) { this.passwordHash = passwordHash;}

    public User(String username, String email, byte[] passwordHash) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }
}
