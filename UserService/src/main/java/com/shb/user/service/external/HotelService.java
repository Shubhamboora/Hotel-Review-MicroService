package com.shb.user.service.external;

import com.shb.user.service.common.CommonUtils;
import com.shb.user.service.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = CommonUtils.HOTEL_SERVICE)
public interface HotelService {

    @GetMapping("hotels/get/{id}")
    Hotel getHotel(@PathVariable String id);
}
