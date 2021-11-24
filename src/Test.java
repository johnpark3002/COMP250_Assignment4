import java.util.ArrayList;
import java.util.HashMap;

public class Test {
	
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
		
		
//		ERPriorityQueue emptyQueue = new ERPriorityQueue();
//        ArrayList<ERPriorityQueue.Patient> truePatients = makeDeepCopy(emptyQueue.patients);
//        HashMap<String,Integer> trueNameToIndex = new HashMap<>(emptyQueue.nameToIndex);
//        emptyQueue.removeMin();
//        
//        truePatients.equals(emptyQueue.patients);
//        trueNameToIndex.equals(emptyQueue.nameToIndex);
//        
//        System.out.println(truePatients.toString());
//        System.out.println(trueNameToIndex.toString());
//        
//        System.out.println("--------------------------------");
//		
//        System.out.println(emptyQueue.patients.toString());
//        System.out.println(emptyQueue.nameToIndex.toString());
		
		
		ArrayList<String> list = new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		
		System.out.println(list.size());
		
		list.remove(list.size() - 1);
		
		System.out.println(list.get(list.size() - 1).toString());
		System.out.println(list.size());
		
	}
	
	 public static ArrayList<ERPriorityQueue.Patient> makeDeepCopy(ArrayList<ERPriorityQueue.Patient> list) {
	        ArrayList<ERPriorityQueue.Patient> copy = new ArrayList<ERPriorityQueue.Patient>();
	        for (ERPriorityQueue.Patient patient : list) {
	            copy.add(new ERPriorityQueue.Patient(patient.getName(), patient.getPriority()));
	        }
	        return copy;
	    }

}
