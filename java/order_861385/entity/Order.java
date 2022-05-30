package order_861385.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author LN
 * @since 2021-12-11
 */
@Data
@TableName("orders")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("u_id")
    private Integer uId;
    @TableField("c_id")
    private Integer cId;
    private BigDecimal spend;
    private Integer number;
    @TableField("u_name")
    private String uName;
    @TableField("c_name")
    private String cName;

    private LocalDateTime time;

    @TableField("is_conf")
    private int isConf;
    /**
     * 1 付钱 2未付钱
     */
    @TableField("is_pay")
    private Integer isPay;

    private String address;


    public Integer getId() {
        return id;
    }

    public Order setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getuId() {
        return uId;
    }

    public Order setuId(Integer uId) {
        this.uId = uId;
        return this;
    }

    public Integer getcId() {
        return cId;
    }

    public Order setcId(Integer cId) {
        this.cId = cId;
        return this;
    }

    public BigDecimal getSpend() {
        return spend;
    }

    public Order setSpend(BigDecimal spend) {
        this.spend = spend;
        return this;
    }

    public Integer getNumber() {
        return number;
    }

    public Order setNumber(Integer number) {
        this.number = number;
        return this;
    }

    public String getuName() {
        return uName;
    }

    public Order setuName(String uName) {
        this.uName = uName;
        return this;
    }

    public String getcName() {
        return cName;
    }

    public Order setcName(String cName) {
        this.cName = cName;
        return this;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public Order setIsPay(Integer isPay) {
        this.isPay = isPay;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Order{" +
        ", id=" + id +
        ", uId=" + uId +
        ", cId=" + cId +
        ", spend=" + spend +
        ", number=" + number +
        ", uName=" + uName +
        ", cName=" + cName +
        ", isPay=" + isPay +
        "}";
    }
}
