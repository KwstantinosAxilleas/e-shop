package myspringbootproject.myspringbootproject.entity;

import java.math.BigDecimal;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Description cannot be blank")
    @NonNull
    @Column(name = "description", nullable = false)
    private String description;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be in range (1-99999)")
    @Digits(integer = 5, fraction = 2)
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Min(value = 1, message = "Quantity must exceed one.")
    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @JsonIgnore
	@OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
	private Set<Cart> carts;

    public Product(Product item) { // COPY CONSTRUCTOR
        this.name = item.getName();
        this.description = item.getDescription();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
    }


}


