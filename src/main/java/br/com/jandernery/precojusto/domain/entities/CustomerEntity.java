package br.com.jandernery.precojusto.domain.entities;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column()
    private String name;

    @Column(columnDefinition = "BOOLEAN DEFAULT false", name = "eligible_discount")
    private boolean eligibleDiscount = false;

    @Column()
    private String email;

    @Column()
    private String phone;

}