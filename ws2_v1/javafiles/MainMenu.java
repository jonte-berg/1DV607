package w2;

import java.nio.file.*; 
import java.util.Scanner;
import w2.Boat;

public class MainMenu {
	
	public static void main(String args[]) throws Exception{ 
			
		Path path = Paths.get("Club.xml"); 
	        if(Files.notExists(path)){ 
	         	BoatClub club = new BoatClub();
				XML.save(club);	
	           	menu();
	        }
	      
	        	menu();
	        
	 }
	
	
	public static void menu() throws Exception{
		
		int choice = 0; 
		Scanner listener = new Scanner(System.in); 
		System.out.println("~~~~~~~~~~~~~~~Member Registry Program~~~~~~~~~~~~~~~~"); 
		System.out.println("~Press enter the number corresponding to the function~");
		System.out.println("1. Create a member");
		System.out.println("2. Edit a member");
		System.out.println("3. Delete a member");
		System.out.println("4. Show list  of members");
		System.out.println("5. Register a boat");
		System.out.println("6. Delete a boat");
		System.out.println("7. Edit a boat");
		while(listener.hasNextInt()==true){ 
			choice = listener.nextInt(); 
			if(choice < 1 || choice >7){ 
				System.err.println("Your input is invalid ! Try again");
			}else{ 
				switch(choice){ 
				case 1: 
				createMember();  
				break; 
				case 2: 
				editMember(); 
				break; 
				case 3: 
				deleteMember(); 
				break; 
				case 4:
				showList(); 
				break; 
				case 5: 
				registerBoat(); 
				break; 
				case 6: 
				deleteBoat(); 
				break;
				case 7:
				editBoat();
				break;
				default: System.err.println("Your input is invalid ! Try again"); 
				}
			}
		}; 
  }
	

	public static void createMember() throws Exception{ 
	String name = null; 
	String personnr = null;
	String confirm = null;
	Scanner listener = new Scanner(System.in);
	System.out.println("Enter the name of the member you want to add: ");
	while(listener.hasNext()){ 
	name = listener.nextLine(); 
	if(name.length()<3){ 
		System.err.println("The name you entered is too short (min 3 characters) !  Try again!");
		listener = new Scanner(System.in); 
	}else{ 
		break; 
	}
	} 
	System.out.println("Enter the personal number of member (10 characters) : ");
	while(listener.hasNext()){
		personnr = listener.nextLine(); 
	if(personnr.length()!=10){ 
		System.err.println("The personal number you entered is invalid ! Use the format: YYMMDDXXXX Try again !");
		listener = new Scanner(System.in);
	}else{ 
		break; 
	}	 
	} 
	System.out.println("Member Name :  " + name + "   Member Personal Number :  " + personnr);
	System.out.println("Are you sure you want to create this member ? Y/N");
	while(listener.hasNext()){ 
	confirm = listener.nextLine();
	
	if(confirm.equals("Y")||confirm.equals("y")){ 
		System.out.println("DEBUG: Confirmed !");
		BoatClub club = XML.load();
		club.addMember(new Member(name,personnr));
		XML.save(club);
		menu(); 
		//CREATE / SAVE MEMBER CODE HERE
	}else{
		System.out.println("Cancelled !");
		menu(); 
		 }
		break; 
	}
	listener.close();
}
	

