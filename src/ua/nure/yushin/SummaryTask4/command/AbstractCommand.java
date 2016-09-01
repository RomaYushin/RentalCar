package ua.nure.yushin.SummaryTask4.command;

/**
 * Abstract class with implements interface ICommand.
 * There are realisation of method toString.
 * 
 * @version 	01.09.2016
 * @author 		Yushin Roman
 * @see			ua.nure.yushin.SummaryTask4.command.CommandContainer
 * @see			ua.nure.yushin.SummaryTask4.command.ICommand
 */
public abstract class AbstractCommand implements ICommand {

	private static final long serialVersionUID = -8894409276452777280L;

	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}	
}
