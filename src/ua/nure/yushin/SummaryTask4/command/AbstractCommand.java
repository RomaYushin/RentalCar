package ua.nure.yushin.SummaryTask4.command;

public abstract class AbstractCommand implements ICommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8894409276452777280L;

	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}	

}
