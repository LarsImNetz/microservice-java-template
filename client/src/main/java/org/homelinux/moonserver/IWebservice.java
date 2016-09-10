package org.homelinux.moonserver;

public interface IWebservice {

	public String hasAnyKontingent(Integer kundenNr);

	public String hasKontingent(Integer kundenNr, Integer plz);

	public String decrementKontingent(Integer kundenNr, Integer plz);

}
