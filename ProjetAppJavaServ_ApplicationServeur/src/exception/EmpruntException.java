package exception;

public class EmpruntException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public EmpruntException(String s) {
		super(s);
	}
	
	public EmpruntException() {
		super();
	}
}
