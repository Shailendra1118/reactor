package com.example.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CoreIt {

    @Test
    public void testMatch(){
        Permissions p1 = new Permissions("auth1", "reseller1", "role1");
        Permissions p2 = new Permissions("auth2", "reseller2", "role2");;
        Permissions p3 = new Permissions("auth3", "reseller3", "role3");;
        List<Permissions> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        boolean enabled = list.stream().anyMatch(item -> "auth3".equals(item.authority));
        System.out.println("Enabled: "+enabled);
    }

    class Permissions{
        String authority;
        String role;
        String cap;

        public Permissions(String auth, String reseller, String role) {
            this.authority = auth;
            this.role = reseller;
            this.cap = role;
        }
    }
}
