import java.util.Arrays;
import java.util.Scanner;


/*
Here I will describe the problems that I could not solve:
1. I use System.out.println("\n".repeat(10));
because it is difficult to find work my idea a way without crutches for all platforms.
2. I must everywhere use throws InterruptedException to use TimeUnit.SECONDS.sleep(time); - fixed i use myself method can not work perfect (maybe)
3. I am use regular expression twice. - fixed. Not shure
3. Username can have spaces. And cannot be in Ukraine symbols - fixed
4. Book name cannot have symbol "," - big problem
5. if the user changes his mind about performing the operation. He always must DO it;
6. Add more class to name functions
7. Problems with scanner;
8. 2 method have 80% same code;
9. Need do normal print of all books;
10. Add joke;
11. Add method to check books (he is clean)
12. reconstruction method (or just fix 6 task)




 */

public class Realization {
    static Scanner sc = new Scanner(System.in);
    static String userName;
    static String robot;
    static int timeRobot = 3;
    static String[] arrayMenu = {"1. Add book"
            , "2. Delete book"
            , "3. Change name book"
            , "4. Show all books"
            , "5. Find book"
            , "6. Sort books by names"
            , "7. back to robot configuration speed"
            , "8. Exit"
            , "99. Joke"};
    static String bookToArray = "";
    static String bookToarrayUpdate;


    public static void start() {
        robotHello();
        menuOfProgram();
    }

    public static void robotHello() {
        robot("Hello. My name is Alex. A am librarian.");
        robot("So, how is you name? Please write it:");
        stringCheckUserName();
        robot(String.format("So, i glad to see you %s", userName));
        robot("I help you in library, but first you need help me.");
        robot("Like you see, i robot. But you just are human!");
        robot("So, you can - write me speed how i will talk with you.");
        robot("It must be second from 3-20 and an integer");
        robot("So, all it time before we talk on speed 3.");
        robotSpeedChange();

    }

    static void menuOfProgram() {
        while (true) {
            robot(String.format("Brrrr, now we in menu of my library %s. Choice menu:", userName == null ? "Guest" : userName));
            System.out.println();
            showMenu();
            pause(timeRobot+2);
            robot("Brrrrrr, what menu you want to use? Write number");
            menu();
        }
    }

    public static void menu() {
        while (true) {
            stopDublicateCode();
            String choiceMenu = sc.nextLine();
            switch (choiceMenu.toLowerCase()) {
                case "1":
                    addBook();
                    break;
                case "2":
                    if (bookToArray.length() <= 1) {
                        robot("Sorry, but in my library no one book. Add first.");
                    } else {
                        deleteBook();
                    }
                    break;
                case "3":
                    if (bookToArray.length() <= 1) {
                        robot("Sorry, but in my library no one book. Add first.");
                    } else {
                        changeBookName();
                    }
                    break;
                case "4":
                    System.out.println(Arrays.toString(bookToArray.split(",")));
                    break;
                case "5":
                    if (bookToArray.length() <= 1) {
                        robot("Sorry, but in my library no one book. Add first.");
                    } else {
                        findBook();
                    }
                    break;
                case "6":
                    robot("Books are sorted. Please, check it in menu \"4\" ");
                    Arrays.toString(bookToArray.split(","));
                    break;
                case "7":
                    robotSpeedChange();
                    break;
                case "8":
                    robot("Bye");
                    System.exit(0);
                    break;
                case "99":
                    System.out.println("best code == no code");
                    break;
                case "menu":
                    showMenu();
                    pause(timeRobot+2);
                    break;
                default:
                    robot("Sorry, but you answer is wrong. Try again");
            }
            robot("Write you choice number of menu");
            robot("If you forgot the menu, just write \"menu\"");
            System.out.println();
        }


    }

