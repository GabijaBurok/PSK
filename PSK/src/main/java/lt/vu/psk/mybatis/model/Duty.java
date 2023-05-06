package lt.vu.psk.mybatis.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Duty {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.DUTY.ID
     *
     * @mbg.generated Wed May 18 11:32:46 EEST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.DUTY.DUTY_NAME
     *
     * @mbg.generated Wed May 18 11:32:46 EEST 2022
     */
    private String dutyName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.DUTY.STRUCTURE_ID
     *
     * @mbg.generated Wed May 18 11:32:46 EEST 2022
     */
    private Integer structureId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.DUTY.ID
     *
     * @return the value of PUBLIC.DUTY.ID
     *
     * @mbg.generated Wed May 18 11:32:46 EEST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.DUTY.ID
     *
     * @param id the value for PUBLIC.DUTY.ID
     *
     * @mbg.generated Wed May 18 11:32:46 EEST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.DUTY.DUTY_NAME
     *
     * @return the value of PUBLIC.DUTY.DUTY_NAME
     *
     * @mbg.generated Wed May 18 11:32:46 EEST 2022
     */
    public String getDutyName() {
        return dutyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.DUTY.DUTY_NAME
     *
     * @param dutyName the value for PUBLIC.DUTY.DUTY_NAME
     *
     * @mbg.generated Wed May 18 11:32:46 EEST 2022
     */
    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.DUTY.STRUCTURE_ID
     *
     * @return the value of PUBLIC.DUTY.STRUCTURE_ID
     *
     * @mbg.generated Wed May 18 11:32:46 EEST 2022
     */
    public Integer getStructureId() {
        return structureId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.DUTY.STRUCTURE_ID
     *
     * @param structureId the value for PUBLIC.DUTY.STRUCTURE_ID
     *
     * @mbg.generated Wed May 18 11:32:46 EEST 2022
     */
    public void setStructureId(Integer structureId) {
        this.structureId = structureId;
    }

    private List<Representative> representatives;
}