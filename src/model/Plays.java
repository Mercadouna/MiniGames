package model;

import java.time.LocalDate;

public class Plays {
	private String g_Name;
	private LocalDate dati;
	private int score;
	public Plays(String g_Name, LocalDate dati, int score) {
		this.g_Name = g_Name;
		this.dati = dati;
		this.score = score;
	}
	public String getG_Name() {
		return g_Name;
	}
	public void setG_Name(String g_Name) {
		this.g_Name = g_Name;
	}
	public LocalDate getDati() {
		return dati;
	}
	public void setDati(LocalDate dati) {
		this.dati = dati;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "|Game:" + g_Name + "| Date:" + dati + "| SCORE:" + score + "|";
	}
	
}