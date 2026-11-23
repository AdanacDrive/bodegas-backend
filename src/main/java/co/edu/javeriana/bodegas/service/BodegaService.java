package co.edu.javeriana.bodegas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.javeriana.bodegas.dto.BodegaDto;
import co.edu.javeriana.bodegas.entity.Bodega;
import co.edu.javeriana.bodegas.repository.BodegaRepository;

@Service
public class BodegaService {

    private final BodegaRepository bodegaRepository;
    private final ModelMapper mapper;

    public BodegaService(BodegaRepository bodegaRepository, ModelMapper mapper) {
        this.bodegaRepository = bodegaRepository;
        this.mapper = mapper;
    }

    @Transactional
    public BodegaDto registrar(BodegaDto dto) {
        Bodega bodega = new Bodega();
        bodega.setNombre(dto.getNombre());
        bodega.setUbicacion(dto.getUbicacion());
        Bodega guardada = bodegaRepository.save(bodega);
        return mapper.map(guardada, BodegaDto.class);
    }

    // los productos son LAZY, se necesita la transaccion abierta para leerlos al mapear
    @Transactional(readOnly = true)
    public List<BodegaDto> listar() {
        return bodegaRepository.findAll()
                .stream()
                .map(b -> mapper.map(b, BodegaDto.class))
                .collect(Collectors.toList());
    }

}
