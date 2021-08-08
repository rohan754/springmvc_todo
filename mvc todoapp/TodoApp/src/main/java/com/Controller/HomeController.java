package com.Controller;

import java.util.Date;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Entities.Todo;
import com.dao.TodoDao;

@Controller
public class HomeController {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	TodoDao todoDao;
	

  @RequestMapping("/home")
  public String home(Model m)
  {
	  String str="home";
	    m.addAttribute("page",str);
	    
	    List<Todo> list=this.todoDao.getAll();
	    
	    m.addAttribute("todos", list);
	    
		return "home";
	  
  }
  
  @RequestMapping("/add")
	public String addToDo(Model m)
	{
		
		Todo t=new Todo();
		m.addAttribute("page","add");
		m.addAttribute("todo",t);
		return "home";
   
  }
  @RequestMapping(value="/saveTodo", method = RequestMethod.POST)
	public String saveTodo(@ModelAttribute("todo") Todo t,Model m)
	{
		System.out.println(t);
		t.setTodoDate(new Date());
		this.todoDao.save(t);
	    m.addAttribute("msg","Successfully Added.....");
		return "home";
	
	
	
	}	
}
