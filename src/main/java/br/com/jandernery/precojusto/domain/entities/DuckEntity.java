package br.com.jandernery.precojusto.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ducks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DuckEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String name;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "mother_id")
    // @JsonBackReference

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mother_id")
    @JsonBackReference
    private DuckEntity mother;

    @OneToMany(mappedBy = "mother", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DuckEntity> children = new ArrayList<>();

    public void addChild(DuckEntity child) {
        children.add(child);
        child.setMother(this);
    }

    public void removeChild(DuckEntity child) {
        children.remove(child);
        child.setMother(null);
    }
}