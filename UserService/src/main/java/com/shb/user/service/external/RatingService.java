package com.shb.user.service.external;

import com.shb.user.service.common.CommonUtils;
import com.shb.user.service.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = CommonUtils.RATING_SERVICE)
public interface RatingService {

    @GetMapping("/rating/all/byUserId/{userId}")
    public List<Rating> getAllRatingsByuserId(@PathVariable String userId);
}
