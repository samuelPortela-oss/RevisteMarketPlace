package app.service;

import app.domain.Prenda;
import app.domain.enums.EstadoPrendaEnum;
import app.service.inputports.PrendaUseCase;
import app.service.outputports.CategoriaRepositoryInterface;
import app.service.outputports.PrendaRepositoryInterface;
import app.service.outputports.UsuarioRepositoryInterface;
import app.utils.FechaUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PrendaServiceImpl implements PrendaUseCase {

    // Lista cerrada de tallas del modelo Reviste: letras, numéricas 6..44 y talla única
    private static final Set<String> TALLAS_LETRA =
            Set.of("XS", "S", "M", "L", "XL", "XXL", "TALLA_UNICA");

    private final PrendaRepositoryInterface repository;
    private final UsuarioRepositoryInterface usuarioRepository;
    private final CategoriaRepositoryInterface categoriaRepository;

    public PrendaServiceImpl(
            PrendaRepositoryInterface repository,
            UsuarioRepositoryInterface usuarioRepository,
            CategoriaRepositoryInterface categoriaRepository) {

        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void crearPrenda(Prenda prenda) {

        validarDatos(prenda);

        if (usuarioRepository.obtenerUsuarioPorId(
                prenda.getIdVendedor()).isEmpty()) {

            throw new RuntimeException(
                    "El vendedor no existe.");
        }

        if (categoriaRepository.obtenerCategoriaPorId(
                prenda.getIdCategoria()).isEmpty()) {

            throw new RuntimeException(
                    "La categoría no existe.");
        }

        prenda.setEstado(EstadoPrendaEnum.DISPONIBLE);
        prenda.setFechaPublicacion(FechaUtil.ahora());

        repository.crearPrenda(prenda);
    }

    @Override
    public Optional<Prenda> obtenerPrendaPorId(int idPrenda) {

        return repository.obtenerPrendaPorId(idPrenda);
    }

    @Override
    public List<Prenda> listarPrendas() {

        return repository.listarPrendas();
    }

    @Override
    public List<Prenda> listarPrendasDisponibles() {

        List<Prenda> resultado = new ArrayList<>();

        for (Prenda prenda : repository.listarPrendas()) {

            if (prenda.getEstado()
                    == EstadoPrendaEnum.DISPONIBLE) {

                resultado.add(prenda);
            }
        }

        return resultado;
    }

    @Override
    public List<Prenda> listarPrendasPorVendedor(int idVendedor) {

        return repository.listarPrendasPorVendedor(idVendedor);
    }

    @Override
    public List<Prenda> listarPrendasPorCategoria(int idCategoria) {

        return repository.listarPrendasPorCategoria(idCategoria);
    }

    @Override
    public boolean actualizarPrenda(Prenda prenda) {

        validarDatos(prenda);

        if (categoriaRepository.obtenerCategoriaPorId(
                prenda.getIdCategoria()).isEmpty()) {

            throw new RuntimeException(
                    "La categoría no existe.");
        }

        // El estado se deriva del stock, como en el modelo Reviste
        prenda.setEstado(prenda.getStock() > 0
                ? EstadoPrendaEnum.DISPONIBLE
                : EstadoPrendaEnum.VENDIDA);

        return repository.actualizarPrenda(prenda);
    }

    @Override
    public boolean marcarPrendaComoVendida(int idPrenda) {

        return repository.obtenerPrendaPorId(idPrenda)
                .map(prenda -> {
                    prenda.setEstado(EstadoPrendaEnum.VENDIDA);
                    prenda.setStock(0);
                    return repository.actualizarPrenda(prenda);
                })
                .orElse(false);
    }

    @Override
    public boolean eliminarPrenda(int idPrenda) {

        return repository.eliminarPrenda(idPrenda);
    }

    private void validarDatos(Prenda prenda) {

        if (prenda.getPrecio() <= 0) {

            throw new RuntimeException(
                    "El precio debe ser mayor que cero.");
        }

        if (prenda.getStock() < 0) {

            throw new RuntimeException(
                    "El stock no puede ser negativo.");
        }

        if (prenda.getCondicion() == null) {

            throw new RuntimeException(
                    "La condición de la prenda es obligatoria.");
        }

        if (!esTallaValida(prenda.getTalla())) {

            throw new RuntimeException(
                    "Talla inválida. Use XS, S, M, L, XL, XXL, "
                            + "TALLA_UNICA o un número entre 6 y 44.");
        }
    }

    private boolean esTallaValida(String talla) {

        if (talla == null) {

            return false;
        }

        if (TALLAS_LETRA.contains(talla)) {

            return true;
        }

        try {

            int numero = Integer.parseInt(talla);

            return numero >= 6 && numero <= 44;

        } catch (NumberFormatException e) {

            return false;
        }
    }
}
