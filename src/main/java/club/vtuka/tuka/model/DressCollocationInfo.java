package club.vtuka.tuka.model;

public class DressCollocationInfo {
    private Long id;

    private String collocationUrl;

    private String imgUrl;

    private String collocationType;

    private String collocationDesc;

    private Boolean isDelete;

    private Boolean isShow;

    public DressCollocationInfo(Long id, String collocationUrl, String imgUrl, String collocationType, String collocationDesc, Boolean isDelete, Boolean isShow) {
        this.id = id;
        this.collocationUrl = collocationUrl;
        this.imgUrl = imgUrl;
        this.collocationType = collocationType;
        this.collocationDesc = collocationDesc;
        this.isDelete = isDelete;
        this.isShow = isShow;
    }

    public DressCollocationInfo(String collocationUrl, String imgUrl, String collocationType, String collocationDesc, Boolean isDelete, Boolean isShow) {
        this.collocationUrl = collocationUrl;
        this.imgUrl = imgUrl;
        this.collocationType = collocationType;
        this.collocationDesc = collocationDesc;
        this.isDelete = isDelete;
        this.isShow = isShow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCollocationUrl() {
        return collocationUrl;
    }

    public void setCollocationUrl(String collocationUrl) {
        this.collocationUrl = collocationUrl == null ? null : collocationUrl.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getCollocationType() {
        return collocationType;
    }

    public void setCollocationType(String collocationType) {
        this.collocationType = collocationType == null ? null : collocationType.trim();
    }

    public String getCollocationDesc() {
        return collocationDesc;
    }

    public void setCollocationDesc(String collocationDesc) {
        this.collocationDesc = collocationDesc == null ? null : collocationDesc.trim();
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }
}