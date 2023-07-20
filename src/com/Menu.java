package com;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static int int_getChoice(ArrayList options) {
        int response;

        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + "-" + options.get(i));
        }
        System.out.println("Choose 1.." + options.size() + ":");
        Scanner scanner = new Scanner(System.in);
        response = Integer.parseInt(scanner.nextLine());
        return response;
    }

    public static <T> T ref_getChoice(ArrayList<T> options) {
        int response;
        do {
            response = int_getChoice(options);
        } while (response < 0 || response > options.size());
        return options.get(response - 1);

    }

    public static ArrayList<String> Load_Main_Menu() {
        ArrayList<String> options = new ArrayList<>();
        options.add("List all brands");
        options.add("Add a new brand");
        options.add("Search a brand based on its ID");
        options.add("Update a brand");
        options.add("Save brands to file, named brands.txt");
        options.add("List all cars in ascending order of brand names");
        options.add("List cars based on a part of an input brand name");
        options.add("Add a car");
        options.add("Remove a car based on its ID");
        options.add("Update a car based on its ID");
        options.add("Save Cars to file, named cars.txt");
        return options;
    }

}
