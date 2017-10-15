package w2.model;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
//class for handling XML save/load and ID assignment of members
public class XML {
	
	 public static void save(BoatClub s)throws Exception{
	       try{
	    	 s= assignID(s);
	         JAXBContext context = JAXBContext.newInstance(BoatClub.class);
	         Marshaller m = context.createMarshaller();
	         m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	         m.marshal(s, new File("Club.xml"));

	       }
	       catch(JAXBException e){
	         e.printStackTrace();
	       }
	}
	 public static  BoatClub load() throws Exception {

	     try {

	         JAXBContext context = JAXBContext.newInstance(BoatClub.class);
	         Unmarshaller un = context.createUnmarshaller();
	         BoatClub temp = new BoatClub();
	         temp =  (BoatClub) un.unmarshal(new File("Club.xml"));
	          Marshaller m = context.createMarshaller();
	          m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	          //test if things work ( prints the content of the XML file in the console)
	          //  m.marshal(temp, System.out);

	         return temp;
	     } catch (JAXBException e) {
	         e.printStackTrace();
	     }

	     return null;
	 }
	 public static BoatClub assignID(BoatClub c) throws Exception{
		  int s;
		  for(int i=0;i<c.getAllMembers().size();i++){
		  s =i;
		  c.getAllMembers().get(i).setId(s);
		}
		return c;
		}

}
