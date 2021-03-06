package lt.vu.psk.mybatis.dao;

import java.util.List;
import lt.vu.psk.mybatis.model.DutiesRepresentatives;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

@Mapper
public interface DutiesRepresentativesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.DUTIES_REPRESENTATIVES
     *
     * @mbg.generated Wed May 18 11:32:46 EEST 2022
     */
    int insert(DutiesRepresentatives record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.DUTIES_REPRESENTATIVES
     *
     * @mbg.generated Wed May 18 11:32:46 EEST 2022
     */
    List<DutiesRepresentatives> selectAll();

    int getResultCountByDutyAndRepresentativeId(@Param("dutyId") Integer dutyId, @Param("representativeId") Integer representativeId);
}