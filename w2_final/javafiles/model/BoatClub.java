package w2.model;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "BoatClub")
public class BoatClub {
	
	public ArrayList<Member> members = new ArrayList<Member>();
	public int size;
	
	
		public BoatClub(){
			
		
		}
		
		
		public void addMember(String name, String pn) throws Exception{
	
			members.add(new Member(name,pn));
			XML.save(this);
		}
		public void editMember(int id,String name,String personnr) throws Exception{
			getAllMembers().get(id).setName(name);
			getAllMembers().get(id).setPersonalNumber(personnr);
			XML.save(this);
			
		
			
		}
		public void deleteMember(int id) throws Exception{
			members.remove(id);
			XML.save(this);
			
		
			
		}
		
		public void addBoat(int id, String type, int length) throws Exception{
			Boat b = new Boat();
			b.setLength(length);
			b.setType(type);
			b.setId(getMemberBoatNumber(id));
			members.get(id).addBoat(b);
			XML.save(this);
		}
		public void removeBoat(int id, int boatid) throws Exception{
			members.get(id).removeBoat(boatid);
			XML.save(this);
		}
		public void editBoat ( int id, int boatid,String type, int length) throws Exception{
			if(length!=0)
			members.get(id).list.get(boatid).setLength(length);
			if(type!="")
				members.get(id).list.get(boatid).setType(type);
			XML.save(this);
		}
		public int getSize(){
			size=members.size();
			return size;
		}
		public String getMemberName(int id){
			return members.get(id).getName();
				
			
		}
		public String getMemberBoats(int id){
			ArrayList<Boat> memberBoats = members.get(id).getBoatList();
			String s="";
			 if(getMemberBoatNumber(id)>0){
				for(int n=0;n<getMemberBoatNumber(id);n++){
					s+="ID:"+memberBoats.get(n).getId();
					s+="  TYPE:"+memberBoats.get(n).getType();
					s+="  LENGTH:"+memberBoats.get(n).getLength();
					s+=", ";
					}
				}
				s+="]";
					
			return s;
		}
		public int getMemberBoatNumber(int id){
			return members.get(id).getBoatList().size();
		}
		public String getMemberPersonnr(int id){
			return members.get(id).getPersonalNumber();
				
			
		}
		public ArrayList<Member> getAllMembers() throws Exception{
			XML.load();
			return members;
		}
		
}