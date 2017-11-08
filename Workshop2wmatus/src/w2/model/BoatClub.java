package w2.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "BoatClub")

public class BoatClub {
	@XmlElement
	private ArrayList<Member> members = new ArrayList<Member>();
	private XML xml;
	
	
	private int size;
	
	
		public BoatClub() throws Exception{
			xml = new XML();
		
		}
		
		
		public void addMember(String name, String pn) throws Exception{
	
			members.add(new Member(name,pn));
			xml.save(this);
		}
		public void editMember(int id,String name,String personnr) throws Exception{
			getMember(id).setName(name);
			getMember(id).setPersonalNumber(personnr);
			xml.save(this);
			
		
			
		}
		public void deleteMember(int id) throws Exception{
			members.remove(id);
			xml.save(this);
			
		
			
		}
		
		
		public void addBoat(int id, String type, int length) throws Exception{
			Boat b = new Boat();
			b.setLength(length);
			b.setType(type);
			b.setId(getMemberTotalBoats(id));
			getMember(id).addBoat(b);
			xml.save(this);
		}
		public void removeBoat(int id, int boatid) throws Exception{
			getMember(id).removeBoat(boatid);
			xml.save(this);
		}
		public void editBoat ( int id, int boatid,String type, int length) throws Exception{
			if(length!=0)
				getMember(id).getBoat(boatid).setLength(length);
			if(type!="")
				getMember(id).getBoat(boatid).setType(type);
			xml.save(this);
		}
		public int getSize(){
			size=members.size();
			return size;
		}
		public String getMemberName(int id){
			return getMember(id).getName();
				
			
		}
		/*public String getMemberBoats(int id,int boatid){
					
			return getMember(id).getBoat(boatid).toString();
		}*/
		public String getMemberBoatType(int id,int boatid){
			return getMember(id).getBoat(boatid).getType();
		}
		public int getMemberBoatLength(int id,int boatid){
			return getMember(id).getBoat(boatid).getLength();
		}
		
		public int getMemberTotalBoats(int id){
			return getMember(id).getNumberOfBoats();
		}
		public String getMemberPersonnr(int id){
			return getMember(id).getPersonalNumber();
				
			
		}
		


		public Member getMember(int id) {
			return members.get(id);
		}


		public void setMembers(ArrayList<Member> members) {
			this.members = members;
		}
		
		
}