    package com.example.desafioconcreterestful.services;

    import java.time.LocalDate;
    import java.time.LocalDateTime;
    import java.util.Optional;
    import java.util.UUID;

    import com.example.desafioconcreterestful.dto.ProfileDTO;
    import com.example.desafioconcreterestful.dto.UserDTO;
    import com.example.desafioconcreterestful.entities.Phone;
    import com.example.desafioconcreterestful.exceptions.AccessDeniedException;
    import com.example.desafioconcreterestful.exceptions.InvalidSessionException;
    import com.example.desafioconcreterestful.exceptions.InvalidUserOrPasswordException;
    import net.bytebuddy.implementation.bytecode.Throw;

    import org.mindrot.jbcrypt.BCrypt;
    import org.springframework.beans.BeanUtils;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.stereotype.Service;

    import com.example.desafioconcreterestful.dto.LoginDTO;
    import com.example.desafioconcreterestful.entities.User;
    import com.example.desafioconcreterestful.exceptions.EmailAlreadyRegisteredException;
    import com.example.desafioconcreterestful.repository.PhoneRepository;
    import com.example.desafioconcreterestful.repository.UserRepository;
    import com.example.desafioconcreterestful.services.UserService;

    @Service
    public class UserServiceImpl implements UserService {

        @Autowired
        private UserRepository userRepository;
        @Autowired
        private PhoneRepository phoneRepository;

        /**
         * Create user in database
         * @param pUser
         * @return
         * @throws EmailAlreadyRegisteredException
         */
        public UserDTO createUser(User pUser) throws EmailAlreadyRegisteredException {

            User user;
            UserDTO userDTO = new UserDTO();

            //Verifica se o e-mail já está cadastrado no banco de dados
            user = userRepository.findUserByEmail(pUser.getEmail()).orElse(null);

            if (user != null) {
                throw new EmailAlreadyRegisteredException(HttpStatus.UNAUTHORIZED);
            }

            pUser = this.getPasswordEncrypted(pUser);
            pUser.setCreated(LocalDate.now());
                  pUser.setModified(LocalDateTime.now());
                  pUser.setLastLogin(LocalDateTime.now());
                  pUser.generateToken();

                  user = userRepository.save(pUser);

                  if (pUser.getPhones() != null && !pUser.getPhones().isEmpty()) {
                      for (Phone phone : pUser.getPhones()) {
                          phone.setUser(user);
                      }
                      phoneRepository.saveAll(pUser.getPhones());
                  }

                  BeanUtils.copyProperties(user, userDTO);

                  return userDTO;
              }

              /**
               * Validate user login
               * @param pLoginDTO
               * @return
               * @throws InvalidUserOrPasswordException
              */

        public UserDTO login(LoginDTO pLoginDTO) throws InvalidUserOrPasswordException {

         Optional<User> userOptional = userRepository.findUserByEmail(pLoginDTO.getEmail());
         User user = userOptional.orElseThrow(() -> new InvalidUserOrPasswordException(HttpStatus.UNAUTHORIZED));

         if (!this.passwordAuthentication(pLoginDTO.getPassword(), user.getPassword())) {

         throw new InvalidUserOrPasswordException(HttpStatus.UNAUTHORIZED);
         }

         user.setLastLogin(LocalDateTime.now());
         user.generateToken();

         userRepository.save(user);

         UserDTO userDTO = new UserDTO();
         BeanUtils.copyProperties(user, userDTO);

         return userDTO;
         }

        /**
         *      * Gives access to user profile
         *      * @param pProfileDTO
         *      * @return
         *      * @throws AccessDeniedException
         */
        public UserDTO profile(ProfileDTO pProfileDTO) throws AccessDeniedException {

            Optional<User> userOptionalToken = userRepository.findByToken(UUID.fromString(pProfileDTO.getToken()));
            userOptionalToken.orElseThrow(() -> new AccessDeniedException(HttpStatus.UNAUTHORIZED));

            Optional<User> userOptionalId = userRepository.findById(UUID.fromString(pProfileDTO.getIdUser()));
            if(userOptionalId.isPresent()){

                UUID token = userOptionalId.get().getToken();
                UUID tokenProfile = UUID.fromString(pProfileDTO.getToken());

                if(!tokenProfile.equals(token)){
                    throw new AccessDeniedException(HttpStatus.UNAUTHORIZED);
                }else if(LocalDateTime.now().minusMinutes(30).compareTo(userOptionalId.get().getLastLogin()) > 0){
                    throw new InvalidSessionException(HttpStatus.GONE);
                }
            }else{
                throw new InvalidSessionException(HttpStatus.UNAUTHORIZED);
            }

            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userOptionalId.get(), userDTO);

            return userDTO;
        }

        /**
         * Hashes user password
         * @param pUser
         * @return
         */
        private User getPasswordEncrypted(User pUser){

            String salt = BCrypt.gensalt();

            String passwordHash = BCrypt.hashpw(pUser.getPassword(), salt);

            pUser.setPassword(passwordHash);

            return pUser;
        }

        /**
         * Check passwords
         * @param passwordDTO
         * @param userPassword
         * @return
         */
        private boolean passwordAuthentication(String passwordDTO, String userPassword){

            boolean isAuthenticationSuccessful = BCrypt.checkpw(passwordDTO, userPassword);

            return isAuthenticationSuccessful;
        }
    }






