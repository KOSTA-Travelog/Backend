package kosta.travelog.servlet;

import java.nio.charset.StandardCharsets;

public class Hashing {
    public String getHex(String str) {
        return com.google.common.hash.Hashing.sha256()
                .hashString(str, StandardCharsets.UTF_8)
                .toString()
                .toUpperCase();
    }
}
