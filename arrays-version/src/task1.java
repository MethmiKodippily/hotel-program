//Any code taken from other sources is referenced within my code solution.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class task1 {

    static Scanner  input = new Scanner(System.in);

    public static void main(String[] args) {
        //arrays to store rooms, number of guests, first name of the paying guest, surname of the paying guest, and credit card number of the paying guest respectively.
        String[] hotel = new String[8];
        int[] guests= new int[8];
        String[] firstName= new String[8];
        String[] surname= new String[8];
        int[] ccNumber= new int[8];

        //calling the initialize method.
        initialise(hotel);

        //while loop to print the menu again after the user completes running one choice.
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
                AddCustomer(hotel,guests,firstName,surname,ccNumber); //calling the AddCustomer method.
            }
            if(choice.equals("V")){
                ViewRoom(hotel); //calling the ViewRoom method.
            }
            if(choice.equals("E")){
                EmptyRooms(hotel); //calling the EmptyRooms method.
            }
            if(choice.equals("D")){
                //getting the name of the guest that should be deleted from the user.
                System.out.println("Enter the name of the guest who should be removed: ");
                String delName = input.next();
                DeleteCustomer(delName,hotel); //calling the DeleteCustomer method.
            }
            if(choice.equals("F")){
                //getting the name of the guest who needs to be found, from the user.
                System.out.println("Enter the name of the customer: ");
                String findName = input.next();
                FindRoom(findName,hotel); //calling the FindRoom method.
            }

            if(choice.equals("S")){
                StoreData(hotel,guests,firstName,surname,ccNumber); //calling the StoreData method.
            }
            if(choice.equals("L")){
                LoadData(); //calling the LoadData method.
            }
            if(choice.equals("O")){
                //creating a new array with all the elements in hotel[] array.
                String[] hotelNew=new String[8];
                for (int x = 0; x < 8; x++ ) {
                    hotelNew[x]=hotel[x];
                }
                OrderNames(hotelNew); //calling the OrderNames method.
            }
            //the program will be terminated when the user enters "exit".
            if(choice.equals("exit")){
                System.exit(0);
            }
        }
    }

    //a method to add a new guest to a room.
    public static void AddCustomer (String hotel[],int guests[],String firstName[],String surname[],int ccNumber[]){
        System.out.print("Enter room number (0-7): ");
        int roomNum = input.nextInt();
        //the program will prompt the user for more information if the chosen room is empty.
        if (hotel[roomNum].equals("e")) {
            System.out.print("Enter the name you would like to book room number " + roomNum + " under:");
            String customerName = input.next();
            System.out.print("Enter number of guests: ");
            int guestNum = input.nextInt();
            System.out.println("Enter personal information of the paying guest");
            System.out.print("First Name:");
            String fName=input.next();
            System.out.print("Surname:");
            String sName=input.next();
            System.out.print("Credit card number:");
            int cardNum = input.nextInt();

            //adding the respective information to the right index of the respective arrays.
            hotel[roomNum] = customerName;
            guests[roomNum] = guestNum;
            firstName[roomNum] = fName;
            surname[roomNum] = sName;
            ccNumber[roomNum] = cardNum;

            //if the chosen room is already occupied, the bellow message will be displayed.
        }else {
            System.out.println("This room is already occupied!");
        }input.nextLine();
    }
    //method thp view all rooms
    public static void ViewRoom(String hotel[]){
        for (int x = 0; x < 8; x++ )
        {
            System.out.println("Room " + x + " is occupied by " + hotel[x]);
        }
    }
    //method to print all the rooms that are empty
    public static void EmptyRooms(String hotel[]){
        for (int x = 0; x < 8; x++ )
        {
            if (hotel[x].equals("e"))System.out.println("Room " + x + " is empty");
        }
    }
    //method to delete a guest that has already been added toa room.
    public static void DeleteCustomer(String delName,String hotel[]){
        for (int x = 0; x < 8; x++ )
        {
            if(delName.equals(hotel[x])){
                hotel[x]="e";
            }
        }input.nextLine();
    }
    //method find to find out which room a given guest is in.
    public static void FindRoom(String findName, String hotel[]){
        for (int x = 0; x < 8; x++ )
        {
            if(findName.equals(hotel[x])){
                System.out.println(findName +" is in room "+x);
            }
        }input.nextLine();
    }
    //storing all the data that has been entered up to that point in a text file
    //https://www.youtube.com/watch?v=SslMi6ptwH8
    public static void StoreData(String hotel[], int guests[], String firstName[],String surname[], int ccNumber[]){
        try {
            FileWriter myWriter= new FileWriter("programData_1.txt");
            int count=0;
            for (int x = 0; x < 8; x++ ) {
                if (hotel[x].equals("e")){
                    count++; //counts the number of empty rooms.
                }
            }
            if(count!=8){
                for (int x = 0; x < 8; x++ )
                {
                    if (!hotel[x].equals("e")) {
                        myWriter.write("room " + x + " is occupied by " + hotel[x]+"\n");
                        myWriter.write("Number of guests: "+ guests[x]+"\n");
                        myWriter.write("First name of paying customer: "+ firstName[x]+"\n");
                        myWriter.write("Last name of paying customer: "+ surname[x]+"\n");
                        myWriter.write("Credit card number: "+ ccNumber[x]+"\n\n");
                    }
                }
                System.out.println("Successfully stored in file.");
                //if every room is empty when the data is loaded the bellow message will be printed
            }else {
                //if every room is empty when the data is saved the bellow message will be printed
                System.out.println("No data has been entered to save!\n");
                //if every room is empty when the data is loaded the bellow message will be printed
                myWriter.write("No data has been saved to display!\n");
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
            File myObj = new File("programData_1.txt");
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
            for (int j = i+1; j<8; j++)
            {
                //compares each elements of the array to all the remaining elements
                if(hotelNew[i].compareTo(hotelNew[j])>0)
                {
                    //swapping array elements
                    String temp = hotelNew[i];
                    hotelNew[i] = hotelNew[j];
                    hotelNew[j] = temp;
                }
            }
        }
        //prints the sorted array in ascending order excluding "e"(empty rooms)
        for (int x = 0; x<8; x++){
            if((!hotelNew[x].equals("e"))){
                System.out.println(hotelNew[x]);
            }
        }
    }
    //method to initialize the hotel array
    private static void initialise (String hotelRef[] ) {
        for (int x = 0; x < 8; x++) hotelRef[x] = "e";
        System.out.println("initialize ");
    }
}

















