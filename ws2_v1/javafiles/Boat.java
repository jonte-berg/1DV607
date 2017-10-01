package w2;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"type", "length"})
@XmlRootElement(name="Boat")
public class Boat {
private int id; 
private String type; 
private int length; 
public Boat(){}
public Boat(int i,String s, int l){ 
	type = s; 
	length = l;
	id = i; 
	
}
@XmlElement
public String getType(){ 
	return type; 
}
@XmlElement
public int getLength(){
	return length; 
	
}
@XmlAttribute
public int getId(){ 
	return id; 
}

public void setType(String s){ 
	type = s; 
}

public void setLength(int l){ 
	length = l; 
}

public void setId(int i){
	id = i; 
}

public String toString(){
	return "ID:  " + id +" Boat Type:  " +type+ " Boat Length:  " + length; 
	
}

}