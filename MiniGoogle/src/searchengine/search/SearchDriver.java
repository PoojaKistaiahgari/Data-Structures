/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Modified by Mahender K on 12-10-2009
 */ 


package searchengine.search;


import java.util.*;

import java.io.*;
import searchengine.dictionary.ObjectIterator;
import searchengine.indexer.Indexer;


/**
 * The user interface for the index structure.
 *
 * This class provides a main program that allows users to search a web
 * site for keywords.  It essentially uses the index structure generated
 * by WebIndex or ListWebIndex, depending on parameters, to do this.
 *
 * To run this, type the following:
 *
 *    % java SearchDriver indexfile list|custom keyword1 [keyword2] [keyword3] ...
 *
 * where indexfile is a file containing a saved index and list or custom indicates index structure.
 *
 */
public class SearchDriver
{
    public static void main(String [] args)
    {
        Vector<String> v=new Vector<String>();
        ArrayList<String> al=new ArrayList<String>();
        TreeMap<Double, String> tmap=new TreeMap<Double, String>();
	if(args.length<3)
	    System.out.println("Usage: java SearchDriver indexfile list|hash keyword1 [keyword2] [keyword3] [...]");
	else
	    {
		Indexer w = null;
		
		// Take care to use the right usage of the Index structure
		// hash - Dictionary Structure based on a Hashtable or HashMap from the Java collections 
		// list - Dictionary Structure based on Linked List 
		// myhash - Dictionary Structure based on a Hashtable implemented by the students
		// bst - Dictionary Structure based on a Binary Search Tree implemented by the students
		// avl - Dictionary Structure based on AVL Tree implemented by the students
		if(args[1].equalsIgnoreCase("list") || args[1].equals("hash") || args[1].equals("myhash") || args[1].equals("bst") 
				|| args[1].equals("avl")){
		    w = new Indexer(args[1]);
		}
		else
		{
			System.out.println("Invalid Indexer mode \n");
		}
		
		try{
		    FileInputStream indexSource=new FileInputStream(args[0]);
		    w.restore(indexSource);
		}
		catch(IOException e){
		    System.out.println(e.toString());
		}
		
		for(int i=2;i<args.length;i++)
		    v.addElement(args[i]);
		
		ObjectIterator<?> i= w.retrievePages(new ObjectIterator<String>(v));
		
		if(i!=null) {
			while(i.hasNext())
			{
				////////////////////////////////////////////////////////////////////
			    //  Write your Code here as part of Sorting based on Rank Assignment
			    //  
			    ///////////////////////////////////////////////////////////////////
				//System.out.println("Search Driver in");
				//System.out.println(i.next());
				Double Rank=0.0;
				String s=(String) i.next();
				int frequency=0,depth=0;
				String[] s1=s.split("-->");
				String st=s1[1];
			//	System.out.println("string-->"+st);
				depth=Integer.parseInt(st.substring(0, 1));
				frequency=Integer.parseInt(st.substring(3));
				Rank=(double) (frequency*1/depth)*100;
				//System.out.println("Rank-->"+Rank+"----------->"+s1[0]);
				 // If this is first occurrence of element    
	            if (tmap.get(Rank) == null) 
	               tmap.put(Rank,s1[0]); 
	  
	            // If elements already exists in hash map 
	            else
	              tmap.put(Rank,s1[0]); 

				
			}
			for (Map.Entry m:tmap.entrySet()) {
		      //    System.out.println("Frequency of " + m.getKey() +"is " + m.getValue()); 
		          al.add((String) m.getValue());
			}
			
		//	System.out.println("Search complete.");
		//	System.out.println("---------------\n");
			System.out.println("Reverse order of arraylist");
			for(int i1=al.size()-1;i1>=0;i1--)
				System.out.println(al.get(i1));
			System.out.println("Search complete.");
			System.out.println("---------------\n");
			}
		else
		{
			System.out.println("Search complete.  0  hits found.");
		}
	    }
    }
}


