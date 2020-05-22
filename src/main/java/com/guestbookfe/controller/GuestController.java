package com.guestbookfe.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guestbookfe.model.GuestForm;
import com.guestbookfe.service.GuestService;


@Controller
@RequestMapping(value ="/guestbook-fe")
public class GuestController {

	private GuestService guestService;
	@Autowired
	public void setGuestService(GuestService guestService) {
		this.guestService = guestService;
	}

	@GetMapping(value = "/guestsign")
	public String getGuestAdd (Model model) {
		model.addAttribute("form", new GuestForm());
		return "/postguest";
	}
	
	@PostMapping(value = "/guestsign")
	public String postGuestAdd  ( @ModelAttribute("form") GuestForm form, BindingResult bindingResult, Model model) throws URISyntaxException {
		if (bindingResult.hasErrors()){
			return "redirect:/postguest";
		}
		guestService.addGuest(form);
			
		return "/postguest";
	}
	@GetMapping(value = "/guestlist")
	public String getGuestList (Model model) throws URISyntaxException {
		model.addAttribute("list",guestService.getGuests());
		return "/getguest";
	}

}
