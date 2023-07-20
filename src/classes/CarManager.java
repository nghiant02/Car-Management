package classes;

import com.BrandList;
import com.CarList;
import com.Menu;
import java.util.ArrayList;
import java.util.Scanner;

public class CarManager {

    public static void main(String args[]) throws Exception {
        ArrayList<String> options = new ArrayList<>();
        BrandList brandList = new BrandList();
        if (!brandList.loadFromFile("brands.txt")) {
            return;
        }
        CarList carList = new CarList(brandList);
        int choice;
        carList.loadFromFile("cars.txt");
        options = Menu.Load_Main_Menu();

        do {
            System.out.flush();
            choice = Menu.int_getChoice(options);
            switch (choice) {
                case 1: {
                    brandList.listBrands();
                    break;
                }
                case 2: {
                    brandList.addBrand();
                    break;
                }
                case 3: {
                    brandList.searchBrandByID();
                    break;
                }
                case 4: {
                    brandList.updateBrand();
                    break;
                }
                case 5: {
                    brandList.saveToFile("brands.txt");
                    break;
                }
                case 6: {
                    carList.listCars();
                    break;
                }
                case 7: {
                    carList.printBasedBrandName();
                    break;
                }
                case 8: {
                    carList.addCar();
                    break;
                }
                case 9: {
                    carList.removeCar();
                    break;
                }
                case 10: {
                    carList.updateCar();
                }
                case 11: {
                    carList.saveToFile("cars.txt");
                    break;
                }
                default: {
                    System.err.println("invalid choice");
                }
            }
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        } while (choice != 0);
    }
}
