package com.kh.spring;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private MessageDAO mDao;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	@ResponseBody	// produces를 깜빡하면 jsp에서 dataType : "json"을 적어주면 된다.
	@RequestMapping(value="listMessage", produces="application/json;charset=utf-8")
	public String listMessage(/* Model model */) {
		List<MessageDTO> mList = mDao.listMessage();
		// MessageDTO mDto = mDao.oneMessage();
		//model.addAttribute("mList", mList);
		// mList -> jsonObject -> jsonArray
		// GSON
		Gson gson = new Gson();
		return gson.toJson(mList); // [{}, {}, {}, {} ]
		//return "message/list"; // View URL -> /WEB-INF/views/성공.jsp
	}
	
	@ResponseBody
	@RequestMapping(value="insertMessage", produces="text/html;charset=utf-8")
	public String insertMessage(
			@ModelAttribute MessageDTO messageDTO) {
		int result = mDao.insertMessage(messageDTO);
		return "성공"; // View URL -> /WEB-INF/views/성공.jsp
	}
	
	@ExceptionHandler
	public String errorHandler(Exception e) {
		return "redirect:/error";
	}
	@RequestMapping(value="error")
	public String toError() {
		return "error"; // /WEB-INF/views/error.jsp
	}
	
	
	
	
	
	
}
