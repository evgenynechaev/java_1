package ru.rtk.homework11.repository;

import ru.rtk.homework11.model.Car;

import java.io.*;
import java.util.*;

public class CarsRepositoryImpl implements CarsRepository {
    private List<Car> cars = new ArrayList<>();

    public List<Car> getCars() {
        return cars;
    }

    @Override
    public void printDb() {
        System.out.println();
        if(cars.isEmpty()) {
            System.out.println("В базе данных нет автомобилей");
            return;
        }
        System.out.println("Автомобили в базе данных:");
        cars.forEach(car -> {
            System.out.printf("%d) %s\n", cars.indexOf(car)+1 , car);
        });
    }

    @Override
    public void clearDb(boolean unconditional) {
        Scanner scanner = new Scanner(System.in);
        if(!unconditional) {
            while (true) {
                System.out.print("Очистить таблицу? (y/n) ");
                String answer = scanner.nextLine().trim();
                if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("д")) {
                    break;
                }
                if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("н")) {
                    return;
                }
            }
        }

        this.cars.clear();
        System.out.println();
        System.out.println("База данных очищена");
    }

    @Override
    public void getNumbersByColorOrMileage(String find) {
        String[] list = find.split(",");
        if (list.length != 2) {
            System.out.println("Строка не соответствует шаблону 'Цвет, пробег'");
            return;
        }
        final String colorToFind = list[0].trim();
        final long mileageToFind;
        try {
            mileageToFind = Long.parseLong(list[1].trim());
        } catch (NumberFormatException | NullPointerException ignored) {
            System.out.printf("ОШИБКА: Неправильно введен пробег в строке '%s'.\n", find);
            return;
        }

        List<String> numbers = this.cars.stream().
                filter(car ->
                        car.getColor().equalsIgnoreCase(colorToFind) || car.getMileage() == mileageToFind
                ).map(Car::getNumber).toList();
        String result = this.delimiterString(numbers);

        System.out.printf("Результат поиска по параметрам '%s':\n", find);
        System.out.println(result);
    }

    @Override
    public void getCountModelsByCostInterval(String find) {
        String[] list = find.split(",");
        if (list.length != 2) {
            System.out.println("Строка не соответствует шаблону 'Цена_1, Цена_2'");
            return;
        }
        long from, to;
        try {
            from = Long.parseLong(list[0].trim());
            to = Long.parseLong(list[1].trim());
        } catch (NumberFormatException | NullPointerException ignored) {
            System.out.printf("ОШИБКА: Неправильно введен диапазон цен в строке '%s'.\n", find);
            return;
        }
        final long costFrom, costTo;
        if (from > to) {
            costFrom = to;
            costTo = from;
        }
        else {
            costFrom = from;
            costTo = to;
        }

        long count = this.cars.stream().
                filter(car ->  car.getCost() >= costFrom && car.getCost() <= costTo).count();

        System.out.println();
        System.out.printf("Уникальные автомобили: %d шт.\n", count);
    }

    @Override
    public void getMinimalCostAutoColor() {
        try {
            Optional<Car> car = Optional.of(this.cars.stream().min(Comparator.comparing(Car::getCost)).get());
            String color = car.get().getColor();
            System.out.println();
            System.out.printf("Цвет автомобиля с минимальной стоимостью: '%s'\n", color);
        } catch (NullPointerException | NoSuchElementException ignored) {
            System.out.println("Минимальное значение не найдено");
        }
    }

    @Override
    public void getAverageCostByModel(String find) {
        final String modelToFind = find.trim();

        double average = this.cars.stream().
                filter(car -> car.getModel().equalsIgnoreCase(modelToFind)).
                mapToLong(Car::getCost).
                average().orElse(0);

        System.out.println();
        System.out.printf("Средняя стоимость модели '%s': %.2f\n", modelToFind, average);
    }

    @Override
    public void addCarByPattern(String pattern) {
        Car car = new Car(pattern);
        if (car.isValid()) {
            cars.add(car);
            System.out.printf("Автомобиль '%s' успешно добавлен\n", car);
            return;
        }
        System.out.println("Автомобиль не добавлен");
    }

    @Override
    public void editCar() {
        this.printDb();
        System.out.print("Выберите запись для редактирования: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        try {
            int index = Integer.parseInt(input.trim())-1;
            Car car = this.cars.get(index);
            car.edit();
        } catch (NumberFormatException | NullPointerException ignored) {
            System.out.printf("ОШИБКА: Неправильно введен номер записи '%s'.\n", input);
        }
    }

    @Override
    public void deleteCar() {
        this.printDb();
        System.out.print("Введите номер записи для удаления: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        try {
            int index = Integer.parseInt(input.trim())-1;
            System.out.printf("Удалить запись '%s'? (y/n) ",cars.get(index));
            String answer = scanner.nextLine().trim();
            if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("д")) {
                this.cars.remove(index);
                System.out.println("Запись удалена");
            }
        } catch (NumberFormatException | NullPointerException ignored) {
            System.out.printf("ОШИБКА: Неправильно введен номер записи '%s'.\n", input);
        }
    }

    @Override
    public void saveDbToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dbPath))) {
            oos.writeObject(this.cars);
            System.out.println("Данные сохранены");
        } catch (IOException e) {
            System.out.println("Не удалось сохранить данные");
            e.printStackTrace();
        }
    }

    @Override
    public void loadDbFromFile() {
        File file = new File(dbPath);
        if (!file.exists()) {
            System.out.printf("Файл базы данных '%s' не найден.\n", dbPath);
            System.out.println("Загрузка данных не выполнена.");
            return;
        }
        if (!file.isFile()) {
            System.out.printf("Это не файл: %s\n", dbPath);
            System.out.println("Загрузка данных не выполнена.");
            return;
        }

        this.clearDb(true);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dbPath))) {
            this.cars = (List<Car>) ois.readObject();
            System.out.println("Данные загружены");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Не удалось загрузить данные");
            e.printStackTrace();
        }
    }

    private <T> String delimiterString(List<T> list) {
        return String.join(", ", list.stream().map(Object::toString).toList());
    }
}
