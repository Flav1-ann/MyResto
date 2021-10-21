package eu.ensup.myresto;

import java.util.ArrayList;
import java.util.List;

import eu.ensup.myresto.exceptions.DaoException;
import eu.ensup.myresto.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Category service.
 */
public class CategoryService implements ICategoryService {

    private static final Logger log = LogManager.getLogger(CategoryService.class);

    private ICategoryDao dao;

    /**
     * Instantiates a new Category service.
     *
     * @param dao the dao
     */
    public CategoryService(ICategoryDao dao) {
        this.dao = dao;
    }

    /**
     * Instantiates a new Category service.
     */
    public CategoryService() {
        this.dao = new CategoryDao();
    }

    @Override
    public List<CategoryDto> getAll() throws ServiceException {
        try {
            List<CategoryDto> listCategory = new ArrayList<>();

            for (Category category : dao.getAll()) {
                listCategory.add(CategoryMapper.convertDomaineDto(category));
            }
            return listCategory;
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(ProductDao.class.getName(), "getAll", e.getMessage(), "Une erreur s'est produite lors de la récupération de tout les categories");
        }
    }

    @Override
    public CategoryDto getById(int idCategory) throws ServiceException {
        try {
            return CategoryMapper.convertDomaineDto(dao.getById(idCategory));
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(ProductDao.class.getName(), "get", e.getMessage(), "Une erreur s'est produite lors de la récupération d'une categorie");
        }
    }

    @Override
    public CategoryDto getByName(String nameCategory) throws ServiceException {
        try {
            return CategoryMapper.convertDomaineDto(dao.getByName(nameCategory));
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(ProductDao.class.getName(), "get", e.getMessage(), "Une erreur s'est produite lors de la récupération d'une categorie");
        }
    }

    @Override
    public int create(CategoryDto category) throws ServiceException {
        try {
            return dao.create(CategoryMapper.convertDtoDomaine(category));
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(ProductDao.class.getName(), "create", e.getMessage(), "Une erreur s'est produite lors de la creation d'une categorie");
        }
    }

    @Override
    public int update(CategoryDto category) throws ServiceException {
        try {
            return dao.update(CategoryMapper.convertDtoDomaine(category));
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(ProductDao.class.getName(), "update", e.getMessage(), "Une erreur s'est produite lors de la mise a jour d'une categorie");
        }
    }

    @Override
    public int delete(CategoryDto category) throws ServiceException {
        try {
            return dao.delete(CategoryMapper.convertDtoDomaine(category));
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(ProductDao.class.getName(), "delete", e.getMessage(), "Une erreur s'est produite lors de la suppression d'une categorie");
        }
    }

    @Override
    public int delete(int idCategory) throws ServiceException {
        try {
            return dao.delete(idCategory);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(ProductDao.class.getName(), "delete", e.getMessage(), "Une erreur s'est produite lors de la suppression d'une categorie");
        }
    }
}
