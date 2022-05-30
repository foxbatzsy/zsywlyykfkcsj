package order_861385.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 浏览日志
 * </p>
 *
 * @author LN
 * @since 2021-12-11
 */
public class Log extends Model<Log> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("u_id")
    private Integer uId;
    @TableField("c_id")
    private Integer cId;
    @TableField("u_name")
    private String uName="游客";
    @TableField("c_name")
    private String cName;


    public Integer getId() {
        return id;
    }

    public Log setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getuId() {
        return uId;
    }

    public Log setuId(Integer uId) {
        this.uId = uId;
        return this;
    }

    public Integer getcId() {
        return cId;
    }

    public Log setcId(Integer cId) {
        this.cId = cId;
        return this;
    }

    public String getuName() {
        return uName;
    }

    public Log setuName(String uName) {
        this.uName = uName;
        return this;
    }

    public String getcName() {
        return cName;
    }

    public Log setcName(String cName) {
        this.cName = cName;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Log{" +
        ", id=" + id +
        ", uId=" + uId +
        ", cId=" + cId +
        ", uName=" + uName +
        ", cName=" + cName +
        "}";
    }
}
