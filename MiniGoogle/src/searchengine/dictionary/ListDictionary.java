package searchengine.dictionary;

import java.util.ArrayList;

class Value
{
	
	@Override
	public String toString() {
		return  val + "=" + count ;
	}

	String val;
	int count=0;

	public Value(String value,int count)
	{
		this.val=value;
		this.count=count;
	}
	
}
class Node
{
	String key;
	ArrayList<Value> va=new ArrayList<Value>();
	Node next;
	public Node(String key, String value) {
		super();
		this.key = key;
		Value v=new Value(value,1);	
		va.add(v);
		}
}

class Linkl {
Node head;
String keys[];
public void pushend(String key,String value)
	{
		Node n=new Node(key,value);
	if(get(key)!=null)
	{
		push(key,value);
	}
	else
	{
		if(head==null)
		{
			head=n;
			

		}
		else
		{	
		Node temp =head;
		while(temp.next!=null)
		{
			
			temp=temp.next;
		}
		
		temp.next=n;
		
		}
	}
	}
public void  push(String key1,String value)
{
	
    Node temp=head;
   
    if(head.key.equals(key1))
    {
   	 int c1=0;
   	 for(int i = 0;i<head.va.size();i++)
   	 {
   		 String svale=head.va.get(i).val;
   		 if( value.equals(svale))
  					{
  							c1=1;
  							head.va.get(0).count++;
  					}
   	 }
   	 if(c1==0)
   	 {
   		 head.va.add(new Value(value,1));
   	 }
    }
    else
    {
		while(temp!=null )
		{int c11=0;
			if(temp.key.compareTo(key1)==0)
			{
				for(int i = 0;i<temp.va.size();i++)
				{
					String svale=temp.va.get(i).val;
		    		 if( value.equals(svale))
					{
						temp.va.get(i).count++;
						c11=1;
					}
					
				}
				if(c11==0)
				{
					temp.va.add(new Value(value,1));
					
				}
				break;
			}
			temp=temp.next;
		}
    }
		
}
public void  pop(String key1)
{
	
     Node temp=head;
     if(head.key.equals(key1))
     {
   	  head=temp.next;
     }
     else
     {
		while(temp.next!=null )
		{
			if(temp.next.key.compareTo(key1)==0)
			{
				temp.next=temp.next.next;
				break;
			}
			temp=temp.next;
		}
     }
		
}
public String[] getkeys()
	{	int i=0;
	keys=new String[size()];
	Node temp=head;
	
	while(temp!=null )
	{
		keys[i]=(String) temp.key;
		i++;
		temp=temp.next;
	}
  return keys;
		
	}
	
public ArrayList<Value>  get(String key1)
	{
		Node temp1=null;
		
		if(key1==null)
			
		{
			
			return null;
		}
		
		else
		{
			Node temp=head;
		
		while(temp!=null )
		{
			if(temp.key.compareTo(key1)==0)
				return temp.va;
			
			temp=temp.next;
		}
						return null;
		}	
	}
public int size()
{Node temp1=head;
int count=0;
while(temp1!=null)
{
	temp1=temp1.next;
	count++;
	
}
return count;  
	
}

}

public class ListDictionary implements DictionaryInterface {
	Linkl l=new Linkl();
	@Override
	public String[] getKeys() {
		// TODO Auto-generated method stub
		return l.getkeys();
	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return l.get(key);
	}

	@Override
	public void insert(String key, Object value) {
		// TODO Auto-generated method stub
		l.pushend(key, (String) value);
	}
	
	@Override
	public void remove(String key) {
		// TODO Auto-generated method stub
		l.pop(key);
	}

}
