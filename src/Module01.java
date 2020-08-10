import java.util.Scanner;

public class Module01 {
   static Scanner scanner = new Scanner(System.in);
    /*
I know this program should be simpler.
But I decided to make myself a challenge.
And also, I think it's fun!
Let you have fun too!
Idea - interactive chat-bot
     */






    public static void main(String[] args)  {

        System.out.println("Idea - interactive chat-bot \nFor test menu write test\nFor test full program write start");
        while (true){
            String answ = scanner.nextLine();
            if(answ.equalsIgnoreCase("start")){
                Realization.start();
                break;
            }
            else if(answ.equalsIgnoreCase("test")){
                Realization.timeRobot = 0;
                Realization.menuOfProgram();
                break;
            }
            else {
                System.out.println("Try again");
            }
        }



    }
}
