package com.noobs.respository;

import com.noobs.model.Registry;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RegistryRepository implements PanacheRepositoryBase<Registry, String> {

}
