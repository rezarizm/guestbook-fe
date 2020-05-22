package com.guestbookfe.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.guestbookfe.model.GuestForm;
import com.guestbookfe.model.GuestProto;

@Service
public class GuestbookServiceImpl implements GuestService {

	@Override
	public String addGuest(GuestForm guest) throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
	    
	    final String baseUrl = "http://localhost:8080/guestbook-bl/addguest";
	    URI uri = new URI(baseUrl);
	    GuestProto.Guest gs = GuestProto.Guest.newBuilder()
	    		.setName(guest.getName())
	    		.setInstitution(guest.getInstitution())
	    		.setEmail(guest.getEmail())
	    		.setMessage(guest.getMessage())
	    		.build();
	   return restTemplate.postForObject(uri, gs, String.class);	
	}

	@Override
	public List<GuestForm> getGuests() throws URISyntaxException {
		List<GuestForm> guestList = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();
	    
	    final String baseUrl = "http://localhost:8080/guestbook-bl/getallguests";
	    URI uri = new URI(baseUrl);
	    
	    ResponseEntity<GuestProto.Guests> result = restTemplate.getForEntity(uri, GuestProto.Guests.class);
	    GuestProto.Guests gs= result.getBody();
	    for (int i = 0; i<gs.getGuestCount();i++) {
	    	GuestForm guest = new GuestForm();
	    	guest.setName(gs.getGuest(i).getName());
	    	guest.setEmail(gs.getGuest(i).getEmail());
	    	guest.setInstitution(gs.getGuest(i).getInstitution());
	    	guest.setMessage(gs.getGuest(i).getMessage());
	    	guestList.add(guest);
	    }	   
		return guestList;
	}
	

}
