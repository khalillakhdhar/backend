package com.example.back.Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Utilisateur;
import com.example.back.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@RestController
@Transactional
@RequestMapping("/utilisateur")
@CrossOrigin(origins = "localhost:3000", maxAge = 3600)
public class UtilisateurController {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    // Get All Utilisateurs
@GetMapping("")
public List<Utilisateur> getUtilisateurs() {
    return (List<Utilisateur>) utilisateurRepository.findAll();
}
// Create a new Utilisateur
@PostMapping("")
public Utilisateur createUtilisateur(@Valid @RequestBody Utilisateur utilisateur) {
    return utilisateurRepository.save(utilisateur);
}

// Get a Single Utilisateur
@GetMapping("/{id}")
public Utilisateur getUtilisateurById(@PathVariable(value = "id") Long utilisateurId) {
    return utilisateurRepository.findById(utilisateurId)
            .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", utilisateurId));
}
  
// Delete a Utilisateur
@DeleteMapping("/{id}")
public ResponseEntity<?> deleteUtilisateur(@PathVariable(value = "id") Long utilisateurId) {
    Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
            .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", utilisateurId));

    utilisateurRepository.delete(utilisateur);

    return ResponseEntity.ok().build();
}
}