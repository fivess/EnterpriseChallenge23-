package com.noobs.respository;

import com.noobs.model.User;
import com.noobs.request.UserRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    @Transactional
    public User update(Long id, UserRequest user) {
        final Optional<User> findOpt = findByIdOptional(id);

        final var find = findOpt.orElseThrow(NotFoundException::new);

        find.setEmail(user.getEmail());
        find.setName(user.getName());
        find.setCpf(user.getCpf());
        find.setPhone(user.getPhone());
        find.setAddress(user.getAddress());
        find.setCity(user.getCity());
        find.setState(user.getState());
        find.setBirth(user.getBirth());
        find.setGenre(user.getGenre());
        find.setSchooling(user.getSchooling());
        find.setMaritalStatus(user.getMaritalStatus());
        find.setProfession(user.getProfession());


        return find;
    }

    public List<User> findByName(String name) {
        return list("lower(name) LIKE ?1", name.toLowerCase() + "%");
    }

    public void save(User entity) {
        find("cpf = ?1", entity.getCpf()).firstResultOptional().ifPresentOrElse(
                user -> {
                    throw new ClientErrorException(Response.status(Response.Status.CONFLICT)
                            .entity("{\"error\": \"CPF already exists\"}")
                            .build());
                },
                () -> persist(entity)
        );

    }
}
