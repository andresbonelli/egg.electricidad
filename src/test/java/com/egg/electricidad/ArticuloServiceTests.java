package com.egg.electricidad;

import com.egg.electricidad.api.dto.CrearArticuloDTO;
import com.egg.electricidad.domain.entity.Fabrica;
import com.egg.electricidad.domain.repository.ArticuloRepository;
import com.egg.electricidad.domain.repository.FabricaRepository;
import com.egg.electricidad.service.ArticuloService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class ArticuloServiceTests {

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private FabricaRepository fabricaRepository;

    private UUID fabricaTestId;

    @BeforeEach
    void setUp() {
        Fabrica f1 = new Fabrica();
        f1.setNombre("Fabrica Test 1");
        fabricaTestId = fabricaRepository.save(f1).getId();
    }

    @AfterEach
    void tearDown() {
        fabricaRepository.deleteById(fabricaTestId);
    }


    @Test
    void testNroArticuloSequence() {
        articuloService.crearArticulo(new CrearArticuloDTO(
                "Articulo Test 1",
                "descripcion 1",
                fabricaTestId.toString()
                )
        );
        articuloService.crearArticulo(new CrearArticuloDTO(
                "Articulo Test 2",
                "descripcion 2",
                fabricaTestId.toString()
                )
        );
        articuloService.crearArticulo(new CrearArticuloDTO(
                        "Articulo Test 3",
                        "descripcion 3",
                        fabricaTestId.toString()
                )
        );
        Assertions.assertEquals(3, articuloRepository.count());
        Assertions.assertNotNull(articuloRepository.findByNroArticulo(1));
        Assertions.assertNotNull(articuloRepository.findByNroArticulo(2));

        articuloService.eliminarArticulo(2);
        articuloService.crearArticulo(new CrearArticuloDTO(
                        "Articulo Test 4",
                        "descripcion 4",
                        fabricaTestId.toString()
                )
        );
        Assertions.assertNotNull(articuloRepository.findByNroArticulo(4));
        Assertions.assertNull(articuloRepository.findByNroArticulo(2));
        articuloService.eliminarArticulo(1);
        articuloService.eliminarArticulo(3);
        articuloService.eliminarArticulo(4);
    }


}
