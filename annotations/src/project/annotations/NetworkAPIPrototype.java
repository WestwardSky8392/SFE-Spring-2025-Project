package project.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
@NetworkAPI
@Target(ElementType.METHOD)
public @interface NetworkAPIPrototype {
	// Marker annotation, should be applied to a method within a prototype class
}
