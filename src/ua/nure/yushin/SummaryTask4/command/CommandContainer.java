package ua.nure.yushin.SummaryTask4.command;

import java.util.Map;
import java.util.TreeMap;

import ua.nure.yushin.SummaryTask4.command.profile.EditLanguage;

public class CommandContainer {
	
	private static Map <String, ICommand> commands = new TreeMap<String, ICommand> ();
	
	static {
		commands.put("editLanguage", new EditLanguage ());
	}

}
