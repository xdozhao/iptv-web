package com.xdozhao.iptv.module.m3u.entity.convert;

import com.xdozhao.iptv.common.m3u.entity.ExtInfExtEntity;
import com.xdozhao.iptv.module.m3u.entity.M3uExtInf;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/8 10:48
 */
@Mapper
public interface M3uExtInfConvert {
    M3uExtInfConvert INSTANCE = Mappers.getMapper(M3uExtInfConvert.class);

    M3uExtInf toM3uExtInf(ExtInfExtEntity extInfExt);
    List<M3uExtInf> toM3uExtInfList(List<ExtInfExtEntity> extInfExtList);


    ExtInfExtEntity toExtInfExtEntity(M3uExtInf m3uExtInf);

    List<ExtInfExtEntity> toExtInfExtEntity(List<M3uExtInf> m3uExtInf);

}
