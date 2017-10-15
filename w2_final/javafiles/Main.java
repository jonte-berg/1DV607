package w2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import w2.model.BoatClub;
import w2.model.XML;
import w2.view.*;

public class Main {

	public static void main(String[] args) throws Exception {
		//check if XML exists, if not one gets created
		Path path = Paths.get("Club.xml"); 
	        if(Files.notExists(path)){ 
	         	BoatClub club = new BoatClub();
				XML.save(club);		
	        }
	    	
			BoatClub club = XML.load();
	        MainMenu ui = new MainMenu(club);
	        ui.menu();
	}

}
