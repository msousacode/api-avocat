package com.avocat.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "branch_offices")
@AttributeOverride(name = "id", column = @Column(name = "branch_office_id", nullable = false))
public class BranchOffice extends AuditEntity {

    @NotEmpty(message = "invalid corporate name format. Can`t be null or empty.")
    @Column(name = "corporate_name")
    private String corporateName;

    @NotEmpty(message = "invalid branch office name format. Can`t be null or empty.")
    @Column(name = "branch_office_name")
    private String branchOfficeName;

    @Column(name = "code_office")
    private String codeOffice;

    @Column(name = "state_registration_name")
    private String stateRegistration;//inscrição estadual

    //TODO criar um validador que server para cpf e cnpj
    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    @NotEmpty(message = "invalid e-mail format. Can`t be null or empty.")
    @Email(message = "invalid format email")
    @Column(name = "email")
    private String email;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    private BranchOffice(Builder builder) {
        Assert.notNull(builder.customer, "Customer is required");
        this.corporateName = builder.corporateName;
        this.branchOfficeName = builder.branchOfficeName;
        this.codeOffice = builder.codeOffice;
        this.stateRegistration = builder.stateRegistration;
        this.cpfCnpj = builder.cpfCnpj;
        this.email = builder.email;
        this.customer = builder.customer;
    }

    public static class Builder {

        //mandatory
        private String cpfCnpj;
        private String email;
        private String corporateName;
        private String branchOfficeName;
        private Customer customer;

        //optional
        private String codeOffice;
        private String stateRegistration;//inscrição estadual

        public Builder(String cpfCnpj, String email, String corporteName,
                       String branchOfficeName, Customer customer) {
            this.cpfCnpj = cpfCnpj;
            this.email = email;
            this.corporateName = corporteName;
            this.branchOfficeName = branchOfficeName;
            this.customer = customer;
        }

        public Builder codeOffice(String codeOffice) {
            this.codeOffice = codeOffice;
            return this;
        }

        public Builder stateRegistration(String stateRegistration) {
            this.stateRegistration = stateRegistration;
            return this;
        }

        public BranchOffice build() {
            return new BranchOffice(this);
        }

    }

    public static BranchOffice from(BranchOffice branchOffice, BranchOffice result) {
        result.setBranchOfficeName(branchOffice.getBranchOfficeName());
        result.setCorporateName(branchOffice.getCorporateName());
        result.setCodeOffice(branchOffice.getCodeOffice());
        result.setCpfCnpj(branchOffice.getCpfCnpj());
        result.setEmail(branchOffice.getEmail());
        result.setStateRegistration(branchOffice.getStateRegistration());
        return result;
    }
}
