package com.hashi.util;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.hashi.rest.enums.ProductExpiryDate;
import com.hashi.rest.enums.ProductPriority;

public class ExpiryDate {

	public static Date calculateExpiryDate(ProductPriority priority) {
		//calculate days to add for the expiry date 		
		switch (priority) {
		case PRIORITY_NORMAL: 
			LocalDate ldNormal= LocalDate.now().plusDays(ProductExpiryDate.PRIORITY_NORMAL.getValue());
			Instant instantNormal = ldNormal.atStartOfDay(ZoneId.systemDefault()).toInstant();
			return Date.from(instantNormal);
		case PRIORITY_FEATURED: 
			LocalDate ldPremium= LocalDate.now().plusDays(ProductExpiryDate.PRIORITY_FEATURED.getValue());
			Instant instantPremium = ldPremium.atStartOfDay(ZoneId.systemDefault()).toInstant();
			return Date.from(instantPremium);
		default:
			return null;
		}
	}


	public static void main (String args[]){
		LocalDate ldPremium= LocalDate.now().plusDays(ProductExpiryDate.PRIORITY_FEATURED.getValue());
		System.out.println(ldPremium);

		Instant instantPremium = ldPremium. atStartOfDay(ZoneId.systemDefault()).toInstant();

		System.out.println(Date.from(instantPremium));
	}

}