    public static void robot(String robotText) {
        System.out.println("\n".repeat(12));
        robot = String.format("     ,_,_,     \r\n     \\O O/     \r\n     /_E_\\     ---- %s  \r\n()ooo|\\=/|ooo()\r\n     |___|    \r\n     /| |\\     \r\n    [_] [_] ", robotText);
        System.out.print(robot);
        pause(timeRobot);

    }

    static void pause(int time) {
        long timeStart = System.currentTimeMillis();
        long timeOver = timeStart + 1000 * time;
        while (timeStart != timeOver) {
            timeStart = System.currentTimeMillis();
        }
    }

    static void stringCheckUserName() {
        System.out.println();
        while (true) {
            stopDublicateCode();
            userName = sc.nextLine();
            if (userName == null || userName.length() <= 3) {
                System.out.println("Sorry, you name is impossible. Try again.\nMust be more 3 symbol.");
            } else if (!checkName(userName)) {
                System.out.println("Sorry, you name is impossible. Try again.\nAt name cannot be numbers.");
            } else if (checkName(userName)) {
                return;
            }

        }

    }

    static void robotSpeedChange() {

        robot("You want to change my speed? Write y if yes or write n if no");
        while (true) {
            stopDublicateCode();
            String answer = sc.next();
            sc.skip("\n");
            if (answer.equalsIgnoreCase("n")) {
                robot(String.format("You don't change anything, speed still is : %d", timeRobot));
                return;
            } else if (answer.equalsIgnoreCase("y")) {
                while (true) {
                    robot("Brrrrrrrr, what speed you want?");
                    int check = checkDigit();
                    if (check >= 3 && check <= 20) {
                        timeRobot = check;
                        robot(String.format("Brrrrrrr, now my speed is: %d", timeRobot));
                        return;
                    } else {
                        robot("Sorry, you number is wrong.");
                        robot("Like i said before, it must be 3 or more where max is 20");
                        robot("Please, try again");
                    }

                }

            } else {
                robot("Sorry, it not a answer. Try again");
            }
        }
    }


    static int checkDigit() {
        System.out.println();
        while (true) {
            if (sc.hasNextInt()) {
                return Integer.parseInt(sc.next());
            }
            robot("Sorry, it not int number. Try again!");
            sc.next();

        }
    }

    static boolean checkName(String name) {
        char[] array = name.toCharArray();
        for (char check : array) {
            if (Character.isLetter(check) == false) {
                return false;
            }
        }
        return true;
    }

    static void showMenu() {
        System.out.println("\n".repeat(12));
        for (int i = 0; i < arrayMenu.length - 1; i++) {
            System.out.println(arrayMenu[i]);
        }
        if ((int) Math.random() * 100 == 99) {
            System.out.println(arrayMenu[arrayMenu.length - 1]);
        }
    }

    static void addBook() {
        while (true) {
            robot("Brrrrrrrrr, you choice add book. Write book name:");
            checkNameBook();
            robot("Do you want add more books? Write Y or N like always");
            System.out.println();
            stopDublicateCode();
            String answ = sc.nextLine();
            if (answ.equalsIgnoreCase("n")) {
                return;
            }
            robot("Sorry, you mistake. Try again.");

        }


    }

    static void checkNameBook() {
        robot("Remember, books has Rules:");
        rulesBooksName();
        while (true) {
            boolean checkBool = true;
            stopDublicateCode();
            String check = sc.nextLine();
            char[] temp = check.toCharArray();
            if (check.length() == 2
                    && !String.valueOf(temp[0]).equals(" ")
                    && Character.isLetter(temp[0])) {
                bookToArray += check + ",";
                robot("Book is add");
                return;
            } else if (check.length() > 2) {
                int tempCountSymbol = 0;
                int tempCountLetter = 0;
                for (char tempChar : temp) {
                    if (Character.isLetter(tempChar)) {
                        tempCountLetter++;
                    } else {
                        tempCountSymbol++;
                    }
                }
                if (tempCountSymbol < tempCountLetter) {
                    robot("Book is add");
                    bookToArray += check + ",";
                    return;
                }
            }
            if (checkBool) {
                robot("Sorry, but you do mistake. Book names has rules:");
                rulesBooksName();
                System.out.println("\n".repeat(12));
                robot("Try again. Write name book:");
            }
        }

    }

