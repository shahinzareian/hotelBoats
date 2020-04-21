package com.example.demo.repositories;

import com.example.demo.models.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
//* guest repository
public interface GuestRepository extends JpaRepository<Guest,Long> {
}
