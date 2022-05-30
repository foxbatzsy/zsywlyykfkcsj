package order_861385.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 购物车表
 * </p>
 *
 * @author LN
 * @since 2021-12-11
 */
public class Shopping extends Model<Shopping> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("u_id")
    private Integer uId;
    @TableField("c_id")
    private Integer cId;
    @TableField("c_name")
    private String cName;
    private Integer number;
    private BigDecimal spend;


    public Integer getId() {
        return id;
    }

    public Shopping setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getuId() {
        return uId;
    }

    public Shopping setuId(Integer uId) {
        this.uId = uId;
        return this;
    }

    public Integer getcId() {
        return cId;
    }

    public Shopping setcId(Integer cId) {
        this.cId = cId;
        return this;
    }

    public String getcName() {
        return cName;
    }

    public Shopping setcName(String cName) {
        this.cName = cName;
        return this;
    }

    public Integer getNumber() {
        return number;
    }

    public Shopping setNumber(Integer number) {
        this.number = number;
        return this;
    }

    public BigDecimal getSpend() {
        return spend;
    }

    public Shopping setSpend(BigDecimal spend) {
        this.spend = spend;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Shopping{" +
        ", id=" + id +
        ", uId=" + uId +
        ", cId=" + cId +
        ", cName=" + cName +
        ", number=" + number +
        ", spend=" + spend +
        "}";
    }
}