	//working as intended except if a non-int is used as input @start
	public static void editMember()throws Exception{ 
	int id = 0; 
	String name = null; 
	String personnr = null; 
	Scanner listener = new Scanner(System.in); 
	String confirm = null; 
	BoatClub club = XML.load();
	System.out.println(club.verbose());
	System.out.println("Enter member ID you want to edit: ");
	while(listener.hasNextInt()){ 
		id = listener.nextInt();
	if(id < 0|| id >club.getAllMembers().size()-1){
		System.err.println("The ID you entered is invalid ! " );
	}else{ 
		break; 
	}
	}
	name=club.getAllMembers().get(id).getName();
	personnr=club.getAllMembers().get(id).getPersonalNumber();
	System.out.println("Want to change name of selected member? (y/n)");
	listener = new Scanner(System.in); 
	while(listener.hasNext()){ 
		confirm = listener.nextLine();
		
		if(confirm.equals("Y")||confirm.equals("y")){ 
		
			System.out.println("Enter the new name of the member: ");
			listener = new Scanner(System.in); 
			while(listener.hasNext()){ 
				name = listener.nextLine(); 
				if(name.length()<3){ 
					System.err.println("The name you entered is too short ! Try again!");
					listener = new Scanner(System.in); 
				}else{ 
					break; 
				}
				}
		
		
		}else{
			System.out.println("Enter the new personal number of the member: ");
			listener = new Scanner(System.in); 
			while(listener.hasNext()){
				personnr = listener.nextLine(); 
			if(personnr.length()!=12){ 
				System.out.println("The personal number you entered is invalid ! Try again !");
				listener = new Scanner(System.in);
			}else{ 
				break; 
			}	 
			} 
			 }
			break; 
		}
	

	System.err.println("Are you sure (Y/N) you want to edit the member ID : " + id + " to " + "Member name: " +name+"  Personal Number: " + personnr);
	listener = new Scanner(System.in); 
	while(listener.hasNext()){ 
	confirm = listener.nextLine(); 
	if(confirm.equals("Y")||confirm.equals("y")){ 
		System.out.println("DEBUG: Confirmed !");
		club.getAllMembers().get(id).setName(name);
		club.getAllMembers().get(id).setPersonalNumber(personnr);
		XML.save(club);
		menu(); 
	
	}else{ 
		System.out.println("DEBUG: Cancelled !");
		menu(); 
	}
	
	}
	
}
	
	
	//working as intended except if a non-int is used as input @start
	public static void deleteMember() throws Exception{
		int id =0;
		Scanner listener = new Scanner(System.in); 
		String confirm = null; 
		BoatClub club = XML.load();
		System.out.println(club.verbose());
		System.out.println("Enter member ID you want to Delete: ");
		while(listener.hasNext()){ 
			 id = listener.nextInt();
		if(id < 0|| id >club.getAllMembers().size()-1){
			System.err.println("The ID you entered is invalid ! " );
		}else{ 
			break; 
		}
		}
		System.err.println("Are you sure (Y/N) you want to Delete the member ID : " + id + " \n" + "Member name: " +club.getAllMembers().get(id).getName()+"\tPersonal Number: " + club.getAllMembers().get(id).getPersonalNumber());
		listener = new Scanner(System.in); 
		while(listener.hasNext()){ 
		confirm = listener.nextLine(); 
		if(confirm.equals("Y")||confirm.equals("y")){ 
			System.out.println("DEBUG: Confirmed !");
			club.getAllMembers().remove(id);
			
			XML.save(club);
			menu(); 
			//EDIT BOAT CODE HERE
		}
		else{ 
			System.out.println("DEBUG: Cancelled !");
			menu(); 
		}
		}
		
	
	}

	

	public static void showList() throws Exception{ 
	String choice="";
	Scanner listener = new Scanner(System.in); 
	System.out.println("Select a list");
	System.out.println("1. Verbose List"); 
	System.out.println("2. Compact List");
	while(listener.hasNext()){ 
		choice = listener.nextLine(); 
			if(choice.equals("1")){ 
				BoatClub club = XML.load();
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Verbose list of the members in the club: ");
				System.out.println(club.verbose());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				break;
			}
			else if(choice.equals("2")){ 
				BoatClub club = XML.load();
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Compact list of the members in the club: ");
				System.out.println(club.compact());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				
				break;
			}
			else{
				System.err.println("Invalid choice ! Try again !");
			}
		}
	System.out.println("Press any key to go back to the Main menu");
	choice="";
	while(listener.hasNext()){ 
		 choice = listener.next();
		 if(choice!=""){
			 System.out.println("\n\n\n\n\n\n\n\n");
			 menu();
		 }
		
		 
	}
	
	
	
	
}
	
