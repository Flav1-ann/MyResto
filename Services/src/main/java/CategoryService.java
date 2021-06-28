import exceptions.DaoException;
import exceptions.ServiceException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CategoryService implements ICategoryService
{
    private ICategoryDao dao;

    public CategoryService(ICategoryDao dao)
    {
        this.dao = dao;
    }

    public CategoryService()
    {
        this.dao = new CategoryDao();
    }

    @Override
    public List<CategoryDto> getAll() throws ServiceException
    {
        try {
            List<CategoryDto> listCategory = new ArrayList<CategoryDto>();

            for (var category:dao.getAll()) {
                listCategory.add(CategoryMapper.convertDomaineDto(category));
            }
            return listCategory;
        }
        catch (DaoException e) {
            throw new ServiceException(ProductDao.class.getName(), "getAll", e.getMessage(), "Une erreur s'est produite lors de la récupération de tout les categories");
        }
    }

    @Override
    public CategoryDto get(int idCategory) throws ServiceException
    {
        try {
            return CategoryMapper.convertDomaineDto(dao.get(idCategory));
        }
        catch (DaoException e) {
            throw new ServiceException(ProductDao.class.getName(), "get", e.getMessage(), "Une erreur s'est produite lors de la récupération d'une categorie");
        }
    }

    @Override
    public CategoryDto get(String nameCategory) throws ServiceException
    {
        try {
            return CategoryMapper.convertDomaineDto(dao.get(nameCategory));
        }
        catch (DaoException e) {
            throw new ServiceException(ProductDao.class.getName(), "get", e.getMessage(), "Une erreur s'est produite lors de la récupération d'une categorie");
        }
    }

    @Override
    public int create(CategoryDto category) throws ServiceException
    {
        try {
            return dao.create(CategoryMapper.convertDtoDomaine(category));
        }
        catch (DaoException e) {
            throw new ServiceException(ProductDao.class.getName(), "create", e.getMessage(), "Une erreur s'est produite lors de la creation d'une categorie");
        }
    }

    @Override
    public int update(CategoryDto category) throws ServiceException
    {
        try {
            return dao.update(CategoryMapper.convertDtoDomaine(category));
        }
        catch (DaoException e) {
            throw new ServiceException(ProductDao.class.getName(), "update", e.getMessage(), "Une erreur s'est produite lors de la mise a jour d'une categorie");
        }
    }

    @Override
    public int delete(CategoryDto category) throws ServiceException
    {
        try {
            return dao.delete(CategoryMapper.convertDtoDomaine(category));
        }
        catch (DaoException e) {
            throw new ServiceException(ProductDao.class.getName(), "delete", e.getMessage(), "Une erreur s'est produite lors de la suppression d'une categorie");
        }
    }

    @Override
    public int delete(int idCategory) throws ServiceException
    {
        try {
            return dao.delete(idCategory);
        }
        catch (DaoException e) {
            throw new ServiceException(ProductDao.class.getName(), "delete", e.getMessage(), "Une erreur s'est produite lors de la suppression d'une categorie");
        }
    }
}