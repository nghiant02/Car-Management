package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;

import java.util.ArrayList;

public class CarList extends ArrayList<Car> {

    BrandList brandList = new BrandList();

    public CarList(BrandList obj) {
        brandList.addAll(obj);
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
                myOutput.write(this.get(i).toString());
            }
            myOutput.close();
        } catch (Exception ex) {
            throw ex;
        }

        return true;
    }

    public boolean loadFromFile(String filename) throws Exception {
        try {
            String thisLine;
            BufferedReader myInput;

            File f = new File(filename);
            if (!Validation.checkFileExists(filename)) {
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
                    String CarID = split[0];
                    String brandId = split[1];
                    Brand brand = brandList.get(brandList.searchID(brandId));
                    String color = split[2];
                    String frameID = split[3];
                    String engineID = split[4];
                    Car std = new Car(CarID, brand, color, frameID, engineID);
                    this.add(std);
                }
            }
            myInput.close();

        } catch (Exception ex) {
            throw ex;
        }
        return true;
    }

    public int searchID(String CarId) {
        for (int i = 0; i < this.size(); i++) {
            if ((this.get(i).CarID).equals(CarId)) {
                return i;
            }
        }
        return -1;
    }

    public int searchFrame(String FrameId) {
        for (int i = 0; i < this.size(); i++) {
            if ((this.get(i).frameID).equals(FrameId)) {
                return i;
            }
        }
        return -1;
    }

    public int searchEngine(String EngineID) {
        for (int i = 0; i < this.size(); i++) {
            if ((this.get(i).engineID).equals(EngineID)) {
                return i;
            }
        }
        return -1;
    }

    public void addCar() {
        String carID, frameID, color, engineID;
        Brand b = new Brand();
        String idPattern = "^[a-zA-Z0-9-]*$";

        while (true) {
            carID = Validation.checkString("Please, input ID: ");
            int chechId = searchID(carID);
            if (chechId == -1 && carID.matches(idPattern)) {
                break;
            } else if (!carID.matches(idPattern)) {
                System.out.println("Please re-enter the valid format ID!");
            } else {
                System.out.println("Duplicate ID. Try with anther one!");
            }
        }
        b = Menu.ref_getChoice(brandList);
        System.out.println("Your choice is " + b.toString());
        color = Validation.checkString("Please, input color: ");
        frameID = checkFrame("Please, input frame id: ");

        engineID = checkEngine("Please, input engineid: ");
        this.add(new Car(carID, b, color, frameID, engineID));
        System.out.println("\033[0;32m" + "The new Car has been added successfully!!");

    }

    public void printBasedBrandName() {
        if (this == null || this.isEmpty()) {
            System.out.printf("Car list is empty\n");
            return;
        }

        String inputName = Validation.checkString("Input Car name want to search: ");
        int count = 0;

        for (int i = 0; i < this.size(); i++) {
            Car c = this.get(i);
            if (c.getBrand().getBrandName().toLowerCase().contains(inputName.toLowerCase())) {
                System.out.println(this.get(i).screenString());
                count = 1;
            }
        }

        if (count == 0) {
            System.out.printf("No Car has name %s\n", inputName);
        }
    }

    public boolean removeCar() {
        if (this == null || this.isEmpty()) {
            System.out.printf("Car list is empty\n");
            return false;
        }

        String input = Validation.checkString("Input Id Car want to delete: ");

        int pos = searchID(input);
        if (pos < 0) {
            System.out.println("Not Found");
            return false;
        } else {
            this.remove(pos);
            System.out.println("\nRemove successful!");
        }
        return true;
    }

    public boolean updateCar() {
        String carID, frameID, color, engineID;
        carID = Validation.checkString("Please, input ID: ");
        int Update = searchID(carID);
        if (Update > 0) {
            Brand b = (Brand) Menu.ref_getChoice(brandList);
            color = Validation.checkString("Please, input color: ");
            frameID = Validation.checkString("Please, input frameID: ");
            engineID = Validation.checkString("Please, input engineID: ");
            (this.get(Update)).setBrand(b);
            (this.get(Update)).setframeID(frameID);
            (this.get(Update)).setColor(color);
            (this.get(Update)).setengineID(engineID);
        } else {
            System.out.println("Not Found!");
            return false;
        }
        return true;
    }

    public void listCars() {
        Collections.sort(this);
        for (int i = 0; i < this.size(); i++) {
            Car cr = this.get(i);
            System.out.println(cr.screenString());
        }
    }
    public static Scanner sc = new Scanner(System.in);

    public static String checkFrame(String mess) {
        String value;
        String framePattern = "^F[0-9]{5}$";

        while (true) {
            try {
                System.out.print(mess);
                value = sc.nextLine();

                if (value.isEmpty()) {
                    throw new Exception("Please input is not blank");
                }
                if (!value.matches(framePattern)) {
                    throw new Exception("Please input the right format(F00000): ");
                }
                return value.trim();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String checkEngine(String mess) {

        String value;
        String framePattern = "^E[0-9]{5}$";
        while (true) {
            try {
                System.out.print(mess);
                value = sc.nextLine();

                if (value.isEmpty()) {
                    throw new Exception("Please input is not blank");
                }
                if (!value.matches(framePattern)) {
                    throw new Exception("Please input the right format(E00000): ");
                }
                return value.trim();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
