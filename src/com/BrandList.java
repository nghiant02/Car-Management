package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;

public class BrandList extends ArrayList<Brand> {

    public BrandList() {
    }

    public boolean loadFromFile(String filename) throws Exception {
        try {
            String thisLine;
            BufferedReader myInput;

            File f = new File(filename);
            if (!(f.exists() && f.isFile())) {
                System.exit(0);
                return false;
            }
            System.out.println(filename);

            String fullPath = f.getAbsolutePath();
            System.out.println("Fullpath: " + fullPath);

            FileInputStream file = new FileInputStream(fullPath);
            myInput = new BufferedReader(new InputStreamReader(file));
            while ((thisLine = myInput.readLine()) != null) {
                if (!thisLine.isEmpty()) {
                    String[] split = thisLine.split(",");

                    String brandID = split[0];
                    String brandName = split[1];
                    String[] tempS = split[2].split(":");
                    String soundBrand = tempS[0];
                    double Price = Double.parseDouble(tempS[1]);
                    Brand std = new Brand(brandID, brandName, soundBrand, Price);
                    this.add(std);
                }
            }
            myInput.close();

        } catch (Exception ex) {
            throw ex;
        }
        return true;
    }

    public boolean saveToFile(String filename) throws Exception {
        File f;
        FileOutputStream file;
        BufferedWriter myOutput;
        try {
            f = new File(filename);

            String fullPath = f.getAbsolutePath();
            file = new FileOutputStream(fullPath);
            myOutput = new BufferedWriter(new OutputStreamWriter(file));
            System.out.println("Save successful!");
            for (int i = 0; i < this.size(); i++) {
                if (i > 0) {
                    myOutput.newLine();
                }
                myOutput.write(this.get(i).outputString());
            }
            myOutput.close();
        } catch (Exception ex) {
            throw ex;
        }

        return true;
    }

    public void addBrand() {
        String brandID, brandName, soundBrand;
        double Price;
        String idPattern = "^[a-zA-Z0-9-]*$";

        while (true) {
            brandID = Validation.checkString("Please, input ID: ");
            int chechId = searchID(brandID);
            if ((chechId == -1) && (brandID.matches(idPattern))) {
                break;
            } else if (!brandID.matches(idPattern)) {
                System.out.println("Please enter right format the ID(all format like the inout file) !");
            } else {
                System.out.println("Duplicate ID. Try with anther one!");
            }
        }
        brandName = Validation.checkString("Please, input name of Brand: ");
        soundBrand = Validation.checkString("Please, input soundBrand: ");
        Price = Validation.checkDouble("Please, input price: ");
        this.add(new Brand(brandID, brandName, soundBrand, Price));

        System.out.println("\nThe new brand has been added successfully!!");
    }

    public void updateBrand() {
        String brandID, brandName, soundBrand;
        double Price;
        while (true) {
            brandID = Validation.checkString("Please, input ID: ");
            int Update = this.searchID(brandID);
            if (Update != -1) {
                brandName = Validation.checkString("Please, input brandname: ");
                soundBrand = Validation.checkString("Please, input soundBrand: ");
                Price = Validation.checkDouble("Please, input price: ");
                this.get(Update).setBrandName(brandName);
                this.get(Update).setSoundBrand(soundBrand);
                this.get(Update).setPrice(Price);
                break;
            } else {
                System.out.println("Not Found!");
            }
        }
    }

    public void searchBrandByID() {
        if (this == null || this.isEmpty()) {
            System.out.printf("Brand list is empty\n");
            return;
        }
        String inputName = Validation.checkString("Input name want to search: ");
        int count = 0;
        for (Brand std : this) {
            if (std.getBrandID().toLowerCase().equals(inputName.toLowerCase())) {
                if (count == 0) {
                    System.out.printf("%-10s|%-35s|%-17s|%-6s\n", "Brand ID", "Brand Name", "Sound Brand", " Price");
                }
                std.print();
                count++;
            }
        }
        if (count == 0) {
            System.out.printf("No Brand has name %s\n", inputName);
        }
    }

    public int searchID(String BrandId) {
        for (int i = 0; i < this.size(); i++) {
            if ((this.get(i).brandID).equals(BrandId)) {
                return i;
            }

        }
        return -1;
    }

    public void sortBrand() {
        if (this == null || this.isEmpty()) {
            System.out.printf("Brand list is empty\n");
            return;
        }
        Collections.sort(this);
    }

    public void listBrands() {
        if (this.isEmpty()) {
            System.out.printf("Brand list is empty\n");
            return;
        }
        System.out.printf("%-10s|%-35s|%-17s|%-6s\n", "Brand ID", "Brand Name", "Sound Brand", " Price");
        for (Brand std : this) {
            std.print();
        }
    }

    public Brand getUserChoice() {
        Menu mnu = new Menu();
        return (Brand) mnu.ref_getChoice(this);
    }
}
