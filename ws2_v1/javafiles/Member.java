package w2;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"name", "personalNumber","list"})
@XmlRootElement(name = "Member")

public class Member {
	
	
	private String name;
	private String personalNumber;
	private int id;
	@XmlElement(name= "Boat")
	public ArrayList<Boat> list = new ArrayList<Boat>();
	
	
	public Member(){
		
	}
	public Member(String n, String pn){
		name=n;
		personalNumber=pn;
		
	}
	
	public String getName(){
		return name;
	}
	public String getPersonalNumber(){
		return personalNumber;
	}
	@XmlAttribute
	public int getId(){
		return id;
	}
	public void setId(int s){
		id=s;
	}
	public void setName(String s){
		name=s;
	}
	public void setPersonalNumber(String s){
		personalNumber=s;
	}
	
	public String compactToString(){
		
		return "id: "+id+"\tname: "+name+"\tNumber of boats: "+ list.size();
	}
	
	public String verboseToString(){
		
		return "id: "+id+"\tname: "+name + "\tPersonal Number: "+ personalNumber + "\tBoats : "+ list.toString();
	}
	
	
	public void addBoat(Boat s){
		list.add(s);
	}
	public void removeBoat(int s){
		list.remove(s);
	}
}
