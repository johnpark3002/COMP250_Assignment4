import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

public class Test {
	
	ERPriorityQueue testPriorityQueue = new ERPriorityQueue();
	
	 public static ArrayList<ERPriorityQueue.Patient> testPatients = new ArrayList<>();
	    public static HashMap<String,Integer> testNameToIndex = new HashMap<String,Integer>();
	
	public static void main(String[] args) {
	
		
//		 ERPriorityQueue queue = new ERPriorityQueue();
//	        ERPriorityQueue.Patient Rio = new ERPriorityQueue.Patient("Rio", 5);
//	        ERPriorityQueue.Patient Julia = new ERPriorityQueue.Patient("Julia", 10);
//	        ArrayList<ERPriorityQueue.Patient> truePatients = makeDeepCopy(queue.patients);
//	        HashMap<String,Integer> trueNameToIndex = new HashMap<>(queue.nameToIndex);
//	        truePatients.add(Rio);
//	        truePatients.add(Julia);
//	        trueNameToIndex.put(Rio.getName(),1);
//	        trueNameToIndex.put(Julia.getName(),2);
//	        
//	        queue.add(Julia.getName(),Julia.getPriority());
//	        queue.add(Rio.getName(),Rio.getPriority());
//	        
//	        System.out.println(trueNameToIndex.toString());
//	        System.out.println(queue.nameToIndex.toString());
//	        
//	        System.out.println(truePatients.toString());
//	        System.out.println(queue.patients.toString());
		
		
		ERPriorityQueue queue = new ERPriorityQueue();
        ERPriorityQueue.Patient Rio = new ERPriorityQueue.Patient("Rio", 1);
        ERPriorityQueue.Patient Julia = new ERPriorityQueue.Patient("Julia", 2);
        ERPriorityQueue.Patient Michael = new ERPriorityQueue.Patient("Michael", 13);
        queue.patients.add(Rio);
        queue.patients.add(Julia);
        queue.patients.add(Michael);
        queue.nameToIndex.put(Rio.getName(), 1);
        queue.nameToIndex.put(Julia.getName(), 2);
        queue.nameToIndex.put(Michael.getName(), 3);

        ArrayList<ERPriorityQueue.Patient> truePatients = makeDeepCopy(queue.patients);
        HashMap<String, Integer> trueNameToIndex = new HashMap<>(queue.nameToIndex);

        // Removing Rio by removeMin()
        swapList(truePatients, 1, 3);
        swapHashMapValues(trueNameToIndex, "Rio", "Michael");
        swapList(truePatients, 1, 2);
        swapHashMapValues(trueNameToIndex, "Michael", "Julia");
        truePatients.remove(3);
        trueNameToIndex.remove("Rio");
        String minPatientName = queue.removeMin();

//        minPatientName = queue.removeMin();
        
        System.out.println(minPatientName);
        
        System.out.println(trueNameToIndex.toString());
        System.out.println(queue.nameToIndex.toString());
        
        System.out.println(truePatients.toString());
        System.out.println(queue.patients.toString());
		
		
//		ArrayList<String> list = new ArrayList();
//		list.add("1");
//		list.add("2");
//		list.add("3");
//		list.add("4");
//		list.add("5");
//		list.add("6");
//		
//		System.out.println(list.size());
//		
//		list.remove(list.size() - 1);
//		
//		System.out.println(list.get(list.size() - 1).toString());
//		System.out.println(list.size());
		
//		ERPriorityQueue queue = new ERPriorityQueue();
//        queue.patients.add(new ERPriorityQueue.Patient("Hannah",5));
//        queue.patients.add(new ERPriorityQueue.Patient("Ebony",10));
//        queue.patients.add(new ERPriorityQueue.Patient("Ahmad",15));
//        queue.patients.add(new ERPriorityQueue.Patient("Zil",30));
//        queue.patients.add(new ERPriorityQueue.Patient("Ricardo",60));
//        queue.patients.add(new ERPriorityQueue.Patient("Yinou",50));
//        queue.patients.add(new ERPriorityQueue.Patient("Gilbert",100));
//        
//        queue.nameToIndex.put(queue.patients.get(1).getName(),1);
//        queue.nameToIndex.put(queue.patients.get(2).getName(),2);
//        queue.nameToIndex.put(queue.patients.get(3).getName(),3);
//        queue.nameToIndex.put(queue.patients.get(4).getName(),4);
//        queue.nameToIndex.put(queue.patients.get(5).getName(),5);
//        queue.nameToIndex.put(queue.patients.get(6).getName(),6);
//        queue.nameToIndex.put(queue.patients.get(7).getName(),7);
//
//        System.out.println(queue.patients.toString());
//        
//        queue.patients.get(1).setPriority(15);
//
//        System.out.println(queue.patients.toString());
//        
//        ArrayList<ERPriorityQueue.Patient> truePatients = makeDeepCopy(queue.patients);
//        HashMap<String,Integer> trueNameToIndex = new HashMap<>(queue.nameToIndex);
//
//        swapList(truePatients,1,2);
//        swapHashMapValues(trueNameToIndex, "Hannah", "Ebony");
//
//        
//        queue.downHeap(1);
//        
//        System.out.println(truePatients.toString());
//        System.out.println(queue.patients.toString());
//        System.out.println(trueNameToIndex.toString());
//        System.out.println(queue.nameToIndex.toString());
	}
	
	 public static ArrayList<ERPriorityQueue.Patient> makeDeepCopy(ArrayList<ERPriorityQueue.Patient> list) {
	        ArrayList<ERPriorityQueue.Patient> copy = new ArrayList<ERPriorityQueue.Patient>();
	        for (ERPriorityQueue.Patient patient : list) {
	            copy.add(new ERPriorityQueue.Patient(patient.getName(), patient.getPriority()));
	        }
	        return copy;
	    }
	 
	 public static <T> void  swapList(ArrayList<T> list, int i, int j){
	        T temp = list.get(i);
	        list.set(i,list.get(j));
	        list.set(j,temp);
	    }

	    public static <K,V> void swapHashMapValues(HashMap<K,V> hashMap, K key1, K key2){
	        V temp = hashMap.get(key1);
	        hashMap.put(key1, hashMap.get(key2));
	        hashMap.put(key2, temp);
	    }

}
