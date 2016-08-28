package ua.nure.yushin.SummaryTask4.util;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class LocaleUtil {

	private static final Locale EN_LOCALE = new Locale("en");
	private static final Locale RU_LOCALE = new Locale("ru");

	private static final ResourceBundle resourceBundle_EN = ResourceBundle.getBundle("resources", EN_LOCALE);
	private static final ResourceBundle resourceBundle_RU = ResourceBundle.getBundle("resources", RU_LOCALE);

	private static final Logger LOG = Logger.getLogger(LocaleUtil.class);

	public static Locale getCurrentLocale(HttpSession session) {
		String language = (String) session.getAttribute("language");
		if (language != null && language.equals("en")) {
			return EN_LOCALE;
		}
		return RU_LOCALE;
	}

	public static String getValueByKey(String key, HttpSession session) {
		if (key != null && session != null) {
			if (getCurrentLocale(session) == EN_LOCALE) {
				return resourceBundle_EN.getString(key);
			} else {
				return resourceBundle_RU.getString(key);
			}
		} else {
			LOG.info("key or session == null; key: " + key + "; session: " + session);
			return null;
		}
	}

}
