package searchengine.dictionary;

import java.util.ArrayList;

class Node1
{
	String key;
	Object value;
	Node1 left,right;
	public Node1(String key,Object value)
	{
		this.key=key;
		this.value=value;
		left=right=null;
	}
}
class BST
{	
	int count;
	String st[]=new String[count];
	Node1 root;
BST()
{
	root=null;
	count=0;
}


int n=0;
public void put(String Key,Object value)
{
	count++;
	root=put(root,Key,value);
}
private Node1 put (Node1 x, String key, Object value)
{
	
	if(x==null)
	{
		
		return new Node1(key,value);
	}
	if(key.compareTo((String) x.key)<0)
	{
		//count++;
		x.left=put(x.left,key,value);
	}
	else if(key.compareTo((String) x.key)>0)
	{
		//count++;
		x.right=put(x.right,key,value);
	}
	else
	
		x.value=value;
	
	return x;
}
public Object getvalue(String key) {
	return (Object)  getvalue(root,key);
}
public Object getvalue(Node1 x, String key) { 
if (x == null) {
	
	return null;}
else
{
int cmp =key.compareTo((String) x.key) ;  
if (cmp < 0) 
return getvalue(x.left, key);     
else if (cmp > 0) 
return getvalue(x.right, key);   
else return (Object) x.value; 
}
}

void deleteKey(String key) 
{ 
    root = deleteRec(root, key); 
} 

/* A recursive function to insert a new key in BST */
Node1 deleteRec(Node1 root, String key) 
{ 
    /* Base Case: If the tree is empty */
    if (root == null)  return root; 

    /* Otherwise, recur down the tree */
    if (key.compareTo((String) root.key)<0) 
        root.left = deleteRec(root.left, key); 
    else if (key.compareTo((String) root.key) >0) 
        root.right = deleteRec(root.right, key); 

    // if key is same as root's key, then This is the node 
    // to be deleted 
    else
    { 
        // node with only one child or no child 
        if (root.left == null) 
            return root.right; 
        else if (root.right == null) 
            return root.left; 

        // node with two children: Get the inorder successor (smallest 
        // in the right subtree) 
        root.key = minValue(root.right); 

        // Delete the inorder successor 
        root.right = deleteRec(root.right, (String) root.key); 
    } 

    return root; 
} 

String minValue(Node1 root) 
{ 
    Node1 minv=null;
    while (root.left != null) 
    { 
        minv = root.left; 
        root = root.left; 
    } 
    return (String)minv.key; 
} 
public String[] inorderbe()
{
ArrayList<String> al=new ArrayList<String>();
inorderbe(root,al);
int size=al.size();
String [] s=new String[size];
for(int i=0;i<size;i++)
{
	s[i]=(String)al.get(i);
}

return s;
	
}
public void inorderbe(Node1 n,ArrayList al)
{
	if(n!=null) {
	inorderbe(n.left,al);
	al.add(n.key);
	inorderbe(n.right,al);

	}
}
}
public class BSTDictionary implements DictionaryInterface {
	BST b=new BST();
	@Override
	public String[] getKeys() {
		// TODO Auto-generated method stub
		return (String[]) b.inorderbe();
	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return (Object) b.getvalue(key);
	}

	@Override
	public void insert(String key, Object value) {
		// TODO Auto-generated method stub
		b.put(key, value);
	}

	@Override
	public void remove(String key) {
		// TODO Auto-generated method stub
		b.deleteKey(key) ;
	}

}
