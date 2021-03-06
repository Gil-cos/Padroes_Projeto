package br.com.radarSJC.model;

import java.util.LinkedList;
import java.util.List;

import com.pengrad.telegrambot.model.Update;

import br.com.radarSJC.service.RadarDiaService;
import br.com.radarSJC.utils.Observer;
import br.com.radarSJC.utils.Subject;

public class Model implements Subject {

	private List<Observer> observers = new LinkedList<Observer>();

	private List<RadarDia> radares = new LinkedList<RadarDia>();

	private RadarDiaService service = new RadarDiaService();

	private static Model uniqueInstance;

	private Model() {
	}

	public static Model getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Model();
		}
		return uniqueInstance;
	}

	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	public void notifyObservers(long chatId, String studentsData) {
		for (Observer observer : observers) {
			observer.update(chatId, studentsData);
		}
	}

	public void addRadar(RadarDia radar) {
		this.radares.add(radar);
	}

	public String getRadaresRest(Update update) {
		String radarData = "";
		RadarDia[] radaresRest = service.getAll();

		for (RadarDia radar : radaresRest) {
			if ("/semana".equals(update.message().text().toLowerCase()))
				radarData += "\n" + radar.getDate() + "\n" + radar.getLocal() + "\n";
		}
		return radarData;
	}

	public void searchRadar(Update update) {
		String radarData = null;

		RadarDia radarRest = service.get(update.message().text());

		if (radarRest == null) {

			this.notifyObservers(update.message().chat().id(), "Dia invalido");
			this.notifyObservers(update.message().chat().id(), "Digite /semana ou /dia:");

		} else {

			radarData = radarRest.getDate() + "\n" + radarRest.getLocal();
			this.notifyObservers(update.message().chat().id(), radarData);
			this.notifyObservers(update.message().chat().id(), "Digite /semana ou /dia:");
		}
	}

	public String getRadares(Update update) {
		String radarData = "";
		for (RadarDia radar : radares) {
			if ("/semana".equals(update.message().text().toLowerCase()))
				radarData += "\n" + radar.getDate() + "\n" + radar.getLocal() + "\n";
		}

		if (radarData != "") {
			return radarData;
		} else {
			return "Dia invalido";
		}
	}
}