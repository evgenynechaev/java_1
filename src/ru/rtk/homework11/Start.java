package ru.rtk.homework11;

import ru.rtk.homework11.repository.CarsRepositoryImpl;

import java.util.Scanner;

public class Start {
    public Start() {
        CarsRepositoryImpl carsRepository = new CarsRepositoryImpl();
        /*
        carsRepository.addCarByPattern("a123me|Mercedes|White|0|8300000");
        carsRepository.addCarByPattern("b873of|Volga|Black|0|673000");
        carsRepository.addCarByPattern("w487mn|Lexus|Grey|76000|900000");
        carsRepository.addCarByPattern("p987hj|Volga|Red|610|704340");
        carsRepository.addCarByPattern("c987ss|Toyota|White|254000|761000");
        carsRepository.addCarByPattern("o983op|Toyota|Black|698000|740000");
        carsRepository.addCarByPattern("p146op|BMW|White|271000|850000");
        carsRepository.addCarByPattern("u893ii|Toyota|Purple|210900|440000");
        carsRepository.addCarByPattern("l097df|Toyota|Black|108000|780000");
        carsRepository.addCarByPattern("y876wd|Toyota|Black|160000|1000000");
        */

        System.out.println();
        System.out.println("        Домашнее задание по теме");
        System.out.println("     'Java Collections. Stream API'");
        System.out.println("-------------------------------------------");
        while(true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println();
            System.out.println("1) Вывести список автомобилей в базе данных");
            System.out.println("   --- Операции с элементами базы: ---");
            System.out.println("2) Добавить новый автомобиль");
            System.out.println("3) Удалить автомобиль из базы");
            System.out.println("4) Редактировать автомобиль в базе");
            System.out.println("   --- Операции с базой: ---");
            System.out.println("5) Сохранить базу данных в файл");
            System.out.println("6) Загрузить базу данных из файла");
            System.out.println("7) Очистить базу");
            System.out.println("   --- Поиск по базе данных ---");
            System.out.println("A) Показать номера всех автомобилей, имеющих заданный цвет и пробег");
            System.out.println("B) Показать количество уникальных моделей в ценовом диапазоне");
            System.out.println("C) Вывести цвет автомобиля с минимальной стоимостью");
            System.out.println("D) Вывести среднюю стоимость искомой модели");
            System.out.println("   ---");
            System.out.println("Q) Выход из программы");
            System.out.print("Выберите пункт: ");
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                carsRepository.printDb();
            }

            if (choice.equals("2")) {
                System.out.println("Введите информацию об автомобиле в формате 'Номер|Модель|Цвет|Пробег|Цена':");
                String input = scanner.nextLine();
                carsRepository.addCarByPattern(input);
            }

            if (choice.equals("3")) {
                carsRepository.deleteCar();
            }

            if (choice.equals("4")) {
                carsRepository.editCar();
            }

            if (choice.equals("5")) {
                carsRepository.saveDbToFile();
            }

            if (choice.equals("6")) {
                carsRepository.loadDbFromFile();
            }

            if (choice.equals("7")) {
                carsRepository.clearDb(false);
            }

            if (choice.equalsIgnoreCase("A")) {
                System.out.print("Введите поиск в формате 'Цвет, пробег': ");
                String input = scanner.nextLine();
                // String input = "Black, 0";
                carsRepository.getNumbersByColorOrMileage(input);
            }

            if (choice.equalsIgnoreCase("B")) {
                System.out.print("Введите диапазон в формате 'Цена_От, Цена_До': ");
                String input = scanner.nextLine();
                // String input = "700000, 800000";
                carsRepository.getCountModelsByCostInterval(input);
            }

            if (choice.equalsIgnoreCase("C")) {
                carsRepository.getMinimalCostAutoColor();
            }

            if (choice.equalsIgnoreCase("D")) {
                System.out.print("Введите модель для определения средней стоимости: ");
                String input = scanner.nextLine();
                // String input = "Toyota";
                // String input = "Volvo";
                carsRepository.getAverageCostByModel(input);
            }

            if (choice.equalsIgnoreCase("Q")) {
                break;
            }
        }
    }
}
