package com.watson.business.exception.feat.domain.entity.House;

import com.watson.business.exception.feat.domain.item.STATUS;
import com.watson.business.exception.feat.domain.entity.House.houseinfo.MonthlyInfos;
import com.watson.business.exception.feat.domain.entity.House.houseinfo.SaleInfos;
import com.watson.business.exception.feat.domain.entity.House.houseinfo.YearlyInfos;
import com.watson.business.exception.feat.dto.houseregist.HouseRegistRequest;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Entity
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="house_id")
    private Long houseId;

    @Column(nullable = false)
    private String realtorId;

    /**
     * 1: 월세
     * 2: 전세
     * 3: 매매
     */
    @Column(nullable = false)
    private int contractCode;

    @Column(nullable = false)
    private int dongCode;

    /**
     * 1: 원룸
     * 2: 투룸
     * 3: 쓰리룸 이상
     */
    @Column(nullable = false)
    private int houseCode;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date regDate;

    @Column(nullable = false)
    private double squareMeter;

    @Column(nullable = false)
    private int floor;

    @Column(nullable = false)
    private int totalFloor;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT '거래중'")
    private STATUS status;

    @Column(nullable = false)
    private int weeklyViewCount;

    @Column(nullable = false)
    private double supplyAreaMeter;

    @Column(nullable = false)
    private String buildingUse;

    @Column(nullable = false)
    private String approvalBuildingDate;

    @Column(nullable = false)
    private int bathroom;

    @OneToOne(cascade = CascadeType.ALL)
    private HouseOption houseOption;

//    @OneToOne(cascade = CascadeType.ALL)      // 조회수 생성
//    private ViewCounts viewCounts;

    @OneToOne(cascade = CascadeType.ALL)
    private SaleInfos saleInfos;

    @OneToOne(cascade = CascadeType.ALL)
    private YearlyInfos yearlyInfos;

    @OneToOne(cascade = CascadeType.ALL)
    private MonthlyInfos monthlyInfos;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="house_id")
    private List<HouseFile> houseFiles= new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        regDate = new Date(); // 현재 시간을 할당
    }

    public House() {
    }

    public House(String realtorId, HouseRegistRequest houseRegistRequest, HouseOption houseOption) {
        this.realtorId = realtorId;
        this.contractCode = houseRegistRequest.getContractCode();
        this.dongCode = houseRegistRequest.getDongCode();
        this.houseCode = houseRegistRequest.getHouseCode();
        this.squareMeter = houseRegistRequest.getSquareMeter();
        this.floor = houseRegistRequest.getFloor();
        this.totalFloor = houseRegistRequest.getTotalFloor();
        this.address = houseRegistRequest.getAddress();
        this.title = houseRegistRequest.getTitle();
        this.content = houseRegistRequest.getContent();
        this.supplyAreaMeter = houseRegistRequest.getSupplyAreaMeter();
        this.buildingUse = houseRegistRequest.getBuildingUse();
        this.approvalBuildingDate = houseRegistRequest.getApprovalBuildingDate();
        this.bathroom = houseRegistRequest.getBathroom();
        this.houseOption = houseOption;
    }

    public void setSaleInfos(SaleInfos saleInfos) {
        this.saleInfos = saleInfos;
    }

    public void setYearlyInfos(YearlyInfos yearlyInfos) {
        this.yearlyInfos = yearlyInfos;
    }

    public void setMonthlyInfos(MonthlyInfos monthlyInfos) {
        this.monthlyInfos = monthlyInfos;
    }
}