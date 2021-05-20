package com.iluvpwny.graderbot.utils;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

public class UserData {

    private static HashMap<String, Pair<String,String>> userData = new HashMap<>();

    public static void saveCredent() throws IOException {
        StringBuilder bobTheBuilder = new StringBuilder();
        userData.forEach((discord,credent) -> {
            bobTheBuilder.append(discord + " " + credent.getKey() + " " + credent.getValue() + "\n");
        });
        String message = bobTheBuilder.toString();
        Files.write(Paths.get("credent.txt"), message.getBytes());
    }

    public static void loadUser(){
        try (Stream<String> stream = Files.lines(Paths.get("credent.txt"))) {
            stream.forEach(String -> {
                String = String.trim();
                String split[] = String.split(" ");
                userData.put(split[0],new Pair<>(split[1],split[2]));
            });
        }catch (IOException ignore){}
    }

    public static Pair<String,String> getUser(String id){
        return userData.get(id);
    }

    public static void addUser(String id, String name, String password) {
        userData.put(id,new Pair<>(name,password));
        try {
            saveCredent();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}