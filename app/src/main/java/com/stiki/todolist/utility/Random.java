package com.stiki.todolist.utility;

public class Random {

    private static final String NUMBER = "123456789";
    private static final java.util.Random random = new java.util.Random();

    public static int getRandom(int length) {
        StringBuilder character = new StringBuilder();
        for(int i = 0; i < length; i++) {
            character.append(NUMBER.charAt(random.nextInt(NUMBER.length())));
        }
        return Integer.parseInt(character.toString());
    }
}
