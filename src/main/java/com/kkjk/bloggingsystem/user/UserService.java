package com.kkjk.bloggingsystem.user;

import com.kkjk.bloggingsystem.role.RoleEntity;
import com.kkjk.bloggingsystem.role.RoleService;
import com.kkjk.bloggingsystem.user.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        return new User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    public UserEntity getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();

        return userRepository
                .findByEmail(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(principal.getUsername()));
    }

    public UserRegisterResponseDto save(UserRegisterRequestDto userRegisterDto) {

        if (userRepository.existsByEmail(userRegisterDto.getEmail())) {
            throw new EmailAlreadyTakenException(userRegisterDto.getEmail());
        }

        RoleEntity role = roleService.getUserRole();
        Set<RoleEntity> roleSet = new HashSet<>();
        roleSet.add(role);

        UserEntity newUser = UserFactory.registerDtoToEntity(userRegisterDto, roleSet, passwordEncoder);

        return UserFactory.entityToRegisterResponseDto(userRepository.save(newUser));
    }


    private Set<SimpleGrantedAuthority> getAuthority(UserEntity user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(roleEntity -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + roleEntity.getName()));
        });

        return authorities;
    }

    public UserEntity findUserEntityById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Transactional
    public void changePassword(ChangePasswordRequestDto changePasswordRequestDto) {
        UserEntity currentUser = getCurrentUser();
        if (!passwordEncoder.matches(changePasswordRequestDto.getOldPassword(), currentUser.getPassword())) {
            throw new BadCredentialsException(changePasswordRequestDto.getOldPassword());
        }
        currentUser.setPassword(passwordEncoder.encode(changePasswordRequestDto.getNewPassword()));
        userRepository.save(currentUser);
    }

    @Transactional
    public void promoteUserToRedactor(UUID userId) {
        UserEntity user = findUserEntityById(userId);

        RoleEntity role = roleService.getRedactorRole();
        user.getRoles().add(role);

        userRepository.save(user);
    }

    @Transactional
    public void promoteUserToAdmin(UUID userId) {
        UserEntity user = findUserEntityById(userId);

        RoleEntity role = roleService.getAdminRole();
        user.getRoles().add(role);

        userRepository.save(user);
    }

    @Transactional
    public void demoteRedactorToUser(UUID userId) {
        UserEntity user = findUserEntityById(userId);

        RoleEntity role = roleService.getRedactorRole();
        user.getRoles().remove(role);

        userRepository.save(user);
    }

    public List<UserBasicInfoDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        //users.remove(getCurrentUser());
        return users.stream().map(UserFactory::entityToBasicInfoDto).collect(Collectors.toList());
    }

    @Transactional
    public UserProfileDto getCurrentUserInfo() {
        UserEntity user = getCurrentUser();
        return UserFactory.entityToProfileDto(user);

    }
}
