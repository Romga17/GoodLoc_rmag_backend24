package edu.gdlc_project.gdlc_pckgs.security;

import edu.gdlc_project.gdlc_pckgs.model.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class AppUserDetails implements UserDetails {

    protected Utilisateur utilisateur;

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public AppUserDetails(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //if (utilisateur.isAdministrateur()) {
        //    return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        //}
        ArrayList<SimpleGrantedAuthority> listeAuthority = new ArrayList<>();

       listeAuthority.add(new SimpleGrantedAuthority(this.utilisateur.getUserRole().getNom()));

       return listeAuthority;
    }

    @Override
    public String getPassword() {
        return utilisateur.getMotDePasse();
    }

    @Override
    public String getUsername() {
        return utilisateur.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
