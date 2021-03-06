package poly.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import poly.controller.SaveLogged;
import poly.service.UserService;

@Controller
@RequestMapping("/admin/")
public class DeleteUsers {
    @Autowired
    UserService userService;

    @GetMapping("/users/delete/{id}")
    public String delete(@PathVariable(name = "id") Integer id, ModelMap model, RedirectAttributes redirect){
        if(SaveLogged.authenticated()){
            model.addAttribute("login",SaveLogged.USER);
            model.addAttribute("role",SaveLogged.USER.getRole());
            model.addAttribute("name",SaveLogged.USER.getName());
            if(SaveLogged.USER.getRole() == true){
                userService.deleteById(id);
                redirect.addFlashAttribute("success","Delete user successfully");
                return "redirect:/admin/users/list";
            }else{
                model.addAttribute("message","You can not access this page");
                return "error";
            }
        }else {
            return "login";
        }

    }
}
