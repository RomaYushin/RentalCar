package ua.nure.yushin.SummaryTask4.command;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.DBException;

/**
 * Interface ICommand. Part of pattern command, where each command 
 * must realize own method execute.
 * 
 * @version 	01.09.2016
 * @author 		Yushin Roman
 * @see			ua.nure.yushin.SummaryTask4.command.CommandContainer
 * @see			ua.nure.yushin.SummaryTask4.command.ICommand
 * @see			ua.nure.yushin.SummaryTask4.controller.ActionTypes;
 */
public interface ICommand extends Serializable {

	/**
	 * It processes the request and return the path with next command
	 * or return path to jsp page. 
	 * 
	 * @param request
	 * @param response
	 * @param requestMethodType
	 * @return
	 * @throws AppException
	 */
	public String execute(HttpServletRequest request, 
			HttpServletResponse response, ActionType requestMethodType) throws AppException;

}
