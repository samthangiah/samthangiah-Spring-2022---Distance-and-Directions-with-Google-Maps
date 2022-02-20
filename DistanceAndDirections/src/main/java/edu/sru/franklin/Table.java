package edu.sru.franklin;

import java.util.Hashtable;


public class Table{

private Hashtable<Integer, String> table;

public Table(int length)
{
		table = new Hashtable<Integer, String>(length);
}

public String GetString(int key)
{
		return table.get(key);
}


 public void AddString(int key, String value)
 {
 		table.put(key,value);
 }


public Hashtable<Integer, String> GetTable()
{
		return table;
}
}

