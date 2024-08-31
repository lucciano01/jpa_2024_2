package domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Size(min = 2, max = 100, message = "O nome deve ter no minimo 2 caracteres e no maximo 100 caracteres")
    private String nome;

    @CPF(message = "Cpf invalido")
    @Column(unique = true)
    private String cpf;

    @Email(message = "Email invalido")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

}
