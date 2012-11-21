package collections;

public interface Collection {
	Object remove(Object object); //gets a specific object
	
	Object get(); //gets the first available object
	
	void put(Object object); //puts object in the first available slot
	
	Traverser toTraverser(); //outputs a Traverser, you can't iterate through without it
	
	Object[] toArray(); //if you use this for non debug purposes YOU WILL BE SHOT BY THE KGB
}
