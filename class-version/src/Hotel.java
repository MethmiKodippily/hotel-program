//Any code taken from other sources is referenced within my code solution.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Hotel {
    static Scanner  input = new Scanner(System.in);
    //
    static CircularQueue circle = new CircularQueue();
    public static void main(String[] args) {
        //arrays to store rooms, znd the waiting list.
        Person[] hotel = new Person[8];
        Person[] queue = new Person[8];

        //calling the initialize method.
        initialise(hotel);
        initialise(queue);
        //while loop to print the menu again after the user completes running one choice
        while (true) {
            //printing the menu
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("MENU:");
            System.out.println();
            System.out.println("A - Add new customer to room.");
            System.out.println("V - View all rooms.");
            System.out.println("E - Display Empty rooms.");
            System.out.println("D - Delete customer from room.");
            System.out.println("F - Find room from customer name.");
            System.out.println("S - Store program data into file.");
            System.out.println("L - Load program data from file.");
            System.out.println("O - View guests Ordered alphabetically by name.");
            System.out.println();

            System.out.print("Enter Choice or enter exit to stop: ");
            String choice = input.nextLine();
            System.out.println("-----------------------------------------------------------------------");

            if (choice.equals("A")) {
                AddCustomer(hotel,queue); //calling the AddCustomer method.
            }
            if(choice.equals("V")){
                ViewRoom(hotel); //calling the ViewRoom method.
            }
            if(choice.equals("E")){
                EmptyRooms(hotel); //calling the EmptyRooms method.
            }
            if(choice.equals("D")){
                DeleteCustomer(hotel,queue); //calling the DeleteCustomer method.
            }
            if(choice.equals("F")){

                FindRoom(hotel); //calling the FindRoom method.
            }
            if(choice.equals("S")){
                StoreData(hotel); //calling the StoreData method.
            }
            if(choice.equals("L")){
                LoadData(); //calling the LoadData method.
            }
            if(choice.equals("O")){
                //creating a new array with all the elements in hotel[] array.
                String[] hotelNew=new String[8];
                for (int x = 0; x < 8; x++ ) {
                    hotelNew[x]=hotel[x].getName(); //calling the OrderNames method.
                }
                OrderNames(hotelNew);
            }
            //the program will terminate when the user enters "exit".
            if(choice.equals("exit")){
                System.exit(0);
            }
        }
    }
    //a method to add a new guest to a room.
    public static void AddCustomer (Person[] hotel, Person[] queue){
        int count=0;
        //checking the number of empty rooms.
        for (int x = 0; x < 8; x++ ) {
            if (hotel[x].getName().equals("e")){
                count++; }
        }
        if(count==0){
            if (circle.isFull()) {
                System.out.println("Both Queue and Rooms are full");
            } else{
                System.out.println("You will be added to the waiting list");
                System.out.print("Enter the name you would like to book the room under:");
                String customerName = input.next();
                System.out.print("Enter number of guests: ");
                int guestNum = input.nextInt();
                System.out.println("Enter personal information of the paying guest");
                System.out.print("First Name:");
                String fName = input.next();
                System.out.print("Surname:");
                String sName = input.next();
                System.out.print("Credit card number:");
                int cardNum = input.nextInt();

                //assigning the input values to the last index of the queue
                int rear = circle.enQueue();
                queue[rear].setName(customerName);
                queue[rear].setGuestNum(guestNum);
                queue[rear].setFName(fName);
                queue[rear].setSName(sName);
                queue[rear].setCardNum(cardNum);

            }input.nextLine();

        }else {
            System.out.print("Enter room number (0-7): ");
            int roomNum = input.nextInt();
            if (hotel[roomNum].getName().equals("e")) {
                System.out.print("Enter the name you would like to book room number " + roomNum + " under:");
                String customerName = input.next();
                System.out.print("Enter number of guests: ");
                int guestNum = input.nextInt();
                System.out.println("Enter personal information of the paying guest");
                System.out.print("First Name:");
                String fName = input.next();
                System.out.print("Surname:");
                String sName = input.next();
                System.out.print("Credit card number:");
                int cardNum = input.nextInt();

                //assigning user inputs to the index corresponding to the room chosen
                hotel[roomNum].setName(customerName);
                hotel[roomNum].setGuestNum(guestNum);
                hotel[roomNum].setFName(fName);
                hotel[roomNum].setSName(sName);
                hotel[roomNum].setCardNum(cardNum);

                //if the chosen room is already occupied, the bellow message will be displayed.
            } else {
                System.out.println("This room is already occupied!");
            }input.nextLine();

        }
    }
    //method thp view all rooms
    public static void ViewRoom(Person[] hotel){
        for (int x = 0; x < 8; x++ )
        {
            System.out.println("room " + x + " occupied by " + hotel[x].getName()+".");
        }

    }
    //method to print all the rooms that are empty
    public static void EmptyRooms(Person hotel[]){
        for (int x = 0; x < 8; x++ )
        {
            if (hotel[x].getName().equals("e"))System.out.println("room " + x + " is empty.");
        }
    }
    //method to delete a guest that has already been added toa room.
    public static void DeleteCustomer(Person[] hotel,Person[] queue){
        System.out.println("Enter the name of the customer who should be removed: ");
        String delName = input.next();
        input.nextLine();
        int roomNum = 0;
        for (int x = 0; x < 8; x++ )
        {
            if(delName.equals(hotel[x].getName())){
                hotel[x].setName("e");
                roomNum=x;
            }
        }
        if(!circle.isEmpty()){
            int front = circle.deQueue();
            hotel[roomNum].setName(queue[front].getName());
            hotel[roomNum].setGuestNum(queue[front].getGuestNum());
            hotel[roomNum].setFName(queue[front].getFName());
            hotel[roomNum].setSName(queue[front].getSName());
            hotel[roomNum].setCardNum(queue[front].getCardNum());
        }
    }
    //method find to find out which room a given guest is in.
    public static void FindRoom(Person[] hotel){
        System.out.println("Enter the name of the customer: ");
        String findName = input.next();
        input.nextLine();
        for (int x = 0; x < 8; x++ )
        {
            if(findName.equals(hotel[x].getName())){
                System.out.println(findName+" is in room "+x);
            }
        }
    }
    //storing all the data that has been entered up to that point in a text file
    //https://www.youtube.com/watch?v=SslMi6ptwH8
    public static void StoreData(Person hotel[]){
        try {
            FileWriter myWriter= new FileWriter("programData_2.txt");
            int count=0;
            for (int x = 0; x < 8; x++ ) {
                if (hotel[x].getName().equals("e")){
                    count++; //counts the number of empty rooms.
                }
            }
            if(count!=8){
                for (int x = 0; x < 8; x++ )
                {
                    if (!hotel[x].getName().equals("e")) {
                        myWriter.write("room " + x + " occupied by " + hotel[x].getName()+"\n");
                        myWriter.write("Number of guests: "+ hotel[x].getGuestNum()+"\n");
                        myWriter.write("First name of paying customer: "+ hotel[x].getFName()+"\n");
                        myWriter.write("Last name of paying customer: "+ hotel[x].getSName()+"\n");
                        myWriter.write("Credit card number: "+ hotel[x].getCardNum()+"\n\n");
                    }
                }
                System.out.println("Successfully stored in file.");
                //if every room is empty when the data is loaded the bellow message will be printed
            }else {
                //if every room is empty when the data is saved the bellow message will be printed
                System.out.println("No data has been entered to save!\n");
                //if every room is empty when the data is loaded the bellow message will be printed
                myWriter.write("No data has been saved" + " to display!\n");
            }
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    //method to load saved data from the text file
    //https://www.youtube.com/watch?v=SslMi6ptwH8
    public static void LoadData(){
        try {
            File myObj = new File("programData_2.txt");
            Scanner myReader= new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data= myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void OrderNames(String hotelNew[]){
        //https://www.softwaretestinghelp.com/sort-arrays-in-java/
        for(int i = 0; i<hotelNew.length-1; i++)
        {
            for (int j = i+1; j<hotelNew.length; j++)
            {
                //comparing each elements of the array to all the remaining elements
                if(hotelNew[i].compareTo(hotelNew[j])>0)
                {
                    //swapping array elements
                    String temp = hotelNew[i];
                    hotelNew[i] = hotelNew[j];
                    hotelNew[j] = temp;
                }
            }
        }
        //printing the sorted array in ascending order excluding "e"
        for (int x = 0; x<8; x++){
            if((!hotelNew[x].equals("e"))){
                System.out.println(hotelNew[x]);
            }
        }
    }
    //method to initialize the array
    private static void initialise (Person hotelRef[] ){
        for (int x = 0; x < 8; x++) hotelRef[x]=new Person("e");
        System.out.println("initialize ");
    }
}