    static void rulesBooksName() {
        System.out.println("\n".repeat(12));
        System.out.println("Rules of name book" +
                "\n1. Name must be more 1 symbol " +
                "\n2. First symbol must be always be a letter " +
                "\n3. Letters must be more then another symbols" +
                "\n4. Books name cannot be have symbol \",\"");
        pause(timeRobot+2);

    }

    static void stopDublicateCode() {
        System.err.print("\nWrite here:");

    }

    static void deleteBook() {
        while (true) {
            robot("Brrrrrrrrr, you choice delete book. I show you list of books");
            String[] tempArray = bookToArray.split(",");
            String[] bookArray = bookToArray.split(",");
            int count = 0;
            for (int i = 0; i < tempArray.length; i++) {
                tempArray[i] = ++count + " # of book  is" + tempArray[i];
            }
            System.out.println();
            for (String book : tempArray) {
                System.out.println(book);
            }
            pause(timeRobot+2);
            robot("Just write number of book you want to delete");
            stopDublicateCode();
            int choice = checkDigit();
            int tempLength  = tempArray.length == 1? tempArray.length : tempArray.length +1;
            if (choice <= 0 || choice >= tempLength) {
                robot("Sorry, you do mistake. Try again.");
                continue;
            }
            bookArray[choice - 1] = " ";
             bookToarrayUpdate = "";
            for (int i = 0; i < bookArray.length; i++) {
                String s = bookArray[i];
                if (!s.equals(" ") || s.length() > 1) {
                    bookToarrayUpdate += s + ",";
                }
            }
           bookToArray = bookToarrayUpdate;
            if(bookArray.length <=1){
                robot("Sorry, books are over");
                return;
            }
            robot("Do you want delete more books? Write Y or N like always");
            System.out.println();
            stopDublicateCode();
            String answ = sc.next();
            sc.skip("\n");
            if (answ.equalsIgnoreCase("n")) {
                return;
            }
            robot("Sorry, you mistake. Try again.");

        }
        }
    static void changeBookName() {
        while (true) {
            robot("Brrrrrrrrr, you choice change name book. I show you list of books");
            String[] tempArray = bookToArray.split(",");
            String[] bookArray = bookToArray.split(",");
            int count = 0;
            for (int i = 0; i < tempArray.length; i++) {
                tempArray[i] = ++count + " # of book  is" + tempArray[i];
            }
            System.out.println();
            for (String book : tempArray) {
                System.out.println(book);
            }
            pause(timeRobot+2);
            robot("Just write number of book you want to change");
            stopDublicateCode();
            int choice = checkDigit();
            int tempLength  = tempArray.length == 1? tempArray.length : tempArray.length +1;
            if (choice <= 0 || choice >= tempLength) {
                robot("Sorry, you do mistake. Try again.");
                continue;
            }
            robot("Now write new name of book");
            stopDublicateCode();
            bookArray[choice - 1] = sc.nextLine();
            bookToarrayUpdate = "";
            for (int i = 0; i < bookArray.length; i++) {
                String s = bookArray[i];
                if (!s.equals(" ") || s.length() > 1) {
                    bookToarrayUpdate += s + ",";
                }
            }
            bookToArray = bookToarrayUpdate;
            if(bookArray.length <=1){
                robot("Sorry, books are over");
                return;
            }
            robot("Do you want change more books? Write Y or N like always");
            System.out.println();
            stopDublicateCode();
            String answ = sc.next();
            sc.skip("\n");
            if (answ.equalsIgnoreCase("n")) {
                return;
            }
            robot("Sorry, you mistake. Try again.");
        }
    }
    static void findBook(){

    }
    }



