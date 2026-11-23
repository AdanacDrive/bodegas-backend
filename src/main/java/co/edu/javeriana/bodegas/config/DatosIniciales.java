package co.edu.javeriana.bodegas.config;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.edu.javeriana.bodegas.entity.Bodega;
import co.edu.javeriana.bodegas.entity.Producto;
import co.edu.javeriana.bodegas.repository.BodegaRepository;
import co.edu.javeriana.bodegas.repository.ProductoRepository;

@Configuration
public class DatosIniciales {

    @Bean
    CommandLineRunner cargarDatos(BodegaRepository bodegaRepository, ProductoRepository productoRepository) {
        return args -> {
            if (bodegaRepository.count() == 0) {
                Bodega norte = bodegaRepository.save(new Bodega(null, "Bodega Norte", "Calle 170 # 45-10", new ArrayList<>()));
                Bodega sur = bodegaRepository.save(new Bodega(null, "Bodega Sur", "Autopista Sur # 60-25", new ArrayList<>()));

                productoRepository.save(new Producto(null, "Computador portátil", "Portátil de 15 pulgadas", new BigDecimal("2500000"), 15, norte));
                productoRepository.save(new Producto(null, "Monitor", "Monitor de 24 pulgadas", new BigDecimal("750000"), 20, norte));
                productoRepository.save(new Producto(null, "Impresora", "Impresora láser", new BigDecimal("1200000"), 8, sur));
            }
        };
    }

}
