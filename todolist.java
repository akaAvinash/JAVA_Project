import java.util.*;

public class todolist {
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args){

        System.out.println("Welcome TO-DO List.");
        todo to = new todo();

        to.todoList();

    }

    void todoList(){
        int choice;

        do{
            System.out.println("\n====To-Do List====");
            System.out.println("1. Add Task");
            System.out.println("2. Show Task");
            System.out.println("3. Remove Task");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            while(!scanner.hasNextInt()){
                System.out.println("Invalid input. Select an input between 1 to 4.");
                scanner.next();
                System.out.print("Enter your choice: ");
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    addTask();
                    break;
                case 2:
                    showTask();
                    break;
                case 3:
                    removeTask();
                    break;
                case 4:
                    System.out.println("Exiting app, have a good day.");
                    break;
                default:
                    System.out.println("Invalid choice, please put correct input.");
            }

        }while(choice!=4);
    }

    void addTask(){

        char repeat;

        do{
            System.out.print("Enter the task: ");
            String task = scanner.nextLine();
            tasks.add(task);
            System.out.println("Task added successfully.");

            System.out.println("Do you wish to enter more task ? (y/n): ");
            repeat = scanner.next().charAt(0);
            scanner.nextLine();

        }while(repeat == 'y' || repeat == 'Y');
        System.out.println("Thanks for feeding the task input.");
    }

    void showTask(){
        if(tasks.isEmpty()){
            System.out.println("You donot have tasks to show.");
        }
        else {
            System.out.println("\n Your Task List");
            for(int i = 0; i < tasks.size(); i++){
                System.out.println((i+1) + " " + tasks.get(i));
            }
        }
    }

    void removeTask(){
        showTask();
        if(!tasks.isEmpty()){
            System.out.print("Enter the task to remove: ");
            int taskNumber = scanner.nextInt();
            scanner.nextLine();

            if(taskNumber > 0 && taskNumber <= tasks.size()){
                tasks.remove(taskNumber - 1);
                System.out.println("Task Removed Successfully.");
            }
            else {
                System.out.println("Invalid Task Number.");
            }
        }
    }
}
