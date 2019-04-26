package searchengine.dictionary;

import java.util.ArrayList;

class Value1
{
	String value;
	int Count;
	Value1(String value)
	{
		this.value=value;
		Count=1;
	}
	public String toString()
	{
		return value+"-->"+Count;
	}
}
class MHash
{
	String keys;
	ArrayList<Value1> al;
	public MHash(String keys,String value2)
	{
		this.keys=keys;
		al=new ArrayList<Value1>();
		Value1 value1=new Value1(value2);
		al.add(value1);
	}
}
public class MyHashDictionary implements DictionaryInterface {
	int count=0;
	MHash[] mh=new MHash[200];
	@Override
	public String[] getKeys() {
		// TODO Auto-generated method stub
		String[] s=new String[count];
		int j=0;
		for(int i=0;i<200;i++)
		{
			if(mh[i]!=null)
			{
				//System.out.println(mh[i].keys);
				s[j]=(String) mh[i].keys;
				j++;
			}
		}
		return s;
	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		int index=hashcode(key);
	    return mh[index].al;
	}

	@Override
	public void insert(String key, Object value) {
		// TODO Auto-generated method stub
		MHash mhash=new MHash(key,(String)value);
		int index=hashcode(key);
		if(mh[index]==null)
		{
			mh[index]=mhash;
			count++;
		}
		//mh[index]=mhash;
		//count++;
    else
		
 		{
			int f=0;
			for(int i=0;i<mh[index].al.size();i++)
			{
			if(mh[index].al.get(i).value.equals((String)value))
			{
				mh[index].al.get(i).Count++;
				f=1;
                break;
			}
			}
			if(f==0)
				mh[index].al.add(new Value1((String)value))	;			
			}

	}

	@Override
	public void remove(String key) {
		// TODO Auto-generated method stub
		int index=hashcode(key);
		for(int i=0;i<mh.length;i++)
		{
			if(i==index)
			{
				mh[i]=null;
				count--;
			}
		}
	}
	public int hashcode(String key)
	{
		int sum=0;
		String str=(String) key;
		for(int i=0;i<str.length();i++)
		{
			sum=sum+str.charAt(i)*(i+1);
		}
		return sum%300;
	}
}
