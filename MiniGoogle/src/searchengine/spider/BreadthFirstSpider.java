/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Modified by Mahender on 12-10-2009
 */
package searchengine.spider;

import java.io.IOException;

import java.net.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import searchengine.dictionary.ObjectIterator;
import searchengine.element.PageElementInterface;
import searchengine.element.PageHref;
import searchengine.element.PageWord;
import searchengine.indexer.Indexer;
import searchengine.parser.PageLexer;
import searchengine.url.URLFixer;
import searchengine.url.URLTextReader;

/** Web-crawling objects.  Instances of this class will crawl a given
 *  web site in breadth-first order.
 */
public class BreadthFirstSpider implements SpiderInterface {

	/** Create a new web spider.
	@param u The URL of the web site to crawl.
	@param i The initial web index object to extend.
	 */

	private Indexer i = null;
	private URL u; 

	public BreadthFirstSpider (URL u, Indexer i) {
		this.u = u;
		this.i = i;

	}

	/** Crawl the web, up to a certain number of web pages.
	@param limit The maximum number of pages to crawl.
	 * @throws MalformedURLException 
	 */
	public Indexer crawl (int limit) throws MalformedURLException{
		Queue<String> queue=new LinkedList<String>();//add url
		Vector<String> visited=new Vector<String>();//checking url
		System.out.println("limit"+limit);
		queue.add(u.toString());
		visited.add(u.toString());
		int size=1,c=0;
		while(!queue.isEmpty())
		{
			String url=queue.poll();
			System.out.println(url);
			if(size==limit)
			{
				System.out.println(url+"...."+"$"+limit);
				break;
			}
			if(url.length()<3)
			{
				size++;
			}
			if(url.length()>3)
			{
				visited.add(u.toString());
				
				URL ul=u;
				URLFixer urlfixer=new URLFixer();
				URL u=urlfixer.fix(ul, url);
			
			URLTextReader in=new URLTextReader(u);
			PageLexer<PageElementInterface> elts;
			try
			{
				elts=new PageLexer<PageElementInterface>(in, u);
				Vector<String> words=new Vector<String>();
				
				int count=0;
				while(elts.hasNext())
				{
					count++;
					PageElementInterface elt=(PageElementInterface)elts.next();
					if(elt instanceof PageWord)
					{
						String a=elt.toString();
						if(a.length()>2)
							if(a.matches("[a-zA-Z]+"))
						words.add(elt.toString());
					}
					else if(elt instanceof PageHref)
					{
						if(!visited.contains(elt.toString()))
						queue.add(elt.toString());
					}
				}
				
				i.addPage(u, new ObjectIterator(words),size);
			}catch(IOException e)
			{
			//e.printStackTrace();
		    }
			}
			/*size++;
			String url=queue.poll();
			URL ul=u;
			URLFixer urlfixer=new URLFixer();
			URL u=urlfixer.fix(ul, url);*/
			String s="$"+size;
			System.out.println("level: "+s);
			if(c!=size)
			queue.add(s);
			c=size;
		}
	
		////////////////////////////////////////////////////////////////////
	    //  Write your Code here as part of Breadth First Spider assignment
	    //  
	    ///////////////////////////////////////////////////////////////////
		
		return i;
	}

	/** Crawl the web, up to the default number of web pages.
	 * @throws MalformedURLException 
	 * @throws IOException 
	 */
	public Indexer  crawl() throws IOException{
		// This redirection may effect performance, but its OK !!
		System.out.println("Crawling: "+u.toString());
		return  crawl(crawlLimitDefault);
	}

	/** The maximum number of pages to crawl. */
	public int crawlLimitDefault = 3;

}
