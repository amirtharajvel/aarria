package com.aarria.retail.core.domain;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "product")
@TableGenerator(name = "product_generator", initialValue = 0, allocationSize = 1)
@Indexed
@NamedEntityGraphs({
        @NamedEntityGraph(name = "graph.product.stock", attributeNodes = @NamedAttributeNode("productStock")),
        @NamedEntityGraph(name = "graph.product.attributes", attributeNodes = @NamedAttributeNode("attributes"))})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "product_generator")
    private Long id;

    @Column(name="pid", unique = true)
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    private String pid;

    @Column(name = "name")
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductImage> colorImages;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Description> description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductStock> productStock;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = ProductAttribute.class, mappedBy = "product")
    private Set<ProductAttribute> attributes;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "originalPrice")
    private Double originalPrice;

    @Column(name = "actualPrice")
    private Double actualPrice;

    @Column(name = "price")
    private Double price;

    @Column(name = "listingImage")
    private String listingImage;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "addedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;

    @Column(name = "isReturnable")
    private Boolean isReturnable;

    @Column(name = "isWholesale")
    private Boolean isWholesale;

    @Column(name = "measureUnit")
    private String measureUnit;

    @Column(name = "measureMeans")
    private String measureMeans;

    @Column(name = "MOQ")
    private Integer MOQ;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "video")
    private String video;

    @Column(name = "priceBreakup")
    private String priceBreakup;

    @Column(name = "igstRate")
    private Double igstRate;

    @Column(name = "igstCode")
    private String igstCode;

    // How much minimum profit should be added to the product?
    @Column(name = "profitToBeAdded")
    private Double profitToBeAdded;

    // Buy 2 for 299
    @Column(name = "offerText")
    private String offerText;

    // 2
    @Column(name = "offerQuantity")
    private Integer offerQuantity;

    // 2TSHIRTFOR299
    // unique code
    @Column(name = "offerCode")
    private String offerCode;

    @Column(name = "offerPrice")
    private Double offerPrice;

    // Delivery.java is associated with order that is associated with actual
    // shipping charge charged at courier office. But this field here denotes
    // "FREE DELIVERY" or standard shipping charge associated with the product.
    // This is exclusively to display in Product Detail page
    @Column(name = "deliveryCharge")
    private String deliveryCharge;

    @Column(name = "catalogue")
    private String catalogue;

    @Column(name = "isShowDiscount")
    private Boolean isShowDiscount;

    public boolean isAtleastOneSizeHasStock() {

        if (this.productStock != null && CollectionUtils.isNotEmpty(productStock)) {
            for (ProductStock p : productStock) {
                if (p.getStock() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getListingImage() {
        return listingImage;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<ProductImage> getColorImages() {
        return colorImages;
    }

    public void setColorImages(Set<ProductImage> colorImages) {
        this.colorImages = colorImages;
    }

    public Set<Description> getDescription() {
        return description;
    }

    public void setDescription(Set<Description> description) {
        this.description = description;
    }

    public Set<ProductStock> getProductStock() {
        return productStock;
    }

    public void setProductStock(Set<ProductStock> productStock) {
        this.productStock = productStock;
    }

    public Double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Boolean getIsReturnable() {
        return isReturnable;
    }

    public void setIsReturnable(Boolean isReturnable) {
        this.isReturnable = isReturnable;
    }

    public Boolean getIsWholesale() {
        return isWholesale;
    }

    public void setIsWholesale(Boolean isWholesale) {
        this.isWholesale = isWholesale;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public String getMeasureMeans() {
        return measureMeans;
    }

    public void setMeasureMeans(String measureMeans) {
        this.measureMeans = measureMeans;
    }

    public Integer getMOQ() {
        return MOQ;
    }

    public void setMOQ(Integer mOQ) {
        MOQ = mOQ;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setListingImage(String listingImage) {
        this.listingImage = listingImage;
    }

    public String getPriceBreakup() {
        return priceBreakup;
    }

    public void setPriceBreakup(String priceBreakup) {
        this.priceBreakup = priceBreakup;
    }

    public Double getIgstRate() {
        return igstRate;
    }

    public void setIgstRate(Double igstRate) {
        this.igstRate = igstRate;
    }

    public Double getProfitToBeAdded() {
        return profitToBeAdded;
    }

    public void setProfitToBeAdded(Double profitToBeAdded) {
        this.profitToBeAdded = profitToBeAdded;
    }

    public String getIgstCode() {
        return igstCode;
    }

    public void setIgstCode(String igstCode) {
        this.igstCode = igstCode;
    }

    public String getOfferText() {
        return offerText;
    }

    public void setOfferText(String offerText) {
        this.offerText = offerText;
    }

    public String getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(String deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public Integer getOfferQuantity() {
        return offerQuantity;
    }

    public void setOfferQuantity(Integer offerQuantity) {
        this.offerQuantity = offerQuantity;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public void setOfferCode(String offerCode) {
        this.offerCode = offerCode;
    }

    public Double getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(Double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(String catalogue) {
        this.catalogue = catalogue;
    }

    @Transient
    public Set<ProductAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<ProductAttribute> attributes) {
        this.attributes = attributes;
    }


    public Boolean getIsShowDiscount() {
        return isShowDiscount;
    }

    public void setIsShowDiscount(Boolean isShowDiscount) {
        this.isShowDiscount = isShowDiscount;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Product other = (Product) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
