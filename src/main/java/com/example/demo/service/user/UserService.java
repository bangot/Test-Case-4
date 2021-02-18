package com.example.demo.service.user;

import com.example.demo.model.User;
import com.example.demo.model.UserPrinciple;
import com.example.demo.repository.IUseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {


    @Autowired
    IUseRepository iUseRepository;
    @Override
    public Iterable<User> findAll() {
        return iUseRepository.findAll();
    }

    @Override
    public User save(User user) {
        return iUseRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return iUseRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iUseRepository.deleteById(id);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iUseRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.built(user);
    }

    @Override
    public User findByUsername(String username) {
        return iUseRepository.findByUsername(username);
    }
}
