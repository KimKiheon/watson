package com.watson.business.house.domain.entity.house.houseinfo;

import com.watson.business.house.dto.houseregist.ContractInfoRequest;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class SaleInfos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long salePrice;

    public SaleInfos() {
    }
    public SaleInfos(ContractInfoRequest contractInfo) {
        this.salePrice = contractInfo.getSalePrice();
    }

    public SaleInfos(Long salePrice) {
        this.salePrice = salePrice;
    }


}
