package ua.nure.yushin.SummaryTask4.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.admin.ShowAddNewCarFormCommand;
import ua.nure.yushin.SummaryTask4.command.admin.ShowAllCarsFormCommand;
import ua.nure.yushin.SummaryTask4.command.admin.ShowBlockUserFormCommand;
import ua.nure.yushin.SummaryTask4.command.admin.ShowEditCarFormCommand;
//import ua.nure.yushin.SummaryTask4.command.admin.ShowCarParamFormCommand;
//import ua.nure.yushin.SummaryTask4.command.admin.ShowEditCarFormCommand;
import ua.nure.yushin.SummaryTask4.command.admin.ShowRegisterManagerFormCommand;
import ua.nure.yushin.SummaryTask4.command.admin.UpdateUserBlockingCommand;
import ua.nure.yushin.SummaryTask4.command.admin.EditCarCommand;
//import ua.nure.yushin.SummaryTask4.command.admin.ShowAllCarsFormCommand;
import ua.nure.yushin.SummaryTask4.command.admin.RegisterNewCarCommand;
import ua.nure.yushin.SummaryTask4.command.admin.RegisterNewManagerCommand;
import ua.nure.yushin.SummaryTask4.command.admin.RemoveCarCommand;
import ua.nure.yushin.SummaryTask4.command.async.CheckOrderStatusAsyncCommand;
import ua.nure.yushin.SummaryTask4.command.async.OrderCarAsyncCommand;
import ua.nure.yushin.SummaryTask4.command.profile.EditLanguage;
import ua.nure.yushin.SummaryTask4.command.registration.AdminPersonalAreaCommand;
import ua.nure.yushin.SummaryTask4.command.registration.AvailableCarsAsyncCommand;
import ua.nure.yushin.SummaryTask4.command.registration.CalculateTotalPriceCommand;
import ua.nure.yushin.SummaryTask4.command.registration.ClientPersonalAreaCommand;
import ua.nure.yushin.SummaryTask4.command.registration.ClientRegistrationCommand;
import ua.nure.yushin.SummaryTask4.command.registration.ConfirmRegistrationCommand;
import ua.nure.yushin.SummaryTask4.command.registration.CreateOrderCommand;
import ua.nure.yushin.SummaryTask4.command.registration.DeleteOrderCommand;
import ua.nure.yushin.SummaryTask4.command.registration.LogoutCommand;
import ua.nure.yushin.SummaryTask4.command.registration.ManagerPersonalAreaCommand;
import ua.nure.yushin.SummaryTask4.command.registration.PayOrderCommand;
import ua.nure.yushin.SummaryTask4.command.registration.SelectCarsByRentalDatesCommand;
import ua.nure.yushin.SummaryTask4.command.registration.UserAuthorizationCommand;

public class CommandContainer {

	private static final Logger LOG = Logger.getLogger(CommandContainer.class);

	private static Map<String, ICommand> commands = new TreeMap<String, ICommand>();

	static {
		commands.put("editLanguage", new EditLanguage());
		commands.put("logout", new LogoutCommand());
		commands.put("userAuthorization", new UserAuthorizationCommand());
		commands.put("clientRegistration", new ClientRegistrationCommand());

		commands.put("adminPersonalArea", new AdminPersonalAreaCommand());
		commands.put("managerPersonalArea", new ManagerPersonalAreaCommand());
		commands.put("clientPersonalArea", new ClientPersonalAreaCommand());
		commands.put("selectCarsByRentalDates", new SelectCarsByRentalDatesCommand());

		commands.put("availableCarsAsync", new AvailableCarsAsyncCommand());
		commands.put("orderCarButton", new OrderCarAsyncCommand());
		commands.put("checkOrderStatus", new CheckOrderStatusAsyncCommand());
		commands.put("calculateTotalPriceAsync", new CalculateTotalPriceCommand());
		commands.put("createOrder", new CreateOrderCommand());
		commands.put("confirmRegistration", new ConfirmRegistrationCommand());
		commands.put("payOrder", new PayOrderCommand());
		commands.put("deleteOrder", new DeleteOrderCommand());

		//
		commands.put("showAddNewCarForm", new ShowAddNewCarFormCommand());
		//commands.put("showRemoveCarForm", new ShowAllCarsFormCommand());
		commands.put("showEditCarForm", new ShowEditCarFormCommand());
		commands.put("showAllCarsForm", new ShowAllCarsFormCommand());
		commands.put("showBlockUserForm", new ShowBlockUserFormCommand());
		commands.put("showRegisterManagerForm", new ShowRegisterManagerFormCommand());
		
		commands.put("registerNewCar", new RegisterNewCarCommand());
		commands.put("removeCar", new RemoveCarCommand());
		commands.put("editCar", new EditCarCommand());
		commands.put("updateUserBlocking", new UpdateUserBlockingCommand());
		commands.put("registerNewManager", new RegisterNewManagerCommand());
		commands.put("noCommand", new NoCommand());

		LOG.debug("Command container was successfully initialized");
		LOG.trace("Total number of commands equals to " + commands.size());
	}

	public static ICommand getCommand(String commandName) {

		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command with name " + commandName + " don't exist");
			return commands.get("noCommand");
		}

		return commands.get(commandName);
	}
}
