/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bs.bistudio.security;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Balaji Srinivasan
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserInfo {
    private String name;
    private Set<String> roles = new HashSet<>();  
    
}