	public static void registerBoat()throws Exception{ 
	int id = 0; 
	BoatClub club = XML.load();
	String type = null; 
	int length = 0; 
	String confirm = null; 
	Scanner listener = new Scanner(System.in); 
	System.out.println("Enter boat type: ");
	while(listener.hasNext()){ 
		type = listener.nextLine(); 
	if(type.length()<3){ 
		System.err.println("The boat type is too short ! Try again !");
	}else{ 
		break; 
	}
	}
	System.out.println("Enter boat length: ");
	listener = new Scanner(System.in); 
	while(listener.hasNextInt()){ 
		length = listener.nextInt(); 
	if(length<0){ 
	System.err.println("The length of the boat is too short ! Try again !"); 
	}else{ 
		break; 
	}
	}
	System.out.println(club.verbose());
	
	System.out.println("Enter the ID of the owner: ");
	listener = new Scanner(System.in); 
	while(listener.hasNextInt()){ 
		id = listener.nextInt(); 
	if(id<0){ 
	System.err.println("The id of the member is invalid ! Try again !");
	}else{ 
		break; 
	}
	}
	System.out.println("Are you sure (Y/N) you want to add boat of " + "Type: " +type+ "  Length: " + length + " to member with id :  " + id);
	listener = new Scanner(System.in); 
	while(listener.hasNext()){ 
		confirm = listener.nextLine(); 
	if(confirm.equals("Y")||confirm.equals("y")){ 
		Boat b1 = new Boat(club.getAllMembers().get(id).list.size(), type,length);
		club.getAllMembers().get(id).addBoat(b1);
		XML.save(club);
		System.out.println("Boat added to selected member!");
		menu(); 
	}else if(confirm.equals("N")||confirm.equals("n")){ 
		System.out.println("Cancelled");
		menu(); 
	}
	else{
		System.err.println("Invalid input, try again!");
		
	}
	}
	}
	public static void deleteBoat() throws Exception{ 
		int id = 0; 
		int boatid=0;
		BoatClub club = XML.load();

	
		String confirm = null; 
		Scanner listener = new Scanner(System.in); 
	
		
		System.out.println(club.compact());
		
		System.out.println("Enter the ID of the owner: ");
		listener = new Scanner(System.in); 
		while(listener.hasNextInt()){ 
			id = listener.nextInt(); 
		if(id<0){ 
		System.err.println("The id of the member is invalid ! Try again !");
		}else{ 
			break; 
		}
		}
		System.out.println(club.getAllMembers().get(id).verboseToString());
		System.out.println("Select boat id to remove:");
		listener = new Scanner(System.in); 
		while(listener.hasNextInt()){ 
			
			boatid = listener.nextInt(); 
			if(boatid<0||boatid>club.getAllMembers().get(id).list.size()){ 
				System.err.println("The id of the member is invalid ! Try again !");
				}else{ 
					break; 
				}
		}
		System.out.println("Are you sure (Y/N) you want to remove  boat with id:  " + boatid);
		listener = new Scanner(System.in); 
		while(listener.hasNext()){ 
			confirm = listener.nextLine(); 
		if(confirm.equals("Y")||confirm.equals("y")){ 
			
			club.getAllMembers().get(id).removeBoat(boatid);
			XML.save(club);
			System.out.println("Boat deleted!");
			menu(); 
		}else if(confirm.equals("N")||confirm.equals("n")){ 
			System.out.println("Cancelled");
			menu(); 
		}
		else{
			System.err.println("Invalid input, try again!");
			
		}
		
		}
	}
	public static void editBoat() throws Exception{ 
		int id = 0; 
		int boatid=0;
		String type="";
		int length=0;
		BoatClub club = XML.load();

	
		String confirm = null; 
		Scanner listener = new Scanner(System.in); 
	
		
		System.out.println(club.compact());
		System.out.println("Enter the ID of the owner: ");
		listener = new Scanner(System.in); 
		while(listener.hasNextInt()){ 
			id = listener.nextInt(); 
		if(id<0){ 
		System.err.println("The id of the member is invalid ! Try again !");
		}else{ 
			break; 
		}
		}
		System.out.println(club.getAllMembers().get(id).verboseToString());
		System.out.println("Select boat id to edit:");
		listener = new Scanner(System.in); 
		while(listener.hasNextInt()){ 
			
			boatid = listener.nextInt(); 
			if(boatid<0||boatid>club.getAllMembers().get(id).list.size()){ 
				System.err.println("The id of the boat is invalid ! Try again !");
				}else{ 
					break; 
				}
		}
		type=club.getAllMembers().get(id).list.get(boatid).getType();
		length=club.getAllMembers().get(id).list.get(boatid).getLength();
		
		System.out.println("Want to change type  of selected boat? (y/n)");
		listener = new Scanner(System.in); 
		while(listener.hasNext()){ 
			confirm = listener.nextLine();
			
			if(confirm.equals("Y")||confirm.equals("y")){ 
			
				System.out.println("Enter the new type of the boat: ");
				listener = new Scanner(System.in); 
				while(listener.hasNext()){ 
					type = listener.nextLine(); 
					if(type.length()<3){ 
						System.err.println("The type you entered is too short ! Try again!");
						listener = new Scanner(System.in); 
					}else{ 
						break; 
					}
					}
			
				}
			else{
				System.out.println("Enter the new length of the member boat: ");
				listener = new Scanner(System.in); 
				while(listener.hasNextInt()){
					length = listener.nextInt(); 
				if(length<0){ 
					System.out.println("The length you entered is invalid ! Try again !");
					listener = new Scanner(System.in);
				}else{ 
					break; 
				}	 
				} 
				 }
				break; 
			}
		

		System.err.println("Are you sure (Y/N) you want to edit the boat with  ID : " + boatid + " to " + "Boat Type: " +type+"  Length: " + length);
		listener = new Scanner(System.in); 
		while(listener.hasNext()){ 
		confirm = listener.nextLine(); 
		if(confirm.equals("Y")||confirm.equals("y")){ 
			System.out.println("Confirmed !");
			club.getAllMembers().get(id).list.get(boatid).setLength(length);
			club.getAllMembers().get(id).list.get(boatid).setType(type);
			XML.save(club);
			menu(); 
		
		}else{ 
			System.out.println("Cancelled !");
			menu(); 
		}
		
		}
		}
}


