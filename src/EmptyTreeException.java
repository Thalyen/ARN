//Classe EmptyListException

@SuppressWarnings("serial")
public class EmptyTreeException extends NullPointerException{
	public EmptyTreeException(){
		super();
	}
	public EmptyTreeException(String msg){
		super(msg);
	}
}
