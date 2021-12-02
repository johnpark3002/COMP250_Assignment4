import static org.hamcrest.CoreMatchers.nullValue;

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
		int size = this.patients.size();
		if(i > (size / 2) && i <= size) {
			return true;
		}
		
		return false;
	}
	
	private boolean isEmpty() {     // Means that there is only the dummy node in the patients array list
		return this.patients.size() == 1;
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
		int leftChild = leftChild(i);
		int rightChild = rightChild(i);
		int size = this.patients.size();
		
		if(!isLeaf(i)) {
			if(leftChild <= size && rightChild < size) {
				if(this.patients.get(i).getPriority() > this.patients.get(leftChild).getPriority()
					|| this.patients.get(i).getPriority() > this.patients.get(rightChild).getPriority()) {
				
					if(this.patients.get(leftChild).getPriority() < this.patients.get(rightChild).getPriority()) {
						swap(i, leftChild);
						downHeap(leftChild);
					} else {
						swap(i, rightChild);
						downHeap(rightChild);
					}
				} 
			}
		} else {
			return;
		}
		
//		int currentIndex = 1;
//		int currentLeftChildIndex = leftChild(currentIndex);
//		int currentRightChildIndex = rightChild(currentIndex);
//		int child = 0;
//		
//		while(currentLeftChildIndex <= i) {
//			child = currentLeftChildIndex;
//			if(child < i) {
//				if(this.patients.get(child + 1).getPriority() < this.patients.get(child).getPriority()) {
//					child = child + 1;
//				}
//			}
//			if(this.patients.get(child).getPriority() < this.patients.get(currentIndex).getPriority()) {
//				swap(currentIndex, child);
//				currentIndex = child;
//			} else {
//				return;
//			}
//		}
		
//		if(currentIndex == 1) {
//			Patient cur = this.patients.get(currentIndex);
//			
//			//Check priority of left child
//			int leftIndex = leftChild(currentIndex);
//			Patient leftChild = this.patients.get(leftIndex);
//			
//			//Check priority of right child
//			int rightIndex = rightChild(currentIndex);
//			Patient rightChild = this.patients.get(rightIndex);
//			
//			if(leftChild.getPriority() < cur.getPriority()) {
//				swap(currentIndex, leftIndex);
//			} else if (rightChild.getPriority() < cur.getPriority()) {
//				swap(currentIndex, rightIndex);
//			}
//		}
	}

	public boolean contains(String name){
        // TODO: Implement your code here & remove return statement
        return this.nameToIndex.containsKey(name);
	}

	public double getPriority(String name){
        // TODO: Implement your code here & remove return statement
		if(!(this.contains(name))) {
			return -1;
		}
		int cur = this.nameToIndex.get(name);
        return this.patients.get(cur).getPriority();
	}

	public double getMinPriority(){
        // TODO: Implement your code here & remove return statement
		if(this.isEmpty()) {
			return -1;
		}
        return this.patients.get(1).getPriority();
	}

	public String removeMin(){
        // TODO: Implement your code here & remove return statement
		String result = "";
		
		if(this.isEmpty()) {
			return null;
		} else {
			int size = this.patients.size();
			Patient cur = this.patients.get(1);
			Patient last = this.patients.get(size - 1);
			swap(1, size - 1);
//			this.patients.set(1, last);
			this.patients.remove(this.patients.size() - 1);
//			this.nameToIndex.put(last.getName(), 1);
			this.nameToIndex.remove(cur.getName());
			//heap[size] = null;
			size = this.patients.size() - 1;
			downHeap(size);
			
			result = cur.getName();
		}

        return result;
	}

	public String peekMin(){
        // TODO: Implement your code here & remove return statement
		if(this.isEmpty()) {
			return null;
		}
        return this.patients.get(1).getName();
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
		if(!(this.nameToIndex.containsKey(name))) {
			return false;
		}
		Integer patientIndex = this.nameToIndex.get(name);
		Patient patient = this.patients.get(patientIndex);
		double patientPriority = patient.getPriority();
		
		int leftChild = leftChild(patientIndex);
		int rightChild = rightChild(patientIndex);
		int currentPatient = patientIndex;
		
		
		if(patientPriority == priority) {
			return false;
		} else {
			patient.setPriority(priority);
			if(currentPatient < this.getMinPriority()) {
				upHeap(currentPatient);
			} else if(currentPatient > this.getMinPriority()) {
				downHeap(currentPatient);
			}
		}
		
		
        return true;
	}

	public ArrayList<Patient> removeUrgentPatients(double threshold){
        // TODO: Implement your code here & remove return statement
		ArrayList<Patient> urgentPatients = new ArrayList<Patient>();
		for(Patient p : this.patients) {
			double patientPriority = p.getPriority();
			String patientName = p.getName();
			if(patientPriority <= threshold) {
				int patientIndex = this.nameToIndex.get(patientName);
				urgentPatients.add(p);
				this.patients.remove(patientIndex);
				this.nameToIndex.remove(patientName);
			}
		}
        return urgentPatients;
	}

	public ArrayList<Patient> removeNonUrgentPatients(double threshold){
        // TODO: Implement your code here & remove return statement
		ArrayList<Patient> nonUrgentPatients = new ArrayList<Patient>();
		for(Patient p : this.patients) {
			double patientPriority = p.getPriority();
			String patientName = p.getName();
			if(patientPriority > threshold) {
				int patientIndex = this.nameToIndex.get(patientName);
				nonUrgentPatients.add(p);
				this.patients.remove(patientIndex);
				this.nameToIndex.remove(patientName);
			}
		}
        return nonUrgentPatients;
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
