package com.seladanghijau.controller;

import com.seladanghijau.controller.service.FoodService;
import com.seladanghijau.model.FoodTypes;
import com.seladanghijau.model.Foods;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("foods")
public class FoodsController {
    /*
     * Method
     *  -   registerNewFoods(newFoods: Foods @ModelAttribute("foods"), result: BindingResult): ModelAndView
     *  -   updateFoods(updatedFoods: Foods @ModelAttribute("updatedFoods"), result: BindingResult): ModelAndView
     *  -   removeFoods(id: Long @RequestParam): ModelAndView
     *  -   registerNewFoodTypes(newFoodTypes: FoodTypes @ModelAttribute("foodTypes"), result: BindingResult): ModelAndView
     *  -   updateFoodTypes(updatedFoodTypes: FoodTypes @ModelAttribute("updatedFoodTypes"), result: Bindingresult): ModelAndView
     *  -   removeFoodTypes(id: Long @RequestParam): ModelAndView
     */

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerNewFoods(@ModelAttribute("foods") Foods newFood, BindingResult result) {
        ModelAndView mavAdmin;
        Long newFoodId;

        if(result.hasErrors())
            return new ModelAndView("admin/food/add");

        newFoodId = FoodService.addNewFoods(newFood);
        mavAdmin = new ModelAndView("redirect:/link/admin");

        mavAdmin.addObject("message", "New food type (id: " + newFoodId + ") has been registered.");

        return mavAdmin;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateFoods(@ModelAttribute("updatedFoods") Foods updatedFoods, BindingResult result) {
        ModelAndView mavAdmin;

        if(result.hasErrors())
            return new ModelAndView("admin/food/edit");

        FoodService.editFoods(updatedFoods);
        mavAdmin = new ModelAndView("redirect:/link/food/edit");

        mavAdmin.addObject("message", "Food has been updated.");

        return mavAdmin;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView removeFoods(@RequestParam("id") Long id) {
        ModelAndView mavAdmin;

        FoodService.removeFoodsById(id);
        mavAdmin = new ModelAndView("redirect:/link/food/delete");

        mavAdmin.addObject("message", "Food has been deleted.");

        return mavAdmin;
    }

    @RequestMapping(value = "/food-type/register", method = RequestMethod.POST)
    public ModelAndView registerNewFoodTypes(@ModelAttribute("foodTypes") FoodTypes newFoodType, BindingResult result) {
        ModelAndView mavAdmin;
        Long newFoodTypeId;

        if(result.hasErrors())
            return new ModelAndView("admin/food-type/add");

        newFoodTypeId = FoodService.addNewFoodTypes(newFoodType);
        mavAdmin = new ModelAndView("redirect:/link/admin");

        mavAdmin.addObject("message", "New food type (id: " + newFoodTypeId + ") has been registered.");

        return mavAdmin;
    }

    @RequestMapping(value = "/food-type/update", method = RequestMethod.POST)
    public ModelAndView updateFoodTypes(@ModelAttribute("updatedFoodTypes") FoodTypes updatedFoodType, BindingResult result) {
        ModelAndView mavAdmin;

        if(result.hasErrors())
            return new ModelAndView("admin/food-type/edit");

        FoodService.editFoodTypes(updatedFoodType);
        mavAdmin = new ModelAndView("redirect:/link/food-type/edit");

        mavAdmin.addObject("message", "Food type has been updated.");

        return mavAdmin;
    }

    @RequestMapping(value = "/food-type/delete", method = RequestMethod.POST)
    public ModelAndView removeFoodTypes(@RequestParam("foodTypeId") Long id) {
        ModelAndView mavAdmin;

        FoodService.removeFoodTypesById(id);
        mavAdmin = new ModelAndView("redirect:/link/food-type/delete");

        mavAdmin.addObject("message", "Food type has been deleted.");

        return mavAdmin;
    }
}
