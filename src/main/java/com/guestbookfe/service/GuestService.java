package com.guestbookfe.service;

import java.net.URISyntaxException;
import java.util.List;

import com.guestbookfe.model.GuestForm;

public interface GuestService {
	String addGuest(GuestForm guest) throws URISyntaxException;
	List<GuestForm> getGuests() throws URISyntaxException;

}
