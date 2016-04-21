package controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.SQLDao;
import data.SQLResult;

@Controller
public class SQLController {
	@Autowired
	private SQLDao sqlDao;
	@RequestMapping("sql.do")
	public ModelAndView executeSql(@RequestParam("sqlText") String sql){
		ModelAndView mv = new ModelAndView();
		SQLResult results = sqlDao.runSQL(sql);
		ArrayList<String> tables = sqlDao.getTableNames();
		mv.setViewName("index.jsp");
		mv.addObject("results",results);
		mv.addObject("tables",tables);
		return mv;
	}

}
