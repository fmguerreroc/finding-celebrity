package com.test.celebrityfinder.dataprovider.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Table(name = "person")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Person {

   @Id
   @Column(name = "person_id", nullable = false)
   private BigInteger id;

   @Column(name = "first_name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @JsonIgnore
   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "relationship",
   joinColumns = {@JoinColumn(name = "owner_id")},
   inverseJoinColumns = {@JoinColumn(name = "target_id")})
   private Set<Person> knowns;
}
