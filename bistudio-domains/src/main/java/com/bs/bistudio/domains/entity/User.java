/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bs.bistudio.domains.entity;

import com.bs.bistudio.domains.base.BaseAuditableEntity;
import static com.bs.bistudio.domains.entity.User.QUERY_BY_LOGIN_TOKEN;
import static com.bs.bistudio.domains.util.Constants.BISTUDIO_ROLE_USER;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Balaji.S <<sssbalajis@gmail.com>>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "users")
@NamedQuery(name = QUERY_BY_LOGIN_TOKEN, query = "SELECT"
        + "				_user"
        + "			FROM"
        + "				User _user"
        + "					JOIN"
        + "				_user.loginTokens _loginToken"
        + "					JOIN FETCH"
        + "				_user.loginTokens"
        + "			WHERE"
        + "				_loginToken.tokenHash = :tokenHash AND"
        + "				_loginToken.type = :tokenType AND"
        + "				_loginToken.expiredDate > CURRENT_TIMESTAMP")
public class User extends BaseAuditableEntity{
      public static final Set<String> DEFAULT_ROLES = new HashSet<>(asList(BISTUDIO_ROLE_USER));

    public static final String QUERY_BY_LOGIN_TOKEN = "User.byLoginTokens";

    private String username;
    private String password;

    @ElementCollection
    private Set<String> roles = DEFAULT_ROLES;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    List<LoginToken> loginTokens = new ArrayList<>();
}
