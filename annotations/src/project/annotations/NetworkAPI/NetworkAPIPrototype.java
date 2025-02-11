package annotations.src.project.annotations.NetworkAPI;

//import jdk.internal.reflect.ClassFileAssembler;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
@NetworkAPI
@Target(ElementType.METHOD)
public @interface NetworkAPIPrototype{
	//get user data
	DataStorage dataStorage = null;
	RetriveData retriveData = dataStorage.getData(new UserInput());
	// rename these^^^^^^^^^, in correct order though!
	//see if user data is valid by default(Integer)
	Delimiter delimiter = new Delimiter(retriveData);
	//if statement for delimitation? (Not allowed in a prototype interface)
}
