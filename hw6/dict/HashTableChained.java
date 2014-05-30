/* HashTableChained.java */

package dict;
import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/

  protected int numOfKey;
  protected List[] table;



  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    int tmpSize = (int)(sizeEstimate/0.75);
    int i;
    for (i = tmpSize; i>0; i--){
        if (isPrime(i))
          break;
    }
    table = new DList[i];

     for (int j=0; j<i;j++){
      table[j] = new DList();
    } 

    // Your solution here.
  }
  
 private static boolean isPrime(int n) {
    for (int divisor = 2; divisor < n; divisor++) {
      if (n % divisor == 0) { 
        return false;
      }
    }
      return true;
 }
  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    numOfKey = 0;
    table = new DList[97];
     for (int j=0; j<97;j++){
      table[j] = new DList();
    } 

    // Your solution here.
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  public int compFunction(int code) {
    // Replace the following line with your solution.
    return ((code*127+9)%16908799)%(table.length);
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return numOfKey;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return numOfKey==0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    int dest = Math.abs(compFunction(key.hashCode()));
    Entry entry = new Entry();
    entry.key = key;
    entry.value =value;
   
    //System.out.println("In "+dest+"the entry is "+key.hashCode()+" "+ value );
    table[dest].insertFront(entry);
    numOfKey++;
    return entry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    int dest = Math.abs(compFunction(key.hashCode()));
    try{
      ListNode curr = table[dest].front();
      while(curr.isValidNode()){
        if (((Entry)curr.item()).key().equals(key))
            return (Entry)curr.item();
        curr = curr.next();
      }
    }catch(InvalidNodeException e){
      return null;
    }
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
    int dest = Math.abs(compFunction(key.hashCode()));
    try{
      ListNode tmp = table[dest].front();
      while(tmp.isValidNode()){
        if (((Entry)tmp.item()).key().equals(key)){
          Entry r = (Entry) tmp.item();
          tmp.remove();
          numOfKey--;
          return r;
        }
      tmp = tmp.next();
      }
    }catch(InvalidNodeException e){
      return null;
    }
    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
    numOfKey =0;
    table = new DList[table.length];
     for(int i = 0; i< table.length; i++){
        table[i] = new DList();
    }

 
  }
   public int countCollision(){
    int count = 0;
    for(int i=0; i<table.length; i++){
        if(table[i].isEmpty()) continue;
        count = count + table[i].length() - 1;
        System.out.println("this is index "+i+"num is "+table[i].length());
    }
    return count;
  }

}
