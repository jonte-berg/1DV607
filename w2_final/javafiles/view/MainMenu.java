package w2.view;

import java.util.Scanner;
import w2.model.BoatClub;

public class MainMenu  {
	 BoatClub club;
	
	public MainMenu(BoatClub c){
	club=c;	
	}
	public  void menu() throws Exception{
		
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
		System.out.println("8. View a member");
		while(listener.hasNextInt()==true){ 
			choice = listener.nextInt(); 
			if(choice < 1 || choice >8){ 
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
				case 8:
				viewMember();
				break;
				default: System.err.println("Your input is invalid ! Try again"); 
				}
			}
		}
		listener.close();
  }
	

	public  void createMember() throws Exception{ 
	
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
			}
			else{ 
				break; 
			}
		} 
		
	System.out.println("Enter the personal number of member (12 characters) : ");
		while(listener.hasNext()){
			personnr = listener.nextLine(); 
			if(personnr.length()!=12){ 
				System.err.println("The personal number you entered is invalid ! Use the format: YYMMDDXXXX Try again !");
				listener = new Scanner(System.in);
			}
			else{ 
				break; 
			}	 
		}	
		
	System.out.println("Member Name :  " + name + "   Member Personal Number :  " + personnr);
	System.out.println("Are you sure you want to create this member ? Y/N");
	while(listener.hasNext()){ 
		confirm = listener.nextLine();
		if(confirm.equals("Y")||confirm.equals("y")){ 
			System.out.println("DEBUG: Confirmed !");
			club.addMember(name,personnr);
			menu(); 
		}
		else{
			System.out.println("Cancelled !");
			menu(); 
		}
		break; 
	}
	listener.close();
}
	

	//working as intended except if a non-int is used as input @start
	public  void editMember()throws Exception{ 
	
	int id = 0; 
	String name = null; 
	String personnr = null; 
	Scanner listener = new Scanner(System.in); 
	String confirm = null; 
	
	System.out.println(compactList());
	System.out.println("Enter member ID you want to edit: ");
	
	while(listener.hasNextInt()){ 
		id = listener.nextInt();
		if(id < 0|| id >club.getSize()-1){
			System.err.println("The ID you entered is invalid ! " );
		}
		else{ 
			break; 
		}
	}
	
	name=club.getMemberName(id);
	personnr=club.getMemberPersonnr(id);
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
				}
				else{ 
					break; 
				}
			}
		}
		else{
			System.out.println("Enter the new personal number of the member (12): ");
			listener = new Scanner(System.in); 
			while(listener.hasNext()){
				personnr = listener.nextLine(); 
				if(personnr.length()!=12){ 
					System.out.println("The personal number you entered is invalid ! Try again !");
					listener = new Scanner(System.in);
				}
				else{ 
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
				club.editMember(id,name,personnr);
				menu(); 
			}
			else{ 
				System.out.println("DEBUG: Cancelled !");
				menu(); 
			}
	
		}
	
}
	
	
	//working as intended except if a non-int is used as input @start
	public  void deleteMember() throws Exception{
		
		int id =0;
		Scanner listener = new Scanner(System.in); 
		String confirm = null; 
		
		System.out.println(compactList());
		System.out.println("Enter member ID you want to Delete: ");
		
		while(listener.hasNext()){ 
			 id = listener.nextInt();
			 if(id < 0|| id >club.getSize()-1){
				 System.err.println("The ID you entered is invalid ! " );
			 }
			 else{ 
				 break; 
			 }
		}
		
		System.err.println("Are you sure (Y/N) you want to Delete the member ID : " + id + " \n" + "Member name: " +club.getMemberName(id)+"\tPersonal Number: " + club.getMemberPersonnr(id));
		listener = new Scanner(System.in); 
		
		while(listener.hasNext()){ 
			confirm = listener.nextLine(); 
			if(confirm.equals("Y")||confirm.equals("y")){ 
				System.out.println("DEBUG: Confirmed !");
				club.deleteMember(id);
				menu(); 	
			}
			else{ 
				System.out.println("DEBUG: Cancelled !");
				menu(); 
			}
		}
		
}

	

	public  void showList() throws Exception{ 
	
	String choice="";
	Scanner listener = new Scanner(System.in); 
	
	System.out.println("Select a list");
	System.out.println("1. Verbose List"); 
	System.out.println("2. Compact List");
	
	while(listener.hasNext()){ 
		choice = listener.nextLine(); 
		if(choice.equals("1")){ 
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Verbose list of the members in the club: ");
				System.out.println(verboseList());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				break;
		}
		else if(choice.equals("2")){ 
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Compact list of the members in the club: ");
				System.out.println(compactList());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				break;
		}
		else{
				System.err.println("Invalid choice ! Try again !");
		}
	}
	
	System.out.println("Press a key and enter to go back to the Main menu");
	choice=null;
	
	while(listener.hasNext()){ 
		 choice = listener.next();
		 if(choice!=null){
			 System.out.println("\n\n\n\n\n\n\n\n");
			 menu();
		 } 
	}	

}
	
	public  void registerBoat()throws Exception{ 
	
	int id = 0; 
	String type = null; 
	int length = 0; 
	String confirm = null; 
	Scanner listener = new Scanner(System.in); 
	
	System.out.println("Enter boat type: ");
	
	while(listener.hasNext()){ 
		type = listener.nextLine(); 
		if(type.length()<3){ 
			System.err.println("The boat type is too short ! Try again !");
		}
		else{ 
			break; 
		}
	}
	
	System.out.println("Enter boat length: ");
	listener = new Scanner(System.in); 
	
	while(listener.hasNextInt()){ 
		length = listener.nextInt(); 
		if(length<0){ 
			System.err.println("The length of the boat is too short ! Try again !"); 
		}
		else{ 
			break; 
		}
	}
	
	System.out.println(verboseList());
	System.out.println("Enter the ID of the owner: ");
	listener = new Scanner(System.in); 
	
	while(listener.hasNextInt()){ 
		id = listener.nextInt(); 
		if(id<0||id>club.getSize()){ 
			System.err.println("The id of the member is invalid ! Try again !");
		}
		else{ 
			break; 
		}
	}
	
	System.out.println("Are you sure (Y/N) you want to add boat of " + "Type: " +type+ "  Length: " + length + " to member with id :  " + id);
	listener = new Scanner(System.in); 
	
	while(listener.hasNext()){ 
		confirm = listener.nextLine(); 
		if(confirm.equals("Y")||confirm.equals("y")){ 
			club.addBoat(id,type,length);
			System.out.println("Boat added to selected member!");
			menu(); 
			
		}
		else if(confirm.equals("N")||confirm.equals("n")){ 
			System.out.println("Cancelled");
			menu(); 
		}
		else{
			System.err.println("Invalid input, try again!");
		
		}
	}
	
}
	
	
	public  void deleteBoat() throws Exception{ 
		
		int id = 0; 
		int boatid=0;
		String confirm = null; 
		Scanner listener = new Scanner(System.in); 
		System.out.println(compactList());
		System.out.println("Enter the ID of the owner: ");
		listener = new Scanner(System.in); 
		
		while(listener.hasNextInt()){ 
			id = listener.nextInt(); 
			if(id<0|| id>club.getSize()||club.getMemberBoatNumber(id)<=0){ 
				System.err.println("The id of the member is invalid ! Try again !");
			}
			else{ 
				break; 
			}
		}
		
		System.out.println(club.getMemberBoats(id));
		System.out.println("Select boat id to remove:");
		listener = new Scanner(System.in); 
		
		while(listener.hasNextInt()){ 
			boatid = listener.nextInt(); 
			if(boatid<0||boatid>club.getMemberBoatNumber(id)){ 
				System.err.println("The id of the boat is invalid ! Maybe the member has no boats registered, Try again !");
			}
			else{ 
				break; 
			}
		}
		
		System.out.println("Are you sure (Y/N) you want to remove  boat with id:  " + boatid);
		listener = new Scanner(System.in); 
		
		while(listener.hasNext()){ 
			confirm = listener.nextLine(); 
			if(confirm.equals("Y")||confirm.equals("y")){ 
				club.removeBoat(id, boatid);
				System.out.println("Boat deleted!");
				menu(); 
			}
			else if(confirm.equals("N")||confirm.equals("n")){ 
				System.out.println("Cancelled");
				menu(); 
			}
			else{
				System.err.println("Invalid input, try again!");
			}
		}
	
}
	public  void editBoat() throws Exception{ 
		
		int id = 0; 
		int boatid=0;
		String type="";
		int length=0;
		String confirm = null; 
		Scanner listener = new Scanner(System.in); 

		System.out.println(compactList());
		System.out.println("Enter the ID of the owner: ");
		listener = new Scanner(System.in); 
		
		while(listener.hasNextInt()){ 
			id = listener.nextInt(); 
			if(id<0){ 
				System.err.println("The id of the member is invalid ! Try again !");
			}
			else{ 
				break; 
			}
		}
		
		System.out.println(club.getMemberBoats(id));
		System.out.println("Select boat id to edit:");
		listener = new Scanner(System.in); 
		
		while(listener.hasNextInt()){ 
			boatid = listener.nextInt(); 
			if(boatid<0||boatid>club.getSize()){ 
				System.err.println("The id of the boat is invalid ! Try again !");
			}
			else{ 
				break; 
			}
		}
		
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
					}
					else{ 
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
					}
					else{ 
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
				club.editBoat(id, boatid, type, length);
				menu(); 
		
			}
			else{ 
				System.out.println("Cancelled !");
				menu(); 
			}
		
		}
		
}
	
	public void viewMember() throws Exception{
		
		int id=0;
		String s="";
		String choice=null;
		
		Scanner listener = new Scanner(System.in); 
		System.out.println("Enter member ID you want to View: ");
		while(listener.hasNext()){ 
			 id = listener.nextInt();
			 if(id < 0|| id >club.getSize()-1){
				 System.err.println("The ID you entered is invalid ! " );
			 }
			 else{ 
				 break; 
			 }
		}
		
		s+="\n"+"Member id: "+id+"\tName: "+ club.getMemberName(id) +"\t Personalnr:  "+club.getMemberPersonnr(id) +"\t Boats[" +club.getMemberBoats(id);
		System.out.println(s);
		System.out.println("Press a key and enter to go back to the Main menu");
		choice=null;
		while(listener.hasNext()){ 
			 choice = listener.next();
			 if(choice!=null){
				 System.out.println("\n\n\n\n\n\n\n\n");
				 menu();
			 }
		}
		
}
	
	
	
	public String verboseList(){
		
		String s="";
		for(int i=0;i<club.getSize();i++){
			 s+="\n"+"Member id: "+i+"\tName: "+ club.getMemberName(i) +"\tPersonnr: "+club.getMemberPersonnr(i) +"\t Boats[" +club.getMemberBoats(i);
			
		}
		return s;
		
	}
	public String compactList(){
		
		String s="";
		for(int i=0;i<club.getSize();i++){
			 s+="\n"+"Member id: "+i+"\tName: "+ club.getMemberName(i) +"\tBoats: "+ club.getMemberBoatNumber(i);
		}
		return s;
	}

}



