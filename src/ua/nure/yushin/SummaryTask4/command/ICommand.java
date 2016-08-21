package ua.nure.yushin.SummaryTask4.command;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.yushin.SummaryTask4.controller.ActionType;
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.DBException;

public interface ICommand extends Serializable {

	public String execute(HttpServletRequest request, 
			HttpServletResponse response, ActionType requestMethodType) throws AppException;

}
