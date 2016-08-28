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
import ua.nure.yushin.SummaryTask4.command.client.AvailableCarsAsyncCommand;
import ua.nure.yushin.SummaryTask4.command.client.CalculateTotalPriceCommand;
import ua.nure.yushin.SummaryTask4.command.client.ClientCarsReviewCommand;
import ua.nure.yushin.SummaryTask4.command.client.ClientNotClosedOrdersCommand;
import ua.nure.yushin.SummaryTask4.command.client.ClientOrdersArchiveCommand;
import ua.nure.yushin.SummaryTask4.command.client.ClientPersonalAreaCommand;
import ua.nure.yushin.SummaryTask4.command.client.CreateOrderCommand;
import ua.nure.yushin.SummaryTask4.command.client.DeleteOrderCommand;
import ua.nure.yushin.SummaryTask4.command.client.OpenClientOrderCommand;
import ua.nure.yushin.SummaryTask4.command.client.OrderCarAsyncCommand;
import ua.nure.yushin.SummaryTask4.command.client.PayOrderCommand;
import ua.nure.yushin.SummaryTask4.command.client.SelectCarsByRentalDatesCommand;
import ua.nure.yushin.SummaryTask4.command.common.LogoutCommand;
import ua.nure.yushin.SummaryTask4.command.common.NoCommand;
import ua.nure.yushin.SummaryTask4.command.admin.AdminPersonalAreaCommand;
import ua.nure.yushin.SummaryTask4.command.admin.EditCarCommand;
//import ua.nure.yushin.SummaryTask4.command.admin.ShowAllCarsFormCommand;
import ua.nure.yushin.SummaryTask4.command.admin.RegisterNewCarCommand;
import ua.nure.yushin.SummaryTask4.command.admin.RegisterNewManagerCommand;
import ua.nure.yushin.SummaryTask4.command.admin.RemoveCarCommand;
import ua.nure.yushin.SummaryTask4.command.manager.ApproveOrderCommand;
import ua.nure.yushin.SummaryTask4.command.manager.CloseOrderCommand;
import ua.nure.yushin.SummaryTask4.command.manager.CreateBillForRepairCommand;
import ua.nure.yushin.SummaryTask4.command.manager.ManagerPersonalAreaCommand;
import ua.nure.yushin.SummaryTask4.command.manager.RejectOrderCommand;
import ua.nure.yushin.SummaryTask4.command.manager.ShowOrdersCommand;
import ua.nure.yushin.SummaryTask4.command.manager.ShowSpecifiedOrderCommand;
import ua.nure.yushin.SummaryTask4.command.outOfControl.ClientRegistrationCommand;
import ua.nure.yushin.SummaryTask4.command.outOfControl.ConfirmRegistrationCommand;
import ua.nure.yushin.SummaryTask4.command.outOfControl.EditLanguageCommand;
import ua.nure.yushin.SummaryTask4.command.outOfControl.ShowConfirmViewCommand;
import ua.nure.yushin.SummaryTask4.command.outOfControl.UserAuthorizationCommand;

public class CommandContainer {

	private static final Logger LOG = Logger.getLogger(CommandContainer.class);

	private static Map<String, ICommand> commands = new TreeMap<String, ICommand>();

	static {
		// admin command
		commands.put("adminPersonalArea", new AdminPersonalAreaCommand());
		commands.put("editCar", new EditCarCommand());
		commands.put("registerNewCar", new RegisterNewCarCommand());
		commands.put("registerNewManager", new RegisterNewManagerCommand());
		commands.put("removeCar", new RemoveCarCommand());
		commands.put("showAddNewCarForm", new ShowAddNewCarFormCommand());
		commands.put("showAllCarsForm", new ShowAllCarsFormCommand());
		commands.put("showBlockUserForm", new ShowBlockUserFormCommand());
		commands.put("showEditCarForm", new ShowEditCarFormCommand());
		commands.put("showRegisterManagerForm", new ShowRegisterManagerFormCommand());
		commands.put("updateUserBlocking", new UpdateUserBlockingCommand());		

		// client command
		commands.put("availableCarsAsync", new AvailableCarsAsyncCommand());
		commands.put("calculateTotalPriceAsync", new CalculateTotalPriceCommand());
		commands.put("clientCarsReview", new ClientCarsReviewCommand());
		commands.put("clientNotClosedOrders", new ClientNotClosedOrdersCommand());
		commands.put("clientOrdersArchive", new ClientOrdersArchiveCommand());	
		commands.put("clientPersonalArea", new ClientPersonalAreaCommand());
		commands.put("createOrder", new CreateOrderCommand());
		commands.put("deleteOrder", new DeleteOrderCommand());
		commands.put("orderCarButton", new OrderCarAsyncCommand());
		commands.put("payOrder", new PayOrderCommand());
		commands.put("selectCarsByRentalDates", new SelectCarsByRentalDatesCommand());	
		commands.put("openClientOrder", new OpenClientOrderCommand());
			

		// manager command
		commands.put("approveOrder", new ApproveOrderCommand());
		commands.put("closeOrder", new CloseOrderCommand());
		commands.put("createBillForRepair", new CreateBillForRepairCommand());
		commands.put("managerPersonalArea", new ManagerPersonalAreaCommand());
		commands.put("rejectOrder", new RejectOrderCommand());
		commands.put("showOrders", new ShowOrdersCommand());
		commands.put("showSpecifiedOrder", new ShowSpecifiedOrderCommand());
		
		// common commands
		commands.put("logout", new LogoutCommand());
		commands.put("noCommand", new NoCommand());
		
		// out of control
		commands.put("clientRegistration", new ClientRegistrationCommand());
		commands.put("confirmRegistration", new ConfirmRegistrationCommand());
		commands.put("editLanguage", new EditLanguageCommand());
		commands.put("showConfirmView", new ShowConfirmViewCommand());
		commands.put("userAuthorization", new UserAuthorizationCommand());		

		LOG.info("Command container was successfully initialized");
		LOG.info("Total number of commands equals to " + commands.size());
	}

	public static ICommand getCommand(String commandName) {

		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command with name " + commandName + " don't exist");
			return commands.get("noCommand");
		}

		return commands.get(commandName);
	}
}
