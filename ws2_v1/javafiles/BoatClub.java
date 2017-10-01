package w2;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name = "BoatClub")
public class BoatClub {
	private String name;
	public ArrayList<Member> members = new ArrayList<Member>();

	
	
		public BoatClub(){
			
		
		}
		public  BoatClub(String s){
			
		name=s;	
		
		}
		
		public void addMember(Member s){
			members.add(s);
		}
		
		public Member getMember(String s){
			int index=0; //temp solution
			for( int i=0;i<members.size();i++){
				if(members.get(i).getName().contains(s)){
					index=i;
				}
				
			}
				return members.get(index);
		}
		public ArrayList<Member> getAllMembers(){
			return members;
		}
		public String compact(){
			String s="";
			for(int i=0;i<members.size();i++){
				 s+="\n"+members.get(i).compactToString();
			}
			return s;
		}
		public String verbose(){
			String s="";
			for(int i=0;i<members.size();i++){
				 s+="\n"+members.get(i).verboseToString();
			}
			return s;
		}
}