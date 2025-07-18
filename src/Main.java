import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Задача 1
        homework_8_1();

        // Задача 2
        System.out.println("\nЗадача 2.");
        while(true){
            System.out.println("\nИгра Камень-Ножницы-Бумага");
            System.out.println("1) Играть");
            System.out.println("0) Выход");
            System.out.print("Что делать? ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    rock_paper_scissors();
                    break;
                case "0":
                    return;
            }
        }
    }

    private static void homework_8_1() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nЗадача 1.");
        System.out.print("Введите имя: ");
        String name = scanner.nextLine(); // читаем строку
        System.out.println("Привет, " + name);
    }

    private static void rock_paper_scissors() {
        Random random = new Random();

        int round = 1;
        while (true) {
            System.out.println("Раунд " + round);
            int vasily_choice = vasily_choice(random);
            int petr_choice = petr_choice(random);
            int winner = whose_player_win(vasily_choice, petr_choice);
            switch (winner) {
                case 0:
                    round ++;
                    System.out.println();
                    continue;
                case 1:
                    System.out.println("Победил Василий");
                    break;
                case 2:
                    System.out.println("Победил Петр");
                    break;
            }
            break;
        }
    }

    private static int whose_player_win(int player_1_choice, int player_2_choice) {
        // 0 - rock; scissors = 1; paper = 2;
        // rock (0) scissors (1) - win rock (0)
        // rock (0) paper (2) - win paper (2)
        // scissors (1) paper (2) - win scissors (1)
        if(player_1_choice == 0 && player_2_choice == 1) return 1; // rock scissors
        if(player_1_choice == 0 && player_2_choice == 2) return 2; // rock paper
        if(player_1_choice == 1 && player_2_choice == 0) return 2; // scissors rock
        if(player_1_choice == 1 && player_2_choice == 2) return 1; // scissors paper
        if(player_1_choice == 2 && player_2_choice == 0) return 1; // paper rock
        if(player_1_choice == 2 && player_2_choice == 1) return 2; // paper scissors
        return 0;
    }

    private static void print_choice(String name, int choice) {
        String choice_string = "";
        switch (choice) {
            case 0: choice_string = "камень"; break;
            case 1: choice_string = "ножницы"; break;
            case 2: choice_string = "бумага"; break;
        }
        System.out.println(name + " выбрал " + choice_string);
    }

    private static int vasily_choice(Random random) {
        int i = random.nextInt(3);
        print_choice("Василий", i);
        return i;
    }

    private static int petr_choice(Random random) {
        int i = random.nextInt(3);
        print_choice("Петр", i);
        return i;
    }
}