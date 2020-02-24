package fr.d2factory.libraryapp.member;

import java.time.LocalDate;

public class Resident extends Member{

	@Override
	public float payBook(int numberOfDays) {
		
		
		

		
		float toPay = 0;
		

		if ( numberOfDays > 60 ) {
			
			float xMajore = numberOfDays - 60 ; 
			toPay = (float) ((xMajore * 0.20) + ( 60 * 0.10));
			setLate(true);
			
		}
		else {
			toPay = (float) (numberOfDays * 0.10);

		}
		
		setWallet((float) (getWallet()-toPay ));

		return toPay;
	
	}

	

}
