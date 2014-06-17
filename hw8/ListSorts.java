/* ListSorts.java */

import list.*;

public class ListSorts {

  private final static int SORTSIZE = 1000000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    // Replace the following line with your solution.
    LinkedQueue result = new LinkedQueue();
    try{
      while(!q.isEmpty()){
        LinkedQueue tmp = new LinkedQueue();
        tmp.enqueue(q.dequeue());
        result.enqueue(tmp); 
      }
    }catch(QueueEmptyException e){
      System.out.println("Error");
    }
    return result;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    // Replace the following line with your solution.
    LinkedQueue result = new LinkedQueue();
    int flag1 = 0;
    int flag2 = 0;
    Object t2 = null;
    Object t1 = null;
    try{
      while((!q1.isEmpty()) && (!q2.isEmpty())){
        t1 = q1.front();
        t2 = q2.front();
        if (((Comparable)t1).compareTo((Comparable)t2) <= 0){
          result.enqueue(q1.dequeue());
        }else{
          result.enqueue(q2.dequeue());
        }
      }
      while(!q1.isEmpty()){
        result.append(q1);
      }
      while(!q2.isEmpty()){
        result.append(q2);
      }
    }catch(QueueEmptyException e){
      System.out.println("Error");
    }
    return result;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    // Your solution here.
    Object tmp = null;
    try{
      while(!qIn.isEmpty()){
       tmp = qIn.front();
       if (((Comparable)tmp).compareTo(pivot) < 0){
        qSmall.enqueue(qIn.dequeue());
       }else if (((Comparable)tmp).compareTo(pivot) == 0){
         qEquals.enqueue(qIn.dequeue());
       }else{
        qLarge.enqueue(qIn.dequeue());
       }
      }
    }catch (QueueEmptyException e){
      System.out.println(e);
    }
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
    // Your solution here.
    if (q.isEmpty()){
      return;
    }
    try{
      LinkedQueue result = (LinkedQueue) makeQueueOfQueues(q);
      while(result.size() > 1){
        LinkedQueue q1 = (LinkedQueue)result.dequeue();
        LinkedQueue q2 = (LinkedQueue)result.dequeue();
        result.enqueue(mergeSortedQueues(q1, q2));
      }
      q.append((LinkedQueue) result.dequeue());
    }catch(QueueEmptyException e){
      System.out.println("Error : Queue is empty "+ e);
      e.printStackTrace();
    }
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
    // Your solution here.
      if (q.isEmpty()){
        return;
    }
      int index = (int) (q.size() * Math.random());
      Comparable pivot = (Comparable)q.nth(index);
      LinkedQueue qSmall = new LinkedQueue();
      LinkedQueue qEquals = new LinkedQueue();
      LinkedQueue qLarge = new LinkedQueue();
      partition(q,pivot, qSmall, qEquals, qLarge);
      quickSort(qSmall);
      quickSort(qLarge);
      q.append(qSmall); 
      q.append(qEquals);
      q.append(qLarge);

  }


  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {

    LinkedQueue q = makeRandom(10);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString());

     LinkedQueue q1 = makeRandom(0);
    System.out.println(q1.toString());
    mergeSort(q1);
    System.out.println(q1.toString());

     LinkedQueue q2 = makeRandom(1);
    System.out.println(q2.toString());
    mergeSort(q2);
    System.out.println(q2.toString());


    q = makeRandom(10);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    q = makeRandom(0);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    q = makeRandom(1);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

  
    Timer stopWatch = new Timer();
    q = makeRandom(100000);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(100000);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

  }

}
