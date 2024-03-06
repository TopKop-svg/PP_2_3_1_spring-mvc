package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.controller.UserController;
import web.model.User;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userDao.getAll());
        return "users";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        User user = userDao.getById(id);
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") User user) {
        userDao.save(user);
        return "redirect:/users";
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user) {
        user.setId(id);
        userDao.update(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userDao.delete(id);
        return "redirect:/users";
    }
}
