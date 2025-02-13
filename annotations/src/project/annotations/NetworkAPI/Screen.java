package annotations.src.project.annotations.NetworkAPI;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

public interface Screen {
    Window showWindow(AskUser askUser);

    @Target(ElementType.METHOD)
    @interface NetworkAPIPrototype {
        // Marker annotation, should be applied to a method within a prototype class
    }
}
