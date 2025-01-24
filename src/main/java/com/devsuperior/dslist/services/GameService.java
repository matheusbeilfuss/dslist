package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameRepository;

@Service // registra GameService como sendo um componente do sistema (podendo utilizar ele em outros componentes)
public class GameService {

	@Autowired // injeta uma instância do GameRepository dentro de GameService
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true) // garante que a transação do BD vai acontecer obedecendo os princípios ACID
	public GameDTO findById(Long id) {
		Game result = gameRepository.findById(id).get(); // .get() para pegar o game dentro de "optional"
		GameDTO dto = new GameDTO(result);
		return dto;
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll() {
		List<Game> result = gameRepository.findAll();
		List<GameMinDTO> dto = result.stream().map(x -> new GameMinDTO(x)).toList(); // transforma lista de Games em GameMinDTO
		return dto;
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findByList(Long listId) {
		List<GameMinProjection> result = gameRepository.searchByList(listId);
		List<GameMinDTO> dto = result.stream().map(x -> new GameMinDTO(x)).toList(); 
		return dto;
	}
}
