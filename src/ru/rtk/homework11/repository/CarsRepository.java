package ru.rtk.homework11.repository;

import ru.rtk.homework11.Start;
import ru.rtk.homework11.model.Car;

import java.io.File;
import java.util.List;

public interface CarsRepository {
    String userDirectory = System.getProperty("user.dir");
    String packagePath = Start.class.getPackageName().replace(".", "\\");
    String dbPath = userDirectory + File.separator +
            "src" + File.separator +
            packagePath + File.separator +
            "data" + File.separator +
            "cars.db";

    List<Car> getCars();

    void printDb();
    void clearDb(boolean unconditional);

    void addCarByPattern(String pattern);
    void editCar();
    void deleteCar();

    void getNumbersByColorOrMileage(String find);
    void getCountModelsByCostInterval(String find);
    void getMinimalCostAutoColor();
    void getAverageCostByModel(String find);

}
