import exceptions.DaoException;
import exceptions.ServiceException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService implements IUserService {

    private static final Logger log = LogManager.getLogger(UserService.class);

    private IUserDao userDao;

    private static final String algo = "SHA-256";

    @Override
    public int create(UserDto userDto) throws ServiceException {
        try {
            User user = UserMapper.convertDtoDomaine(userDto);
            byte[] salt = createSalt();
            user.setPassword(generateHashPassword(user.getPassword(), salt));
            user.setSalt(Base64.getEncoder().encodeToString(salt));
            return userDao.create(user);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(UserService.class.getName(), "create", e.getMessage(), "Une erreur s'est produite lors de la création de l'utilisateur");
        }
    }

    @Override
    public int update(UserDto userDto) throws ServiceException {
        try {
            return userDao.update(UserMapper.convertDtoDomaine(userDto));
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(UserService.class.getName(), "update", e.getMessage(), "Une erreur s'est produite lors de la modification de l'utilisateur");
        }
    }

    @Override
    public int delete(int userId) throws ServiceException {
        try {
            return userDao.delete(userId);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(UserService.class.getName(), "delete", e.getMessage(), "Une erreur s'est produite lors de la suppression de l'utilisateur");
        }
    }

    @Override
    public UserDto getById(int userId) throws ServiceException {
        try {
            return UserMapper.convertDomaineDto(userDao.getById(userId));
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(UserService.class.getName(), "getById", e.getMessage(), "Une erreur s'est produite lors de la récupération de l'utilisateur");
        }
    }

    @Override
    public UserDto getByLogin(String login) throws ServiceException {
        try {
            return UserMapper.convertDomaineDto(userDao.getByLogin(login));
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(UserService.class.getName(), "getByLogin", e.getMessage(), "Une erreur s'est produite lors de la récupération de l'utilisateur");
        }
    }

    @Override
    public int validateUser(LoginUserDto loginUserDto) throws ServiceException {
        try {
            User user = userDao.getByLogin(loginUserDto.getLogin());
            byte[] salt = Base64.getDecoder().decode(user.getSalt());
            String hash = generateHashPassword(loginUserDto.getPassword(), salt);
            return hash.equals(user.getPassword()) ? 1 : 0;
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(UserService.class.getName(), "validateUser", e.getMessage(), "Une erreur s'est produite lors de la vérification de mot de passe");
        }
    }

    @Override
    public String generateHashPassword(String password, byte[] salt) throws ServiceException {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance(algo);
            digest.reset();
            digest.update(salt);
            return Base64.getEncoder().encodeToString(digest.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
            throw new ServiceException(UserService.class.getName(), "generateHashPassword", e.getMessage(), "Une erreur s'est produite lors de la génération du mot de passe utilisateur");
        }

    }

    @Override
    public byte[] createSalt() {
        byte[] bytes = new byte[20];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);
        return bytes;
    }


}
