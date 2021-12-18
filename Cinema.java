package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the number of rows:");
            int rows = scanner.nextInt();
            System.out.println("Enter the number of seats in each row:");
            int seats = scanner.nextInt();
            
            String[][] cinema = new String[rows+1][seats+1];
            
            for (int i = 0; i < cinema.length; i++) {
            	for (int j = 0; j < cinema[i].length; j++) {
            		if (i == 0 && j == 0) {
            			cinema[i][j] = "  ";
            		} else if (i == 0) {
            			cinema[i][j] = j + " ";
            		} else if (j == 0) {
            			cinema[i][j] = i + " ";
            		} else {
            			cinema[i][j] = "S ";
            		}
            	}
            }
            
            int answer = 1;
            while (true) {
            	System.out.println("1. Show the seats");
                System.out.println("2. Buy a ticket");
                System.out.println("3. Statistics");
                System.out.println("0. Exit");
            	answer = scanner.nextInt();
            	if (answer == 1) {
            		showTheSeats(cinema);
            	} else if (answer == 2) {
            		buyATicket(rows, seats, cinema);
            	} else if (answer == 3) {
            		statistics(rows, seats, cinema);
            	}
            	if (answer == 0) {
            		break;
            	}
            }
    }
    	
    
    public static void showTheSeats(String[][] cinema) {
    	// Printing array with all places over the cinema
        System.out.println("Cinema:");
        for (int i = 0; i < cinema.length; i++) {
        	for (int j = 0; j < cinema[i].length; j++) { 
        		System.out.print(cinema[i][j]);
        	}
        	System.out.print("\n");
        }
    }
    
    
    public static void buyATicket(int rows, int seats, String[][] cinema) {
    	Scanner scanner = new Scanner(System.in);
    	boolean answer = true;
    	int pickedRow = 0;
    	int pickedSeat = 0;
    	while(answer) {
    		System.out.println("Enter a row number:");
	        pickedRow = scanner.nextInt();
	        System.out.println("Enter a seat number in that row:");
	        pickedSeat = scanner.nextInt();
	        if (pickedRow > rows || pickedRow < 0 || pickedSeat > seats || pickedSeat < 0) {
	        	System.out.println("Wrong input!");
	        } else if(cinema[pickedRow][pickedSeat] == "B ") {
	        	System.out.println("That ticket has already been purchased!");
	        } else {
	        	break;
	        }
    	}
	        
        
        
     
        int totalSeats = rows * seats;
        int ticketPrice = 0;
        int half = rows / 2;
        if (totalSeats <= 60) {
        	ticketPrice = 10;
        } else if (pickedRow <= half) {
        	ticketPrice = 10;
        } else if (rows % 2 == 1 && pickedRow >= half + 1) {
        	ticketPrice = 8;
        } else if (rows % 2 == 0 && pickedRow >= half) {
        	ticketPrice = 8; 
        }
        System.out.println("Ticket price: $" + ticketPrice);
        
        // changing array with clients place
        for (int i = 0; i < cinema.length; i++) {
        	for (int j = 0; j < cinema[i].length; j++) { 
        		if (i == pickedRow && j == pickedSeat) {
        			cinema[i][j] = "B ";
        		}
        	}
        }
    }
    
    public static void statistics(int rows, int seats, String[][] cinema) {
  
    	double totalSeats = rows * seats;
    	int ticketPrice = 0;
    	int ticketIncome = 0;
    	int half = rows / 2;
    	int tickets = 0;
    	
    	for (int i = 0; i < cinema.length; i++) {
        	for (int j = 0; j < cinema[i].length; j++) { 
        		if (cinema[i][j] == "B ") {
        			tickets++;
        			if (totalSeats <= 60) {
        	        	ticketPrice = 10;
        	        	ticketIncome += ticketPrice;
        	        } else if(i <= half) {
        	        	ticketPrice = 10;
        	        	ticketIncome += ticketPrice;
        	        } else if ( rows % 2 == 1 && i >= half + 1) {
        	        	ticketPrice = 8;
        	        	ticketIncome += ticketPrice;
        	        } else if (rows % 2 == 0 && i >= half) {
        	        	ticketPrice = 8; 
        	        	ticketIncome += ticketPrice;
        	        }
        		}
        	}
        }
    	System.out.println("Number of purchased tickets: " + tickets);
    	double percentage = 0.00;
    	String perc = "0.00";
    	if (tickets == 0) {
    		percentage = 0.00;
    	} else {
    		percentage =  (tickets * 100) / totalSeats;
    		perc = String.format("%.2f", percentage);
    	}
    	System.out.println("Percentage: " + perc + "%");
    	System.out.println("Current income: $" + ticketIncome);
    	int totalIncome = 0;
        int rowsDivided = rows / 2 + 1;
    	if (totalSeats <= 60) {
        	totalIncome = (int) (10 * totalSeats);
        } else if (rows % 2 == 0){
        	totalIncome = (int) ((10 * totalSeats / 2) + (8 * totalSeats / 2));
        } else {
        	totalIncome = (int) ((8 * rowsDivided * seats) + (10 * (rows / 2) * seats));
        }
    	System.out.println("Total income: $" + totalIncome);
    }
}	
