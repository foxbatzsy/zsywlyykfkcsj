package order_861385.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 目录表
 * </p>
 *
 * @author LN
 * @since 2021-12-11
 */
public class Catalogue extends Model<Catalogue> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;


    public Integer getId() {
        return id;
    }

    public Catalogue setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Catalogue setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Catalogue{" +
        ", id=" + id +
        ", name=" + name +
        "}";
    }
}
