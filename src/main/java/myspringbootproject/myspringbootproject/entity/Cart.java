package myspringbootproject.myspringbootproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Cart")
@Getter
@Setter
public class Cart {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

    @Min(value = 1, message = "Quantity must exceed one.")
    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "consumer_id", referencedColumnName = "id")
    private Consumer consumer;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product products;
}
