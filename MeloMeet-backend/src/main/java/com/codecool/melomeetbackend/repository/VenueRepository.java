package com.codecool.melomeetbackend.repository;

import com.codecool.melomeetbackend.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface VenueRepository extends JpaRepository<Venue, UUID> {

    boolean existsByAddressAndCityAndPostalCode(String address, String city, String postalCode);
}
