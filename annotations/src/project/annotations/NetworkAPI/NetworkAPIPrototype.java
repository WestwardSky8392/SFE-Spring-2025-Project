package annotations.src.project.annotations.NetworkAPI;

//import jdk.internal.reflect.ClassFileAssembler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
@NetworkAPI
@Target(ElementType.METHOD)
public @interface NetworkAPIPrototype{
	//get user data

	AskUser askUser = new AskUser();
	Screen screen = null;
	Window window = screen.showWindow(askUser.getInfo());
	//check if input is valid(if it's an integer)
	ValidInfo validInfo = new ValidInfo(askUser);
	//send to process API
	SendInfo sendInfo = validInfo.sendToProcess();

}
