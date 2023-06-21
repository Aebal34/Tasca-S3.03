package n2exercici1;

import java.util.*;

public interface Dao<T> {

	abstract Optional<T> get(String id);
	
	abstract List<T> getAll();
	
	abstract void update(T t, String[] parameters);
	
	abstract void save(T t);
	
	abstract void delete(T t);
}
