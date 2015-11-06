package project295B;

import javax.validation.Valid;

import stock.Stock;
import user.User;
import db.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import exceptions.InputNotFilledException;
import exceptions.InvalidLoginException;
import exceptions.NoSuchUserException;

@Controller
@RequestMapping("/cmpe295project")
public class ProjectController extends WebMvcConfigurerAdapter {

    @Autowired
    User user;
    private UserRepository userRepository;

    ProjectHandler projectHandler = new ProjectHandler();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getIndexPage(User user) {
        //User user = new User();

        return "index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String checkUserInfo(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "index";
        }
        try {
            user = projectHandler.doesUserExist(user.getUsername());
            model.addAttribute("user", user);
            //return "predict";
        } catch ( InvalidLoginException e) {
            model.addAttribute("message", "Please login to continue");
            return "index";
        } catch (NoSuchUserException e) {
            model.addAttribute("message", "Username or password is incorrect");
            return "index";
        }
        return "redirect:/cmpe295project/predict";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegistrationPage() {
        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkUserInfoAndCreateUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "register";
        }
        String username, password;
        username = user.getUsername();
        password = user.getPassword();
        user.setUsername(username);
        user.setPassword(password);
        projectHandler.addToRepository(user);
        model.addAttribute("user", user);
        return "login";
    }

    @RequestMapping(value = "/index/users", method = RequestMethod.POST)
    public String checkUserInfoAndLogin(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "login";
        }
        try {
            user = projectHandler.doesUserExist(user.getUsername());
            model.addAttribute("user", user);
        }   catch (InvalidLoginException e) {
            model.addAttribute("message", "Please login to continue");
            return "index";
        } catch (NoSuchUserException e) {
            model.addAttribute("message", "Username or password is incorrect");
            return "index";
        }

        return "redirect:/cmpe295project/predict";
    }
    
    @RequestMapping(value = "/predict", method = RequestMethod.GET)
    public String getPredictionPage() {
    	return "predict";
    }
    
    @RequestMapping(value = "/predict", method = RequestMethod.POST) 
    public String enterPrediction(@Valid @ModelAttribute Stock stock, BindingResult bindingResult, Model model) {
    	if (bindingResult.hasErrors()) {
    		return "predict";
    	}
    	try {
    		//return "redirect:/cmpe295project/predictionresult";
    		//to do
    	}
    	catch (InputNotFilledException e) {
    		model.addAttribute("message", "Please input all fields");
    		return "predict";
    		
    	}
    	return "redirect:/cmpe295project/predictionresult";
    }
    
    @RequestMapping(value="/signout", method = RequestMethod.POST) 
    public String signOut() {
    	return "index";
    }
    



	

}

