import java.util.ArrayList;
import java.util.HashMap;

public class ERPriorityQueue{

	public ArrayList<Patient>  patients;
	public HashMap<String,Integer>  nameToIndex;

	public ERPriorityQueue(){

		//  use a dummy node so that indexing starts at 1, not 0

		patients = new ArrayList<Patient>();
		patients.add( new Patient("dummy", 0.0) );

		nameToIndex  = new HashMap<String,Integer>();
	}

	private int parent(int i){
		return i/2;
	}

	private int leftChild(int i){
	    return 2*i;
	}

	private int rightChild(int i){
	    return 2*i+1;
	}

    /*
    TODO: OPTIONAL
    TODO: Additional helper methods such as isLeaf(int i), isEmpty(), swap(int i, int j) could be useful for this assignment
     */
	private boolean isLeaf(int i) {   // It is a leaf if both left and right children are null
		int leftChildIndex = leftChild(i);
		int rightChildIndex = rightChild(i);
		
		return this.patients.get(rightChildIndex) == null && this.patients.get(leftChildIndex) == null;
	}
	
	private boolean isEmpty() { 
		return this.patients.isEmpty();
	}
	
	private void swap(int i, int j) {
		Patient temp = this.patients.get(i);
		Patient cur = this.patients.get(j);
		
		this.patients.set(i, cur);
		this.patients.set(j, temp);
		
		this.nameToIndex.put(temp.getName(), j);
		this.nameToIndex.put(cur.getName(), i);
	}

	public void upHeap(int i){
		// TODO: Implement your code here
		int k = i;
		
		Patient cur = this.patients.get(k);                     // ArrayList get() method is O(1) time complexity
		double curPriority = cur.getPriority();
		
		int parentIndex = parent(k);
		Patient curParent = this.patients.get(parentIndex);     // ArrayList get() method is O(1) time complexity
		double curParentPriority = curParent.getPriority();
		
		if(k > 0 && curParentPriority > curPriority) {
			swap(k, parentIndex);
			upHeap(parentIndex);
		}
	}

	public void downHeap(int i){
        // TODO: Implement your code here
		int currentIndex = 1;
		int currentLeftChildIndex = leftChild(currentIndex);
		int child = 0;
		
		while(currentLeftChildIndex <= i) {
			child = currentLeftChildIndex;
			if(child < i) {
				if(this.patients.get(child + 1).getPriority() < this.patients.get(child).getPriority()) {
					child = child + 1;
				}
			}
			if(this.patients.get(child).getPriority() < this.patients.get(i).getPriority()) {
				swap(i, child);
				i = child;
			} else {
				break;
			}
		}
	}

	public boolean contains(String name){
        // TODO: Implement your code here & remove return statement
        return this.nameToIndex.containsKey(name);
	}

	public double getPriority(String name){
        // TODO: Implement your code here & remove return statement
        return -1;
	}

	public double getMinPriority(){
        // TODO: Implement your code here & remove return statement
        return -1;
	}

	public String removeMin(){
        // TODO: Implement your code here & remove return statement
		if(this.patients.isEmpty()) {
			return null;
		}
		int size = this.patients.size();
		Patient temp = this.patients.get(1);
		Patient lowPriority = this.patients.get(size);
		this.patients.set(1, lowPriority);
		this.patients.remove(size - 1);
		//heap[size] = null;
		downHeap(this.patients.size() - 1);
		
        return temp.getName();
	}

	public String peekMin(){
        // TODO: Implement your code here & remove return statement
        return null;
	}

	/*
	 * There are two add methods.  The first assumes a specific priority.
	 * The second gives a default priority of Double.POSITIVE_INFINITY
	 *
	 * If the name is already there, then return false.
	 */

	public boolean  add(String name, double priority){
        // TODO: Implement your code here & remove return statement
		if(this.nameToIndex.containsKey(name)) {
			return false;
		}
		
		Patient newPatient = new Patient(name, priority);
		this.patients.add(newPatient);
		int newPatientIndex = this.patients.size() - 1;   // The index of the newly added patient should be the size of the array list - 1

		this.nameToIndex.put(name, newPatientIndex);  // Put patient in hash map
		
		upHeap(newPatientIndex);
			
        return true;
	}

	public boolean  add(String name){
        // TODO: Implement your code here
		if(this.nameToIndex.containsKey(name)) {
			return false;
		}
			
		double defaultPriority = Double.POSITIVE_INFINITY;
		Patient newPatient = new Patient(name, defaultPriority);
		this.patients.add(newPatient);                              // ArrayList add takes O(1) time
		int newPatientIndex = this.patients.size() - 1;
		
		this.nameToIndex.put(name, newPatientIndex);                // Should be O(1) time complexity
		
		upHeap(newPatientIndex);
		
		
		return true;
	}

	public boolean remove(String name){
        // TODO: Implement your code here
        return false;
	}

	/*
	 *   If new priority is different from the current priority then change the priority
	 *   (and possibly modify the heap).
	 *   If the name is not there, return false
	 */

	public boolean changePriority(String name, double priority){
        // TODO: Implement your code here & remove return statement
        return false;
	}

	public ArrayList<Patient> removeUrgentPatients(double threshold){
        // TODO: Implement your code here & remove return statement
        return null;
	}

	public ArrayList<Patient> removeNonUrgentPatients(double threshold){
        // TODO: Implement your code here & remove return statement
        return null;
	}



	static class Patient{
		private String name;
		private double priority;

		Patient(String name,  double priority){
			this.name = name;
			this.priority = priority;
		}

		Patient(Patient otherPatient){
			this.name = otherPatient.name;
			this.priority = otherPatient.priority;
		}

		double getPriority() {
			return this.priority;
		}

		void setPriority(double priority) {
			this.priority = priority;
		}

		String getName() {
			return this.name;
		}

		@Override
		public String toString(){
			return this.name + " - " + this.priority;
		}

		public boolean equals(Object obj){
			if (!(obj instanceof  ERPriorityQueue.Patient)) return false;
			Patient otherPatient = (Patient) obj;
			return this.name.equals(otherPatient.name) && this.priority == otherPatient.priority;
		}

	}
}
