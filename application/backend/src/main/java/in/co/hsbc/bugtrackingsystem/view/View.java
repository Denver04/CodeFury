package in.co.hsbc.bugtrackingsystem.view;

public class View {

    public void printHeader() {
        System.out.println("Welcome to Bug Tracking System");
        System.out.println("===============================");
    }

    public void printMenu() {
        System.out.println("1. Create User");
        System.out.println("2. Create Project");
        System.out.println("3. Assign User to Project");
        System.out.println("4. Report Bug");
        System.out.println("5. View All Users");
        System.out.println("6. View All Projects");
        System.out.println("7. View All Bugs in a Project");
        System.out.println("8. Exit");
        System.out.print("Select an option: ");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}