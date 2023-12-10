package com.ra;

import org.mindrot.jbcrypt.BCrypt;

public class Main {
    public static void main(String[] args) {
        String pass = BCrypt.hashpw("chien123",BCrypt.gensalt(12));
        System.out.println("pass: "+pass);
        System.out.println(BCrypt.checkpw("chien","$2a$12$Dzc5DgPa.jQhGGVO/h3Tde0z4Jv340t/4DqqbwCPaaMWsqQUjGeHu"));
    }
}
