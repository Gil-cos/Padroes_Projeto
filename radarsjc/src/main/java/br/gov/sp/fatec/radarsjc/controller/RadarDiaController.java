package br.gov.sp.fatec.radarsjc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.radarsjc.model.Dia;
import br.gov.sp.fatec.radarsjc.model.RadarDia;
import br.gov.sp.fatec.radarsjc.service.RadarDiaService;

@RestController
@RequestMapping("/")
public class RadarDiaController {

	@Autowired
	private RadarDiaService service;
	
	@RequestMapping(value="lista")
	public List<RadarDia> list(){
		return service.findAll();
	}
	
	@RequestMapping(value="{date}")
	public RadarDia getRadarDia(@PathVariable Dia date) {
		return service.findByDay(date);
	}
}
