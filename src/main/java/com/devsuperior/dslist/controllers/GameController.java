package com.devsuperior.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.services.GameService;

@RestController
@RequestMapping(value = "/games") // configura o caminho que a API vai responder
public class GameController {
	
	@Autowired
	private GameService gameService; // injeta um Service no Controller
	
	@GetMapping(value = "/{id}")
	public GameDTO findById(@PathVariable Long id) { // "PathVariable para garantir que o id enviado é o que veio da requisição
		GameDTO result = gameService.findById(id);
		return result;		
	}
	
	@GetMapping
	public List<GameMinDTO> findAll() {
		List<GameMinDTO> result = gameService.findAll();
		return result;		
	}
}
