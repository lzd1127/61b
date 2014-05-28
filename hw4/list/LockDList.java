package list;

public class LockDList extends DList{


	protected LockDListNode newNode(Object item, DListNode prev, DListNode next) {
    	return new LockDListNode(item, prev, next);
  }

    
	public void lockNode(DListNode node){
		((LockDListNode)node).locked = true;
	}
	

	public void remove(DListNode node) {
			if (((LockDListNode)node).locked==false){
				super.remove(node);
			}else{
				return;
			}
	}

	public static void main (String[] args){
		System.out.println("Test starts");
		LockDList lock = new LockDList();
		System.out.println("\nTest empty true:"+lock.isEmpty());
        
        lock.insertFront(3);
        lock.insertBack(5);
        lock.insertFront(2);
        lock.insertBack(7);
        System.out.println("\nTest list:[2 3 5 7]"+lock);

        lock.lockNode(lock.front());
        lock.lockNode(lock.back());
        lock.remove(lock.front());
        lock.remove(lock.back());
        System.out.println("\n Test locked opertation list:[2 3 5 7]"+lock);

        lock.remove(lock.next(lock.front()));
        lock.remove(lock.prev(lock.back()));
        System.out.println("\n Test locked opertation list: [2 7]"+lock);
        


	}	
}