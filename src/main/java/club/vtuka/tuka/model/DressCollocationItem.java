package club.vtuka.tuka.model;

import java.math.BigDecimal;

public class DressCollocationItem {
    private Long id;

    private Long collocationId;

    private String itemUrl;

    private String itemName;

    private String itemBrand;

    private String imgUrl;

    private BigDecimal price;

    private Boolean isDelete;

    public DressCollocationItem(Long collocationId, String itemUrl, String itemName, String itemBrand, String imgUrl, BigDecimal price, Boolean isDelete) {
        this.collocationId = collocationId;
        this.itemUrl = itemUrl;
        this.itemName = itemName;
        this.itemBrand = itemBrand;
        this.imgUrl = imgUrl;
        this.price = price;
        this.isDelete = isDelete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCollocationId() {
        return collocationId;
    }

    public void setCollocationId(Long collocationId) {
        this.collocationId = collocationId;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl == null ? null : itemUrl.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemBrand() {
        return itemBrand;
    }

    public void setItemBrand(String itemBrand) {
        this.itemBrand = itemBrand == null ? null : itemBrand.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}