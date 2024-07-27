package edu.gdlc_project.gdlc_pckgs.security;

import edu.gdlc_project.gdlc_pckgs.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class AppUserDetails implements UserDetails {

    protected User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AppUserDetails(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //if (utilisateur.isAdministrateur()) {
        //    return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        //}
        ArrayList<SimpleGrantedAuthority> listeAuthority = new ArrayList<>();

       listeAuthority.add(new SimpleGrantedAuthority(this.user.getUserRole().getRoleName()));

       return listeAuthority;
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserEmail();
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
