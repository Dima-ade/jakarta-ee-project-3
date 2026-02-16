package org.example.beans;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.entities.UserPassRole;

import java.util.HashMap;
import java.util.Set;

@ApplicationScoped
//@RequestScoped
public class AllowedRoles {

    private HashMap<String, Set<String>> urlAllowedRoles; // contine un Map cu cheie url-ul, lista de permisiuni

    public AllowedRoles() {
        System.out.println("\n\n-------------------- AllowedRoles.AllowedRoles");

        // Example: roles can be configured in web.xml or programmatically
        Set<String> helloWorldUrlRoles = Set.of("USER");
        String helloWorldUrl = "/application-1.0-SNAPSHOT/api/hello/world";
        Set<String> personBonjourUrlRoles = Set.of("ADMIN", "TECH");
        String personBonjourUrl = "/application-1.0-SNAPSHOT/api/person/bonjour";

        urlAllowedRoles = new HashMap<>();
        urlAllowedRoles.put(helloWorldUrl, helloWorldUrlRoles);
        //urlAllowedRoles.put(personBonjourUrl, personBonjourUrlRoles);
    }

    public boolean hasPermission(UserPassRole userPassRole, String url) {
        Set<String> userRolesInPrincipal = userPassRole.getAllowedRoles();

        if (this.urlAllowedRoles.containsKey(url)) {
            Set<String> rolesInUrl = urlAllowedRoles.get(url);
            if (userRolesInPrincipal.size() == rolesInUrl.size() && userRolesInPrincipal.containsAll(rolesInUrl)) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}
