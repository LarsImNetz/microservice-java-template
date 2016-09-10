package org.lla_private;

import java.time.LocalDate;

// -------------------------------------------------------------
final class SimpleDTO {

	private String vorname;
	private String nachname;
	private LocalDate geburtstag;
	private String strasse;
	private String hausnummer;
	private Integer plz;
	private String ort;

	public String getHausnummer() {
		return hausnummer;
	}

	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}

	public Integer getPlz() {
		return plz;
	}

	public void setPlz(Integer plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public SimpleDTO() {
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(final String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(final String nachname) {
		this.nachname = nachname;
	}

	public LocalDate getGeburtstag() {
		return geburtstag;
	}

	public void setGeburtstag(final LocalDate geburtstag) {
		this.geburtstag = geburtstag;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

}
